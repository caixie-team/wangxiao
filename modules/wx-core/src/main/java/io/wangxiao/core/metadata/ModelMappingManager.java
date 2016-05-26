package io.wangxiao.core.metadata;

import io.wangxiao.core.annotation.*;
import io.wangxiao.core.model.BaseIncrementIdModel;
import io.wangxiao.core.util.ClassUtils;
import io.wangxiao.core.util.StringUtils;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 管理所有实体的元数据信息 .
 */
public class ModelMappingManager {

    private static Map<Class<?>, BeanInfo> beanInfoMappings = new HashMap<Class<?>, BeanInfo>();
    private static Map<String, BeanInfo> beanInfoMappingsBySimpleClassName = new HashMap<String, BeanInfo>();

    public static BeanInfo getBeanInfo(String simpleClassName) {
        return beanInfoMappingsBySimpleClassName.get(simpleClassName);
    }

    /**
     * 根据实体的类得到实体的元数据信息
     *
     * @param clazz
     * @return
     */
    public static <T> BeanInfo getBeanInfo(Class<T> clazz) {
        if (!BaseIncrementIdModel.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(clazz.getName() + "is not a entity class");
        }
        BeanInfo beanInfo = beanInfoMappings.get(clazz);
        if (beanInfo == null) {
            beanInfo = new BeanInfo();
            beanInfo.setCached(clazz.isAnnotationPresent(CacheNamespace.class));
            beanInfo.setLogged(!clazz.isAnnotationPresent(NotLogged.class));
            if (clazz.isAnnotationPresent(NotLoggedFields.class)) {
                beanInfo.setNotLoggedFields(clazz.getAnnotation(NotLoggedFields.class).fields());
            }
            if (clazz.isAnnotationPresent(ChineseName.class)) {
                beanInfo.setChineseName(clazz.getAnnotation(ChineseName.class).value());
            } else {
                beanInfo.setChineseName(clazz.getSimpleName());
            }

            if (clazz.isAnnotationPresent(OrderBy.class)) {
                beanInfo.setOrderBy(clazz.getAnnotation(OrderBy.class).value());
            }

//            if (clazz.isAnnotationPresent())

            List<PropertyInfo> properties = initPropertyInfos(clazz, beanInfo);
            Map<String, PropertyInfo> propsMap = new HashMap<String, PropertyInfo>();
            for (PropertyInfo prop : properties) {
                propsMap.put(prop.getPropertyName(), prop);
            }

            beanInfo.setProperties(properties);
            beanInfo.propertiesMap = propsMap;
            beanInfoMappings.put(clazz, beanInfo);
            String simpleClassName = clazz.getSimpleName();
            if (beanInfoMappingsBySimpleClassName.containsKey(simpleClassName)) {
                throw new RuntimeException("有冲突，两个实体用同样的名称" + simpleClassName);
            }
            beanInfoMappingsBySimpleClassName.put(simpleClassName, beanInfo);
        }
        return beanInfo;
    }

    /**
     * 获取所有的bean maps
     *
     * @return
     */
    public static Map<Class<?>, BeanInfo> getBeanMaps() {
        return Collections.unmodifiableMap(beanInfoMappings);
    }

    /**
     * 获取特定接口bean maps
     *
     * @param clazz 接口类
     * @return
     */
    public static Map<Class<?>, BeanInfo> getBeans(Class<?> clazz) {
        Map<Class<?>, BeanInfo> result = new HashMap<Class<?>, BeanInfo>();
        for (Class<?> c : beanInfoMappings.keySet()) {
            if (clazz.isAssignableFrom(c)) {
                result.put(c, beanInfoMappings.get(c));
            }
        }
        return result;
    }

    /**
     * 得到clazz实体对应的属性为propertyName的属性的元数据信息
     *
     * @param clazz
     * @param propertyName
     * @return
     */
    public static <T> PropertyInfo getPropertyInfo(Class<T> clazz, String propertyName) {
        BeanInfo beanInfo = getBeanInfo(clazz);
        if (beanInfo != null) {
            return beanInfo.getPropertiesMap().get(propertyName);
        }
        return null;
    }

    private static <T> List<PropertyInfo> initPropertyInfos(Class<T> clazz, BeanInfo beanInfo) {
        final List<Field> fields = new ArrayList<Field>();


        List<PropertyInfo> propertyInfos = new ArrayList<PropertyInfo>();
        //获取类所有的Fields
        ReflectionUtils.doWithFields(clazz, new FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException,
                    IllegalAccessException {
                fields.add(field);
            }
        });

        //从Fields中获取实体类的属性。这样做主要是为了支持可以在Field上加注解，目前暂不支持处理在Field上加注解的情况
        for (Field field : fields) {
            String fieldName = field.getName();

            PropertyDescriptor prop = BeanUtils.getPropertyDescriptor(clazz, fieldName);
            if (prop == null) continue;
            Method readMethod = prop.getReadMethod();
            Method writeMethod = prop.getWriteMethod();
            if (readMethod == null || writeMethod == null) continue;

            if (!readMethod.isAnnotationPresent(Transient.class) && !isCollectionType(readMethod)) {
                PropertyInfo propertyInfo = new PropertyInfo();

                //该字段是否写log
                if (beanInfo.getNotLoggedFields() != null && beanInfo.getNotLoggedFields().length > 0) {
                    for (String notLoggedFields : beanInfo.getNotLoggedFields()) {
                        if (notLoggedFields.equals(fieldName)) {
                            propertyInfo.setLogged(false);
                        }
                    }
                }


                propertyInfo.setPropertyName(ClassUtils.getPropertyName(readMethod));
                propertyInfo.setReadMethod(readMethod);

                propertyInfo.setReturnType(readMethod.getReturnType());

                if (readMethod.isAnnotationPresent(Property.class)) {
                    Property property = readMethod.getAnnotation(Property.class);
                    propertyInfo.setChineseName(property.chineseName());
                    propertyInfo.setRequired(property.required());
                    if (!StringUtils.isEmpty(property.pattern())) propertyInfo.setPattern(property.pattern());
                    propertyInfo.setMaxLength(property.maxLength());
                } else {
                    propertyInfo.setChineseName(propertyInfo.getPropertyName());
                }


                if (readMethod.isAnnotationPresent(ParentModel.class)) {
                    //propertyInfo.setParentModel(getTopParentModelClass(readMethod.getAnnotation(ParentnModel.class).value()));
                    propertyInfo.setParentModel(readMethod.getAnnotation(ParentModel.class).value());
                    beanInfo.setParentModelProperty(propertyInfo);
                }

                if (readMethod.isAnnotationPresent(DictionaryType.class)) {
                    propertyInfo.setDictionaryType(readMethod.getAnnotation(DictionaryType.class).value());
                }

                propertyInfos.add(propertyInfo);
            }
        }
        propertyInfos = Collections.unmodifiableList(propertyInfos);
        return propertyInfos;

    }


    private static boolean isCollectionType(Method m) {
        boolean result = false;
        if (m.getReturnType().isPrimitive())
            return true;
        if (Collection.class.isAssignableFrom(m.getReturnType())) {
            result = true;
        } else if (Map.class.isAssignableFrom(m.getReturnType())) {
            result = true;
        }
        return result;
    }

    public static void main(String[] args) {
        String[] str = {"aa", "bb"};
        String[] str1 = {"bb", "aa", "cv", "aa"};

        for (String a : str1) {
            System.err.println(Arrays.binarySearch(str, a));
        }
    }

}

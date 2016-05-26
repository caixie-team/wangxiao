package io.wangxiao.core.codebuilder;


import io.wangxiao.core.annotation.DictionaryType;
import io.wangxiao.core.metadata.ModelMappingManager;
import io.wangxiao.core.metadata.PropertyInfo;
import io.wangxiao.core.util.ClassUtils;
import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * 动态生成基于前台velocity macro的velocity代码，支持的宏见"macros.vm"
 * <p>
 * velocity层将本分基本类型封装成了宏，以减少开发人员的开发量，由于我们调用宏对应的值都是从实体的属性中获取的，
 * 所以这里将从实体属性获取调用宏代码的过程给抽离出来，用此工具方法实现。<br/>
 * 这里获取实体属性是通过get,set方法获取的，所以需要解析的实体必须严格遵守JAVA BEAN规范。
 * </p>
 */
public class DynamicEntityFieldTempleBuilder extends UniversalCodeBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicEntityFieldTempleBuilder.class);

    /**
     * 支持的动态模版
     */
    private Map<String, String> dynamicTemples = new HashMap<String, String>();
    /**
     * 支持的动态注解模版
     * <p>
     * 其中Object如果是List，那么其第一个值为template，其他的为注解key，如果没有
     * </p>
     */
    private Map<String, Object> dynamicAnnotationTemples = new HashMap<String, Object>();

    /**
     * 实例化方法，出示了部分已经存在的宏
     */
    public DynamicEntityFieldTempleBuilder() {
        //添加普通模版
        //添加文本类型
        dynamicTemples.put(String.class.getName(), "#trText (\"" + DYNAMIC_PROPERTY_LABEL_NAME + "\",\"" + DYNAMIC_ENTITY_NAME + "." + DYNAMIC_PROPERTY_NAME + "\", 'false','','')");
        //添加日期类型
        dynamicTemples.put(Date.class.getName(), "#trDate (\"" + DYNAMIC_PROPERTY_LABEL_NAME + "\",\"" + DYNAMIC_ENTITY_NAME + "." + DYNAMIC_PROPERTY_NAME + "\", 'false','')");
        //金额类型
        dynamicTemples.put(BigDecimal.class.getName(), "#trAmount (\"" + DYNAMIC_PROPERTY_LABEL_NAME + "\",\"" + DYNAMIC_ENTITY_NAME + "." + DYNAMIC_PROPERTY_NAME + "\", 'false','','')");
        //数字类型
        //INTEGER
        dynamicTemples.put(Integer.class.getName(), "#trNumber (\"" + DYNAMIC_PROPERTY_LABEL_NAME + "\",\"" + DYNAMIC_ENTITY_NAME + "." + DYNAMIC_PROPERTY_NAME + "\", 'false','','')");
        //Long
        dynamicTemples.put(Long.class.getName(), "#trNumber (\"" + DYNAMIC_PROPERTY_LABEL_NAME + "\",\"" + DYNAMIC_ENTITY_NAME + "." + DYNAMIC_PROPERTY_NAME + "\", 'false','','')");
        //添加 category 组件
//		dynamicTemples.put(".entity.Category", "#trCategory (\"" + DYNAMIC_PROPERTY_LABEL_NAME +"\",\"" + DYNAMIC_ENTITY_NAME +"." + DYNAMIC_PROPERTY_NAME +"\", 'false')");


        //添加注解模版

        //字典注解
        dynamicAnnotationTemples.put(DictionaryType.class.getName(), "#trSelectDict (\"" + DYNAMIC_PROPERTY_LABEL_NAME + "\",\"" + DYNAMIC_ENTITY_NAME + "." + DYNAMIC_PROPERTY_NAME + "\", '" + DYNAMIC_ANNOTATION_NAME + "value', 'true','')");
    }

    @Override
    public <T> String buildByClass(Class<T> clazz) {
        //检查clazz是否存在
        if (clazz == null) {
            throw new IllegalArgumentException("clazz is null");
        }
        //检查是否接口类型
        if (clazz.isInterface()) {
            throw new ClassFormatException(clazz.getName() + " can't is interface");
        }
        //生成模版
        StringBuilder rs = new StringBuilder();
        String temp = null;
        List<PropertyInfo> propertyinfos = ModelMappingManager.getBeanInfo(clazz).getProperties();
        for (PropertyInfo propertyinfo : propertyinfos) {
            //生成注解
            temp = generateAnnotationByProperty(propertyinfo, clazz);
            //如果没有注解
            if (temp == null) {
                temp = generateByProperty(propertyinfo, clazz);
            }
            //如果类型支持
            if (temp != null) {
                rs.append(temp).append("\n");
            } else {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(propertyinfo.getPropertyName() + " 属性目前不支持 ");
                }
            }
        }
        return rs.toString();
    }

    /**
     * 向模版工具中注册一个新普通类型模版
     *
     * @param clazz 类型类
     * @param value 模版值，动态字段见{@value #DYNAMIC_ENTITY_NAME},{@value #DYNAMIC_PROPERTY_LABEL_NAME},{@value #DYNAMIC_PROPERTY_NAME}
     */
    public void addTemplate(Class<?> clazz, String value) {
        dynamicTemples.put(clazz.getName(), value);
    }

    /**
     * 向模版工具中注册一个新注解类型模版
     * <p>
     * 注解类型模版用于统一类型处理，该优先级大于普通类型，比如一个字段是Long类型的值，而且其注册了DictionaryType类型的注解，那么其解析式先按注解类型解析，只有此类型解析失败，才会按正常类型解析。<br/>
     * 该注解支持多个tag值解析，tag必须以{@value #DYNAMIC_ANNOTATION_NAME}开头，如（{@value #DYNAMIC_ANNOTATION_NAME}value）表示的是获取value标签值， 在values字段中进行设置，该字段支持String和List<String>类型数据：<br/>
     * String类型：默认方式，tag为value
     * List<String>类型：第一个String是template字符串，以后得均为tag名称，该名称必须加上（{@value #DYNAMIC_ANNOTATION_NAME}）如（{@value #DYNAMIC_ANNOTATION_NAME}value）
     * </p>
     *
     * @param clazz  注解类
     * @param values 模版参数，支持List<String>和String，如果是String型则表示只获取一个value，而且其tag的名称也为value
     */
    public void addAnnotationTemplate(Class<?> clazz, Object values) {
        dynamicAnnotationTemples.put(clazz.getName(), values);
    }

    /**
     * 生成属性对应的模版字符串
     *
     * @param propertyinfo 属性对象
     * @param clazz        实体类
     * @return 生成的结果
     */
    private String generateByProperty(PropertyInfo propertyinfo, Class<?> clazz) {
        //获取属性名称
        String propertyName = propertyinfo.getPropertyName();
        //获取实体名称
        String entityName = clazz.getSimpleName();
        //实体名称首字母小写
        entityName = ClassUtils.getLowerFirstLetterSimpleClassName(entityName);
        //获取类型
        Class<?> type = propertyinfo.getReturnType();
        //获取模版
        String temple = getVelocityMacroTempleByPropertyClass(type);
        //检查模版是否存在
        if (temple == null) {
            LOGGER.debug(clazz.getName() + "." + type.getName() + " is no support");
            return null;
        }
        //开始将模版中的动态文本，替换成真实数据
        //替换属性名称
        temple = temple.replaceAll(DYNAMIC_PROPERTY_NAME, propertyName);
        //替换属性显示名称
        temple = temple.replaceAll(DYNAMIC_PROPERTY_LABEL_NAME, propertyinfo.getChineseName());
        //替换实体名称
        temple = temple.replaceAll(DYNAMIC_ENTITY_NAME, entityName);
        return temple;
    }

    /**
     * 生成属性对应的注解模版字符串
     *
     * @param propertyinfo 属性对象
     * @param clazz        实体类
     * @return 生成结果
     */
    private String generateAnnotationByProperty(PropertyInfo propertyinfo, Class<?> clazz) {
        Method propertyReadMethod = propertyinfo.getReadMethod();
        //先获取模版
        Map<String, String> template = getVelocityMacroTempleByAnnotationPropertyClass(propertyReadMethod);
        //如果不存在
        if (template == null) {
            return null;
        }
        //检查当前是否有支持的temp
        //获取属性名称
        String propertyName = propertyinfo.getPropertyName();
        //获取实体名称
        String entityName = clazz.getSimpleName();
        //实体名称首字母小写
        entityName = ClassUtils.getLowerFirstLetterSimpleClassName(entityName);
        //获取模版
        String temple = template.get(DYNAMIC_ANNOTATION_NAME);
        //开始将模版中的动态文本，替换成真实数据
        //替换属性名称
        temple = temple.replaceAll(DYNAMIC_PROPERTY_NAME, propertyName);
        //替换属性显示名称
        temple = temple.replaceAll(DYNAMIC_PROPERTY_LABEL_NAME, propertyinfo.getChineseName());
        //替换实体名称
        temple = temple.replaceAll(DYNAMIC_ENTITY_NAME, entityName);
        //替换对应tag的值
        Iterator<String> it = template.keySet().iterator();
        String key = null;
        while (it.hasNext()) {
            key = it.next();
            //检查key是否是DYNAMIC_ANNOTATION_NAME
            if (!key.equals(DYNAMIC_ANNOTATION_NAME)) {
                //开始替换
                temple = temple.replaceAll(key, template.get(key));
            }
        }
        return temple;
    }

    /**
     * 获取未解析的模版字符串
     *
     * @param propertyClass
     * @return
     */
    private String getVelocityMacroTempleByPropertyClass(Class<?> propertyClass) {
        //获取模版
        return dynamicTemples.get(propertyClass.getName());
    }

    /**
     * 获取未解析的注解模版字符串
     *
     * @param propertyReadMethod
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, String> getVelocityMacroTempleByAnnotationPropertyClass(Method propertyReadMethod) {
        //获取其get方法
        //根据method获取其注解列表
        Annotation[] classes = propertyReadMethod.getAnnotations();
        //如果注解列表不存在
        if (classes == null || classes.length == 0) {
            return null;
        }
        Map<String, String> rs = new HashMap<String, String>();
        //获取模版
        Object obj = null;
        Annotation temp = null;
        for (Annotation aClass : classes) {
            temp = aClass;
            obj = dynamicAnnotationTemples.get(temp.annotationType().getName());
            if (obj != null) {
                break;
            }
        }
        //如果不存在该注解
        if (obj == null) {
            return null;
        }
        //解析注解
        List<String> values = null;
        if (obj instanceof List) {
            values = (List<String>) obj;
        } else if (obj instanceof String) {
            List<String> list = new ArrayList<String>();
            list.add(obj.toString());
            list.add(DYNAMIC_ANNOTATION_NAME + "value");
            values = list;
        }
        //如果对应的模版数据格式错误
        if (values == null) {
            return null;
        }
        //开始解析
        rs.put(DYNAMIC_ANNOTATION_NAME, values.get(0));
        String tag = null;
        String tt = null;
        for (int i = 1; i < values.size(); i++) {
            tag = values.get(i);
            //去除前置字符串
            tt = tag.replace(DYNAMIC_ANNOTATION_NAME, "");
            //开始获取值
            try {
                propertyReadMethod = temp.getClass().getDeclaredMethod(tt, new Class<?>[]{});
                rs.put(tag, propertyReadMethod.invoke(temp, new Object[]{}).toString());
            } catch (Exception e) {
                LOGGER.error(tag + " field not read ", e);
            }
        }
        return rs;
    }

    /**
     * 属性名称
     */
    public static final String DYNAMIC_PROPERTY_NAME = "dynamic-property-name";
    /**
     * 属性显示名称
     */
    public static final String DYNAMIC_PROPERTY_LABEL_NAME = "dynamic-property-label-name";
    /**
     * 实体名称
     */
    public static final String DYNAMIC_ENTITY_NAME = "dynamic-entity-name";
    /**
     * 注解template key名称
     */
    public static final String DYNAMIC_ANNOTATION_NAME = "dynamic-annotation-template-name";
}

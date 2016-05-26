package io.wangxiao.core.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ClassUtils {
    public static String getLowerFirstLetterSimpleClassName(String className) {
        if (StringUtils.isEmpty(className)) return "";
        String[] parts = className.split("\\.");
        String result = parts[parts.length - 1];
        return result.substring(0, 1).toLowerCase() + (result.length() > 1 ? result.substring(1) : "");
    }

    /**
     * 1.获取属性名称的简单名，如果属性名称是entity.property，那么将返回property
     * 2.如何输入的名称有 [ 标识 说明是查询字段，原样返回即可 ，不做任何处理
     *
     * @param fullName 属性名称
     * @return 处理后的属性名称
     */
    public static String getSimplePropertyName(String fullName) {

        if (!StringUtils.isTrimEmpty(fullName) && fullName.indexOf("[") != -1) {
            //如果字段含有 [ 说明是查询字段，直接返回
            return fullName;
        }

        if (!StringUtils.isTrimEmpty(fullName) && fullName.indexOf(".") != -1) {//检查要获取的属性名是否含有.
            //检查.是否是最后一个字符
            if (fullName.indexOf(".") != fullName.length()) {
                fullName = fullName.substring(fullName.indexOf(".") + 1);
            }
        }

        return fullName;
    }

    /**
     * 获取 get 方法对应的属性名
     *
     * @param readMethod
     * @return
     */
    public static String getPropertyName(Method readMethod) {
        String methodName = readMethod.getName();
        int getPosition = methodName.indexOf("get");
        if (getPosition == -1) {
            throw new RuntimeException(methodName + " 不是 以get开关的方法");
        }
        return getLowerFirstLetterSimpleClassName(methodName.substring(getPosition + 3));
    }

    /**
     * 根据字符串转化成ID
     * 1.如果属性名称是entity.property，那么将返回property
     * 2.如何输入的名称有 [ 标识 说明是查询字段   如 ：conditions['date_date_gt'].value  用  split("'") 截断 返回第二个
     *
     * @param fullName 属性名称
     * @return 处理后的属性名称
     */
    public static String changeToId(String fullName) {

        if (!StringUtils.isTrimEmpty(fullName) && fullName.indexOf("[") != -1) {
            //如果字段含有 [ 说明是查询字段，直接返回
            String[] ids = fullName.split("'");
            if (ids.length == 3)
                return ids[1];
            return fullName;
        }

        if (!StringUtils.isTrimEmpty(fullName) && fullName.indexOf(".") != -1) {//检查要获取的属性名是否含有.
            //检查.是否是最后一个字符
            if (fullName.indexOf(".") != fullName.length()) {
                fullName = fullName.substring(fullName.indexOf(".") + 1);
            }
        }

        return fullName;
    }

    /**
     * 克隆一个对象
     * <p>
     * 该对象必须实现了Serializable接口,<br/>
     * 使用序列化和反序列化形式进行克隆
     * </p>
     *
     * @param entity 要克隆的对象
     * @return 克隆结果
     */
    @SuppressWarnings("unchecked")
    public static <T> T cloneObject(T entity) {
        T result = null;
        if (entity == null) {
            return result;
        }
        //检查克隆实体是否实现了Serializable接口
        if (!Serializable.class.isAssignableFrom(entity.getClass())) {
            throw new IllegalArgumentException("需要克隆的实体必须实现Serializable接口");
        }
        ByteArrayOutputStream t = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        ByteArrayInputStream t2 = null;
        try {
            t = new ByteArrayOutputStream();
            out = new ObjectOutputStream(t);
            out.writeObject(entity);
            out.flush();
            t2 = new ByteArrayInputStream(t.toByteArray());
            in = new ObjectInputStream(t2);
            result = (T) in.readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (t2 != null) {
                try {
                    t2.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
            if (t != null) {
                try {
                    t.close();
                } catch (IOException e) {
                }
            }
        }
        return result;
    }

    /**
     * 获取某超类的子类列表
     *
     * @param superClass                                                           超类
     * @param packageName，如enums，最终会扫描符合com/haixue\/**\/packageName/**\\/*.class的类
     * @return 子类列表
     */
    @SuppressWarnings("unchecked")
    public static <T> List<Class<T>> getClassBySupperClass(Class<T> superClass, String packageName) {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(Thread.currentThread().getContextClassLoader());
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        List<Class<T>> classList = new ArrayList<Class<T>>();
        try {
            // 如com/haixue/**/entity/**/*.class
            Resource[] rs = resolver.getResources("classpath*:com/haixue/**/" + packageName + "/**/*.class");
            for (Resource resource : rs) {
                String className = metadataReaderFactory.getMetadataReader(resource).getClassMetadata().getClassName();
                Class<T> clazz;
                try {
                    clazz = (Class<T>) Class.forName(className);
                    //判断当前cl是否是 superclass的子类
                    if (superClass.isAssignableFrom(clazz) && clazz != superClass) {
                        classList.add(clazz);
                    }
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException("加载类失败", e);
                }

            }
        } catch (IOException e) {
            throw new IllegalArgumentException("加载类失败", e);
        }
        return classList;
    }

    /**
     * 根据类的全限定名，获取类对象
     *
     * @param <T>
     * @param simpleName
     * @return
     * @author shifenghu
     * @date 2013-9-5
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClassBySimpleName(String simpleName) {
        try {
            return (Class<T>) Class.forName(simpleName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 根据构造函数的参数，实例化一个对象
     *
     * @param <T>
     * @param clazz
     * @param params
     * @return
     * @author shifenghu
     * @date 2013-9-5
     */
    public static <T> T newInstance(Class<T> clazz, Object... params) {
        //如果构造函数的参数存在
        if (params != null) {
            Class<?>[] cs = new Class<?>[params.length];
            for (int i = 0; i < params.length; i++) {
                cs[i] = params[i].getClass();
                //如果是代理对象，则获取被代理的接口类
                if (Proxy.isProxyClass(cs[i])) {
                    cs[i] = cs[i].getInterfaces()[0];
                }
            }
            try {
                return clazz.getConstructor(cs).newInstance(params);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
        //默认构造方法
        else {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public static void main(String[] args) {

    }
}

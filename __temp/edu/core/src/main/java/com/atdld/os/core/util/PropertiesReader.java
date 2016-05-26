package com.atdld.os.core.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * @ClassName com.supergenius.sns.util.PropertiesReader
 * @description 获取配置文件
 * @author :
 * @Create Date : 2013-12-13 下午2:21:13
 */
public class PropertiesReader {
    /**
     * 取得指定properties文件的指定key的value
     * 
     * @param file_name
     *            properties文件的名字（没有扩展名）
     * @param key
     *            所指定的key
     * @return 指定key对应的value值
     * @throws MissingResourceException
     *             当没有这个properties文件，或该文件中不存在这个key时
     */
    public static String getValue(String file_name, String key)
            throws MissingResourceException {
        final ResourceBundle res = ResourceBundle.getBundle(file_name);
        String value = res.getString(key);
        return value.trim();
    }

    /**
     * 将文件中配置信息填充到properties对象中
     * 
     * @see
     * @param file_name
     * @return
     */
    public static Properties fillProperties(String file_name)
            throws MissingResourceException {
        Properties properties = new Properties();
        final ResourceBundle res = ResourceBundle.getBundle(file_name);
        Enumeration<String> en = res.getKeys();
        String key = null;
        String value = null;
        while (en.hasMoreElements()) {
            key = en.nextElement().trim();
            value = res.getString(key);
            properties.setProperty(key, value.trim());
        }
        return properties;
    }

    /**
     * 取得指定properties文件的指定key的value
     * 
     * @param file_name
     *            properties文件的名字（没有扩展名）
     * @param key
     *            所指定的key
     * @return 指定key对应的value值
     * @throws MissingResourceException
     *             当没有这个properties文件，或该文件中不存在这个key时
     */
    public static void setValue(String file_name, String key, String value) {
        try {
            Properties properties = new Properties();
            PropertiesReader propertiesReader = new PropertiesReader();
            String staticPath = propertiesReader.getClass().getClassLoader().getResource("").getPath();
            if (file_name.indexOf(".properties") < 0) {
                file_name += ".properties";
            }
            String file_name_path = staticPath + file_name;
            FileInputStream in = new FileInputStream(file_name_path);
            properties.load(in);
            FileOutputStream fis = new FileOutputStream(file_name_path);// 属性文件输入流
            properties.setProperty(key, value);
            properties.store(fis, file_name);
            in.close();
            fis.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }
}

package co.bluepx.edu.core.util;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;

public class SystemGlobals {

    private final static CompositeConfiguration config = new CompositeConfiguration();

    /**
     * 文件内容读入内存
     */
    public static void loadConfig(String configFile) throws ConfigurationException {
        config.addConfiguration(new PropertiesConfiguration(configFile));
    }

    /**
     * 读取配置文件值
     */
    public static String getValue(String paramName) {

        String str = "";
        if (paramName.contains(" ")) {
            for (String s : paramName.split(" ")) {
                str += config.getString(s);
            }
            return str;
        }
        return config.getString(paramName);
    }

    /**
     * 获取值列表
     *
     * @param paramName
     * @return
     */
    public static String[] getValues(String paramName) {
        return config.getStringArray(paramName);
    }

    /**
     * 获取keys
     *
     * @return keys
     */
    public static Iterator<?> getNames() {
        return config.getKeys();
    }

    /**
     * 读取配置文件值，如不存在则返回默认值
     */
    public static String getValue(String paramName, String defaultValue) {

        String value = config.getString(paramName);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value;

    }
}

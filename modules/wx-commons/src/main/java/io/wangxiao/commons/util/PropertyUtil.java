/**
 *
 */
package io.wangxiao.commons.util;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @ClassName PropertyUtil
 * @package com.business.platform.core.util
 */
public class PropertyUtil {

    // 实例map
    private static Map<String, PropertyUtil> instance = Collections
            .synchronizedMap(new HashMap<String, PropertyUtil>());

    protected String sourceUrl;
    protected ResourceBundle resourceBundle;
    private static Map<String, String> convert = Collections
            .synchronizedMap(new HashMap<String, String>());

    protected PropertyUtil(String sourceUrl) {
        this.sourceUrl = sourceUrl;
        load();
    }

    public static PropertyUtil getInstance(String sourceUrl) {
        synchronized (PropertyUtil.class) {
            PropertyUtil manager = (PropertyUtil) instance.get(sourceUrl);
            if (manager == null) {
                manager = new PropertyUtil(sourceUrl);
                instance.put(sourceUrl, manager);
            }
            return manager;
        }
    }

    private synchronized void load() {
        try {
            resourceBundle = ResourceBundle.getBundle(sourceUrl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("sourceUrl = " + sourceUrl + "file load error!", e);
        }
    }

    public String getProperty(String key) {
        try {
            return new String(resourceBundle.getString(key).getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            return resourceBundle.getString(key);
        }
    }

    public Map<String, String> readyConvert() {
        @SuppressWarnings("rawtypes")
        Enumeration enu = resourceBundle.getKeys();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = resourceBundle.getString(key);
            convert.put(value, key);
        }
        return convert;
    }

    public Map<String, String> readyConvert(ResourceBundle resourcebundle) {
        @SuppressWarnings("rawtypes")
        Enumeration enu = resourcebundle.getKeys();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = resourcebundle.getString(key);
            convert.put(value, key);
        }
        return convert;
    }
}

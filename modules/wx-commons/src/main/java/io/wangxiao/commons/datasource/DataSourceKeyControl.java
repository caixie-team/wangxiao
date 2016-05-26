package io.wangxiao.commons.datasource;

import java.util.Map;

/**
 * @ClassName DataSourceKeyControl
 * @description 数据源key的存储控制器接口
 */

public interface DataSourceKeyControl {
    /**
     * 设置读(从)库数据源对应关系
     *
     * @param readDateSourceMap
     */
    void setReadDateSourceMap(Map<String, String> readDateSourceMap);

    /**
     * 获得读(从)库数据源key
     *
     * @param key
     * @return
     */
    String getKey(String key);

    /**
     * 获得写(主)库数据源key
     *
     * @return
     */
    String getWriteKey();

    /**
     * 设置写(主)库的key
     *
     * @param writeKey
     */
    void setWriteKey(String writeKey);

    /**
     * 设置方法调用时使用写(主)库的key
     */
    void setWriteKey();

    /**
     * 设置方法调用时使用读(从)库的key,随机
     */
    void setReadKey();

    /**
     * 设置方法调用时使用读(从)库的key,指定
     */
    void setKey(String key);

    /**
     * 读取方法调用时使用的key
     */
    String getKey();

    /**
     * 清除设置的key
     */
    void clearKey();

}
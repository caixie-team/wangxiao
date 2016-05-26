package io.wangxiao.commons.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @ClassName DataSourceRouter
 * @description 取得数据源的KEY
 *
 */
public class FKRoutingDataSource extends AbstractRoutingDataSource {
    private static final Logger logger = LoggerFactory.getLogger(FKRoutingDataSource.class);
    // 数据源key的存储控制器
    private DataSourceKeyControl dataSourceKeyControl;

    /**
     * 获得数据源的key,覆盖的主要方法
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String key = "";
        try {
            key = dataSourceKeyControl.getKey();
        } catch (Throwable e) {
            logger.error("determineCurrentLookupKey error:", e);
            throw new RuntimeException("get data source key fail", e);
        }
        return key;
    }

    public DataSourceKeyControl getDataSourceKeyControl() {
        return dataSourceKeyControl;
    }

    public void setDataSourceKeyControl(DataSourceKeyControl dataSourceKeyControl) {
        this.dataSourceKeyControl = dataSourceKeyControl;
    }

}

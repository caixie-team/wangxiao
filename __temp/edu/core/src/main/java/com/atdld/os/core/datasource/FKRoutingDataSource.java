package com.atdld.os.core.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @ClassName DataSourceRouter
 * @package com.atdld.os.core.db
 * @description 取得数据源的KEY
 * @author
 * @Create Date: 2013-2-26 下午02:51:53
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

package io.wangxiao.core.spring;


import io.wangxiao.core.ApplicationContextAccessor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件信息
 */
public class ExtendedPropertyPlaceholderConfigurer extends
        PropertyPlaceholderConfigurer {
	
	private static ExtendedPropertyPlaceholderConfigurer configurer;
	private Properties properties;

	@Override
	protected Properties mergeProperties() throws IOException {
		properties = super.mergeProperties();
		return properties;
	}
	
	public static String getValue(Object key){
		return (String) getConfigurer().properties.get(key);
	}

	
	private static ExtendedPropertyPlaceholderConfigurer getConfigurer(){
		if(configurer == null){
			synchronized(ExtendedPropertyPlaceholderConfigurer.class){
				if(configurer == null){
					configurer = ApplicationContextAccessor.getBean(ExtendedPropertyPlaceholderConfigurer.class);
				}
			}
		}
		return configurer;
	}
}

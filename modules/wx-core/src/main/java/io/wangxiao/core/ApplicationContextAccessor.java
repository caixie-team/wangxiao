package io.wangxiao.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextAccessor implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextAccessor.applicationContext = applicationContext;
	}
	
	/**
	 * 注意方法使用前提为当前类必须被Spring容器初始化
	 * @param beanName
	 * @return
	 */
	public static <T> T getBean(String beanName) {
		return (T) applicationContext.getBean(beanName);
	}
	/**
	 * 根据class类型从spring 容器初始化bean
	 * @param beanClass bean class
	 * @return 实体
	 */
	public static <T> T getBean(Class<T> beanClass){
		return (T) applicationContext.getBean(beanClass);
	}

}

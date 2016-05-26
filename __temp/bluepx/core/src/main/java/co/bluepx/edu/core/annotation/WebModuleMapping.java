package co.bluepx.edu.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能：给Controller指定模块路径
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.TYPE})
public @interface WebModuleMapping {
	String value() default "";
}

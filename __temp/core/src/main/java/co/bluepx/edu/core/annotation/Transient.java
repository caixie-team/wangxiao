package co.bluepx.edu.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 *  <code>Transient.java</code>
 *  <p>功能:实现hibernate-jpa包中的不持久化相应的get方法
 *  
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD })
public @interface Transient {

}

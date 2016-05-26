package co.bluepx.edu.core.annotation;

import java.lang.annotation.*;

/**
 * 在实体的get属性方法上加。用来表示当前实体或者属性的中文意思
 *
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Inherited
public @interface Property {
	String SHORT_DATE= "yyyy-MM-dd";
	String LONG_DATE= "yyyy-MM-dd HH:mm:ss";
	String chineseName() default "";
	int maxLength() default 0;
	boolean required() default false;
	String pattern() default "";
//	boolean isColumn() default true;
}

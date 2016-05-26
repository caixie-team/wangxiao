package io.wangxiao.core.annotation;

import java.lang.annotation.*;

/**
 * 在实体上加。用来表示当前实体或者属性的中文意思
 *
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Inherited
public @interface ChineseName {
	String value() ;
}

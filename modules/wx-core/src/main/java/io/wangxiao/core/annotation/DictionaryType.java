package io.wangxiao.core.annotation;

import java.lang.annotation.*;

/**
 * 如果实体上某个属性的值引用Dictionary中中的Id，需要在该属性的get方法中加上此注解。
 * 如 @DictionaryType( "sex") ，其中sex对应Dictionary实体中的dictionaryType的值。
 *
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Inherited
public @interface DictionaryType {
	String value();
}

package io.wangxiao.core.annotation;

import java.lang.annotation.*;

/**
 * 在实体上注解，则当前实体不会被记日志
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Inherited
public @interface NotLogged {

}

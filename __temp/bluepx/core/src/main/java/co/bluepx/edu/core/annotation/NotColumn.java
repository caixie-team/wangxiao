package co.bluepx.edu.core.annotation;

import java.lang.annotation.*;

/**
 * Created by bison on 1/5/16.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Inherited
public @interface NotColumn {
}

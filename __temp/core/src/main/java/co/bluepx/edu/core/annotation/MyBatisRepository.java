package co.bluepx.edu.core.annotation;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: basil
 * Date: 13-11-11
 * Time: 17:32
 * 标识MyBatis的DAO,方便{@link org.mybatis.spring.mapper.MapperScannerConfigurer}的扫描。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MyBatisRepository {
    String value() default "";
}

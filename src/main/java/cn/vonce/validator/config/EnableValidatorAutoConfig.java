package cn.vonce.validator.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用自动配置字段验证器注解(全局)
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020年02月05日下午23:22:00
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ValidatorConfig.class)
public @interface EnableValidatorAutoConfig {

    /**
     * 如果未指定值则自动查找
     * @return
     */
    String[] expressions() default {};

}

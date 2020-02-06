package cn.vonce.validator.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用字段验证器注解(局部)
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020年02月06日下午11:05:00
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ValidatorConfig.class)
public @interface EnableValidator {



}

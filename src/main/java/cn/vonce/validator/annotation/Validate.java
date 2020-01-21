package cn.vonce.validator.annotation;

import java.lang.annotation.*;

/**
 * 统一校验注解规范
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 16:23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validate {

    Class<?> type();

}

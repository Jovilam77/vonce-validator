package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateSQLInject;

import java.lang.annotation.*;

/**
 * 校验是否存在sql注入
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2018年2月28日下午8:13:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Validate(type = ValidateSQLInject.class)
public @interface VSQLInject {

    /**
     * 字段名称
     *
     * @return
     * @author Jovi
     * @date 2018年2月28日下午8:12:48
     */
    String name() default "";

    /**
     * 是否只有不为空的时候才校验
     *
     * @return
     */
    boolean onlyWhenNotEmpty() default false;

    /**
     * 消息提示
     *
     * @return
     * @author Jovi
     * @date 2018年2月28日下午8:12:40
     */
    String value() default "";

    /**
     * 分组校验
     *
     * @return
     * @author Jovi
     * @date 2018年2月28日下午8:12:33
     */
    String[] group() default "";

}

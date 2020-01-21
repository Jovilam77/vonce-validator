package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateRegex;

import java.lang.annotation.*;

/**
 * 校验是否符合正则表达式的规则
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/21 15:49
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Validate(type = ValidateRegex.class)
public @interface VRegex {

    /**
     * 字段名称
     *
     * @return
     */
    String name() default "";

    /**
     * 消息提示
     *
     * @return
     */
    String value() default "";

    /**
     * 正则表达式
     *
     * @return
     */
    String val() default "";

    /**
     * 分组校验
     *
     * @return
     */
    String[] group() default "";

}

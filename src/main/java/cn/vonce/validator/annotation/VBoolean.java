package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateBoolean;

import java.lang.annotation.*;

/**
 * 校验布尔类型
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 10:46
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Validate(type = ValidateBoolean.class)
public @interface VBoolean {

    /**
     * NORMAL 校验值是否为布尔类型
     * TRUE 校验值是否等于TRUE
     * FALSE 校验值是否等于FALSE
     */
    public enum BoolValue {
        NORMAL, TRUE, FALSE
    }

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
     * 校验的字段值必须等于此值
     *
     * @return
     */
    BoolValue val() default BoolValue.NORMAL;

    /**
     * 分组校验
     *
     * @return
     */
    String[] group() default "";

}

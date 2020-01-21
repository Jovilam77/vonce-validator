package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateNumber;

import java.lang.annotation.*;

/**
 * 校验数字
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 10:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Validate(type = ValidateNumber.class)
public @interface VNumber {

    /**
     * NORMAL 校验值是否为数字类型
     * INTEGER 校验值是否为整数类型
     * FLOAT 校验值是否为浮点类型
     */
    public enum NumType {
        NUMBER, INTEGER, FLOAT
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
     * 校验是否为指定的数字类型
     *
     * @return
     */
    VNumber.NumType val() default VNumber.NumType.NUMBER;

    /**
     * 分组校验
     *
     * @return
     */
    String[] group() default "";

}

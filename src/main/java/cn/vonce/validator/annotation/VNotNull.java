package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateNotNull;

import java.lang.annotation.*;

/**
 * 校验字段不能为null
 *
 * @author jovi
 * @version 1.0
 * @email 766255988@qq.com
 * @date 2017年4月20日下午6:45:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Validate(type = ValidateNotNull.class)
public @interface VNotNull {

    /**
     * 标识该字段名称 用于拼接消息提示
     *
     * @return
     * @author jovi
     * @date 2017年4月21日上午11:56:34
     */
    String name() default "";

    /**
     * 消息提示
     *
     * @return
     * @author jovi
     * @date 2017年4月21日上午10:50:02
     */
    String value() default "字段不能为null";

    /**
     * 该字段在某分组进行校验
     *
     * @return
     * @author Jovi
     * @date 2017年6月21日下午12:07:25
     */
    String[] group() default "";

}

package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateUrl;

import java.lang.annotation.*;

/**
 * 校验URl格式
 *
 * @author jovi
 * @version 1.0
 * @email 766255988@qq.com
 * @date 2017年4月23日下午3:28:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Validate(type = ValidateUrl.class)
public @interface VUrl {

    /**
     * 标识该名称 用于拼接消息提示
     *
     * @return
     * @author jovi
     * @date 2017年4月21日上午11:56:34
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
     * @author jovi
     * @date 2017年4月21日上午10:49:47
     */
    String value() default "URl格式不正确";

    /**
     * 校验URl格式是否正确
     *
     * @return
     * @author jovi
     * @date 2017年4月21日上午10:49:41
     */
    boolean val() default true;

    /**
     * 该字段在某分组进行校验
     *
     * @return
     * @author Jovi
     * @date 2017年6月21日下午12:07:25
     */
    String[] group() default "";

}

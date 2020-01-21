package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateUserName;
import java.lang.annotation.*;

/**
 * 校验用户名格式
 *
 * @author jovi
 * @version 1.0
 * @email 766255988@qq.com
 * @date 2017年4月23日下午3:28:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Validate(type = ValidateUserName.class)
public @interface VUserName {

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
    String value() default "用户名格式不正确";

    /**
     * 该字段在某分组进行校验
     *
     * @return
     * @author Jovi
     * @date 2017年6月21日下午12:07:25
     */
    String[] group() default "";

}

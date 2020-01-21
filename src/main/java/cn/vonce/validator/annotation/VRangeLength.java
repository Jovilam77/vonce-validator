package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateRangeLength;

import java.lang.annotation.*;

/**
 * 校验字段长度范围
 *
 * @author jovi
 * @version 1.0
 * @email 766255988@qq.com
 * @date 2017年4月20日下午9:59:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Validate(type = ValidateRangeLength.class)
public @interface VRangeLength {

    /**
     * 标识该字段名称 用于拼接消息提示
     *
     * @return
     * @author jovi
     * @date 2017年4月21日上午11:56:34
     */
    String name() default "";

    /**
     * 校验字段最大长度值
     *
     * @return
     * @author jovi
     * @date 2017年4月21日上午10:50:11
     */
    int max();

    /**
     * 校验字段最小长度值
     *
     * @return
     * @author jovi
     * @date 2017年4月21日上午10:50:16
     */
    int min();

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
     * @date 2017年4月21日上午10:50:21
     */
    String value() default "字段长度最大为%s,最小为%s";

    /**
     * 该字段在某分组进行校验
     *
     * @return
     * @author Jovi
     * @date 2017年6月21日下午12:07:25
     */
    String[] group() default "";

}

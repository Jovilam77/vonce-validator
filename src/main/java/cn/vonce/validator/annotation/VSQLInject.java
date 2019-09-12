package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.ValidFieldDefault;

import java.lang.annotation.*;

/**
 * 验证字段是否存在sql注入
 *
 * @author Jovi
 * @version 1.0
 * @email 766255988@qq.com
 * @date 2018年2月28日下午8:13:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Inherited
public @interface VSQLInject {

    /**
     * 标识该名称 用于拼接消息提示
     *
     * @return
     * @author Jovi
     * @date 2018年2月28日下午8:12:48
     */
    String name() default "";

    /**
     * 是否不为空时才验证
     *
     * @return
     */
    boolean notEmpty() default false;

    /**
     * 消息提示
     *
     * @return
     * @author Jovi
     * @date 2018年2月28日下午8:12:40
     */
    String value() default "非法字段";

    /**
     * 该字段在某分组进行验证
     *
     * @return
     * @author Jovi
     * @date 2018年2月28日下午8:12:33
     */
    String[] group() default "";

    /**
     * 拓展类
     *
     * @return
     * @author Jovi
     * @date 2018年2月28日下午8:12:27
     */
    Class<?> type() default ValidFieldDefault.class;

    /**
     * 拓展方法
     *
     * @return
     * @author Jovi
     * @date 2018年2月28日下午8:12:20
     */
    String method() default "validSQLInject";

}

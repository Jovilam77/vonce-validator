package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.ValidFieldDefault;

import java.lang.annotation.*;

/**
 * 验证字段长度范围
 *
 * @author jovi
 * @version 1.0
 * @email 766255988@qq.com
 * @date 2017年4月20日下午9:59:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Inherited
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
     * 验证字段最大长度值
     *
     * @return
     * @author jovi
     * @date 2017年4月21日上午10:50:11
     */
    int max();

    /**
     * 验证字段最小长度值
     *
     * @return
     * @author jovi
     * @date 2017年4月21日上午10:50:16
     */
    int min();

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
     * @author jovi
     * @date 2017年4月21日上午10:50:21
     */
    String value() default "字段长度最大为%s,最小为%s";

    /**
     * 该字段在某分组进行验证
     *
     * @return
     * @author Jovi
     * @date 2017年6月21日下午12:07:25
     */
    String[] group() default "";

    /**
     * 拓展类
     *
     * @return
     * @author jovi
     * @date 2017年4月21日下午7:02:03
     */
    Class<?> type() default ValidFieldDefault.class;

    /**
     * 拓展方法
     *
     * @return
     * @author jovi
     * @date 2017年4月21日下午7:02:13
     */
    String method() default "validRangeLength";

}

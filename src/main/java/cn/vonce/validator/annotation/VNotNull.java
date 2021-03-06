package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateNotNull;

import java.lang.annotation.*;

/**
 * 校验不能为null
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2017年4月20日下午6:45:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Validate(type = ValidateNotNull.class)
public @interface VNotNull {

    /**
     * 字段名称
     *
     * @return
     * @author Jovi
     * @date 2017年4月21日上午11:56:34
     */
    String name() default "";

    /**
     * 消息提示
     *
     * @return
     * @author Jovi
     * @date 2017年4月21日上午10:50:02
     */
    String value() default "";

    /**
     * 分组校验
     *
     * @return
     * @author Jovi
     * @date 2017年6月21日下午12:07:25
     */
    String[] group() default "";

}

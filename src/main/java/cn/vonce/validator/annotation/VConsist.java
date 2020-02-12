package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateBoolean;
import cn.vonce.validator.rule.impl.ValidateConsist;

import java.lang.annotation.*;

/**
 * 校验是否由指定值构成
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/2/11 9:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Validate(type = ValidateConsist.class)
public @interface VConsist {

    /**
     * 字段名称
     *
     * @return
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
     */
    String value() default "";

    /**
     * 包含以下值
     *
     * @return
     */
    String[] val();

    /**
     * 分组校验
     *
     * @return
     */
    String[] group() default "";

}

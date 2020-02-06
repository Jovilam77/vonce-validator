package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateChinese;

import java.lang.annotation.*;

/**
 * 校验中文
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2017年4月23日下午3:28:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Validate(type = ValidateChinese.class)
public @interface VChinese {

    /**
     * IS_CHINESE_NOT_HAS_SYMBOL 中文汉字不包含中文符号的字符串<br>
     * HAS_CHINESE_NOT_HAS_SYMBOL 包含中文汉字但不包含中文符号的字符串<br>
     * IS_CHINESE_HAS_SYMBOL 中文汉字或者中文符号的字符串<br>
     * HAS_CHINESE_HAS_SYMBOL 包含中文汉字或者中文符号的字符串
     *
     * @author Jovi
     * @version 1.0
     * @email imjovi@qq.com
     * @date 2017年4月24日下午3:46:25
     */
    public static enum ChineseType {
        IS_CHINESE_NOT_HAS_SYMBOL, HAS_CHINESE_NOT_HAS_SYMBOL, IS_CHINESE_HAS_SYMBOL, HAS_CHINESE_HAS_SYMBOL
    }

    /**
     * 字段名称
     *
     * @return
     * @author Jovi
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
     * @author Jovi
     * @date 2017年4月21日上午10:49:47
     */
    String value() default "";

    /**
     * 校验是否未中文
     *
     * @return
     * @author Jovi
     * @date 2017年4月21日上午10:49:41
     */
    ChineseType val() default ChineseType.IS_CHINESE_NOT_HAS_SYMBOL;

    /**
     * 分组校验
     *
     * @return
     * @author Jovi
     * @date 2017年6月21日下午12:07:25
     */
    String[] group() default "";

}

package cn.vonce.validator.annotation;

import java.lang.annotation.*;

/**
 * 校验bean里配置的字段
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2017年4月20日下午23:45:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface VBean {

    /**
     * 是否校验bean
     *
     * @return
     * @author Jovi
     * @date 2017年4月21日上午10:48:49
     */
    boolean value() default true;

    /**
     * 需要校验的分组
     *
     * @return
     * @author Jovi
     * @date 2017年6月21日下午12:07:25
     */
    String group() default "";

    /**
     * 校验字段遇到错误是否中断不再继续校验
     *
     * @return
     * @author Jovi
     * @date 2018年1月17日下午4:33:57
     */
    boolean interrupt() default true;

}

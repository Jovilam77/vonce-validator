package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateNotEmpty;

import java.lang.annotation.*;

/**
 * 校验不能为empty(null和空字符串)
 * 
 * @author Jovi
 * @email imjovi@qq.com
 * @version 1.0
 * @date 2017年4月20日下午6:44:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Validate(type = ValidateNotEmpty.class)
public @interface VNotEmpty {
	
	/**
	 * 字段名称
	 * 
	 * @author Jovi
	 * @date 2017年4月21日上午11:56:34
	 * @return
	 */
	String name() default "";

	/**
	 * 消息提示
	 * 
	 * @author Jovi
	 * @date 2017年4月21日上午10:49:47
	 * @return
	 */
	String value() default "";
	
	/**
	 * 分组校验
	 * 
	 * @author Jovi
	 * @date 2017年6月21日下午12:07:25
	 * @return
	 */
	String[] group() default "";

}

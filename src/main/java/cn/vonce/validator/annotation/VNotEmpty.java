package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateNotEmpty;

import java.lang.annotation.*;

/**
 * 校验字段不能为empty(null和空字符串)
 * 
 * @author jovi
 * @email 766255988@qq.com
 * @version 1.0
 * @date 2017年4月20日下午6:44:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Validate(type = ValidateNotEmpty.class)
public @interface VNotEmpty {
	
	/**
	 * 标识该字段名称 用于拼接消息提示
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午11:56:34
	 * @return
	 */
	String name() default "";

	/**
	 * 消息提示
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:49:47
	 * @return
	 */
	String value() default "字段不能为empty";
	
	/**
	 * 该字段在某分组进行校验
	 * 
	 * @author Jovi
	 * @date 2017年6月21日下午12:07:25
	 * @return
	 */
	String[] group() default "";

}

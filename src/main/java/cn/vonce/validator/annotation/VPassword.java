package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidatePassword;

import java.lang.annotation.*;

/**
 * 校验密码格式
 * 
 * @author Jovi
 * @email imjovi@qq.com
 * @version 1.0
 * @date 2017年4月23日下午3:28:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Validate(type = ValidatePassword.class)
public @interface VPassword {

	/**
	 * 字段名称
	 * 
	 * @author Jovi
	 * @date 2017年4月21日上午11:56:34
	 * @return
	 */
	String name() default "";

	/**
	 * 是否只有不为空的时候才校验
	 * @return
	 */
	boolean onlyWhenNotEmpty() default false;

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

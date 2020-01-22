package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateEqualTo;

import java.lang.annotation.*;

/**
 * 校验该字段值是否与指定的值一致(主要应用场景：确认密码与密码是否一致)
 * 
 * @author Jovi
 * @email 766255988@qq.com
 * @version 1.0
 * @date 2018年1月17日上午9:53:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Validate(type = ValidateEqualTo.class)
public @interface VEqualTo {

	/**
	 * 字段名称
	 *
	 * @author jovi
	 * @date 2018年1月17日上午9:54:50
	 * @return
	 */
	String name() default "";

	/**
	 * 消息提示
	 * 
	 * @author Jovi
	 * @date 2018年1月17日上午9:54:50
	 * @return
	 */
	String value() default "";

	/**
	 * 指定的值
	 * 
	 * @author Jovi
	 * @date 2018年1月17日上午9:55:03
	 * @return
	 */
	String val() default "";

	/**
	 * 指定的字段值，如果该值不为空优先使用该值(仅支持bean模式)
	 * 
	 * @author Jovi
	 * @date 2018年1月17日上午9:57:06
	 * @return
	 */
	String field() default "";

	/**
	 * 分组校验
	 * 
	 * @author Jovi
	 * @date 2018年1月17日上午10:24:51
	 * @return
	 */
	String[] group() default "";

}

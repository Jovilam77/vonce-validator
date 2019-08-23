package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.ValidFieldDefault;

import java.lang.annotation.*;

/**
 * 验证该字段值是否与指定的值一致(主要应用场景：确认密码与密码是否一致)
 * 
 * @author Jovi
 * @email 766255988@qq.com
 * @version 1.0
 * @date 2018年1月17日上午9:53:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Inherited
public @interface VEqualTo {

	/**
	 * 消息提示
	 * 
	 * @author Jovi
	 * @date 2018年1月17日上午9:54:50
	 * @return
	 */
	String value() default "字段值与指定的值不一致";

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
	 * 该字段在某分组进行验证
	 * 
	 * @author Jovi
	 * @date 2018年1月17日上午10:24:51
	 * @return
	 */
	String[] group() default "";

	/**
	 * 拓展类
	 * 
	 * @author Jovi
	 * @date 2018年1月17日上午10:25:16
	 * @return
	 */
	Class<?> type() default ValidFieldDefault.class;

	/**
	 * 拓展方法
	 * 
	 * @author Jovi
	 * @date 2018年1月17日上午10:25:22
	 * @return
	 */
	String method() default "validEqualTo";

}

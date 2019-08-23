package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.ValidFieldDefault;

import java.lang.annotation.*;

/**
 * 验证字段最大值
 * 
 * @author jovi
 * @email 766255988@qq.com
 * @version 1.0
 * @date 2017年4月21日上午10:47:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Inherited
public @interface VMinValue {

	/**
	 * 标识该字段名称 用于拼接消息提示
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午11:56:34
	 * @return
	 */
	String name() default "";

	/**
	 * 字段最大值
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:48:19
	 * @return
	 */
	double val();

	/**
	 * 消息提示
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:48:29
	 * @return
	 */
	String value() default "字段值小于最小值%s";

	/**
	 * 该字段在某分组进行验证
	 * 
	 * @author Jovi
	 * @date 2017年6月21日下午12:07:25
	 * @return
	 */
	String[] group() default "";

	/**
	 * 拓展类
	 * 
	 * @author jovi
	 * @date 2017年4月21日下午7:02:03
	 * @return
	 */
	Class<?> type() default ValidFieldDefault.class;

	/**
	 * 拓展方法
	 * 
	 * @author jovi
	 * @date 2017年4月21日下午7:02:13
	 * @return
	 */
	String method() default "validMinValue";

}

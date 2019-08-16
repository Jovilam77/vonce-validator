package cn.vonce.valid.annotation;

import cn.vonce.valid.rule.ValidFieldDefault;

import java.lang.annotation.*;

/**
 * 验证字段是否存在sql注入
 * 
 * @author Jovi
 * @email 766255988@qq.com
 * @version 1.0
 * @date 2018年2月28日下午8:13:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Inherited
public @interface VSQLInject {

	/**
	 * 标识该名称 用于拼接消息提示
	 * 
	 * @author Jovi
	 * @date 2018年2月28日下午8:12:48
	 * @return
	 */
	String name() default "";

	/**
	 * 消息提示
	 * 
	 * @author Jovi
	 * @date 2018年2月28日下午8:12:40
	 * @return
	 */
	String value() default "非法字段";

	/**
	 * 该字段在某分组进行验证
	 * 
	 * @author Jovi
	 * @date 2018年2月28日下午8:12:33
	 * @return
	 */
	String[] group() default "";

	/**
	 * 拓展类
	 * 
	 * @author Jovi
	 * @date 2018年2月28日下午8:12:27
	 * @return
	 */
	Class<?> type() default ValidFieldDefault.class;

	/**
	 * 拓展方法
	 * 
	 * @author Jovi
	 * @date 2018年2月28日下午8:12:20
	 * @return
	 */
	String method() default "validSQLInject";

}

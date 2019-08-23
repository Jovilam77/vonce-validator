package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.ValidFieldDefault;

import java.lang.annotation.*;

/**
 * 验证字段长度范围
 * 
 * @author jovi
 * @email 766255988@qq.com
 * @version 1.0
 * @date 2017年4月20日下午9:59:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Inherited
public @interface VRangeLength {
	
	/**
	 * 标识该字段名称 用于拼接消息提示
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午11:56:34
	 * @return
	 */
	String name() default "";

	/**
	 * 验证字段最大长度值
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:50:11
	 * @return
	 */
	int max();

	/**
	 * 验证字段最小长度值
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:50:16
	 * @return
	 */
	int min();

	/**
	 * 消息提示
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:50:21
	 * @return
	 */
	String value() default "字段长度最大为%s,最小为%s";
	
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
	String method() default "validRangeLength";

}

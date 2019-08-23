package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.ValidFieldDefault;

import java.lang.annotation.*;

/**
 * 验证字段值范围
 * 
 * @author jovi
 * @email 766255988@qq.com
 * @version 1.0
 * @date 2017年4月21日上午10:51:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Inherited
public @interface VRangeValue {

	/**
	 * 标识该字段名称 用于拼接消息提示
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午11:56:34
	 * @return
	 */
	String name() default "";

	/**
	 * 验证字段最大值
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:52:07
	 * @return
	 */
	double max();

	/**
	 * 验证字段最小值
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:52:14
	 * @return
	 */
	double min();

	/**
	 * 消息提示
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:52:20
	 * @return
	 */
	String value() default "字段值最大为%s,最小为%s";
	
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
	String method() default "validRangeValue";

}

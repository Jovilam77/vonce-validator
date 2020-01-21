package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateRangeValue;

import java.lang.annotation.*;

/**
 * 校验字段值范围
 * 
 * @author jovi
 * @email 766255988@qq.com
 * @version 1.0
 * @date 2017年4月21日上午10:51:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Validate(type = ValidateRangeValue.class)
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
	 * 校验字段最大值
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:52:07
	 * @return
	 */
	double max();

	/**
	 * 校验字段最小值
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
	 * 该字段在某分组进行校验
	 * 
	 * @author Jovi
	 * @date 2017年6月21日下午12:07:25
	 * @return
	 */
	String[] group() default "";

}

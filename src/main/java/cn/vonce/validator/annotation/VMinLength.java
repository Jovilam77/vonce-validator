package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidateMinLength;
import java.lang.annotation.*;

/**
 * 校验设置的最小长度
 * 
 * @author Jovi
 * @email imjovi@qq.com
 * @version 1.0
 * @date 2017年4月20日下午6:44:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Validate(type = ValidateMinLength.class)
public @interface VMinLength {
	
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
	 * @date 2017年4月21日上午10:49:31
	 * @return
	 */
	String value() default "";

	/**
	 * 校验字段最小长度值
	 *
	 * @author Jovi
	 * @date 2017年4月21日上午10:49:24
	 * @return
	 */
	int val();
	
	/**
	 * 分组校验
	 * 
	 * @author Jovi
	 * @date 2017年6月21日下午12:07:25
	 * @return
	 */
	String[] group() default "";

}

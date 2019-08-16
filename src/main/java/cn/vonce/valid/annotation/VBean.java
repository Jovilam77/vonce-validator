package cn.vonce.valid.annotation;

import java.lang.annotation.*;

/**
 * 验证bean里配置的字段
 * 
 * @author jovi
 * @email 766255988@qq.com
 * @version 1.0
 * @date 2017年4月20日下午23:45:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
@Inherited
public @interface VBean {

	/**
	 * 是否验证bean
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:48:49
	 * @return
	 */
	boolean value() default true;

	/**
	 * 需要验证的分组
	 * 
	 * @author Jovi
	 * @date 2017年6月21日下午12:07:25
	 * @return
	 */
	String group() default "";

	/**
	 * 验证字段遇到错误是否中断不再继续验证
	 * 
	 * @author Jovi
	 * @date 2018年1月17日下午4:33:57
	 * @return
	 */
	boolean interrupt() default true;

}

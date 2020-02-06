package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.impl.ValidatePhone;

import java.lang.annotation.*;

/**
 * 校验手机或电话格式
 * 
 * @author Jovi
 * @email imjovi@qq.com
 * @version 1.0
 * @date 2017年4月23日下午3:28:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Validate(type = ValidatePhone.class)
public @interface VPhone {

	public enum VPhoneType {
		MOBILEPHONE("手机号码"), TELEPHONE("电话号码");
		private String name;

		private VPhoneType() {

		}

		VPhoneType(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}

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
	 * @date 2017年4月21日上午10:49:41
	 * @return
	 */
	String value() default "";

	/**
	 * 校验号码类型，默认为手机号码
	 *
	 * @author Jovi
	 * @date 2017年11月3日下午5:51:52
	 * @return
	 */
	VPhoneType phoneType() default VPhoneType.MOBILEPHONE;

	/**
	 * 分组校验
	 * 
	 * @author Jovi
	 * @date 2017年6月21日下午12:07:25
	 * @return
	 */
	String[] group() default "";

}

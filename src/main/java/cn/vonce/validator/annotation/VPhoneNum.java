package cn.vonce.validator.annotation;

import cn.vonce.validator.rule.ValidFieldDefault;

import java.lang.annotation.*;

/**
 * 验证手机或电话格式
 * 
 * @author jovi
 * @email 766255988@qq.com
 * @version 1.0
 * @date 2017年4月23日下午3:28:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Inherited
public @interface VPhoneNum {

	public enum VPhoneType {
		MOBILEPHONE("手机号码"), TELEPHONE("电话号码"), CHINATELECOM("中国电信号码"), CHINAUNICOM("中国联通号码"), CHINAMOBILE("中国移动号码");
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
	 * 验证号码类型，默认为手机号码
	 * 
	 * @author Jovi
	 * @date 2017年11月3日下午5:51:52
	 * @return
	 */
	VPhoneType phoneType() default VPhoneType.MOBILEPHONE;

	/**
	 * 是否不为空时才验证
	 * @return
	 */
	boolean notEmpty() default false;

	/**
	 * 消息提示
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:49:41
	 * @return
	 */
	String value() default "号码不正确";

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
	String method() default "validPhoneNum";
}

package cn.vonce.valid.annotation;

import cn.vonce.valid.rule.ValidFieldDefault;

import java.lang.annotation.*;

/**
 * 验证中文
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
public @interface VChinese {

	/**
	 * IS_CHINESE_NOT_HAS_SYMBOL 是中文不包含符号<br>
	 * HAS_CHINESE_NOT_HAS_SYMBOL 包含中文不包含符号<br>
	 * IS_CHINESE_HAS_SYMBOL 是中文包含符号<br>
	 * HAS_CHINESE_HAS_SYMBOL 包含中文包含符号
	 * 
	 * @author jovi
	 * @email 766255988@qq.com
	 * @version 1.0
	 * @date 2017年4月24日下午3:46:25
	 */
	public static enum ChineseType {
		IS_CHINESE_NOT_HAS_SYMBOL, HAS_CHINESE_NOT_HAS_SYMBOL, IS_CHINESE_HAS_SYMBOL, HAS_CHINESE_HAS_SYMBOL
	}

	/**
	 * 标识该名称 用于拼接消息提示
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午11:56:34
	 * @return
	 */
	String name() default "";

	/**
	 * 消息提示
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:49:47
	 * @return
	 */
	String value() default "并正确中文字符串";

	/**
	 * 验证是否未中文
	 * 
	 * @author jovi
	 * @date 2017年4月21日上午10:49:41
	 * @return
	 */
	ChineseType val() default ChineseType.IS_CHINESE_NOT_HAS_SYMBOL;

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
	String method() default "validChinese";
}

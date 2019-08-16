package cn.vonce.valid.rule;

import cn.vonce.common.uitls.CheckChineseUtil;
import cn.vonce.common.uitls.IdCardUtil;
import cn.vonce.common.uitls.IdCardUtil.Result;
import cn.vonce.common.uitls.ValidatorUtil;
import cn.vonce.valid.annotation.*;
import cn.vonce.valid.helper.ValidFieldHelper;

import java.lang.reflect.Field;
import java.text.ParseException;

/**
 * 默认的验证规则
 * 
 * @author Jovi
 * @email 766255988@qq.com
 * @version 1.0
 * @date 2018年1月17日下午4:06:04
 */
public class ValidFieldDefault {

	/**
	 * 验证字段不能为null
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:11:21
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validNotNull(VNotNull valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		}
		return message;
	}

	/**
	 * 验证字段不能为empty
	 * 
	 * @author jovi
	 * @date 2017年4月20日下午5:58:05
	 * @param valid
	 * @param valueObject
	 * @param beanObject
	 * @return
	 */
	public String validNotEmpty(VNotEmpty valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				if ("".equals(valueObject.toString().trim())) {
					message = valid.value();
				}
			}
		}
		return message;
	}

	/**
	 * 验证字段长度不能超过最大值
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:06:42
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validMaxLength(VMaxLength valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				if (valid != null) {
					if (valueObject != null && valueObject.toString().trim().length() > valid.val()) {
						message = valid.value();
					}
				}
			}
		}
		return message;
	}

	/**
	 * 验证字段长度不能超过最小值
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:06:53
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validMinLength(VMinLength valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				if (valid != null) {
					if (valueObject != null && valueObject.toString().trim().length() < valid.val()) {
						message = valid.value();
					}
				}
			}
		}
		return message;
	}

	/**
	 * 验证字段长度范围
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:06:59
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validRangeLength(VRangeLength valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				if (valid != null) {
					if (valueObject != null) {
						if (valueObject.toString().trim().length() > valid.max() || valueObject.toString().trim().length() < valid.min()) {
							message = valid.value();
						}
					}
				}
			}
		}
		return message;
	}

	/**
	 * 验证字段值范围
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:07:06
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validRangeValue(VRangeValue valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型或值类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE
					|| ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.VALUE_TYPE) {
				if (valid != null) {
					if (valueObject != null) {
						double value = 0;
						try {
							value = Double.parseDouble(valueObject.toString().trim());
						} catch (NumberFormatException e) {
							// 捕获异常
							message = valid.name() + "该字段值类型不正确：" + e.getMessage();
							return message;
						}
						if (value > valid.max() || value < valid.min()) {
							message = valid.name() + String.format(valid.value(), valid.max(), valid.min());
						}
					}
				}
			}
		}
		return message;
	}

	/**
	 * 验证字段值不能超过最大值
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:07:12
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validMaxValue(VMaxValue valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型或值类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE
					|| ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.VALUE_TYPE) {
				if (valid != null) {
					if (valueObject != null) {
						double val = 0;
						try {
							val = Double.parseDouble(valueObject.toString().trim());
						} catch (NumberFormatException e) {
							// 捕获异常
							message = valid.name() + "该字段值类型不正确：" + e.getMessage();
							return message;
						}
						if (val > valid.val()) {
							message = valid.name() + String.format(valid.value(), valid.value());
						}
					}
				}
			}
		}
		return message;
	}

	/**
	 * 验证字段值不能超过最小值
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:07:18
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validMinValue(VMinValue valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型或值类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE
					|| ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.VALUE_TYPE) {
				if (valid != null) {
					double value = 0;
					try {
						value = Double.parseDouble(valueObject.toString().trim());
					} catch (NumberFormatException e) {
						// 捕获异常
						message = valid.name() + "该字段值类型不正确：" + e.getMessage();
						return message;
					}
					if (value < valid.val()) {
						message = valid.name() + String.format(valid.value(), valid.value());
					}
				}
			}
		}
		return message;
	}

	/**
	 * 验证用户名格式是否正确
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:07:24
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validUserName(VUserName valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				if (valueObject == null || !ValidatorUtil.isUsername(valueObject.toString().trim())) {
					message = valid.value();
				}
			}
		}
		return message;
	}

	/**
	 * 验证密码格式是否正确
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:07:29
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validPassword(VPassword valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				if (valueObject == null || !ValidatorUtil.isPassword(valueObject.toString().trim())) {
					message = valid.value();
				}
			}
		}
		return message;
	}

	/**
	 * 验证验证验证手机或格式是否正确
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:07:37
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validPhoneNum(VPhoneNum valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				switch (valid.phoneType()) {
				case MOBILEPHONE:
					if (!ValidatorUtil.isMobilePhone(valueObject.toString().trim())) {
						message = valid.value();
					}
					break;
				case TELEPHONE:
					if (!ValidatorUtil.isTelePhone(valueObject.toString().trim())) {
						message = valid.value();
					}
					break;
				case CHINATELECOM:
					if (!ValidatorUtil.isChinaTelecom(valueObject.toString().trim())) {
						message = valid.value();
					}
					break;
				case CHINAUNICOM:
					if (!ValidatorUtil.isChinaUnicom(valueObject.toString().trim())) {
						message = valid.value();
					}
					break;
				case CHINAMOBILE:
					if (!ValidatorUtil.isChinaMobile(valueObject.toString().trim())) {
						message = valid.value();
					}
					break;
				}
			}
		}
		return message;
	}

	/**
	 * 验证邮箱格式是否正确
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:07:42
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validEmail(VEmail valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				if (valueObject == null || !ValidatorUtil.isEmail(valueObject.toString().trim())) {
					message = valid.value();
				}
			}
		}
		return message;
	}

	/**
	 * 验证是否未中文
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:07:47
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validChinese(VChinese valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				if (valid != null) {
					// 是否为中文但不包含符号
					if (valid.val() == VChinese.ChineseType.IS_CHINESE_NOT_HAS_SYMBOL) {
						if (!CheckChineseUtil.hasChineseByRange(valueObject.toString().trim())) {
							message = valid.name() + valid.value();
						}
					} // 是否包含中文但不包含符号
					else if (valid.val() == VChinese.ChineseType.HAS_CHINESE_NOT_HAS_SYMBOL) {
						if (!CheckChineseUtil.hasChineseByRange(valueObject.toString().trim())) {
							message = valid.name() + valid.value();
						}
					}
					// 是否为中文包含符号
					else if (valid.val() == VChinese.ChineseType.IS_CHINESE_HAS_SYMBOL) {
						if (!CheckChineseUtil.hasChineseByRange(valueObject.toString().trim())) {
							message = valid.name() + valid.value();
						}
					}
					// 是否包含中文包含符号
					else if (valid.val() == VChinese.ChineseType.HAS_CHINESE_HAS_SYMBOL) {
						if (!CheckChineseUtil.hasChineseByRange(valueObject.toString().trim())) {
							message = valid.name() + valid.value();
						}
					}
				}
			}
		}
		return message;
	}

	/**
	 * 验证身份证号码格式是否正确
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:07:54
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validIDCard(VIDCard valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				Result result = null;
				try {
					result = IdCardUtil.validate(valueObject.toString().trim());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (valueObject == null || !result.isOk()) {
					message = valid.name() + result.getMessage();
				}
			}
		}
		return message;
	}

	/**
	 * 验证URl格式是否正确
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:08:01
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validUrl(VUrl valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.message();
		} else {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				if (valid.value()) {
					if (valueObject == null || !ValidatorUtil.isUrl(valueObject.toString().trim())) {
						message = valid.message();
					}
				}
			}
		}
		return message;
	}

	/**
	 * 验证IP地址格式是否正确
	 * 
	 * @author Jovi
	 * @date 2017年6月22日下午7:08:06
	 * @param valid
	 * @param valueObject
	 * @return
	 */
	public String validIPAddr(VIPAddr valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				if (!ValidatorUtil.isIPAddr(valueObject.toString().trim())) {
					message = valid.value();
				}
			}
		}
		return message;
	}

	/**
	 * 验证该字段值是否与指定的值一致
	 * 
	 * @author Jovi
	 * @date 2018年1月17日上午11:15:43
	 * @param valid
	 * @param valueObject
	 * @param beanObject
	 * @return
	 */
	public String validEqualTo(VEqualTo valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject == null) {
			message = valid.value();
		} else {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				Object contrastValue = "";
				if (beanObject != null && !valid.field().equals("")) {
					contrastValue = valid.field();
					Field field;
					try {
						field = beanObject.getClass().getDeclaredField(valid.field());
						field.setAccessible(true);
						contrastValue = field.get(beanObject);
					} catch (NoSuchFieldException e) {
						message = e.getMessage();
					} catch (SecurityException e) {
						message = e.getMessage();
					} catch (IllegalArgumentException e) {
						message = e.getMessage();
					} catch (IllegalAccessException e) {
						message = e.getMessage();
					}
				} else {
					contrastValue = valid.val();
				}
				if (!valueObject.equals(contrastValue)) {
					message = valid.value();
				}

			}
		}
		return message;
	}
	
	public String validSQLInject(VSQLInject valid, Object valueObject, Object beanObject) {
		String message = null;
		if (valueObject != null) {
			// 字符串类型可以进行验证
			if (ValidFieldHelper.whatIsType(valueObject.getClass().getSimpleName()) == ValidFieldHelper.WhatIsType.STRING_TYPE) {
				if (!ValidatorUtil.isSQLInject(valueObject.toString())) {
					message = valid.value();
				}
			}
		}
		return message;
	}

}

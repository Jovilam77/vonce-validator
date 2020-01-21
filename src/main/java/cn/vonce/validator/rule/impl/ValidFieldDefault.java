//package cn.vonce.validator.rule;
//
//import cn.vonce.common.utils.CheckChineseUtil;
//import cn.vonce.common.utils.IdCardUtil;
//import cn.vonce.common.utils.ValidatorUtil;
//import cn.vonce.validator.annotation.*;
//import cn.vonce.validator.helper.ValidFieldHelper;
//import cn.vonce.validator.model.Result;
//import cn.vonce.validator.utils.ValidFieldUtil;
//import java.lang.reflect.Field;
//import java.text.ParseException;
//
///**
// * 默认的校验规则
// *
// * @author Jovi
// * @version 1.0
// * @email 766255988@qq.com
// * @date 2018年1月17日下午4:06:04
// */
//public class ValidFieldDefault {
//
//    /**
//     * 校验字段不能为null
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:11:21
//     */
//    public Result validNotNull(VNotNull valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (fieldInfo.getValue() == null) {
//            result.setPass(false);
//           result.setMessage(valid.value());
//        }
//        return result;
//    }
//
//    /**
//     * 校验字段不能为empty
//     *
//     * @param valid
//     * @param fieldValue
//     * @param beanObject
//     * @return
//     * @author jovi
//     * @date 2017年4月20日下午5:58:05
//     */
//    public Result validonlyWhenNotEmpty(VonlyWhenNotEmpty valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (fieldInfo.getValue() == null) {
//            result.setPass(false);
//           result.setMessage(valid.value());
//        } else {
//            // 字符串类型可以进行校验
//            if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                if ("".equals(fieldInfo.getValue().toString())) {
//                    result.setPass(false);
//                   result.setMessage(valid.value());
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验字段长度不能超过最大值
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:06:42
//     */
//    public Result validMaxLength(VMaxLength valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
//            if (fieldInfo.getValue() == null) {
//                result.setPass(false);
//               result.setMessage(valid.value());
//            } else {
//                // 字符串类型可以进行校验
//                if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                    if (valid != null) {
//                        if (fieldInfo.getValue().toString().length() > valid.val()) {
//                            result.setPass(false);
//                           result.setMessage(valid.value());
//                        }
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验字段长度不能超过最小值
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:06:53
//     */
//    public Result validMinLength(VMinLength valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
//            if (fieldInfo.getValue() == null) {
//                result.setPass(false);
//               result.setMessage(valid.value());
//            } else {
//                // 字符串类型可以进行校验
//                if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                    if (valid != null) {
//                        if (fieldInfo.getValue().toString().length() < valid.val()) {
//                            result.setPass(false);
//                           result.setMessage(valid.value());
//                        }
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验字段长度范围
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:06:59
//     */
//    public Result validRangeLength(VRangeLength valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
//            if (fieldInfo.getValue() == null) {
//                result.setPass(false);
//               result.setMessage(valid.value());
//            } else {
//                // 字符串类型可以进行校验
//                if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                    if (valid != null) {
//                        if (fieldValue != null) {
//                            if (fieldInfo.getValue().toString().length() > valid.max() || fieldInfo.getValue().toString().length() < valid.min()) {
//                                result.setPass(false);
//                               result.setMessage(valid.value());
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验字段值范围
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:07:06
//     */
//    public Result validRangeValue(VRangeValue valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (fieldInfo.getValue() == null) {
//            result.setPass(false);
//           result.setMessage(valid.value());
//        } else {
//            // 字符串类型或值类型可以进行校验
//            if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE
//                    || ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.VALUE_TYPE) {
//                if (valid != null) {
//                    if (fieldValue != null) {
//                        double value = 0;
//                        try {
//                            value = Double.parseDouble(fieldInfo.getValue().toString());
//                        } catch (NumberFormatException e) {
//                            // 捕获异常
//                            result.setPass(false);
//                           result.setMessage(valid.name() + "该字段值类型不正确：" + e.getMessage());
//                            return result;
//                        }
//                        if (value > valid.max() || value < valid.min()) {
//                            result.setPass(false);
//                           result.setMessage(valid.name() + String.format(valid.value(), valid.max(), valid.min()));
//                        }
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验字段值不能超过最大值
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:07:12
//     */
//    public Result validMaxValue(VMaxValue valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (fieldInfo.getValue() == null) {
//            result.setPass(false);
//           result.setMessage(valid.value());
//        } else {
//            // 字符串类型或值类型可以进行校验
//            if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE
//                    || ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.VALUE_TYPE) {
//                if (valid != null) {
//                    if (fieldValue != null) {
//                        double val = 0;
//                        try {
//                            val = Double.parseDouble(fieldInfo.getValue().toString());
//                        } catch (NumberFormatException e) {
//                            // 捕获异常
//                            result.setPass(false);
//                           result.setMessage(valid.name() + "该字段值类型不正确：" + e.getMessage());
//                            return result;
//                        }
//                        if (val > valid.val()) {
//                            result.setPass(false);
//                           result.setMessage(valid.name() + String.format(valid.value(), valid.value()));
//                        }
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验字段值不能超过最小值
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:07:18
//     */
//    public Result validMinValue(VMinValue valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (fieldInfo.getValue() == null) {
//            result.setPass(false);
//           result.setMessage(valid.value());
//        } else {
//            // 字符串类型或值类型可以进行校验
//            if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE
//                    || ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.VALUE_TYPE) {
//                if (valid != null) {
//                    double value = 0;
//                    try {
//                        value = Double.parseDouble(fieldInfo.getValue().toString());
//                    } catch (NumberFormatException e) {
//                        // 捕获异常
//                        result.setPass(false);
//                       result.setMessage(valid.name() + "该字段值类型不正确：" + e.getMessage());
//                        return result;
//                    }
//                    if (value < valid.val()) {
//                        result.setPass(false);
//                       result.setMessage(valid.name() + String.format(valid.value(), valid.value()));
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验用户名格式是否正确
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:07:24
//     */
//    public Result validUserName(VUserName valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
//            if (fieldInfo.getValue() == null) {
//                result.setPass(false);
//               result.setMessage(valid.value());
//            } else {
//                // 字符串类型可以进行校验
//                if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                    if (fieldValue == null || !ValidatorUtil.isUsername(fieldInfo.getValue().toString())) {
//                        result.setPass(false);
//                       result.setMessage(valid.value());
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验密码格式是否正确
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:07:29
//     */
//    public Result validPassword(VPassword valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
//            if (fieldInfo.getValue() == null) {
//                result.setPass(false);
//               result.setMessage(valid.value());
//            } else {
//                // 字符串类型可以进行校验
//                if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                    if (fieldValue == null || !ValidatorUtil.isPassword(fieldInfo.getValue().toString())) {
//                        result.setPass(false);
//                       result.setMessage(valid.value());
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验手机或格式是否正确
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:07:37
//     */
//    public Result validPhoneNum(VPhoneNum valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
//            if (fieldInfo.getValue() == null) {
//                result.setPass(false);
//               result.setMessage(valid.value());
//            } else {
//                // 字符串类型可以进行校验
//                if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                    switch (valid.phoneType()) {
//                        case MOBILEPHONE:
//                            if (!ValidatorUtil.isMobilePhone(fieldInfo.getValue().toString())) {
//                                result.setPass(false);
//                               result.setMessage(valid.value());
//                            }
//                            break;
//                        case TELEPHONE:
//                            if (!ValidatorUtil.isTelePhone(fieldInfo.getValue().toString())) {
//                                result.setPass(false);
//                               result.setMessage(valid.value());
//                            }
//                            break;
//                        case CHINATELECOM:
//                            if (!ValidatorUtil.isChinaTelecom(fieldInfo.getValue().toString())) {
//                                result.setPass(false);
//                               result.setMessage(valid.value());
//                            }
//                            break;
//                        case CHINAUNICOM:
//                            if (!ValidatorUtil.isChinaUnicom(fieldInfo.getValue().toString())) {
//                                result.setPass(false);
//                               result.setMessage(valid.value());
//                            }
//                            break;
//                        case CHINAMOBILE:
//                            if (!ValidatorUtil.isChinaMobile(fieldInfo.getValue().toString())) {
//                                result.setPass(false);
//                               result.setMessage(valid.value());
//                            }
//                            break;
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验邮箱格式是否正确
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:07:42
//     */
//    public Result validEmail(VEmail valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
//            if (fieldInfo.getValue() == null) {
//                result.setPass(false);
//               result.setMessage(valid.value());
//            } else {
//                // 字符串类型可以进行校验
//                if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                    if (fieldValue == null || !ValidatorUtil.isEmail(fieldInfo.getValue().toString())) {
//                        result.setPass(false);
//                       result.setMessage(valid.value());
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验是否未中文
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:07:47
//     */
//    public Result validChinese(VChinese valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
//            if (fieldInfo.getValue() == null) {
//                result.setPass(false);
//               result.setMessage(valid.value());
//            } else {
//                // 字符串类型可以进行校验
//                if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                    if (valid != null) {
//                        // 是否为中文但不包含符号
//                        if (valid.val() == VChinese.ChineseType.IS_CHINESE_NOT_HAS_SYMBOL) {
//                            if (!CheckChineseUtil.hasChineseByRange(fieldInfo.getValue().toString())) {
//                                result.setPass(false);
//                               result.setMessage(valid.name() + valid.value());
//                            }
//                        } // 是否包含中文但不包含符号
//                        else if (valid.val() == VChinese.ChineseType.HAS_CHINESE_NOT_HAS_SYMBOL) {
//                            if (!CheckChineseUtil.hasChineseByRange(fieldInfo.getValue().toString())) {
//                                result.setPass(false);
//                               result.setMessage(valid.name() + valid.value());
//                            }
//                        }
//                        // 是否为中文包含符号
//                        else if (valid.val() == VChinese.ChineseType.IS_CHINESE_HAS_SYMBOL) {
//                            if (!CheckChineseUtil.hasChineseByRange(fieldInfo.getValue().toString())) {
//                                result.setPass(false);
//                               result.setMessage(valid.name() + valid.value());
//                            }
//                        }
//                        // 是否包含中文包含符号
//                        else if (valid.val() == VChinese.ChineseType.HAS_CHINESE_HAS_SYMBOL) {
//                            if (!CheckChineseUtil.hasChineseByRange(fieldInfo.getValue().toString())) {
//                                result.setPass(false);
//                               result.setMessage(valid.name() + valid.value());
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验身份证号码格式是否正确
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:07:54
//     */
//    public Result validIDCard(VIDCard valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
//            if (fieldInfo.getValue() == null) {
//                result.setPass(false);
//               result.setMessage(valid.value());
//            } else {
//                // 字符串类型可以进行校验
//                if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                    cn.vonce.common.utils.IdCardUtil.Result idCardResult = null;
//                    try {
//                        idCardResult = IdCardUtil.validate(fieldInfo.getValue().toString());
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    if (fieldValue == null || !idCardResult.isOk()) {
//                        result.setPass(false);
//                       result.setMessage(valid.name() + idCardResult.getMessage());
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验URl格式是否正确
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:08:01
//     */
//    public Result validUrl(VUrl valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
//            if (fieldInfo.getValue() == null) {
//                result.setPass(false);
//               result.setMessage(valid.value());
//            } else {
//                // 字符串类型可以进行校验
//                if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                    if (valid.val()) {
//                        if (fieldValue == null || !ValidatorUtil.isUrl(fieldInfo.getValue().toString())) {
//                            result.setPass(false);
//                           result.setMessage(valid.value());
//                        }
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验IP地址格式是否正确
//     *
//     * @param valid
//     * @param fieldValue
//     * @return
//     * @author Jovi
//     * @date 2017年6月22日下午7:08:06
//     */
//    public Result validIPAddr(VIPAddress valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
//            if (fieldInfo.getValue() == null) {
//                result.setPass(false);
//               result.setMessage(valid.value());
//            } else {
//                // 字符串类型可以进行校验
//                if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                    if (!ValidatorUtil.isIPAddr(fieldInfo.getValue().toString())) {
//                        result.setPass(false);
//                       result.setMessage(valid.value());
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验该字段值是否与指定的值一致
//     *
//     * @param valid
//     * @param fieldValue
//     * @param beanObject
//     * @return
//     * @author Jovi
//     * @date 2018年1月17日上午11:15:43
//     */
//    public Result validEqualTo(VEqualTo valid, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (fieldInfo.getValue() == null) {
//            result.setPass(false);
//           result.setMessage(valid.value());
//        } else {
//            // 字符串类型可以进行校验
//            if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                Object contrastValue = "";
//                if (beanObject != null && !valid.field().equals("")) {
//                    contrastValue = valid.field();
//                    Field field;
//                    try {
//                        field = beanObject.getClass().getDeclaredField(valid.field());
//                        field.setAccessible(true);
//                        contrastValue = field.get(beanObject);
//                    } catch (NoSuchFieldException e) {
//                        result.setPass(false);
//                       result.setMessage(e.getMessage());
//                    } catch (SecurityException e) {
//                        result.setPass(false);
//                       result.setMessage(e.getMessage());
//                    } catch (IllegalArgumentException e) {
//                        result.setPass(false);
//                       result.setMessage(e.getMessage());
//                    } catch (IllegalAccessException e) {
//                        result.setPass(false);
//                       result.setMessage(e.getMessage());
//                    }
//                } else {
//                    contrastValue = valid.val();
//                }
//                if (!fieldInfo.getValue().equals(contrastValue)) {
//                    result.setPass(false);
//                   result.setMessage(valid.value());
//                }
//
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验sql注入
//     *
//     * @param vsqlInject
//     * @param fieldValue
//     * @param beanObject
//     * @return
//     */
//    public Result validSQLInject(VSQLInject vsqlInject, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (ValidFieldUtil.isNeedValidation(vsqlInject.onlyWhenNotEmpty(), fieldInfo.getValue())) {
//            if (fieldValue != null) {
//                // 字符串类型可以进行校验
//                if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
//                    if (!ValidatorUtil.isSQLInject(fieldInfo.getValue().toString())) {
//                        result.setPass(false);
//                       result.setMessage(vsqlInject.value());
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 校验布尔值
//     *
//     * @param vBoolean
//     * @param fieldValue
//     * @param beanObject
//     * @return
//     */
//    public Result vBoolean(VBoolean vBoolean, String fieldName, Object fieldValue, Object beanObject) {
//        Result result = new Result(true);
//        if (fieldValue != null) {
//            ValidFieldHelper.WhatType whatType = ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
//            if (whatType == ValidFieldHelper.WhatType.STRING_TYPE || whatType == ValidFieldHelper.WhatType.BOOL_TYPE) {
//                if (!fieldInfo.getValue().equals(vBoolean.val())) {
//                    result.setPass(false);
//                   result.setMessage(vBoolean.value());
//                }
//            }
//        }
//        return result;
//    }
//
//}

package cn.vonce.validator.utils;

import cn.vonce.common.utils.StringUtil;
import cn.vonce.validator.helper.WhatType;

/**
 * 校验字段工具类
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2019/9/12 16:11
 */
public class ValidatorUtil {

    /**
     * 是否只有不为空的时候才校验
     *
     * @param onlyWhenNotEmpty
     * @param fieldValue
     * @return
     */
    public static boolean isNeedValidation(boolean onlyWhenNotEmpty, Object fieldValue) {
        if (onlyWhenNotEmpty) {
            if (StringUtil.isNotEmpty(fieldValue)) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * 获取字段名称
     *
     * @param fieldName
     * @param annotationName
     * @return
     */
    public static String getFieldName(String fieldName, String annotationName) {
        return StringUtil.isEmpty(annotationName) ? fieldName : annotationName;
    }

    /**
     * 获取类型名称
     *
     * @param type
     * @return
     */
    public static String getTypeName(WhatType type) {
        switch (type) {
            case STRING_TYPE:
                return "字符";
            case BOOL_TYPE:
                return "布尔";
            case VALUE_TYPE:
                return "数值";
            case DATE_TYPE:
                return "日期";
            case OTHER_TYPE:
                return "非字符、布尔、数值、日期";
        }
        return null;
    }

    /**
     * 获取字段错误提示
     *
     * @param fieldName
     * @param annotationTips
     * @param defaultTips
     * @return
     */
    public static String getTips(String fieldName, String annotationTips, String defaultTips) {
        if (StringUtil.isEmpty(annotationTips)) {
            return String.format("'%s'必须是%s", fieldName, defaultTips);
        } else {
            return annotationTips;
        }
    }

    /**
     * 获取null错误信息
     *
     * @return
     */
    public static String getNullError() {
        return "等于null";
    }

    /**
     * 获取类型错误信息
     *
     * @param type
     * @return
     */
    public static String getTypeError(WhatType[] type) {
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < type.length; i++) {
            stringbuffer.append(getTypeName(type[i]));
            if (i < type.length - 1) {
                stringbuffer.append(",");
            }
        }
        return String.format("仅支持%s类型校验", stringbuffer.toString());
    }

    /**
     * 获取预期错误信息
     *
     * @param anticipate
     * @return
     */
    public static String getAnticipateError(String anticipate) {
        return String.format("不符合%s的预期", anticipate);
    }

    /**
     * <p>Checks whether the String a valid Java number.</p>
     *
     * <p>Valid numbers include hexadecimal marked with the <code>0x</code>
     * qualifier, scientific notation and numbers marked with a type
     * qualifier (e.g. 123L).</p>
     *
     * <p><code>Null</code> and empty String will return
     * <code>false</code>.</p>
     *
     * @param str  the <code>String</code> to check
     * @return <code>true</code> if the string is a correctly formatted number
     */
    public static boolean isNumber(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        }
        char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        // deal with any possible sign up front
        int start = (chars[0] == '-') ? 1 : 0;
        if (sz > start + 1) {
            if (chars[start] == '0' && chars[start + 1] == 'x') {
                int i = start + 2;
                if (i == sz) {
                    return false; // str == "0x"
                }
                // checking hex (it can't be anything else)
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9')
                            && (chars[i] < 'a' || chars[i] > 'f')
                            && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--; // don't want to loop to the last char, check it afterwords
        // for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                // no type qualifier, OK
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                // can't have an E at the last byte
                return false;
            }
            if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                // single trailing decimal point after non-exponent is ok
                return foundDigit;
            }
            if (!allowSigns
                    && (chars[i] == 'd'
                    || chars[i] == 'D'
                    || chars[i] == 'f'
                    || chars[i] == 'F')) {
                return foundDigit;
            }
            if (chars[i] == 'l'
                    || chars[i] == 'L') {
                // not allowing L with an exponent
                return foundDigit && !hasExp;
            }
            // last character is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
        return !allowSigns && foundDigit;
    }

}

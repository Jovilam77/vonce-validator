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
            }
        } else {
            return true;
        }
        return false;
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
                return "字符串";
            case BOOL_TYPE:
                return "布尔";
            case VALUE_TYPE:
                return "数值";
            case DATE_TYPE:
                return "日期";
            case OBJECT_TYPE:
                return "对象";
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

}

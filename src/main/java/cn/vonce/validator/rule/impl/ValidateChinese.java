package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.CheckChineseUtil;
import cn.vonce.validator.annotation.VChinese;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.ValidateString;

/**
 * 校验中文
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:24
 */
public class ValidateChinese extends ValidateString<VChinese> /*implements ValidateRule<VChinese>*/ {

    @Override
    public String getAnticipate(VChinese valid) {
        String anticipate = "'中文汉字不包含中文符号的字符串'";
        if (valid.val() == VChinese.ChineseType.HAS_CHINESE_NOT_HAS_SYMBOL) {
            anticipate = "'包含中文汉字但不包含中文符号的字符串'";
        } else if (valid.val() == VChinese.ChineseType.IS_CHINESE_HAS_SYMBOL) {
            anticipate = "'中文汉字或者中文符号的字符串'";
        } else if (valid.val() == VChinese.ChineseType.HAS_CHINESE_HAS_SYMBOL) {
            anticipate = "'包含中文汉字或者中文符号的字符串'";
        }
        return anticipate;
    }

    @Override
    public boolean check(VChinese valid, FieldInfo fieldInfo) {
        switch (valid.val()) {
            case IS_CHINESE_NOT_HAS_SYMBOL:
                if (!CheckChineseUtil.isChineseByRange(fieldInfo.getValue().toString())) {
                    return false;
                }
                break;
            case HAS_CHINESE_NOT_HAS_SYMBOL:
                if (!CheckChineseUtil.hasChineseByRange(fieldInfo.getValue().toString())) {
                    return false;
                }
                break;
            case IS_CHINESE_HAS_SYMBOL:
                if (!CheckChineseUtil.isChinese(fieldInfo.getValue().toString())) {
                    return false;
                }
                break;
            case HAS_CHINESE_HAS_SYMBOL:
                if (!CheckChineseUtil.hasChinese(fieldInfo.getValue().toString())) {
                    return false;
                }
                break;
        }
        return true;
    }

    /*@Override
    public FieldResult handle(VChinese valid, FieldInfo fieldInfo) {
        String anticipate = "'中文汉字不包含中文符号的字符串'";
        if (valid.val() == VChinese.ChineseType.HAS_CHINESE_NOT_HAS_SYMBOL) {
            anticipate = "'包含中文汉字但不包含中文符号的字符串'";
        } else if (valid.val() == VChinese.ChineseType.IS_CHINESE_HAS_SYMBOL) {
            anticipate = "'中文汉字或者中文符号的字符串'";
        } else if (valid.val() == VChinese.ChineseType.HAS_CHINESE_HAS_SYMBOL) {
            anticipate = "'包含中文汉字或者中文符号的字符串'";
        }
        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), valid.value(), "必须是" + anticipate);
        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
            if (fieldInfo.getValue() == null) {
                return new FieldResult(fieldInfo.getName(), tips, "等于null");
            }
            if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) != ValidFieldHelper.WhatType.STRING_TYPE) {
                return new FieldResult(fieldInfo.getName(), tips, "仅支持String类型校验");
            }
            switch (valid.val()) {
                case IS_CHINESE_NOT_HAS_SYMBOL:
                    if (!CheckChineseUtil.isChineseByRange(fieldInfo.getValue().toString())) {
                        return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
                    }
                    break;
                case HAS_CHINESE_NOT_HAS_SYMBOL:
                    if (!CheckChineseUtil.hasChineseByRange(fieldInfo.getValue().toString())) {
                        return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
                    }
                    break;
                case IS_CHINESE_HAS_SYMBOL:
                    if (!CheckChineseUtil.isChinese(fieldInfo.getValue().toString())) {
                        return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
                    }
                    break;
                case HAS_CHINESE_HAS_SYMBOL:
                    if (!CheckChineseUtil.hasChinese(fieldInfo.getValue().toString())) {
                        return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
                    }
                    break;
            }
        }
        return new FieldResult(true, fieldInfo.getName());
    }*/

}

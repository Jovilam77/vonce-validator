package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.CheckChineseUtil;
import cn.vonce.validator.annotation.VChinese;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验中文
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:24
 */
public class ValidateChinese extends AbstractValidate<VChinese> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE};
    }

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

}

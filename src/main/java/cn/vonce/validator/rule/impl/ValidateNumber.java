package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VNumber;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;
import cn.vonce.validator.utils.ValidatorUtil;

/**
 * 校验数字
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 16:46
 */
public class ValidateNumber extends AbstractValidate<VNumber> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE};
    }

    @Override
    public String getAnticipate(VNumber valid) {
        String anticipate = "";
        if (valid.val() == VNumber.NumType.NUMBER) {
            anticipate = "'数字类型'";
        } else if (valid.val() == VNumber.NumType.INTEGER) {
            anticipate = "'整数类型'";
        } else if (valid.val() == VNumber.NumType.FLOAT) {
            anticipate = "'浮点类型'";
        }
        return anticipate;
    }

    @Override
    public boolean check(VNumber valid, FieldInfo fieldInfo) {
        int index = fieldInfo.getValue().toString().indexOf(".");
        switch (valid.val()) {
            case NUMBER:
                if (!ValidatorUtil.isNumber(fieldInfo.getValue().toString())) {
                    return false;
                }
                break;
            case INTEGER:
                if (!ValidatorUtil.isNumber(fieldInfo.getValue().toString()) || (index > -1 && index < fieldInfo.getValue().toString().length() - 1)) {
                    return false;
                }
                break;
            case FLOAT:
                if (!ValidatorUtil.isNumber(fieldInfo.getValue().toString()) || (index == -1 || index == fieldInfo.getValue().toString().length() - 1)) {
                    return false;
                }
                break;
        }
        return true;
    }

}

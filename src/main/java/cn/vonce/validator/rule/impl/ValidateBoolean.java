package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VBoolean;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验布尔值
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:15
 */
public class ValidateBoolean extends AbstractValidate<VBoolean> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE, WhatType.BOOL_TYPE};
    }

    @Override
    public String getAnticipate(VBoolean valid) {
        String anticipate = "'布尔类型'";
        if (valid.val() == VBoolean.BoolValue.TRUE) {
            anticipate = "'布尔值true'";
        } else if (valid.val() == VBoolean.BoolValue.FALSE) {
            anticipate = "'布尔值false'";
        }
        return anticipate;
    }

    @Override
    public boolean check(VBoolean valid, FieldInfo fieldInfo) {
        switch (valid.val()) {
            case NORMAL:
                if (!fieldInfo.getValue().toString().equals("true") && !fieldInfo.getValue().toString().equals("false")) {
                    return false;
                }
                break;
            case TRUE:
                if (!fieldInfo.getValue().toString().equals("true")) {
                    return false;
                }
                break;
            case FALSE:
                if (!fieldInfo.getValue().toString().equals("false")) {
                    return false;
                }
                break;
        }
        return true;
    }

}

package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.ValidatorUtil;
import cn.vonce.validator.annotation.VPassword;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验密码
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:27
 */
public class ValidatePassword extends AbstractValidate<VPassword> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE};
    }

    @Override
    public String getAnticipate(VPassword valid) {
        return "'标准密码格式'";
    }

    @Override
    public boolean check(VPassword valid, FieldInfo fieldInfo) {
        if (!ValidatorUtil.isPassword(fieldInfo.getValue().toString())) {
            return false;
        }
        return true;
    }

}

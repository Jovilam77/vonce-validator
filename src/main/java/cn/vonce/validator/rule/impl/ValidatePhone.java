package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.ValidatorUtil;
import cn.vonce.validator.annotation.VPhone;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验手机号码
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:25
 */
public class ValidatePhone extends AbstractValidate<VPhone> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE};
    }

    @Override
    public String getAnticipate(VPhone valid) {
        if (valid.phoneType() == VPhone.VPhoneType.TELEPHONE) {
            return "'标准电话号码格式'";
        }
        return "'标准手机号码格式'";
    }

    @Override
    public boolean check(VPhone valid, FieldInfo fieldInfo) {
        switch (valid.phoneType()) {
            case MOBILEPHONE:
                if (!ValidatorUtil.isMobilePhone(fieldInfo.getValue().toString())) {
                    return false;
                }
                break;
            case TELEPHONE:
                if (!ValidatorUtil.isTelePhone(fieldInfo.getValue().toString())) {
                    return false;
                }
                break;
        }
        return true;
    }

}

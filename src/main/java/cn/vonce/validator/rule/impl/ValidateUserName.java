package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.ValidatorUtil;
import cn.vonce.validator.annotation.VUserName;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验用户名
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:28
 */
public class ValidateUserName extends AbstractValidate<VUserName> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE};
    }

    @Override
    public String getAnticipate(VUserName valid) {
        return "'标准用户名格式'";
    }

    @Override
    public boolean check(VUserName valid, FieldInfo fieldInfo) {
        if (!ValidatorUtil.isUsername(fieldInfo.getValue().toString())) {
            return false;
        }
        return true;
    }

}

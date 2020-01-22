package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.ValidatorUtil;
import cn.vonce.validator.annotation.VIPAddress;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验IP地址
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:21
 */
public class ValidateIPAddress extends AbstractValidate<VIPAddress> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE};
    }

    @Override
    public String getAnticipate(VIPAddress valid) {
        return "'标准ip地址格式'";
    }

    @Override
    public boolean check(VIPAddress valid, FieldInfo fieldInfo) {
        if (!ValidatorUtil.isIPAddress(fieldInfo.getValue().toString())) {
            return false;
        }
        return true;
    }

}

package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.IdCardUtil;
import cn.vonce.validator.annotation.VIDCard;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验身份证号码
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:23
 */
public class ValidateIDCard extends AbstractValidate<VIDCard> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE};
    }

    @Override
    public String getAnticipate(VIDCard valid) {
        return "'标准身份证号码格式'";
    }

    @Override
    public boolean check(VIDCard valid, FieldInfo fieldInfo) {
        IdCardUtil.Result idCardResult = IdCardUtil.validate(fieldInfo.getValue().toString());
        if (!idCardResult.isOk()) {
            return false;
        }
        return true;
    }

}

package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VMinLength;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验字段设置的最小长度
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:32
 */
public class ValidateMinLength extends AbstractValidate<VMinLength> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE};
    }

    @Override
    public String getAnticipate(VMinLength valid) {
        return String.format("'设置的最小长度%d'", valid.val());
    }

    @Override
    public boolean check(VMinLength valid, FieldInfo fieldInfo) {
        if (fieldInfo.getValue().toString().length() < valid.val()) {
            return false;
        }
        return true;
    }

}

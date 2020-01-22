package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VRangeLength;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验字段设置的长度范围
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:31
 */
public class ValidateRangeLength extends AbstractValidate<VRangeLength> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE};
    }

    @Override
    public String getAnticipate(VRangeLength valid) {
        return String.format("'设置的长度范围max{%d},min{%d}'", valid.max(), valid.min());
    }

    @Override
    public boolean check(VRangeLength valid, FieldInfo fieldInfo) {
        if (fieldInfo.getValue().toString().length() > valid.max() || fieldInfo.getValue().toString().length() < valid.min()) {
            return false;
        }
        return true;
    }

}

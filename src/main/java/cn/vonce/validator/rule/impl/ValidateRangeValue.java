package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VRangeValue;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;
import cn.vonce.validator.utils.ValidatorUtil;

/**
 * 校验字段设置的值范围
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:30
 */
public class ValidateRangeValue extends AbstractValidate<VRangeValue> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE, WhatType.VALUE_TYPE};
    }

    @Override
    public String getAnticipate(VRangeValue valid) {
        return String.format("'设置的值范围max{%f},min{%f}'", valid.max(), valid.min());
    }

    @Override
    public boolean check(VRangeValue valid, FieldInfo fieldInfo) {
        if (!ValidatorUtil.isNumber(fieldInfo.getValue().toString())) {
            return false;
        }
        double value = Double.parseDouble(fieldInfo.getValue().toString());
        if (value > valid.max() || value < valid.min()) {
            return false;
        }
        return true;
    }

}

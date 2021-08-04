package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VMinValue;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;
import cn.vonce.validator.utils.ValidatorUtil;

/**
 * 校验字段设置的最小值
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:28
 */
public class ValidateMinValue extends AbstractValidate<VMinValue> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE, WhatType.VALUE_TYPE};
    }

    @Override
    public String getAnticipate(VMinValue valid) {
        return String.format("'设置的最小值%f'", valid.val());
    }

    @Override
    public boolean check(VMinValue valid, FieldInfo fieldInfo) {
        if (!ValidatorUtil.isNumber(fieldInfo.getValue().toString())) {
            return false;
        }
        double value = Double.parseDouble(fieldInfo.getValue().toString());
        if (value < valid.val()) {
            return false;
        }
        return true;
    }

}

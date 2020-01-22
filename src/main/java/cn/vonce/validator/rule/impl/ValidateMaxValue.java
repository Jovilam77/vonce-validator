package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VMaxValue;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 校验字段设置的最大值
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:29
 */
public class ValidateMaxValue extends AbstractValidate<VMaxValue> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE, WhatType.VALUE_TYPE};
    }

    @Override
    public String getAnticipate(VMaxValue valid) {
        return String.format("'设置的最大值%f'", valid.val());
    }

    @Override
    public boolean check(VMaxValue valid, FieldInfo fieldInfo) {
        if (!NumberUtils.isNumber(fieldInfo.getValue().toString())) {
            return false;
        }
        double value = Double.parseDouble(fieldInfo.getValue().toString());
        if (value > valid.val()) {
            return false;
        }
        return true;
    }

}

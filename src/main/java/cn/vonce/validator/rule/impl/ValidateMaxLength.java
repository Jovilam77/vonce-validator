package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VMaxLength;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验字段设置的最大长度
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:32
 */
public class ValidateMaxLength extends AbstractValidate<VMaxLength> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE};
    }

    @Override
    public String getAnticipate(VMaxLength valid) {
        return String.format("'设置的最大长度%d'", valid.val());
    }

    @Override
    public boolean check(VMaxLength valid, FieldInfo fieldInfo) {
        if (fieldInfo.getValue().toString().length() > valid.val()) {
            return false;
        }
        return true;
    }

}

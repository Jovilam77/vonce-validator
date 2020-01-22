package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VNotNull;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验字段不能为null
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:34
 */
public class ValidateNotNull extends AbstractValidate<VNotNull> {

    @Override
    public WhatType[] type() {
        return null;
    }

    @Override
    public String getAnticipate(VNotNull valid) {
        return "'不能为null'";
    }

    @Override
    public boolean check(VNotNull valid, FieldInfo fieldInfo) {
        return false;
    }

}

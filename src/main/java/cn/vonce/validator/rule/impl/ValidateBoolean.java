package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VBoolean;
import cn.vonce.validator.helper.ValidFieldHelper;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.rule.ValidateRule;
import cn.vonce.validator.utils.ValidFieldUtil;

/**
 * 校验布尔值
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:15
 */
public class ValidateBoolean implements ValidateRule<VBoolean> {

    @Override
    public FieldResult handle(VBoolean valid, FieldInfo fieldInfo) {
        String anticipate = "'布尔类型'";
        if (valid.val() == VBoolean.BoolValue.TRUE) {
            anticipate = "'布尔值true'";
        } else if (valid.val() == VBoolean.BoolValue.FALSE) {
            anticipate = "'布尔值false'";
        }
        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), valid.value(), anticipate);
        if (fieldInfo.getValue() == null) {
            return new FieldResult(fieldInfo.getName(), tips, "等于null");
        }
        ValidFieldHelper.WhatType whatType = ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
        if (whatType != ValidFieldHelper.WhatType.STRING_TYPE && whatType != ValidFieldHelper.WhatType.BOOL_TYPE) {
            return new FieldResult(fieldInfo.getName(), tips, "仅支持Boolean和String类型校验");
        }
        switch (valid.val()) {
            case NORMAL:
                if (!fieldInfo.getValue().toString().equals("true") && !fieldInfo.getValue().toString().equals("false")) {
                    return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
                }
                break;
            case TRUE:
                if (!fieldInfo.getValue().toString().equals("true")) {
                    return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
                }
                break;
            case FALSE:
                if (!fieldInfo.getValue().toString().equals("false")) {
                    return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
                }
                break;
        }
        return new FieldResult(true, fieldInfo.getName());
    }
}

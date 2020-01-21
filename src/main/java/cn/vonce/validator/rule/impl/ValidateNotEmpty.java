package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VNotEmpty;
import cn.vonce.validator.helper.ValidFieldHelper;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.rule.ValidateRule;
import cn.vonce.validator.utils.ValidFieldUtil;

/**
 * 校验字段不能为empty
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:33
 */
public class ValidateNotEmpty implements ValidateRule<VNotEmpty> {


    @Override
    public FieldResult handle(VNotEmpty valid, FieldInfo fieldInfo) {
        String anticipate = "'不能为empty'";
        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), valid.value(), anticipate);
        if (fieldInfo.getValue() == null) {
            return new FieldResult(fieldInfo.getName(), tips, "等于null");
        }
        ValidFieldHelper.WhatType whatType = ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
        if (whatType != ValidFieldHelper.WhatType.STRING_TYPE) {
            return new FieldResult(fieldInfo.getName(), tips, "仅支持String类型校验");
        }
        if ("".equals(fieldInfo.getValue().toString())) {
            return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
        }
        return new FieldResult(true, fieldInfo.getName());
    }
}

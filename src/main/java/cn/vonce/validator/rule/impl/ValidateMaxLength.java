package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VMaxLength;
import cn.vonce.validator.helper.ValidFieldHelper;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.rule.ValidateRule;
import cn.vonce.validator.utils.ValidFieldUtil;

/**
 * 校验字段设置的最大长度
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:32
 */
public class ValidateMaxLength implements ValidateRule<VMaxLength> {

    @Override
    public FieldResult handle(VMaxLength valid, FieldInfo fieldInfo) {
        String anticipate = String.format("'设置的最大长度%d'", valid.val());
        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), valid.value(), anticipate);
        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
            if (fieldInfo.getValue() == null) {
                return new FieldResult(fieldInfo.getName(), tips, "等于null");
            }
            ValidFieldHelper.WhatType whatType = ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
            if (whatType != ValidFieldHelper.WhatType.STRING_TYPE) {
                return new FieldResult(fieldInfo.getName(), tips, "仅支持String类型校验");
            }
            if (fieldInfo.getValue().toString().length() > valid.val()) {
                return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
            }
        }
        return new FieldResult(true, fieldInfo.getName());
    }

}

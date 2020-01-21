package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VRegex;
import cn.vonce.validator.helper.ValidFieldHelper;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.rule.ValidateRule;
import cn.vonce.validator.utils.ValidFieldUtil;

import java.util.regex.Pattern;

/**
 * 校验是否符合正则表达式的规则
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/21 15:51
 */
public class ValidateRegex implements ValidateRule<VRegex> {

    @Override
    public FieldResult handle(VRegex valid, FieldInfo fieldInfo) {
        String anticipate = "'正则表达式格式'";
        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), valid.value(), anticipate);
        if (ValidFieldUtil.isNeedValidation(false, fieldInfo.getValue())) {
            if (fieldInfo.getValue() == null) {
                return new FieldResult(fieldInfo.getName(), tips, "等于null");
            }
            ValidFieldHelper.WhatType whatType = ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
            if (whatType != ValidFieldHelper.WhatType.STRING_TYPE) {
                return new FieldResult(fieldInfo.getName(), tips, "仅支持String类型校验");
            }
            if (!Pattern.matches(valid.val(), fieldInfo.getValue().toString())) {
                return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
            }
        }
        return new FieldResult(true, fieldInfo.getName());
    }

}

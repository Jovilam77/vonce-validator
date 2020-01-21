package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.ValidatorUtil;
import cn.vonce.validator.annotation.VSQLInject;
import cn.vonce.validator.helper.ValidFieldHelper;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.rule.ValidateRule;
import cn.vonce.validator.utils.ValidFieldUtil;

/**
 * 校验sql注入
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:18
 */
public class ValidateSQLInject implements ValidateRule<VSQLInject> {

    @Override
    public FieldResult handle(VSQLInject valid, FieldInfo fieldInfo) {
        String anticipate = "'标准Sql语句'";
        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), valid.value(), anticipate);
        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
            if (fieldInfo.getValue() == null) {
                return new FieldResult(fieldInfo.getName(), tips, "等于null");
            }
            ValidFieldHelper.WhatType whatType = ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
            if (whatType != ValidFieldHelper.WhatType.STRING_TYPE) {
                return new FieldResult(fieldInfo.getName(), tips, "仅支持String类型校验");
            }
            if (!ValidatorUtil.isSQLInject(fieldInfo.getValue().toString())) {
                return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
            }
        }
        return new FieldResult(true, fieldInfo.getName());
    }

}

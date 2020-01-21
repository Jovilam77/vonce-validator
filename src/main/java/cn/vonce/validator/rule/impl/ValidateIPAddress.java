package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.ValidatorUtil;
import cn.vonce.validator.annotation.VIPAddress;
import cn.vonce.validator.helper.ValidFieldHelper;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.rule.ValidateRule;
import cn.vonce.validator.utils.ValidFieldUtil;

/**
 * 校验IP地址
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:21
 */
public class ValidateIPAddress implements ValidateRule<VIPAddress> {

    @Override
    public FieldResult handle(VIPAddress valid, FieldInfo fieldInfo) {
        String anticipate = "'标准ip地址格式'";
        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), valid.value(), anticipate);
        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
            if (fieldInfo.getValue() == null) {
                return new FieldResult(fieldInfo.getName(), tips, "等于null");
            }
            ValidFieldHelper.WhatType whatType = ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
            if (whatType != ValidFieldHelper.WhatType.STRING_TYPE) {
                return new FieldResult(fieldInfo.getName(), tips, "仅支持String类型校验");
            }
            if (!ValidatorUtil.isIPAddress(fieldInfo.getValue().toString())) {
                return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
            }
        }
        return new FieldResult(true, fieldInfo.getName());
    }

}

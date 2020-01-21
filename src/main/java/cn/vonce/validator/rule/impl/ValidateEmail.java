package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.ValidatorUtil;
import cn.vonce.validator.annotation.VEmail;
import cn.vonce.validator.helper.ValidFieldHelper;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.rule.ValidateString;
import cn.vonce.validator.utils.ValidFieldUtil;

/**
 * 校验邮箱格式
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:24
 */
public class ValidateEmail extends ValidateString<VEmail> /*implements ValidateRule<VEmail>*/ {

    @Override
    public String getAnticipate(VEmail valid) {
        return "'标准邮箱格式'";
    }

    @Override
    public boolean check(VEmail valid, FieldInfo fieldInfo) {
        if (!ValidatorUtil.isEmail(fieldInfo.getValue().toString())) {
            return false;
        }
        return true;
    }

//    @Override
//    public FieldResult handle(VEmail valid, FieldInfo fieldInfo) {
//        String anticipate = "'标准邮箱格式'";
//        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), valid.value(), anticipate);
//        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
//            if (fieldInfo.getValue() == null) {
//                return new FieldResult(fieldInfo.getName(), tips, "等于null");
//            }
//            ValidFieldHelper.WhatType whatType = ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
//            if (whatType != ValidFieldHelper.WhatType.STRING_TYPE) {
//                return new FieldResult(fieldInfo.getName(), tips, "仅支持String类型校验");
//            }
//            if (!ValidatorUtil.isEmail(fieldInfo.getValue().toString())) {
//                return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
//            }
//        }
//        return new FieldResult(true, fieldInfo.getName());
//    }

}
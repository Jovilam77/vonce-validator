package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.IdCardUtil;
import cn.vonce.validator.annotation.VIDCard;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.ValidateString;

/**
 * 校验身份证号码
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:23
 */
public class ValidateIDCard extends ValidateString<VIDCard> /*implements ValidateRule<VIDCard>*/ {

    @Override
    public String getAnticipate(VIDCard valid) {
        return "'标准身份证号码格式'";
    }

    @Override
    public boolean check(VIDCard valid, FieldInfo fieldInfo) {
        IdCardUtil.Result idCardResult = IdCardUtil.validate(fieldInfo.getValue().toString());
        if (!idCardResult.isOk()) {
            return false;
        }
        return true;
    }

    /*@Override
    public FieldResult handle(VIDCard valid, FieldInfo fieldInfo) {
        String anticipate = "'标准身份证号码格式'";
        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), valid.value(), anticipate);
        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
            if (fieldInfo.getValue() == null) {
                return new FieldResult(fieldInfo.getName(), tips, "等于null");
            }
            ValidFieldHelper.WhatType whatType = ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
            if (whatType != ValidFieldHelper.WhatType.STRING_TYPE) {
                return new FieldResult(fieldInfo.getName(), tips, "仅支持String类型校验");
            }
            IdCardUtil.Result idCardResult = IdCardUtil.validate(fieldInfo.getValue().toString());
            if (!idCardResult.isOk()) {
                return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate) + "," + idCardResult.getMessage());
            }
        }
        return new FieldResult(true, fieldInfo.getName());
    }*/

}

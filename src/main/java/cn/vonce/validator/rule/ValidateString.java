package cn.vonce.validator.rule;

import cn.vonce.validator.helper.ValidFieldHelper;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.utils.ValidFieldUtil;

import java.lang.annotation.Annotation;

/**
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/21 17:01
 */
public abstract class ValidateString<T extends Annotation> implements ValidateRule<T> {

    public abstract String getAnticipate(T valid);

    public abstract boolean check(T valid, FieldInfo fieldInfo);

    @Override
    public FieldResult handle(T valid, FieldInfo fieldInfo) {
        String anticipate = getAnticipate(valid);
        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), fieldInfo.getTips(), anticipate);
        if (ValidFieldUtil.isNeedValidation(fieldInfo.getOnlyWhenNotEmpty(), fieldInfo.getValue())) {
            if (fieldInfo.getValue() == null) {
                return new FieldResult(fieldInfo.getName(), tips, "等于null");
            }
            ValidFieldHelper.WhatType whatType = ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
            if (whatType != ValidFieldHelper.WhatType.STRING_TYPE) {
                return new FieldResult(fieldInfo.getName(), tips, "仅支持String类型校验");
            }
            if (!check(valid, fieldInfo)) {
                return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
            }
        }
        return new FieldResult(true, fieldInfo.getName());
    }

}

package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VRangeLength;
import cn.vonce.validator.helper.ValidFieldHelper;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.rule.ValidateRule;
import cn.vonce.validator.utils.ValidFieldUtil;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 校验字段设置的长度范围
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:31
 */
public class ValidateRangeLength implements ValidateRule<VRangeLength> {

    @Override
    public FieldResult handle(VRangeLength valid, FieldInfo fieldInfo) {
        String anticipate = String.format("'设置的长度范围max{%d},min{%d}'", valid.max(), valid.min());
        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), valid.value(), anticipate);
        if (fieldInfo.getValue() == null) {
            return new FieldResult(fieldInfo.getName(), tips, "等于null");
        }
        ValidFieldHelper.WhatType whatType = ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
        if (whatType != ValidFieldHelper.WhatType.STRING_TYPE && whatType != ValidFieldHelper.WhatType.VALUE_TYPE) {
            return new FieldResult(fieldInfo.getName(), tips, "仅支持String类型校验");
        }
        if (fieldInfo.getValue().toString().length() > valid.max() || fieldInfo.getValue().toString().length() < valid.min()) {
            return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
        }
        return new FieldResult(true, fieldInfo.getName());
    }

}

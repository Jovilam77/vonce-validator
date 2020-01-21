package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VNumber;
import cn.vonce.validator.helper.ValidFieldHelper;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.rule.ValidateRule;
import cn.vonce.validator.utils.ValidFieldUtil;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 校验数字
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 16:46
 */
public class ValidateNumber implements ValidateRule<VNumber> {

    @Override
    public FieldResult handle(VNumber valid, FieldInfo fieldInfo) {
        String anticipate = "";
        if (valid.val() == VNumber.NumType.NUMBER) {
            anticipate = "'数字类型'";
        } else if (valid.val() == VNumber.NumType.INTEGER) {
            anticipate = "'整数类型'";
        } else if (valid.val() == VNumber.NumType.FLOAT) {
            anticipate = "'浮点类型'";
        }
        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), valid.value(), anticipate);
        if (fieldInfo.getValue() == null) {
            return new FieldResult(fieldInfo.getName(), tips, "等于null");
        }
        ValidFieldHelper.WhatType whatType = ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
        if (whatType != ValidFieldHelper.WhatType.STRING_TYPE) {
            return new FieldResult(fieldInfo.getName(), tips, "仅支持String类型校验");
        }
        int index = fieldInfo.getValue().toString().indexOf(".");
        switch (valid.val()) {
            case NUMBER:
                if (!NumberUtils.isNumber(fieldInfo.getValue().toString())) {
                    return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
                }
                break;
            case INTEGER:
                if (!NumberUtils.isNumber(fieldInfo.getValue().toString()) || (index > -1 && index < fieldInfo.getValue().toString().length() - 1)) {
                    return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
                }
                break;
            case FLOAT:
                if (!NumberUtils.isNumber(fieldInfo.getValue().toString()) || (index == -1 || index == fieldInfo.getValue().toString().length() - 1)) {
                    return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
                }
                break;
        }
        return new FieldResult(true, fieldInfo.getName());
    }

}

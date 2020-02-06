package cn.vonce.validator.rule;

import cn.vonce.validator.helper.ValidatorHelper;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.utils.ValidatorUtil;

import java.lang.annotation.Annotation;

/**
 * 校验规则抽象实现
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/21 17:01
 */
public abstract class AbstractValidate<T extends Annotation> implements ValidateRule<T> {

    public abstract WhatType[] type();

    public abstract String getAnticipate(T valid);

    public abstract boolean check(T valid, FieldInfo fieldInfo);

    @Override
    public FieldResult handle(T valid, FieldInfo fieldInfo) {
        String tips = ValidatorUtil.getTips(fieldInfo.getName(), fieldInfo.getTips(), getAnticipate(valid));
        if (ValidatorUtil.isNeedValidation(fieldInfo.getOnlyWhenNotEmpty(), fieldInfo.getValue())) {
            if (fieldInfo.getValue() == null) {
                return new FieldResult(fieldInfo.getName(), tips, ValidatorUtil.getNullError());
            }
            WhatType thisType = ValidatorHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
            boolean accordWith = false;
            if (type() != null) {
                for (WhatType whatType : type()) {
                    if (thisType == whatType) {
                        accordWith = true;
                        break;
                    }
                }
            }
            if (!accordWith) {
                return new FieldResult(fieldInfo.getName(), tips, ValidatorUtil.getTypeError(type()));
            }
            if (!check(valid, fieldInfo)) {
                return new FieldResult(fieldInfo.getName(), tips, ValidatorUtil.getAnticipateError(getAnticipate(valid)));
            }
        }
        return new FieldResult(true, fieldInfo.getName());
    }

}

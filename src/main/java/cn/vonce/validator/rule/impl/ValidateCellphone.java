package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.ValidatorUtil;
import cn.vonce.validator.annotation.VPhoneNum;
import cn.vonce.validator.helper.ValidFieldHelper;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.rule.ValidateRule;
import cn.vonce.validator.utils.ValidFieldUtil;

/**
 * 校验手机号码
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:25
 */
public class ValidateCellphone implements ValidateRule<VPhoneNum> {

    @Override
    public FieldResult handle(VPhoneNum valid, FieldInfo fieldInfo) {
        FieldResult fieldResult = new FieldResult(true, fieldInfo.getName());
        if (ValidFieldUtil.isNeedValidation(valid.onlyWhenNotEmpty(), fieldInfo.getValue())) {
            if (fieldInfo.getValue() == null) {
                fieldResult.setPass(false);
                fieldResult.setTips(valid.value());
            } else {
                // 字符串类型可以进行校验
                if (ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName()) == ValidFieldHelper.WhatType.STRING_TYPE) {
                    switch (valid.phoneType()) {
                        case MOBILEPHONE:
                            if (!ValidatorUtil.isMobilePhone(fieldInfo.getValue().toString())) {
                                fieldResult.setPass(false);
                                fieldResult.setTips(valid.value());
                            }
                            break;
                        case TELEPHONE:
                            if (!ValidatorUtil.isTelePhone(fieldInfo.getValue().toString())) {
                                fieldResult.setPass(false);
                                fieldResult.setTips(valid.value());
                            }
                            break;
                        case CHINATELECOM:
                            if (!ValidatorUtil.isChinaTelecom(fieldInfo.getValue().toString())) {
                                fieldResult.setPass(false);
                                fieldResult.setTips(valid.value());
                            }
                            break;
                        case CHINAUNICOM:
                            if (!ValidatorUtil.isChinaUnicom(fieldInfo.getValue().toString())) {
                                fieldResult.setPass(false);
                                fieldResult.setTips(valid.value());
                            }
                            break;
                        case CHINAMOBILE:
                            if (!ValidatorUtil.isChinaMobile(fieldInfo.getValue().toString())) {
                                fieldResult.setPass(false);
                                fieldResult.setTips(valid.value());
                            }
                            break;
                    }
                }
            }
        }
        return fieldResult;
    }

}

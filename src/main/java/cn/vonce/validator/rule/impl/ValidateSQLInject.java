package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.ValidatorUtil;
import cn.vonce.validator.annotation.VSQLInject;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验sql注入
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:18
 */
public class ValidateSQLInject extends AbstractValidate<VSQLInject> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE};
    }

    @Override
    public String getAnticipate(VSQLInject valid) {
        return "'标准Sql语句'";
    }

    @Override
    public boolean check(VSQLInject valid, FieldInfo fieldInfo) {
        if (!ValidatorUtil.isSQLInject(fieldInfo.getValue().toString())) {
            return false;
        }
        return true;
    }

}

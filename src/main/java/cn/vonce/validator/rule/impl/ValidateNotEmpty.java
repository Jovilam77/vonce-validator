package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VNotEmpty;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验字段不能为empty
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:33
 */
public class ValidateNotEmpty extends AbstractValidate<VNotEmpty> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE, WhatType.VALUE_TYPE, WhatType.BOOL_TYPE, WhatType.DATE_TYPE, WhatType.OTHER_TYPE};
    }

    @Override
    public String getAnticipate(VNotEmpty valid) {
        return "'不能为empty'";
    }

    @Override
    public boolean check(VNotEmpty valid, FieldInfo fieldInfo) {
        if ("".equals(fieldInfo.getValue().toString())) {
            return false;
        }
        return true;
    }

}

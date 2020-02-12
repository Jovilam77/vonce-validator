package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VConsist;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验是否由指定值构成
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/2/11 9:44
 */
public class ValidateConsist extends AbstractValidate<VConsist> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE, WhatType.VALUE_TYPE, WhatType.BOOL_TYPE};
    }

    @Override
    public String getAnticipate(VConsist valid) {
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < valid.val().length; i++) {
            stringbuffer.append(valid.val()[i]);
            if (i < valid.val().length - 1) {
                stringbuffer.append(",");
            }
        }
        return "'" + stringbuffer.toString() + "'";
    }

    @Override
    public boolean check(VConsist valid, FieldInfo fieldInfo) {
        for (String value : valid.val()) {
            if (fieldInfo.getValue().toString().equals(value)) {
                return true;
            }
        }
        return false;
    }

}

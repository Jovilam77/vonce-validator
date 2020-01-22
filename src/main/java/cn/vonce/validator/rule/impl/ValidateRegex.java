package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VRegex;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;
import java.util.regex.Pattern;

/**
 * 校验是否符合正则表达式的规则
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/21 15:51
 */
public class ValidateRegex extends AbstractValidate<VRegex> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE};
    }

    @Override
    public String getAnticipate(VRegex valid) {
        return null;
    }

    @Override
    public boolean check(VRegex valid, FieldInfo fieldInfo) {
        if (!Pattern.matches(valid.val(), fieldInfo.getValue().toString())) {
            return false;
        }
        return true;
    }

}

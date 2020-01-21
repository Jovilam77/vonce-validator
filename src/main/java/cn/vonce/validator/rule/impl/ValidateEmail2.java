package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.ValidatorUtil;
import cn.vonce.validator.annotation.VEmail;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.ValidateString;

/**
 * 校验邮箱格式
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:24
 */
public class ValidateEmail2 extends ValidateString<VEmail> {

    @Override
    public String getAnticipate(VEmail valid) {
        return "'标准邮箱格式'";
    }

    @Override
    public boolean check(VEmail valid, FieldInfo fieldInfo) {
        if (!ValidatorUtil.isEmail(fieldInfo.getValue().toString())) {
            return false;
        }
        return true;
    }

}

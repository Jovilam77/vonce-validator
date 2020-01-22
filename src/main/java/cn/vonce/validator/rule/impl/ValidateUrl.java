package cn.vonce.validator.rule.impl;

import cn.vonce.common.utils.ValidatorUtil;
import cn.vonce.validator.annotation.VUrl;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

/**
 * 校验URl格式
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:22
 */
public class ValidateUrl extends AbstractValidate<VUrl> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.STRING_TYPE};
    }

    @Override
    public String getAnticipate(VUrl valid) {
        return "'标准Url链接'";
    }

    @Override
    public boolean check(VUrl valid, FieldInfo fieldInfo) {
        if (!ValidatorUtil.isUrl(fieldInfo.getValue().toString())) {
            return false;
        }
        return true;
    }

}

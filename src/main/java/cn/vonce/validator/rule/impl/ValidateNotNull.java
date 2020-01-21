package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VNotNull;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.rule.ValidateRule;
import cn.vonce.validator.utils.ValidFieldUtil;

/**
 * 校验字段不能为null
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:34
 */
public class ValidateNotNull implements ValidateRule<VNotNull> {

    @Override
    public FieldResult handle(VNotNull valid, FieldInfo fieldInfo) {
        String anticipate = "'不能为null'";
        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), valid.value(), anticipate);
        if (fieldInfo.getValue() == null) {
            return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
        }
        return new FieldResult(true, fieldInfo.getName());
    }

}

package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VEqualTo;
import cn.vonce.validator.helper.WhatType;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.AbstractValidate;

import java.lang.reflect.Field;

/**
 * 校验指定值
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:20
 */
public class ValidateEqualTo extends AbstractValidate<VEqualTo> {

    @Override
    public WhatType[] type() {
        return new WhatType[]{WhatType.VALUE_TYPE, WhatType.STRING_TYPE, WhatType.BOOL_TYPE};
    }

    @Override
    public String getAnticipate(VEqualTo valid) {
        return "'设置的指定值'";
    }

    @Override
    public boolean check(VEqualTo valid, FieldInfo fieldInfo) {
        String contrastValue;
        if (fieldInfo.getBean() != null && !valid.field().equals("")) {
            try {
                Field field = fieldInfo.getBean().getClass().getDeclaredField(valid.field());
                field.setAccessible(true);
                Object value = field.get(fieldInfo.getBean());
                if (value == null) {
                    return false;
                }
                contrastValue = value.toString();
            } catch (NoSuchFieldException e) {
                return false;
            } catch (SecurityException e) {
                return false;
            } catch (IllegalArgumentException e) {
                return false;
            } catch (IllegalAccessException e) {
                return false;
            }
        } else {
            contrastValue = valid.val();
        }
        if (!fieldInfo.getValue().equals(contrastValue)) {
            return false;
        }
        return true;
    }

}

package cn.vonce.validator.rule.impl;

import cn.vonce.validator.annotation.VEqualTo;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.rule.ValidateString;

import java.lang.reflect.Field;

/**
 * 校验指定值
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:20
 */
public class ValidateEqualTo extends ValidateString<VEqualTo> /*implements ValidateRule<VEqualTo>*/ {

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


//    @Override
//    public FieldResult handle(VEqualTo valid, FieldInfo fieldInfo) {
//        String anticipate = "'设置的指定值'";
//        String tips = ValidFieldUtil.getTips(fieldInfo.getName(), valid.value(), anticipate);
//        if (fieldInfo.getValue() == null) {
//            return new FieldResult(fieldInfo.getName(), tips, "等于null");
//        }
//        ValidFieldHelper.WhatType whatType = ValidFieldHelper.whatType(fieldInfo.getValue().getClass().getSimpleName());
//        if (whatType != ValidFieldHelper.WhatType.STRING_TYPE) {
//            return new FieldResult(fieldInfo.getName(), tips, "仅支持String类型校验");
//        }
//        String contrastValue;
//        if (fieldInfo.getBean() != null && !valid.field().equals("")) {
//            try {
//                Field field = fieldInfo.getBean().getClass().getDeclaredField(valid.field());
//                field.setAccessible(true);
//                Object value = field.get(fieldInfo.getBean());
//                if (value == null) {
//                    return new FieldResult(fieldInfo.getName(), tips, "指定的bean字段值等于null");
//                }
//                contrastValue = value.toString();
//            } catch (NoSuchFieldException e) {
//                return new FieldResult(fieldInfo.getName(), tips, "NoSuchFieldException：" + e.getMessage());
//            } catch (SecurityException e) {
//                return new FieldResult(fieldInfo.getName(), tips, "SecurityException：" + e.getMessage());
//            } catch (IllegalArgumentException e) {
//                return new FieldResult(fieldInfo.getName(), tips, "IllegalArgumentException：" + e.getMessage());
//            } catch (IllegalAccessException e) {
//                return new FieldResult(fieldInfo.getName(), tips, "IllegalAccessException：" + e.getMessage());
//            }
//        } else {
//            contrastValue = valid.val();
//        }
//        if (!fieldInfo.getValue().equals(contrastValue)) {
//            return new FieldResult(fieldInfo.getName(), tips, ValidFieldUtil.getError(anticipate));
//        }
//        return new FieldResult(true, fieldInfo.getName());
//    }
}

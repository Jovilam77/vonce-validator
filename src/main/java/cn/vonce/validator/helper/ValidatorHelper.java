package cn.vonce.validator.helper;

import cn.vonce.validator.annotation.Validate;
import cn.vonce.validator.model.BeanResult;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 校验字段助手
 *
 * @author jovi
 * @version 1.0
 * @email 766255988@qq.com
 * @date 2017年4月20日下午6:48:08
 */
public class ValidatorHelper {

    private static Logger logger = LoggerFactory.getLogger(ValidatorHelper.class);

    /**
     * 校验bean
     *
     * @param beanObject bean对象
     * @param group      分组
     * @param interrupt  校验字段遇到错误是否中断不再继续校验(bean模式下生效)
     * @return
     * @author Jovi
     * @date 2018年1月17日下午7:33:27
     */
    public static BeanResult validBean(Object beanObject, String group, boolean interrupt) {
        if (beanObject == null) {
            return new BeanResult("bean" + ValidatorUtil.getNullError());
        }
        Field[] fields = beanObject.getClass().getDeclaredFields();
        List<FieldResult> fieldResultList = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                List<FieldResult> validFieldResultList = valid(fields[i].getAnnotations(), fields[i].getName(), fields[i].get(beanObject), beanObject, group, interrupt);
                if (validFieldResultList != null && validFieldResultList.size() > 0) {
                    fieldResultList.addAll(validFieldResultList);
                    if (interrupt) {
                        break;
                    }
                }
            } catch (SecurityException e) {
                logger.error(e.getMessage(), e);
                return new BeanResult("SecurityException" + e.getMessage());
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(), e);
                return new BeanResult("SecurityException" + e.getMessage());
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage(), e);
                return new BeanResult("SecurityException" + e.getMessage());
            }
        }
        if (!fieldResultList.isEmpty()) {
            return new BeanResult(fieldResultList.get(0).getTips(), fieldResultList);
        }
        return new BeanResult(true, "校验通过");
    }

    /**
     * 校验单个字段或参数
     *
     * @param annotations bean字段或方法参数注解
     * @param fieldValue  bean字段或方法参数名称
     * @param fieldValue  字段或参数字
     * @param beanObject  bean对象
     * @param group       分组
     * @param interrupt   校验字段遇到错误是否中断不再继续校验(bean模式下生效)
     * @return
     * @author Jovi
     * @date 2018年1月17日下午7:32:06
     */
    public static List<FieldResult> valid(Annotation[] annotations, String fieldName, Object fieldValue, Object beanObject, String group, boolean interrupt) {
        // 消息提示列表
        List<FieldResult> fieldResultList = new ArrayList<>();
        FieldInfo fieldInfo = null;
        String annName = "";
        String annValue = "";
        boolean annOnlyWhenNotEmpty = false;
        String[] groups = null;
        try {
            for (int i = 0; i < annotations.length; i++) {
                // 判断此注解是否为遵循校验规范的注解
                Validate validate = annotations[i].annotationType().getAnnotation(Validate.class);
                if (validate == null) {
                    continue;
                }
                for (Method method : annotations[i].annotationType().getMethods()) {
                    Object methodResult;
                    if ("name".equals(method.getName())) {
                        methodResult = method.invoke(annotations[i]);
                        annName = (String) methodResult;
                    }
                    if ("value".equals(method.getName())) {
                        methodResult = method.invoke(annotations[i]);
                        annValue = (String) methodResult;
                    }
                    if ("onlyWhenNotEmpty".equals(method.getName())) {
                        methodResult = method.invoke(annotations[i]);
                        annOnlyWhenNotEmpty = (boolean) methodResult;
                    }
                    if ("group".equals(method.getName())) {
                        methodResult = method.invoke(annotations[i]);
                        groups = (String[]) methodResult;
                    }
                }
                boolean isMust = false;
                for (String string : groups) {
                    // 如果该分组需要校验
                    if (string.equals(group)) {
                        isMust = true;
                        break;
                    }
                }
                if (isMust) {
                    fieldInfo = new FieldInfo();
                    fieldInfo.setName(ValidatorUtil.getFieldName(fieldName, annName));
                    fieldInfo.setTips(annValue);
                    fieldInfo.setValue(fieldValue);
                    fieldInfo.setBean(beanObject);
                    fieldInfo.setOnlyWhenNotEmpty(annOnlyWhenNotEmpty);
                    Method method = validate.type().getMethod("handle", Annotation.class, FieldInfo.class);
                    FieldResult fieldResult = (FieldResult) method.invoke(validate.type().newInstance(), annotations[i], fieldInfo);
                    if (fieldResult != null && !fieldResult.getPass()) {
                        fieldResultList.add(fieldResult);
                        if (interrupt) {
                            break;
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            logger.error(e.getMessage(), e);
            fieldResultList.add(new FieldResult(fieldInfo.getName(), "出现异常", "SecurityException：" + e.getMessage()));
            return fieldResultList;
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
            fieldResultList.add(new FieldResult(fieldInfo.getName(), "出现异常", "IllegalAccessException：" + e.getMessage()));
            return fieldResultList;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            fieldResultList.add(new FieldResult(fieldInfo.getName(), "出现异常", "IllegalArgumentException：" + e.getMessage()));
            return fieldResultList;
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(), e);
            fieldResultList.add(new FieldResult(fieldInfo.getName(), "出现异常", "InvocationTargetException：" + e.getMessage()));
            return fieldResultList;
        } catch (NoSuchMethodException e) {
            logger.error(e.getMessage(), e);
            fieldResultList.add(new FieldResult(fieldInfo.getName(), "出现异常", "NoSuchMethodException：" + e.getMessage()));
            return fieldResultList;
        } catch (InstantiationException e) {
            logger.error(e.getMessage(), e);
            fieldResultList.add(new FieldResult(fieldInfo.getName(), "出现异常", "InstantiationException：" + e.getMessage()));
            return fieldResultList;
        }
        return fieldResultList;
    }

    /**
     * Parameter与Field 通用的根据注解类型获取注解方法
     *
     * @param annotations
     * @param annotationClass
     * @return
     * @author jovi
     * @date 2017年4月20日下午23:15:22
     */
    public static <T extends Annotation> T getAnnotation(Annotation[] annotations, Class<T> annotationClass) {
        T t = null;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().getName().equals(annotationClass.getName())) {
                t = annotationClass.cast(annotation);
            }
        }
        return t;
    }

    /**
     * 获取字段类型
     *
     * @param typeName
     * @return
     * @author Jovi
     * @date 2017年6月22日下午7:08:13
     */
    public static WhatType whatType(String typeName) {
        WhatType whatType = null;
        switch (typeName) {
            case "String":
            case "Char":
                whatType = WhatType.STRING_TYPE;
                break;
            case "Boolean":
                whatType = whatType.BOOL_TYPE;
                break;
            case "byte":
            case "Byte":
            case "short":
            case "Short":
            case "int":
            case "Integer":
            case "long":
            case "Long":
            case "float":
            case "Float":
            case "double":
            case "Double":
                whatType = WhatType.VALUE_TYPE;
                break;
            case "Date":
                whatType = WhatType.DATE_TYPE;
                break;
            default:
                whatType = WhatType.OBJECT_TYPE;
                break;
        }
        return whatType;
    }

}

package cn.vonce.validator.helper;

import cn.vonce.validator.annotation.Validate;
import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;
import cn.vonce.validator.utils.ValidFieldUtil;
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
public class ValidFieldHelper {

    private static Logger logger = LoggerFactory.getLogger(ValidFieldHelper.class);

    /**
     * STRING_TYPE 字符串类型<br>
     * PACK_TYPE 包装类型 OBJECT_TYPE 对象类型 VALUE_TYPE 值类型
     *
     * @author jovi
     * @version 1.0
     * @email 766255988@qq.com
     * @date 2017年6月2日下午5:24:16
     */
    public static enum WhatType {
        STRING_TYPE, VALUE_TYPE, BOOL_TYPE, DATE_TYPE, OBJECT_TYPE
    }

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
    public static List<FieldResult> validBean(Object beanObject, String group, boolean interrupt) {
        // 消息提示列表
        List<FieldResult> fieldResultList = new ArrayList<>();
        // 不能为空
        if (beanObject == null) {
//            fieldResultList.add(new FieldResult("bean参数不能为空"));
            return fieldResultList;
        }
        // 获取bean 全部字段
        Field[] fields = beanObject.getClass().getDeclaredFields();
        // 遍历bean 全部字段
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
//                fieldResultList.add(new FieldResult(e.getMessage()));
            } catch (IllegalAccessException e) {
//                fieldResultList.add(new FieldResult(e.getMessage()));
            } catch (IllegalArgumentException e) {
//                fieldResultList.add(new FieldResult(e.getMessage()));
            }
        }
        return fieldResultList;
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
        try {
            // 遍历该字段或参数的注解数组 存在的注解才进行校验
            for (int i = 0; i < annotations.length; i++) {
                // 判断此注解是否为遵循校验规范的注解
                Validate validate = annotations[i].annotationType().getAnnotation(Validate.class);
                if (validate == null) {
                    continue;
                }
                String annName = "";
                String annValue = "";
                boolean annOnlyWhenNotEmpty = false;
                String[] groups = null;
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
                    FieldInfo fieldInfo = new FieldInfo();
                    fieldInfo.setName(ValidFieldUtil.getName(fieldName, annName));
                    fieldInfo.setTips(annValue);
                    fieldInfo.setValue(fieldValue);
                    fieldInfo.setBean(beanObject);
                    fieldInfo.setOnlyWhenNotEmpty(annOnlyWhenNotEmpty);
                    FieldResult fieldResult = execute(validate.type(), annotations[i], fieldInfo);
                    if (fieldResult != null && !fieldResult.getPass()) {
                        fieldResultList.add(fieldResult);
                        if (interrupt) {
                            break;
                        }
                    }
                }
            }
        } catch (SecurityException e) {
//            fieldResultList.add(new FieldResult(e.getMessage()));
        } catch (IllegalAccessException e) {
//            fieldResultList.add(new FieldResult(e.getMessage()));
        } catch (IllegalArgumentException e) {
//            fieldResultList.add(new FieldResult(e.getMessage()));
        } catch (InvocationTargetException e) {
//            fieldResultList.add(new FieldResult(e.getMessage()));
        }
        return fieldResultList;
    }

    /**
     * 进行规则校验
     *
     * @param clazz
     * @param annotation
     * @return
     * @author Jovi
     * @date 2018年1月28日下午11:07:02
     */
    private static FieldResult execute(Class<?> clazz, Annotation annotation, FieldInfo fieldInfo) {
        FieldResult fieldResult = new FieldResult();
        try {
            Method method = clazz.getMethod("handle", annotation.annotationType(), FieldInfo.class);
            if (method != null) {
                Object methodResult = method.invoke(clazz.newInstance(), annotation, fieldInfo);
                if (methodResult != null) {
                    fieldResult = (FieldResult) methodResult;
                }
            } else {
                fieldResult.setTips("字段校验失败，该注解未指定校验方法：" + annotation.annotationType().getName());
                return fieldResult;
            }
        } catch (SecurityException e) {

            fieldResult.setTips(e.getMessage());
        } catch (IllegalAccessException e) {
            fieldResult.setTips(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            fieldResult.setTips(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            fieldResult.setTips(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            fieldResult.setTips("没有找到该方法，请检查方法名与参数是否正确：" + e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (InstantiationException e) {
            fieldResult.setTips(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return fieldResult;
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

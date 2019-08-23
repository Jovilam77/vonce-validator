package cn.vonce.validator.helper;

import cn.vonce.validator.annotation.VBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证字段助手
 * 
 * @author jovi
 * @email 766255988@qq.com
 * @version 1.0
 * @date 2017年4月20日下午6:48:08
 */
public class ValidFieldHelper {

	/**
	 * STRING_TYPE 字符串类型<br>
	 * PACK_TYPE 包装类型 OBJECT_TYPE 对象类型 VALUE_TYPE 值类型
	 * 
	 * @author jovi
	 * @email 766255988@qq.com
	 * @version 1.0
	 * @date 2017年6月2日下午5:24:16
	 */
	public static enum WhatIsType {
		STRING_TYPE, VALUE_TYPE, BOOL_TYPE, DATE_TYPE, OBJECT_TYPE
	}

	/**
	 * 验证bean
	 * 
	 * @author Jovi
	 * @date 2018年1月17日下午7:33:27
	 * @param beanObject
	 *            bean对象
	 * @param group
	 *            分组
	 * @param interrupt
	 *            验证字段遇到错误是否中断不再继续验证(bean模式下生效)
	 * @return
	 */
	public static List<String> validBean(Object beanObject, String group, boolean interrupt) {
		// 消息提示列表
		List<String> messageList = new ArrayList<>();
		// 不能为空
		if (beanObject == null) {
			messageList.add("bean参数不能为空");
			return messageList;
		}
		// 获取bean 全部字段
		Field[] fields = beanObject.getClass().getDeclaredFields();
		// 遍历bean 全部字段
		for (int i = 0; i < fields.length; i++) {
			try {
				fields[i].setAccessible(true);
				Object value = fields[i].get(beanObject);
				List<String> mList = valid(fields[i].getAnnotations(), value, beanObject, group, interrupt);
				if (mList != null && mList.size() > 0) {
					messageList.addAll(mList);
					if (interrupt) {
						break;
					}
				}
			} catch (SecurityException e) {
				messageList.add(e.getMessage());
			} catch (IllegalAccessException e) {
				messageList.add(e.getMessage());
			} catch (IllegalArgumentException e) {
				messageList.add(e.getMessage());
			}
		}
		return messageList;
	}

	/**
	 * 验证单个字段或参数
	 * 
	 * @author Jovi
	 * @date 2018年1月17日下午7:32:06
	 * @param annotations
	 *            字段或参数注解
	 * @param valueObject
	 *            字段或参数值
	 * @param beanObject
	 *            bean对象
	 * @param group
	 *            分组
	 * @param interrupt
	 *            验证字段遇到错误是否中断不再继续验证(bean模式下生效)
	 * @return
	 */
	public static List<String> valid(Annotation[] annotations, Object valueObject, Object beanObject, String group, boolean interrupt) {
		// 消息提示列表
		List<String> messageList = new ArrayList<>();
		try {
			// 遍历该字段或参数的注解数组 存在的注解才进行验证
			for (int i = 0; i < annotations.length; i++) {
				// 过滤此注解
				if (annotations[i] instanceof VBean) {
				} else {
					String typeName = null;
					String methodName = null;
					String[] groups = null;
					Method[] methods = annotations[i].annotationType().getMethods();
					for (Method method : methods) {
						Object methodResult = null;
						if ("type".equals(method.getName())) {
							methodResult = method.invoke(annotations[i]);
							typeName = methodResult.toString().split(" ")[1];
						}
						if ("method".equals(method.getName())) {
							methodResult = method.invoke(annotations[i]);
							methodName = methodResult.toString();
						}
						if ("group".equals(method.getName())) {
							methodResult = method.invoke(annotations[i]);
							groups = (String[]) methodResult;
						}
					}
					// 判断此注解是否为标准验证注解
					if (typeName == null || methodName == null || groups == null) {
						continue;
					}
					boolean isMust = false;
					for (String string : groups) {
						// 如果该分组需要验证
						if (string.equals(group)) {
							isMust = true;
							break;
						}
					}
					if (isMust) {
						String message = execute(typeName, methodName, groups, annotations[i], valueObject, beanObject, group);
						if (message != null && !message.equals("")) {
							messageList.add(message);
							if (interrupt) {
								break;
							}
						}
					}
				}
			}
		} catch (SecurityException e) {
			messageList.add(e.getMessage());
		} catch (IllegalAccessException e) {
			messageList.add(e.getMessage());
		} catch (IllegalArgumentException e) {
			messageList.add(e.getMessage());
		} catch (InvocationTargetException e) {
			messageList.add(e.getMessage());
		}
		return messageList;
	}

	/**
	 * 进行规则验证
	 * 
	 * @author Jovi
	 * @date 2018年1月28日下午11:07:02
	 * @param typeName
	 * @param methodName
	 * @param groups
	 * @param annotation
	 * @param valueObject
	 * @param beanObject
	 * @param group
	 * @return
	 */
	private static String execute(String typeName, String methodName, String[] groups, Annotation annotation, Object valueObject, Object beanObject,
			String group) {
		String messageExpand = null;
		try {
			if (!"".equals(methodName)) {
				Class<?> clazz = Class.forName(typeName);
				Method method = clazz.getMethod(methodName, annotation.annotationType(), Object.class, Object.class);
				if (method != null) {
					Object methodResult = method.invoke(clazz.newInstance(), annotation, valueObject, beanObject);
					if (methodResult != null) {
						if (whatIsType(methodResult.getClass().getSimpleName()) != WhatIsType.STRING_TYPE) {
							messageExpand = "该验证方法返回值不正确：" + typeName + "." + methodName;
						} else {
							messageExpand = methodResult.toString();
						}
					}
				}
			} else {
				return "字段验证失败，该注解未指定验证方法：" + annotation.annotationType().getName();
			}
		} catch (SecurityException e) {
			messageExpand = e.getMessage();
		} catch (IllegalAccessException e) {
			messageExpand = e.getMessage();
		} catch (IllegalArgumentException e) {
			messageExpand = e.getMessage();
		} catch (InvocationTargetException e) {
			messageExpand = e.getMessage();
		} catch (NoSuchMethodException e) {
			messageExpand = "没有找到该方法，请检查方法名与参数是否正确：" + e.getMessage();
		} catch (ClassNotFoundException e) {
			messageExpand = "没有找到该类：" + e.getMessage();
		} catch (InstantiationException e) {
			messageExpand = e.getMessage();
		}
		return messageExpand;
	}

	/**
	 * Parameter与Field 通用的根据注解类型获取注解方法
	 * 
	 * @author jovi
	 * @date 2017年4月20日下午23:15:22
	 * @param annotations
	 * @param annotationClass
	 * @return
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
	 * @author Jovi
	 * @date 2017年6月22日下午7:08:13
	 * @param typeName
	 * @return
	 */
	public static WhatIsType whatIsType(String typeName) {
		WhatIsType whatIsType = null;
		switch (typeName) {
		case "String":
		case "Char":
			whatIsType = WhatIsType.STRING_TYPE;
			break;
		case "Boolean":
			whatIsType = WhatIsType.BOOL_TYPE;
			break;
		case "byte":
		case "short":
		case "int":
		case "long":
		case "float":
		case "double":
		case "Byte":
		case "Short":
		case "Integer":
		case "Long":
		case "Float":
		case "Double":
			whatIsType = WhatIsType.VALUE_TYPE;
			break;
		case "Date":
			whatIsType = WhatIsType.DATE_TYPE;
			break;
		default:
			whatIsType = WhatIsType.OBJECT_TYPE;
			break;
		}
		return whatIsType;
	}
}

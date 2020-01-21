package cn.vonce.validator.rule;

import cn.vonce.validator.model.FieldInfo;
import cn.vonce.validator.model.FieldResult;

import java.lang.annotation.Annotation;

/**
 * 校验规则接口
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:13
 */
public interface ValidateRule<T extends Annotation> {

    FieldResult handle(T valid, FieldInfo fieldInfo);

}

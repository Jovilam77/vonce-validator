package cn.vonce.validator.utils;

import cn.vonce.common.utils.StringUtil;

/**
 * 验证字段工具类
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2019/9/12 16:11
 */
public class ValidFieldUtil {

    /**
     * 是否字段不为空才验证
     *
     * @param notEmpty
     * @param valueObject
     * @return
     */
    public static boolean needValidation(boolean notEmpty, Object valueObject) {
        boolean isTrue = false;
        if (notEmpty) {
            if (StringUtil.isNotEmpty(valueObject)) {
                isTrue = true;
            }
        } else {
            isTrue = true;
        }
        return isTrue;
    }

}

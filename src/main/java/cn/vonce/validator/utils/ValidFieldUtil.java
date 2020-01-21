package cn.vonce.validator.utils;

import cn.vonce.common.utils.StringUtil;

import java.lang.annotation.Annotation;

/**
 * 校验字段工具类
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2019/9/12 16:11
 */
public class ValidFieldUtil {

    /**
     * 是否只有不为空的时候才校验
     *
     * @param onlyWhenNotEmpty
     * @param fieldValue
     * @return
     */
    public static boolean isNeedValidation(boolean onlyWhenNotEmpty, Object fieldValue) {
        if (onlyWhenNotEmpty) {
            if (StringUtil.isNotEmpty(fieldValue)) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    public static String getName(String fieldName, String annotationName) {
        return StringUtil.isEmpty(annotationName) ? fieldName : annotationName;
    }

    public static String getTips(String fieldName, String annotationTips, String def) {
        String tips = "";
        if (StringUtil.isEmpty(annotationTips)) {
            tips = "'" + fieldName + "'必须是" + def;
        } else {
            tips += annotationTips;
        }
        return tips;
    }

    public static String getError(String anticipate) {
        return "不符合" + anticipate + "的预期";
    }

}

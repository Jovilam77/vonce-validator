package cn.vonce.validator.intercept;

import cn.vonce.common.base.BaseController;
import cn.vonce.common.utils.RequestDataUtil;
import cn.vonce.validator.annotation.VBean;
import cn.vonce.validator.helper.ValidFieldHelper;
import cn.vonce.validator.model.FieldResult;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * 校验字段拦截器
 *
 * @author jovi
 * @version 1.0
 * @email 766255988@qq.com
 * @date 2017年4月20日下午23:56:08
 */
public class ValidFieldInterceptor implements MethodInterceptor {

    private final Logger logger = LoggerFactory.getLogger(ValidFieldInterceptor.class);

    @Override
    public Object invoke(MethodInvocation arg0) throws Throwable {
        String fullName = arg0.getMethod().getReturnType().getSimpleName() + " " + arg0.getThis().getClass().getName() + "." + arg0.getMethod().getName();
        ResponseBody responseBody = arg0.getMethod().getAnnotation(ResponseBody.class);
        RestController restController = arg0.getThis().getClass().getAnnotation(RestController.class);
        BaseController baseController;
        if (arg0.getThis() instanceof BaseController) {
            baseController = (BaseController) arg0.getThis();
        } else {
            baseController = new BaseController();
        }
        List<FieldResult> fieldResultList = new ArrayList<>();

        for (int i = 0; i < arg0.getArguments().length; i++) {
            // 获取实际对象-即为原始对象(可能是Bean，也可能是字段)
            Object object = arg0.getArguments()[i];
            // 获取参数对象-用于后面获取该参数的注解
            Annotation[] annotations = arg0.getMethod().getParameterAnnotations()[i];
            if (baseController.getRequest() == null && object instanceof HttpServletRequest) {
                baseController.setRequest((HttpServletRequest) object);
            }
            if (baseController.getResponse() == null && object instanceof HttpServletResponse) {
                baseController.setResponse((HttpServletResponse) object);
            }
            // 优先校验bean
            VBean validBean = ValidFieldHelper.getAnnotation(annotations, VBean.class);
            if (validBean != null) {
                fieldResultList.addAll(ValidFieldHelper.validBean(object, validBean.group(), validBean.interrupt()));
                break;
            }
            // 校验有注解的字段
            List<FieldResult> validFieldResultList = ValidFieldHelper.valid(annotations, arg0.getMethod().getName() + "方法的第" + (i + 1) + "个参数", object, null, "", true);
            if (validFieldResultList != null && validFieldResultList.size() > 0) {
                fieldResultList.addAll(validFieldResultList);
                break;
            }

        }
        logger.info("正在校验参数：" + fullName);
        if (baseController.getRequest() != null) {
            logger.info("请求URL参数：" + RequestDataUtil.getParameters(baseController.getRequest().getParameterMap()));
        } else {
            logger.info("请求URL参数：该方法缺少HttpServletRequest参数无法读取请求URL参数 ");
        }
        // 如果参数错误则直接包装返回结果，不继续往下执行
        if (fieldResultList != null && fieldResultList.size() > 0) {
            logger.warn("参数校验不通过：" + fullName);
            logger.warn("响应内容：" + fieldResultList.toString());
            // 如果该注解存在
            if (!arg0.getMethod().getReturnType().getName().equals("void") && (responseBody != null || restController != null)) {
                if (fieldResultList != null && fieldResultList.size() == 1) {
                    return baseController.parameterHint(fieldResultList.get(0).getTips());
                } else {
                    return baseController.parameterHint("参数错误", fieldResultList);
                }
            } else if (baseController.getRequest() != null && baseController.getResponse() != null) {
                if (fieldResultList != null && fieldResultList.size() == 1) {
                    baseController.parameterHintJSONP(fieldResultList.get(0).getTips());
                } else {
                    baseController.parameterHintJSONP("参数错误", fieldResultList);
                }
            } else {
                logger.error("参数错误：" + fieldResultList);
            }
            return null;
        }
        logger.info("参数校验通过：" + fullName);
        // 参数无错误则执行方法并返回返回值
        return arg0.proceed();
    }


}

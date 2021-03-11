package cn.vonce.validator.intercept;

import cn.vonce.common.base.BaseController;
import cn.vonce.common.utils.RequestDataUtil;
import cn.vonce.validator.annotation.VBean;
import cn.vonce.validator.helper.ValidatorHelper;
import cn.vonce.validator.model.BeanResult;
import cn.vonce.validator.model.FieldResult;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 校验字段拦截器
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2017年4月20日下午23:56:08
 */
public class ValidatorInterceptor implements MethodInterceptor {

    private final Logger logger = LoggerFactory.getLogger(ValidatorInterceptor.class);

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
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpServletResponse response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
            baseController.setRequest(request);
            baseController.setResponse(response);
        }
        BeanResult beanResult = new BeanResult(true, "校验通过");
        for (int i = 0; i < arg0.getArguments().length; i++) {
            // 获取实际对象-即为原始对象(可能是Bean，也可能是字段)
            Object object = arg0.getArguments()[i];
            // 获取参数对象-用于后面获取该参数的注解
            Annotation[] annotations = arg0.getMethod().getParameterAnnotations()[i];
            // 优先校验bean
            VBean validBean = ValidatorHelper.getAnnotation(annotations, VBean.class);
            if (validBean != null) {
                beanResult = ValidatorHelper.validBean(object, validBean.group(), validBean.interrupt());
                break;
            }
            // 校验有注解的字段
            List<FieldResult> validFieldResultList = ValidatorHelper.valid(annotations, arg0.getMethod().getName() + "方法参数" + (i + 1), object, null, "", true);
            if (!validFieldResultList.isEmpty()) {
                beanResult = new BeanResult("校验存在" + validFieldResultList.size() + "条错误", validFieldResultList);
                break;
            }

        }
        logger.info("正在校验参数：" + fullName);
        if (baseController.getRequest() != null) {
            logger.info("请求URL参数：" + RequestDataUtil.getParameters(baseController.getRequest().getParameterMap()));
        }
        if (!beanResult.isPass()) {
            String tips = beanResult.getFieldResultList().get(0).getTips();
            logger.error(beanResult.getMessage() + "，详情请看：" + beanResult.getFieldResultList());
            logger.info("参数校验不通过：" + fullName);
            logger.info("响应内容：" + tips);
            // 如果该方法是标注未@ResponseBody或者该类标识为@RestController
            if (!arg0.getMethod().getReturnType().getName().equals("void") && (responseBody != null || restController != null)) {
                return baseController.parameterHint(tips);
            } else if (baseController.getRequest() != null && baseController.getResponse() != null) {
                baseController.parameterHintJSONP(tips);
            }
            return null;
        }
        logger.info("参数校验通过：" + fullName);
        return arg0.proceed();
    }


}

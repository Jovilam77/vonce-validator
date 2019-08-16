package cn.vonce.valid.intercept;

import cn.vonce.common.base.BaseController;
import cn.vonce.common.uitls.RequestDataUtil;
import cn.vonce.valid.annotation.VBean;
import cn.vonce.valid.helper.ValidFieldHelper;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证字段拦截器
 * 
 * @author jovi
 * @email 766255988@qq.com
 * @version 1.0
 * @date 2017年4月20日下午23:56:08
 */
public class ValidFieldInterceptor extends BaseController implements MethodInterceptor {

	private final Logger logger = LoggerFactory.getLogger(ValidFieldInterceptor.class);

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		Object result = null;
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		String fullName = arg0.getMethod().getReturnType().getSimpleName() + " "+ arg0.getThis().getClass().getName() + "." + arg0.getMethod().getName();
		ResponseBody responseBody = arg0.getMethod().getAnnotation(ResponseBody.class);
		List<String> messageList = new ArrayList<>();
		for (int i = 0; i < arg0.getArguments().length; i++) {
			// 获取实际对象-即为原始对象(可能是Bean，也可能是字段)
			Object object = arg0.getArguments()[i];
			// 获取参数对象-用于后面获取该参数的注解
			Annotation[] annotations = arg0.getMethod().getParameterAnnotations()[i];
			if (object instanceof HttpServletRequest) {
				request = (HttpServletRequest) object;
			}
			if (object instanceof HttpServletResponse) {
				response = (HttpServletResponse) object;
			}
			// 优先验证bean
			VBean validBean = ValidFieldHelper.getAnnotation(annotations, VBean.class);
			if (validBean != null) {
				messageList.addAll(ValidFieldHelper.validBean(object, validBean.group(), validBean.interrupt()));
				break;
			}

			// 验证有注解的字段
			List<String> mList = ValidFieldHelper.valid(annotations, object, null, "", true);
			if (mList != null && mList.size() > 0) {
				messageList.addAll(mList);
				break;
			}

		}

		logger.info("正在验证参数：" + fullName);
		if(request == null && arg0.getThis() instanceof BaseController){
			request = ((BaseController)arg0.getThis()).getRequest();
		}
		if (request != null) {
			logger.info("请求URL参数：" + RequestDataUtil.getParameters(request.getParameterMap()));
		} else {
			logger.info("请求URL参数：该方法缺少HttpServletRequest参数无法读取请求URL参数 ");
		}
		// 如果参数错误则直接包装返回结果，不继续往下执行
		if (messageList != null && messageList.size() > 0) {
			logger.warn("参数验证不通过：" + fullName);
			logger.warn("响应内容：" + messageList.toString());
			// 如果该注解存在
			if (responseBody != null) {
				return super.parameterHint("参数错误", messageList);
			} else if (request != null && response != null) {
				super.setRequest(request);
				super.setResponse(response);
				super.parameterHintJSONP("参数错误", messageList);
			}
			return null;
		}
		logger.info("参数验证通过：" + fullName);
		// 参数无错误则执行方法并返回返回值
		result = arg0.proceed();
		return result;
	}



}

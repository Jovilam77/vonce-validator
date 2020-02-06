package cn.vonce.validator.config;

import cn.vonce.validator.intercept.ValidatorInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自动配置字段验证器
 *
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020年02月05日下午23:22:00
 */
public class ValidatorConfig implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        if (beanDefinitionRegistry instanceof DefaultListableBeanFactory) {
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanDefinitionRegistry;
            ValidatorInterceptor validatorInterceptor = null;
            String[] validatorInterceptorNames = defaultListableBeanFactory.getBeanNamesForType(ValidatorInterceptor.class);
            if (validatorInterceptorNames == null || validatorInterceptorNames.length == 0) {
                beanDefinitionRegistry.registerBeanDefinition("validatorInterceptor", new RootBeanDefinition(ValidatorInterceptor.class));
            }
            validatorInterceptor = defaultListableBeanFactory.getBean(ValidatorInterceptor.class);
            //全局自动配置
            if (annotationMetadata.isAnnotated(EnableValidatorAutoConfig.class.getName())) {
                Map<String, Object> annotationAttributesMap = annotationMetadata.getAnnotationAttributes(EnableValidatorAutoConfig.class.getName());
                if (annotationAttributesMap != null && !annotationAttributesMap.isEmpty()) {
                    String[] expressions = (String[]) annotationAttributesMap.get("expressions");
                    if (expressions != null && expressions.length > 0) {
                        for (int i = 0; i < expressions.length; i++) {
                            if (!"".equals(expressions[i])) {
                                defaultListableBeanFactory.registerBeanDefinition("defaultPointcutAdvisor" + i, defaultPointcutAdvisorBeanDefinition(expressions[i], validatorInterceptor));
                            }
                        }
                    } else {
                        String[] controllerBeanNames = defaultListableBeanFactory.getBeanNamesForAnnotation(Controller.class);
                        if (controllerBeanNames != null && controllerBeanNames.length > 0) {
                            Set<String> packageNameSet = new HashSet<>();
                            for (String controllerBeanName : controllerBeanNames) {
                                String packageName = defaultListableBeanFactory.getBeanDefinition(controllerBeanName).getBeanClassName();
                                if (packageName != null) {
                                    packageName = packageName.substring(0, packageName.lastIndexOf("."));
                                    if (packageName.indexOf("spring") == -1 && packageName.indexOf("swagger") == -1) {
                                        packageNameSet.add(packageName);
                                    }
                                }
                            }
                            int i = 0;
                            for (String name : packageNameSet) {
                                defaultListableBeanFactory.registerBeanDefinition("defaultPointcutAdvisor" + i, defaultPointcutAdvisorBeanDefinition("execution(* " + name + "..*.*(..))", validatorInterceptor));
                                i++;
                            }
                        }
                    }
                }
            }
            //单个Controller配置
            else if (annotationMetadata.isAnnotated(EnableValidator.class.getName())) {
                defaultListableBeanFactory.registerBeanDefinition("defaultPointcutAdvisor" + annotationMetadata.getClassName(), defaultPointcutAdvisorBeanDefinition("execution(* " + annotationMetadata.getClassName() + ".*(..))", validatorInterceptor));
            }
        }

    }

    private BeanDefinition defaultPointcutAdvisorBeanDefinition(String expression, ValidatorInterceptor validatorInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DefaultPointcutAdvisor.class);
        beanDefinitionBuilder.addPropertyValue("pointcut", pointcut);
        beanDefinitionBuilder.addPropertyValue("advice", validatorInterceptor);
        return beanDefinitionBuilder.getBeanDefinition();
    }

}

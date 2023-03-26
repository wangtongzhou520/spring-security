package org.springsecurity.demo2.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * 资源服务器对外直接暴露URL
 *
 * @author wangtongzhou 
 * @since 2023-03-19 15:47
 */
@Service
public class NotAuthenticationConfig implements InitializingBean, ApplicationContextAware {

    private static final String PATTERN = "\\{(.*?)}";

    public static final String ASTERISK = "*";


    private ApplicationContext applicationContext;

    @Getter
    @Setter
    private List<String> permitAllUrls = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        map.keySet().forEach(x -> {
            HandlerMethod handlerMethod = map.get(x);

            // 获取方法上边的注解 替代path variable 为 *
            NotAuthentication method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), NotAuthentication.class);
            Optional.ofNullable(method).ifPresent(inner -> Objects.requireNonNull(x.getPathPatternsCondition())
                    .getPatternValues().forEach(url -> permitAllUrls.add(url.replaceAll(PATTERN, ASTERISK))));

            // 获取类上边的注解, 替代path variable 为 *
            NotAuthentication controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), NotAuthentication.class);
            Optional.ofNullable(controller).ifPresent(inner -> Objects.requireNonNull(x.getPathPatternsCondition())
                    .getPatternValues().forEach(url -> permitAllUrls.add(url.replaceAll(PATTERN, ASTERISK))));
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

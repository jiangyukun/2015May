package me.jiangyu.may.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * root.xml加载的bean
 * Created by jiangyukun on 2015/5/21.
 */
@Component
public class BeanClassPrinter implements BeanPostProcessor {
    private Logger logger = LoggerFactory.getLogger(BeanClassPrinter.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.debug(bean.getClass().getName());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

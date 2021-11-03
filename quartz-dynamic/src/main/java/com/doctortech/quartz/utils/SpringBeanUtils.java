package com.doctortech.quartz.utils;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: foo
 * @Date: 2021-10-26 14:54
 * @description:
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		if (SpringBeanUtils.applicationContext == null) {
			SpringBeanUtils.applicationContext = arg0;
		}
	}

	// 获取applicationContext
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	// 通过name获取 Bean.
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	// 通过class获取Bean.
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	// 通过name,以及Clazz返回指定的Bean
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}

	/**
	 * 注入bean
	 *
	 */
	public static synchronized void setBean(String beanName, Class<?> clazz, Map<String, Object> original) {
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) getApplicationContext()
				.getAutowireCapableBeanFactory();
		if (beanFactory.containsBean(beanName)) {
			return;
		}
		// BeanDefinition beanDefinition = new RootBeanDefinition(clazz);
		GenericBeanDefinition definition = new GenericBeanDefinition();
		// 类class
		definition.setBeanClass(clazz);
		// 属性赋值
		definition.setPropertyValues(new MutablePropertyValues(original));
		// 注册到spring上下文
		beanFactory.registerBeanDefinition(beanName, definition);
	}

	/**
	 * 删除bean by beanId
	 */
	public static void removeBean(String beanName) {
		DefaultListableBeanFactory acf = (DefaultListableBeanFactory) getApplicationContext()
				.getAutowireCapableBeanFactory();
		if (acf.containsBean(beanName)) {
			acf.removeBeanDefinition(beanName);
		}
	}

	/**
	 * 判断bean是否存在
	 */
	public static boolean containsBean(String beanName) {
		DefaultListableBeanFactory acf = (DefaultListableBeanFactory) getApplicationContext()
				.getAutowireCapableBeanFactory();
		return acf.containsBean(beanName);
	}
}


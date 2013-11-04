package com.lakala.demo.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {
	static ClassPathXmlApplicationContext context = null;
	static {
		if(context == null)
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static void start(){
		if(context == null){
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
			context.start();
		}
	}
	public static <T> T getBean(Class<T> type) {
		return context.getBean(type);
	}
	
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}
}

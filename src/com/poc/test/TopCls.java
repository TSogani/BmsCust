package com.poc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TopCls {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		ApplicationContext context = new ClassPathXmlApplicationContext("com/poc/cfg/application-context.xml");
		EISCDTest test= context.getBean("eiscdTest",EISCDTest.class);
		
		test.m1();
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("total taken time : "+totalTime/1000);
	}

}

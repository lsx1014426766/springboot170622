package com.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
/**
 * 业务层操作，web操作中，对模板处理的一些共用方法提取，放在接口中
 * request,response ,servlerContext,templateEngine
 * 它的作用相当于web中的HttpServlet接口，所有的controller层的对外接口需要统一实现或继承的类
 * process其实是对模版引擎templateEngine的process方法进行了一步封装
 * @author lsx
 *
 */
public interface IGTVGController {

	
	public void process(HttpServletRequest request,
			HttpServletResponse response, ServletContext servletContext,
			TemplateEngine templateEngine);

	public boolean process(HttpServletRequest request,
			HttpServletResponse response);

}
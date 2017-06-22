package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
/**
 * controller接口
 * thymeleaf也提供了web上下文对象   webContext
 * 如何访问 这个应用呢？
 * @author lsx
 *
 */
public class HomeController implements IGTVGController {

	/**
	 * 简单的处理home.html模板，并填充数据 today
	 */
	public void process(HttpServletRequest request,
			HttpServletResponse response, ServletContext servletContext,
			TemplateEngine templateEngine) {
		//包含模板引擎所需要的all data
		WebContext ctx = 
	            new WebContext(request, response, servletContext, request.getLocale());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
	    Calendar cal = Calendar.getInstance();
	    ctx.setVariable("today", dateFormat.format(cal.getTime()));
	        try {
				templateEngine.process("home", ctx, response.getWriter());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/*
     * Query controller/URL mapping and obtain the controller
     * that will process the request. If no controller is available,
     * return false and let other filters/servlets process the request.
     * at the web layer our application will have a filter that will delegate execution to Thymeleaf-enabled commands depending on the request URL
     */
	public boolean process(HttpServletRequest request,
			HttpServletResponse response) {
	/*	try {
			 IGTVGController controller = GTVGApplication.resolveControllerForRequest(request);
		        if (controller == null) {
		            return false;
		        }
		        TemplateEngine templateEngine = GTVGApplication.getTemplateEngine();
		        
		        response.setContentType("text/html;charset=UTF-8");
		        response.setHeader("Pragma", "no-cache");
		        response.setHeader("Cache-Control", "no-cache");
		        response.setDateHeader("Expires", 0);
		        
		        controller.process(
		                request, response, this.servletContext, templateEngine);

		        return true;
		            
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		return false;
	}


}

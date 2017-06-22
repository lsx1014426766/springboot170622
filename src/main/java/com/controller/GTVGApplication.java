package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
/**
 * 获取模板引擎，并初始化一些基础的配置信息
 * @author lsx
 *
 */
public class GTVGApplication {
	    private static TemplateEngine templateEngine;
	    
	    
	    static {
	        initializeTemplateEngine();
	    }
	    
	    
	    public static TemplateEngine getTemplateEngine() {
			return templateEngine;
		}


		public static void setTemplateEngine(TemplateEngine templateEngine) {
			GTVGApplication.templateEngine = templateEngine;
		}
/**
 * 
 * 对thymeleaf使用的一些初始化配置信息，也可以使用配置文件的方式
 * 1模板模式 template mode
 * 2模板文件的统一存放包路径
 * 3模板文件后缀
 * 4设置缓存策略，如果没有设置，也有默认的LRU
 * 5关键对象：模板解析器 template resolver  模板引擎template engine
 * 
 * 换种方式写在xml配置文件里application.properties
 *  #spring.thymeleaf.prefix=classpath:/templates/  
	#spring.thymeleaf.suffix=.html  
	#spring.thymeleaf.mode=HTML5  
	#spring.thymeleaf.encoding=UTF-8  
	# ;charset=<encoding> is added  
	#spring.thymeleaf.content-type=text/html  
 */

		private static void initializeTemplateEngine() {
	        //模板解析
	        ServletContextTemplateResolver templateResolver = 
	            new ServletContextTemplateResolver();
	        // XHTML is the default mode, but we set it anyway for better understanding of code
	        templateResolver.setTemplateMode("XHTML");
	        // This will convert "home" to "/WEB-INF/templates/home.html"
	        templateResolver.setPrefix("/WEB-INF/templates/");
	        templateResolver.setSuffix(".html");
	        // Template cache TTL=1h. If not set, entries would be cached until expelled by LRU
	        templateResolver.setCacheTTLMs(3600000L);
	        
	        templateEngine = new TemplateEngine();
	        //模板引擎
	        templateEngine.setTemplateResolver(templateResolver);
	        
	    }


		public static IGTVGController resolveControllerForRequest(
				HttpServletRequest request) {
			// TODO Auto-generated method stub
			return null;
		}
	    

	}
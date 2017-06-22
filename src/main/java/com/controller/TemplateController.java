package com.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test1")  
public class TemplateController {
	//http://localhost:8080/lsx/test1/hello1
	@RequestMapping("/hello1")  
    public String helloHtml(Map<String,Object> map){  
  
       map.put("name","from TemplateController.helloHtml");  
       return"/hello";  
    }  
}

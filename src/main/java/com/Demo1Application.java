package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
/*
 * 注意此为启动入口，所有的配置如果Application类所在的包为：com.boot.app，则只会扫描com.boot.app包及其所有子包，如果service或dao所在包不在com.boot.app及其子包下，则不会被扫描！
即, 把Application类放到dao、service所在包的上级，com.boot.Application
这样子才会扫描到注解，而且不需要再手动配置一些bean，因为此时省略了spring的dao，service的一些bean
全部由此类自行处理
并且自己在pom.xml里找到web包后 ，就知道这是个web项目了，然后启动内嵌的jetty/tomcat容器
还有像jdbcTemplate这个类也不需要像以前那样先交由spring管理，在xml中写上此bean在自行注入@Autowired
这里springboot应该是都已经处理了
前提是这些包类都必须放在spring的包及其子包下

访问的上下文名字不是demo1 /  没有名字
默认在哪里配置呢？也是application.properties server.context-path=/lsx
thymeleaf是springboot的默认模板，springboot已经提供了默认实现相关的配置




springboot应用启动入口
Spring WebMvc框架会将Servlet容器里收到的HTTP请求根据路径分发给对应的@Controller类进行处理，
@RestController是一类特殊的@Controller，它的返回值直接作为HTTP Response的Body部分返回给浏览器
 */
//@RestController
@Controller
public class Demo1Application {
//启动当前应用的方式：mvn spring-boot:run或在IDE中运行Demo1Application.main()方法
	public static void main(String[] args) {
		/*
		 * SpringApplication是Spring Boot框架中描述Spring应用的类，
		 * 它的run()方法会创建一个Spring应用上下文（Application Context）。
		 * 另一方面它会扫描当前应用类路径上的依赖，
		 * 例如本例中发现spring-webmvc（由 spring-boot-starter-web传递引入）在类路径中，
		 * 那么Spring Boot会判断这是一个Web应用，
		 * 并启动一个内嵌的Servlet容器（默认是Tomcat）用于处理HTTP请求
		 */
		SpringApplication.run(Demo1Application.class, args);
	//	Demo1Application demo = new Demo1Application();
		//System.out.println(demo.dataSource());
	}
	//http://localhost:8080/lsx
	 @RequestMapping("/")
	    public String greeting() {
	        return "hello";
	  }
	 /*
	  * 在Web应用中URL通常不是一成不变的，例如微博两个不同用户的个人主页对应两个不同的URL
	  * :http://weibo.com/user1，http://weibo.com/user2。
	  * 我们不可能对于每一个用户都编写一个被@RequestMapping注解的方法来处理其请求，
	  * Spring MVC提供了一套机制来处理这种情况
	  * URL中的变量可以用{variableName}来表示，
	  * 同时在方法的参数中加上@PathVariable("variableName")，
	  * 那么当请求被转发给该方法处理时，对应的URL中的变量会被自动赋值给被@PathVariable注解的参数
	  * （能够自动根据参数类型赋值，例如上例中的int）
	  */
	//http://localhost:8080/lsx/users/lsxxx
	 @RequestMapping("/users/{username}")
	 @ResponseBody  //指定返回json字符串，not视图名viewname
	 public String userProfile(@PathVariable("username") String username) {
	     return String.format("user %s", username);//%s string
	 }
	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	 @ResponseBody
	 public String loginGet() {
	     return "Login Page";
	 }

	 //http://localhost:8080/lsx/login
	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	 @ResponseBody
	 public String loginPost() {
	     return "Login Post Request";
	 }
	 /*
	  * 为了能够进行模板渲染，需要将@RestController改成@Controller
	  * @RestController 直接将对象转化为json返回
	  * @Controller返回modelAndView 数据+视图
	  * localhost:8080/lsx/hello/lsxss
	  */
	 @RequestMapping("/hello/{name}")
	    public String hello(@PathVariable("name") String name, Model model) {
	        model.addAttribute("name", name);
	        return "hello";//resources/templates/hello.html
	    }
	 //这里自己定义bean可以没有，因为springboot已经通过application.properties实现了db配置
	/* @Autowired
	  private Environment env;
	 //直接在方法上注入 获取数据源 
	  @Bean
	    public DataSource dataSource() {
	        DataSource dataSource = new DataSource();
	        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver"));
	        dataSource.setUrl(env.getProperty("spring.datasource.url"));
	        dataSource.setUsername(env.getProperty("spring.datasource.username"));
	        dataSource.setPassword(env.getProperty("spring.datasource.password"));
	        return dataSource;
	    }*/
}

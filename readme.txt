
springboot默认模板实现：thymeleaf

测试启动正常，访问不了404？？？？ 启动项目的context-path搞错了
启动入口类：Demo1Application
resource包下：spring包下的配置文件和controller包下的几个配置的也都没用
SpringBoot自动配置功能  application.properties 在这里可以自定义配置信息，覆盖默认的
thymeleaf语法
表达式语法
message and variable expressions:
message  例 #{home}
variable ognl表达式 ${user.name}

关于启动方式：

启动当前应用的方式：mvn spring-boot:run或在IDE中运行Demo1Application.main()方法

 * 注意此Demo1Application为启动入口，所有的配置如果Application类所在的包为：com.boot.app，
 则只会扫描com.boot.app包及其所有子包，
 如果service或dao所在包不在com.boot.app及其子包下，则不会被扫描！
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


local message消息传递
关于local配置信息properties文件要放在和html同一个包下，并且要同名，这是在使用标准默认的获取message接口的情况下
属性文件也不全是静态的static,当需要动态传参时 使用java的MessageFormat：
<p th:utext="#{home.welcome(${session.user.name})}">
  Welcome to our grocery store, Sebastian Pepper!
</p>
注意：不止value可以动态获取，key也可以作为参数传递，当value中需要传递多个值{0}{1}时，用逗号隔开：
<p th:utext="#{${welcomeMsgKey}(${session.user.name})}">
  Welcome to our grocery store, Sebastian Pepper!
</p>
thymeleaf标准方言   和   spring 标准方言

下面是从文档摘下的部分语法表达式

Simple expressions:

    Variable Expressions: ${...}
    Selection Variable Expressions: *{...}
    Message Expressions: #{...}
    Link URL Expressions: @{...}
    
Text operations:

    String concatenation: +
    Literal substitutions: |The name is ${name}|
    
Boolean operations:

    Binary operators: and, or
    Boolean negation (unary operator): !, not
    
Comparisons and equality:

    Comparators: >, <, >=, <= (gt, lt, ge, le)
    Equality operators: ==, != (eq, ne)

Conditional operators:

    If-then: (if) ? (then)
    If-then-else: (if) ? (then) : (else)
    Default: (value) ?: (defaultvalue)
    
All these features can be combined and nested:

'User is of type ' + (${user.isAdmin()} ? 'Administrator' : (${user.type} ?: 'Unknown'))


But getter method navigation is just one of OGNL’s features. Let’s see some more:

/*
 * Access to properties using the point (.). Equivalent to calling property getters.
 */
${person.father.name}

/*
 * Access to properties can also be made by using brackets ([]) and writing 
 * the name of the property as a variable or between single quotes.
 */
${person['father']['name']}

/*
 * If the object is a map, both dot and bracket syntax will be equivalent to 
 * executing a call on its get(...) method.
 如果是map对象，点语法和中括号语法均可
 */
${countriesByCode.ES}
${personsByName['Stephen Zucchini'].age}

/*
 * Indexed access to arrays or collections is also performed with brackets, 
 * writing the index without quotes.
      数组对象
 */
${personsArray[0].name}

/*
 * Methods can be called, even with arguments.
 直接调用方法，可传参数
 */
${person.createCompleteName()}
${person.createCompleteNameWithSeparator('-')}

${}  *{}   美元符号和星号这样同等
 as long as there is no selected object, the dollar and the asterisk syntaxes do exactly the same.
 
 
Boolean literals

The boolean literals are true and false. For example:

<div th:if="${user.isAdmin()} == false"> ...

注意：上面==写在大括号外面是thymeleaf的语法，写在里面是ognl和spring el的语法

Note that in the above example, the == false is written outside the braces, and thus it is Thymeleaf itself who takes care of it. If it were written inside the braces,
 it would be the responsibility of the OGNL/SpringEL engines:

<div th:if="${user.isAdmin() == false}"> ...

如果文本中的部分内容是动态填充的，则整个语句需要用竖线包裹，这等价于使用加号这个字符串连接符

 These substitutions must be surrounded by vertical bars (|), like:

<span th:text="|Welcome to our application, ${user.name}!|">

Which is actually equivalent to:

<span th:text="'Welcome to our application, ' + ${user.name} + '!'">

多种符号表达式可以同时存在
Literal substitutions can be combined with other types of expressions:

<span th:text="${onevar} + ' ' + |${twovar}, ${threevar}|">

Note: only variable expressions (${...}) are allowed inside |...| literal substitutions. No other literals ('...'), boolean/numeric tokens, conditional expressions etc. are.

 they can be variables (${...}, *{...}), messages (#{...}), URLs (@{...}) or literals ('...').
 
 <div th:object="${session.user}">
  ...
  <p>Age: <span th:text="*{age}?: '(no age specified)'">27</span>.</p>
</div>
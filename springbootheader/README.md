## Springboot 获取 Header 参数

#### 场景
* 在开发过程中，与前端交互的时候，基础信息(比如设备信息)会通过 `Header` 传递参数。需要拿到 header 中的参数做校验或者记录信息系。

#### 获取 Header 参数方法
##### 方法一
* 通过在类中注入 `HttpServletRequest` ，然后拿到 header
    ```java
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/printname/{name}", method = RequestMethod.GET)
    public String getHeader1(@PathVariable String name) {
        String token = httpServletRequest.getHeader("token");
        logger.debug("header:" + token + ", name:" + name);
        return "header-> token:" + token + ", name:" + name;
    }
    ```

##### 方法二
* 在方法参数中加入`@RequestHeader` 获取到 header ，这种可以有两种获取方式。代码如下： 
    ```java
    @GetMapping(value = "/printname2/{name}")
    public String getHeader2(@PathVariable String name, @RequestHeader(value = "token") String token) {
        logger.debug("header:" + token + ", name:" + name);
        return "header-> token:" + token + ", name:" + name;
    }
    ```
  或者
    ```java
    @GetMapping(value = "/printname3/{name}")
    public String getHeader3(@PathVariable String name, @RequestHeader HttpHeaders headers) {
        logger.debug("header:" + headers.getFirst("token") + ", name:" + name);
        logger.debug("header:" + headers.get("token").get(0) + ", name:" + name);
        return "header-> token:" + headers.getFirst("token") + ", name:" + name;
    }
    ```

##### 方法三
* 自定义注解 + 自定义参数解析器 + 注册解析器 
    1. 定义注解：`RequestBodyAndHeader`
        ```java
        @Target(ElementType.PARAMETER)
        @Retention(RetentionPolicy.RUNTIME)
        @Documented
        public @interface RequestBodyAndHeader {
            boolean required() default true;
        }
        ```
    2. 定义注解解析器 `RequestBodyAndHeaderResolver`，继承 `RequestResponseBodyMethodProcessor`
        ```java
        @Component
        public class RequestBodyAndHeaderResolver extends RequestResponseBodyMethodProcessor {
        
            public RequestBodyAndHeaderResolver(List<HttpMessageConverter<?>> converters) {
                super(converters);
            }
        
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return parameter.hasParameterAnnotation(RequestBodyAndHeader.class);
            }
        
            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
                Object result = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
                HashMap<String, String> headerParams = new HashMap<>(10);
                Iterator<String> headerNames = webRequest.getHeaderNames();
                headerNames.forEachRemaining(headerName -> headerParams.put(headerName, webRequest.getHeader(headerName)));
                BeanUtils.populate(result, headerParams);
                return result;
            }
        }
        ```
    3. 注册解析器：在配置类的 `addArgumentResolvers` 方法中注册解析器
        ```java
        @Configuration
        public class WebConfig implements WebMvcConfigurer {
        
            @Autowired
            private RequestBodyAndHeaderResolver resolver;
        
            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
                resolvers.add(resolver);
            }
        }
        ```

> 注意： header 大小写不敏感，https://stackoverflow.com/questions/5258977/are-http-headers-case-sensitive。     
> 参考： https://www.w3.org/Protocols/rfc2616/rfc2616-sec4.html#sec4.2    
> 参考： https://www.cnblogs.com/yuwentims/articles/9721953.html    
> 参考： https://www.jianshu.com/p/bf59fbf5ccce

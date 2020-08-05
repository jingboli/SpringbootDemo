# Springboot 集成 Redis

### Redis 的使用
1. 添加 Redis 依赖

    ```$xslt
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-pool2</artifactId>
    </dependency>
    ```
2. 配置 Redis 环境

    ```yaml
    spring:
      redis:
        database: 0
        host: 127.0.0.1
        port: 6379
        password: 123456
        lettuce:
          pool:
            max-active: 8
            max-idle: 8
            max-wait: -1ms
            min-idle: 0
    ```

3. 配置 Redis config   
    
    ```java
    @Configuration
    @ConditionalOnClass(RedisOperations.class)
    @EnableConfigurationProperties(RedisProperties.class)
    //@Import({ LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class })
    public class RedisAutoConfiguration {
        @Bean
        @ConditionalOnMissingBean(name = "redisTemplate")
        public RedisTemplate<String, Object> redisTemplate(
                RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
            RedisTemplate<String, Object> template = new RedisTemplate<>();
            template.setConnectionFactory(redisConnectionFactory);
            template.setKeySerializer(new StringRedisSerializer());
            template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
            template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
            template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
            template.afterPropertiesSet();
            return template;
        }
    
        @Bean
        @ConditionalOnMissingBean
        public StringRedisTemplate stringRedisTemplate(
                RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
            StringRedisTemplate template = new StringRedisTemplate();
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }
    }
    ```
       
4. 在 RedisUtils 中使用 redisTemplate

    ```java
    @Component
    public class RedisUtils {
    
        /**
         * 注入redisTemplate bean
         */
        @Autowired
        private RedisTemplate<String,Object> redisTemplate;
        //...
    }    
    ```
5. Bean 实现序列化

    ```java
    public class UserInfo implements Serializable {
        // ...
    }
    ```
6. key,value 存入 redis 

    ```java
   redisUtils.set(key, userInfo, 30);
    ```
   
5. 根据 key 取出来缓存
    ```java
    Object o = redisUtils.get("987456123____wps");
    ```






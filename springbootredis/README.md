# Springboot Mybatis 集成 Redis
### 版本信息
1. Sprintboot 采用 2.1.7 RELEASE 版本
2. Mybatis 采用 2.1.0
3. Redis 采用 2.1.6.RELEASE

### Redis 的使用
1. 添加 Redis 依赖

    ```$xslt
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis</artifactId>
      <version>2.1.6.RELEASE</version>
    </dependency>
    ```
2. 开启缓存   
    
    ```java
    @SpringBootApplication
    @EnableCaching
    public class HelloApplication {
        public static void main(String[] args) {
            SpringApplication.run(HelloApplication.class, args);
        }
    } 
    ```
    * 使用注解 @EnableCaching 开启缓存
       
2. 添加缓存注解

    ```java
        @Override
        @Cacheable(value = "UserCache", key = "'user.getAllUsers'")
        public List<User> getAllUsers() {
            return userMapper.getAllUsers();
        }
    ```
   * 在业务逻辑类的方法上添加 @Cacheable 注解来支持缓存
   * @Cacheable 注解中的 key 属性值除了需要被英文双引号引用外，还需要加入英文单引号
3. Bean 实现序列化

    ```java
    public class User implements Serializable {
    
        private static final long serialVersionUID = 1L;
    
        private Integer id;
        private String username;
        private String address;
    
        public User() {
    
        }
    
        public User(Integer id, String username, String address) {
            this.id = id;
            this.username = username;
            this.address = address;
        }
    
        public Integer getId() {
            return id;
        }
    
        public void setId(Integer id) {
            this.id = id;
        }
    
        public String getUsername() {
            return username == null ? "" : username;
        }
    
        public void setUsername(String username) {
            this.username = username;
        }
    
        public String getAddress() {
            return address == null ? "" : address;
        }
    
        public void setAddress(String address) {
            this.address = address;
        }
    }
    ```
4. 指定 Redis 缓存地址

    ```xml
      redis:
        host: localhost
        port: 6379
    ```
   * 在 Application.yml 文件指定 Redis 的地址和端口号
   
5. 清除 Redis 缓存
    ```java
        @Override
        @CacheEvict(value = "UserCache", key = "'user.getAllUsers'")
        public void delete(Integer id) {
            System.out.println("删除了 id 为" + id + "的用户");
            userMapper.delete(id);
        }
    ```
   * 当删除了数据库的数据的时候，对 Redis 中缓存的数据进行清除。
   * @CacheEvict 注解，与 @Cacheable 注解配置完全相同
6. 启动项目测试缓存





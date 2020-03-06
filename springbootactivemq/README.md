# Springboot 集成 ActiveMQ
### 版本信息
1. Sprintboot 采用 2.1.7 RELEASE 版本

### ActiveMQ 的使用
1. 添加 ActiveMQ 依赖

    ```xml
    <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-activemq</artifactId>
    </dependency>
    ```
    * pom 文件添加 ActiveMQ 依赖
    
2. 添加 ActiveMQ 远程地址和端口

    ```xml
      activemq:
        broker-url: tcp://localhost:61617
    ```
   * yml 文件配置远程地址和端口
   
3. 创建消息队列对象

    ```java
        @Bean
        public Queue queue(){
            return new ActiveMQQueue("active.queue");
        }
    ```

4. 创建消息生产者

    ```java
        @RequestMapping("/send")
        public void send() {
            String message = "新发送的第 %s 条消息!";
            for (int i = 0; i < 10; i++) {
                jmsMessagingTemplate.convertAndSend(queue, String.format(message, i));
            }
        }
    ```

5. 创建消息监听者

    ```java
        @JmsListener(destination = "active.queue")
        public void readActiveQueue(String message) {
            System.out.println("接收到消息：" + message);
        }
    ```

6. 测试应用
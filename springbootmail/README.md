# Springboot 集成 Mail
### 版本信息
1. Sprintboot 采用 2.1.7 RELEASE 版本

### Mail 的使用
1. 添加 Mail 依赖

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    ```
2. Application.yml 文件配置

    ```xml
      mail:
        host: smtp.yeah.net
        username: xxx
        password: xxx
        default-encoding: UTF-8
        properties:
          mail:
            smtp:
              auth: true
              starttle:
                enable: true
                required: true
    
    lance:
      mail:
        sender: xxx@xxx.net
    ```
   * host, username,password 这几个参数，请登录自己的邮箱找
  
3. 发送一个简单文本邮件

    ```java
        @Override
        public void sendTextMail(MailBean mailBean) {
            List<String> recipient = mailBean.getRecipient();
            List<String> mailBeanCc = mailBean.getCc();
            String[] to = recipient.toArray(new String[recipient.size()]);
            String[] cc = mailBeanCc.toArray(new String[mailBeanCc.size()]);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(mailSender);
            simpleMailMessage.setCc(cc);
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject("Springboot mail test");
            simpleMailMessage.setText("Springboot test mail content");
            javaMailSender.send(simpleMailMessage);
        }
    ```
   * MailBean 封装了邮件的主题，内容，收件人，cc 等信息，可查看源码
4. 发送 HTML 格式的邮件
    1. 首先添加解析 HTML 的依赖
    
        ```xml
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-thymeleaf</artifactId>
            </dependency>
        ```
    
    2. 在 resources 的 templates 目录下，创建 html 文件 index.html
       
        ```html
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <title>用户信息</title>
            <link rel="stylesheet" type="text/css" href="ui/themes/default/easyui.css">
            <link rel="stylesheet" type="text/css" href="ui/themes/icon.css">
            <script type="text/javascript" src="ui/jquery.min.js"></script>
            <script type="text/javascript" src="ui/jquery.easyui.min.js"></script>
            <script type="text/javascript" src="ui/locale/easyui-lang-zh_CN.js"></script>
            <script type="text/javascript">
                $(function a(){
                    $('#grid').datagrid({
                        url:'user/userList',
                        fit:true,
                        columns:[[
                            {field:'id',title:'编号',width:50},
                            {field:'username',title:'姓名',width:200},
                            {field:'address',title:'地址',width:200}
                        ]]
                    });
                });
            </script>
        </head>
        <body>
        <table id="grid"></table>
        </body>
        </html>
        ```
    3. 发送 HTML 邮件
    
        ```java
        @Value("${lance.mail.sender}")
        private String mailSender;
    
        @Autowired
        private JavaMailSender javaMailSender;
    
        @Autowired
        private TemplateEngine templateEngine;
    
        @Override
        public void sendHtmlMail(MailBean mailBean) {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            try {
                List<String> recipient = mailBean.getRecipient();
                List<String> mailBeanCc = mailBean.getCc();
                String[] to = recipient.toArray(new String[recipient.size()]);
                String[] cc = mailBeanCc.toArray(new String[mailBeanCc.size()]);
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom(mailSender);
                helper.setTo(to);
                helper.setCc(cc);
                helper.setSubject(mailBean.getSubject());
                helper.setText(getHtmlContent(mailBean.getContent()), true);
                javaMailSender.send(mimeMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        ```
       * 在 MimeMessageHelper 的 setText 中，设置为 true ，就可以发送 html 邮件了。
       
5. 发送带有附件的 Mail

    ```java
    @Value("${lance.mail.sender}")
    private String mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;
    
    @Override
    public void sendAttathMentMail(MailBean mailBean) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            List<String> recipient = mailBean.getRecipient();
            List<String> mailBeanCc = mailBean.getCc();
            String[] to = recipient.toArray(new String[recipient.size()]);
            String[] cc = mailBeanCc.toArray(new String[mailBeanCc.size()]);
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailSender);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(mailBean.getSubject());
            helper.setText(getHtmlContent(mailBean.getContent()), true);
            addAttachment(helper, ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static" + File.separator + "user.html");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    ```
6. 其他代码

    ```java
    private String getHtmlContent(String content) {
        Context context = new Context();
        context.setVariable("id", content);
        return templateEngine.process("index", context);
    }

    private void addAttachment(MimeMessageHelper helper, String filepath) throws MessagingException {
        FileSystemResource fileSystemResource = new FileSystemResource(new File(filepath));
        String fileName = filepath.substring(filepath.lastIndexOf(File.separator));
        helper.addAttachment(fileName, fileSystemResource);
    }
    ```
   
# springboot 定时任务

## 使用注解方式

## 实现 SchedulingConfigurer 接口
* 实现 configureTasks 方法
```java
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                doSomethings();
            }
        }, triggerContext -> {
            String cron = cronMapper.getCron();
            if (StringUtils.isEmpty(cron)) {
                System.out.println("动态执行定时任务失败：cron 为 empty " + LocalDateTime.now().toLocalTime());
            }
            return new CronTrigger(cron).nextExecutionTime(triggerContext);
        });
    }
```

* 在方法 doSomethings() 中处理业务数据

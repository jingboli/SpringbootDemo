package cc.lijingbo.scheduled.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;


@Configuration
public abstract class DynamicScheduleTask implements SchedulingConfigurer {

    @Mapper
    public interface CronMapper {
        @Select("select cron from cron limit 1")
        public String getCron();
    }

    @Autowired
    @SuppressWarnings("all")
    CronMapper cronMapper;

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

   protected abstract void doSomethings();
}

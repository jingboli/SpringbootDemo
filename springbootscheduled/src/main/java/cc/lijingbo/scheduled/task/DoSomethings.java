package cc.lijingbo.scheduled.task;

import cc.lijingbo.scheduled.config.DynamicScheduleTask;
import cc.lijingbo.scheduled.dao.CheckAccountMapper;
import cc.lijingbo.scheduled.domain.CheckAccountBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DoSomethings extends DynamicScheduleTask {

    @Autowired
    private CheckAccountMapper checkAccountMapper;

    @Override
    protected void doSomethings() {
        System.out.println("动态执行定时任务： " + LocalDateTime.now().toLocalTime());
        CheckAccountBean checkAccountBean = checkAccountMapper.queryInfoByUserName("xxx");
        System.out.println(checkAccountBean.toString());
    }
}

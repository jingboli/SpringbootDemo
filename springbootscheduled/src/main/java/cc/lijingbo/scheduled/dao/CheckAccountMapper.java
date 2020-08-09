package cc.lijingbo.scheduled.dao;


import cc.lijingbo.scheduled.domain.CheckAccountBean;

public interface CheckAccountMapper {
    CheckAccountBean queryInfoByUserName(String userName);
}

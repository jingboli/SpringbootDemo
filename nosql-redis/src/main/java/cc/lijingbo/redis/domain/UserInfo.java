package cc.lijingbo.redis.domain;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private String user_name;
    private String email;
    private String user_id;
    private String employee_number;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_name='" + user_name + '\'' +
                ", email='" + email + '\'' +
                ", user_id='" + user_id + '\'' +
                ", employee_number='" + employee_number + '\'' +
                '}';
    }
}

package cc.lijingbo.scheduled.domain;

public class CheckAccountBean {

    private String userName;
    private String password;
    private boolean stauts;

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStauts() {
        return stauts;
    }

    public void setStauts(boolean stauts) {
        this.stauts = stauts;
    }

    @Override
    public String toString() {
        return "CheckAccountBean{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", stauts=" + stauts +
                '}';
    }
}

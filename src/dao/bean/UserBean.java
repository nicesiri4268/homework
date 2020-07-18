package dao.bean;

import dao.baseDao.IBean;

public class UserBean implements IBean {
    /**
     * @program: Dao层
     * @description: 用户表的所有东西
     * @author: niceSiri
     * @create: 2020-07-05 21:31
     */
    public int ID = -1;//不存在set函数，由数据库自动设置，默认为-1
    public String userName;
    public String password;
    public String userSex;
    public String address;
    public String email;
    public String SignUpTime = null;//不存在set函数，由数据库自动设定,默认为null
    public int isVIP = 0;//默认为零，不是vip
    public int userState = 0;//具有不同的数字表示用户状态

    public UserBean() {
    }
    public UserBean(String userName, String password,
                    String userSex, String address, String email,
                    int isVIP, int userState) {
        //给从数据库创建userbean使用的
        this.userName = userName;
        this.password = password;
        this.userSex = userSex;
        this.address = address;
        this.email = email;
        this.isVIP = isVIP;
        this.userState = userState;
    }

    public UserBean(int ID, String userName, String password,
                    String userSex, String address, String email,
                    String signUpTime, int isVIP, int userState) {
        //给从数据库创建userbean使用的
        this.ID = ID;
        this.userName = userName;
        this.password = password;
        this.userSex = userSex;
        this.address = address;
        this.email = email;
        this.SignUpTime = signUpTime;
        this.isVIP = isVIP;
        this.userState = userState;
    }

    public UserBean(String userName, String password, String userSex,
                    String address, String email) {
        //用途注册页面，ID=-1，不进行设置，isVip=0，UserState=0
        this.userName = userName;
        this.password = password;
        this.userSex = userSex;
        this.address = address;
        this.email = email;
    }


    public int getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

    public UserBean setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserBean setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUserSex() {
        return userSex;
    }

    public UserBean setUserSex(String userSex) {
        this.userSex = userSex;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserBean setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserBean setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSignUpTime() {
        return SignUpTime;
    }

    public int getIsVIP() {
        return isVIP;
    }

    public UserBean setIsVIP(int isVIP) {
        this.isVIP = isVIP;
        return this;
    }

    public int getUserState() {
        return userState;
    }

    public UserBean setUserState(int userState) {
        this.userState = userState;
        return this;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "ID=" + ID +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", SingUpTime='" + SignUpTime + '\'' +
                ", isVIP=" + isVIP +
                ", userState=" + userState +
                '}';
        //不显示密码但是可以显示其他信息
    }
}

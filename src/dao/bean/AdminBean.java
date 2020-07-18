package dao.bean;

import dao.baseDao.IBean;

public class AdminBean implements IBean {
    /**
     * @program: Dao层
     * @description: Admin表的内容
     * @author: niceSiri
     * @create: 2020-07-08 11:23
     */
    private int adminID = -1;
    private int adminJobID;
    private int adminLevel;
    private String adminName;
    private String adminPassword;
    private int adminState = 0;

    public AdminBean() {
    }

    public AdminBean(int adminID, int adminJobID, int adminLevel, String adminName, String adminPassword, int adminState) {
        //完整的建立
        this.adminID = adminID;
        this.adminJobID = adminJobID;
        this.adminLevel = adminLevel;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.adminState = adminState;
    }

    public AdminBean(int adminJobID, int adminLevel, String adminName, String adminPassword, int adminState) {
        this.adminJobID = adminJobID;
        this.adminLevel = adminLevel;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.adminState = adminState;
    }

    public int getAdminID() {
        return adminID;
    }

    public AdminBean setAdminID(int adminID) {
        this.adminID = adminID;
        return this;
    }

    public int getAdminJobID() {
        return adminJobID;
    }

    public AdminBean setAdminJobID(int adminJobID) {
        this.adminJobID = adminJobID;
        return this;
    }

    public int getAdminLevel() {
        return adminLevel;
    }

    public AdminBean setAdminLevel(int adminLevel) {
        this.adminLevel = adminLevel;
        return this;
    }

    public String getAdminName() {
        return adminName;
    }

    public AdminBean setAdminName(String adminName) {
        this.adminName = adminName;
        return this;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public AdminBean setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
        return this;
    }

    public int getAdminState() {
        return adminState;
    }

    public AdminBean setAdminState(int adminState) {
        this.adminState = adminState;
        return this;
    }

    @Override
    public String toString() {
        return "AdminBean{" +
                "adminID=" + adminID +
                ", adminJobID=" + adminJobID +
                ", adminLevel=" + adminLevel +
                ", adminName='" + adminName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", adminState=" + adminState +
                '}';
    }
}

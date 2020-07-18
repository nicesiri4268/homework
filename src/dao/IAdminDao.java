package dao;

import dao.baseDao.IDao;
import dao.bean.AdminBean;

import java.util.List;

public interface IAdminDao<T> extends IDao {
    public boolean checkAdminName(String adminName);//检查管理员的名称是否存在

    public boolean signUp(AdminBean adminBean);//管理员注册

    public boolean signIn(String adminName, String adminPassword);//管理员的登录

    public boolean deleteAdmin(int adminID);//根据工号删除管理员

    public int getAdminJobID(String adminName);//获取管理员工号

    public boolean setAdminJobID(String adminName, int jobID);//设置管理员工号

    public int getAdminLevel(String adminName);//获取管理员等级

    public boolean setAdminLevel(String adminName, int adminLevel);//设置管理员等级

    public int getAdminState(String adminName);//检查管理员的状态

    public boolean setAdminState(String adminName, int adminState);//设置管理员的状态

    public boolean resetAdminPassword(String adminName, String oldPassword, String newPassword);//重设管理员的密码

    public List<T> allAdmin();//获取所有管理员信息

    public boolean close();//关闭数据库连接
}

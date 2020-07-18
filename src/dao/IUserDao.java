package dao;

import dao.baseDao.IBean;
import dao.baseDao.IDao;
import dao.bean.UserBean;

import java.util.List;

public interface IUserDao<T extends IBean> extends IDao<T> {
    public boolean checkUserName(T userBean);//检查用户名是否已存在

    public boolean signUp(T userBean); //用户注册

    public boolean login(String userName, String password); //用户登录

    public boolean deleteUser(int userID, String userName);//删除用户

    public int getUserState(String userName, String password);//检查用户状态

    public boolean setUserState(String userName, int userState);//设置用户状态

    public boolean updatePassword(String userName, String oldPassword, String newPassword); //用户修改密码

    public boolean updateOtherMsgs(T userBean);//重新设置用户的具体信息

    public boolean updateUserAll(T userBean ,int userID);//重设用户所有信息

    public UserBean getUserMsgs(int userID);//根据用户的ID获取用户数据

    public int getUserID(String userName);//获取用户的ID
    public String getUserAddress(String userName);//根据用户名称获取地址

    public boolean payVip(String userName);//充值vip

    public boolean checkVip(String userName);//检查用户是否为Vip

    //以上是对于单个用户的具体修改
    public List<T> allUser();//获取所有用户

    public boolean close();//关闭数据库连接
}

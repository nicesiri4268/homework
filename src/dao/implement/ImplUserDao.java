package dao.implement;


import dao.IUserDao;
import dao.baseDao.IBean;
import dao.baseDao.IControlDao;
import dao.baseDao.IDaoFactory;
import dao.baseDao.UtilControl;
import dao.bean.UserBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplUserDao implements IUserDao<UserBean> {
    public static IDaoFactory<IUserDao<UserBean>> iUserDaoIDaoFactory =new IDaoFactory<IUserDao<UserBean>>() {
        @Override
        public IUserDao<UserBean> returnIFactoryDAO() {
            return new ImplUserDao();
        }
    };
    //工厂类的是实现
    /**
     * @program: Dao层
     * @description: IUserDao的实现类
     * @author: niceSiri
     * @create: 2020-07-07 22:39
     */

    private IControlDao iControlDao = UtilControl.factoryDao.returnIFactoryDAO();
    private Connection connection;

    public ImplUserDao() {

    }

    public ImplUserDao(Connection connection) {
        this.connection = connection;
    }

    public ImplUserDao setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    @Override
    public boolean checkUserName(UserBean userBean) {
        String sql = "select count(*) from `user` where UserName=?";//检查用户是否已经被注册
        ResultSet resultset = iControlDao.select(sql, userBean.getUserName());
        try {
            if (resultset != null && resultset.next()) {
                int i = resultset.getInt("count(*)");
                return i == 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean signUp(UserBean userBean) {
        if (!checkUserName(userBean)) {
            String sql = "INSERT INTO `user`(UserName,UserPassword,UserSex,UserAddress,UserEmail) VALUES (?,?,?,?,?)";
            int count = iControlDao.add(sql, userBean.getUserName(),
                    userBean.getPassword(), userBean.getUserSex()
                    , userBean.getAddress(), userBean.getEmail());
            System.out.println("修改的行数是 " + count + " ");
            return count == 1;
        } else
            return false;
    }

    @Override
    public boolean login(String userName, String password) {
        String sql = "select count(*) from `user` where UserName=?&&UserPassword=?";//检查用户是否已经被注册
        ResultSet resultset = iControlDao.select(sql, userName, password);
        try {
            if (resultset != null && resultset.next()) {
                int i = resultset.getInt("count(*)");
                return i == 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(int userID, String userName) {
        String sql = "DELETE FROM `user` WHERE UserID =?&& UserName=?";
        return iControlDao.delete(sql, userID, userName) == 1;
    }

    @Override
    public int getUserState(String userName, String password) {
        String sql = "select UserState from `user` where UserName=?&&UserPassword=?";//检查用户是否已经被注册
        ResultSet resultset = iControlDao.select(sql, userName, password);
        try {
            if (resultset != null && resultset.next()) {
                return resultset.getInt("UserState");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultset != null)
                    resultset.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public boolean setUserState(String userName, int userState) {
        String sql = "UPDATE `user` SET `user`.UserState=? WHERE `user`.UserName = ?";
        int i = iControlDao.update(sql, userState, userName);
        return i == 1;
    }

    @Override
    public boolean updatePassword(String userName, String oldPassword, String newPassword) {
        String sql = "UPDATE `user` SET `user`.UserPassword=? " +
                "WHERE `user`.UserName = ?&&`user`.UserPassword=?";
        return iControlDao.update(sql, newPassword, userName, oldPassword) == 1;
    }

    @Override
    public boolean updateUserAll(UserBean userBean, int userID) {
        String sql = "UPDATE `user` SET UserName=?,UserPassword=?, UserSex= ?, UserAddress=?,UserEmail=? WHERE UserID=?";
        return iControlDao.update(sql, userBean.getUserName(),userBean.getPassword(),userBean.getUserSex(),
                userBean.getAddress(), userBean.getEmail()
                , userID) == 1;
    }

    @Override
    public boolean updateOtherMsgs(UserBean userBean) {
        String sql = "UPDATE `user` SET UserSex= ?, UserAddress=?,UserEmail=? WHERE UserName=?&&UserPassword=?";
        return iControlDao.update(sql, userBean.getUserSex(),
                userBean.getAddress(), userBean.getEmail()
                , userBean.getUserName(), userBean.getPassword()) == 1;
    }


    @Override
    public UserBean getUserMsgs(int userID) {
        String sql = "SELECT * FROM `user` where UserID=?";
        ResultSet resultSet = iControlDao.select(sql,userID);
        try {
            if (resultSet!=null&&resultSet.next()) {
                 return new UserBean(
                        resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getTimestamp(7).toString(), resultSet.getInt(8), resultSet.getInt(9)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int getUserID(String userName) {
        String sql = "select UserID from `user` where UserName=?";//检查用户是否已经被注册
        ResultSet resultset = iControlDao.select(sql, userName);
        try {
            if (resultset != null && resultset.next()) {
                return resultset.getInt("UserID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultset != null)
                    resultset.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public String getUserAddress(String userName) {
        String sql = "select UserAddress from `user` where UserName=?";
        ResultSet resultset = iControlDao.select(sql, userName);
        try {
            if (resultset != null && resultset.next()) {
                return resultset.getString("UserAddress");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultset != null)
                    resultset.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean payVip(String userName) {
        String sql = "UPDATE `user` SET UserIsVip = ? WHERE UserName = ?";
        return iControlDao.update(sql, 1, userName) == 1;
    }

    @Override
    public boolean checkVip(String userName) {
        String sql = "select UserIsVip from `user` where UserName=?";//检查用户是否已经被注册
        ResultSet resultset = iControlDao.select(sql, userName);
        try {
            if (resultset != null && resultset.next()) {
                return resultset.getInt("UserIsVip")==1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultset != null)
                    resultset.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean close() {
        return iControlDao.close();
    }

    @Override
    public List<UserBean> allUser() {
        String sql = "SELECT * FROM `user`";
        List<UserBean> userBeans = new ArrayList<UserBean>();
        ResultSet resultSet = iControlDao.select(sql);
        try {
            while (resultSet.next()) {
                UserBean userBean = new UserBean(
                        resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getTimestamp(7).toString(), resultSet.getInt(8), resultSet.getInt(9)
                );
                userBeans.add(userBean);
            }
            if (!userBeans.isEmpty()) {
                return userBeans;
            } else
                return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}

package dao.implement;

import dao.baseDao.IControlDao;
import dao.baseDao.UtilControl;
import dao.bean.AdminBean;
import dao.factory.IAdminDaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplAdminDao implements dao.IAdminDao {
    /**
     * @program: Dao层
     * @description: 管理员的管理类
     * @author: niceSiri
     * @create: 2020-07-08 12:09
     */

    public static IAdminDaoFactory iAdminDaoFactory = new IAdminDaoFactory() {
        @Override
        public ImplAdminDao returnIFactoryDAO() {
            return new ImplAdminDao();
        }
    };
    public IControlDao iControlDao = UtilControl.factoryDao.returnIFactoryDAO();

    @Override
    public boolean checkAdminName(String adminName) {
        String sql = "select count(*) from `admin` where UserName=?";//检查用户是否已经被注册
        ResultSet resultset = iControlDao.select(sql, adminName);
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
    public boolean signUp(AdminBean adminBean) {
        if (!checkAdminName(adminBean.getAdminName())) {
            String sql = "INSERT INTO admin(AdminJobID,AdminLevel,AdminName,AdminPassword,AdminState)" +
                    "VALUES(?,?,?,?,?)";
            int count = iControlDao.add(sql, adminBean.getAdminJobID(),
                    adminBean.getAdminLevel(), adminBean.getAdminName()
                    , adminBean.getAdminPassword(), adminBean.getAdminState());
            System.out.println("修改的行数是 " + count + " ");
            if (count == 1)
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public boolean signIn(String adminName, String adminPassword) {
        String sql = "SELECT COUNT(*) FROM admin WHERE AdminName=?&&AdminPassword=?";
        ResultSet resultset = iControlDao.select(sql, adminName, adminPassword);
        try {
            if (resultset != null && resultset.next()) {
                int i = resultset.getInt("count(*)");
                return i == 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteAdmin(int adminID) {
        String sql = "DELETE FROM `admin` WHERE AdminID=?";
        return iControlDao.delete(sql, adminID) == 1;
    }

    @Override
    public int getAdminJobID(String adminName) {
        String sql = "SELECT AdminName,AdminJobID FROM admin WHERE AdminName=? ";
        ResultSet resultSet = iControlDao.select(sql, adminName);
        try {
            if (resultSet.next()) {
                return resultSet.getInt("AdminJobID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public boolean setAdminJobID(String adminName, int jobID) {
        String sql = "UPDATE admin SET AdminJobID=? WHERE AdminName=?";
        return iControlDao.update(sql, jobID, adminName) == 1;
    }

    @Override
    public int getAdminLevel(String adminName) {
        String sql = "SELECT AdminName,AdminLevel FROM admin WHERE AdminName =?";
        ResultSet resultset = iControlDao.select(sql, adminName);
        try {
            if (resultset != null && resultset.next()) {
                return resultset.getInt("AdminLevel");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public boolean setAdminLevel(String adminName, int adminLevel) {
        String sql = "UPDATE admin SET AdminLevel = ? WHERE AdminName=?";
        return iControlDao.update(sql, adminLevel, adminName) == 1;
    }

    @Override
    public int getAdminState(String adminName) {
        String sql = "SELECT AdminName,AdminState FROM admin WHERE AdminName =?";
        ResultSet resultset = iControlDao.select(sql, adminName);
        try {
            if (resultset != null && resultset.next()) {
                return resultset.getInt("AdminState");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public boolean setAdminState(String adminName, int adminState) {
        String sql = "UPDATE admin SET AdminState = ? WHERE AdminName=?";
        return iControlDao.update(sql, adminState, adminName) == 1;
    }

    @Override
    public boolean resetAdminPassword(String adminName, String oldPassword, String newPassword) {
        String sql = "UPDATE admin SET AdminPassword=? WHERE AdminName=?&&AdminPassword=?";
        return iControlDao.update(sql, newPassword, adminName, oldPassword) == 1;
    }


    @Override
    public List<AdminBean> allAdmin() {
        String sql = "SELECT * FROM admin ";
        List<AdminBean> adminBeans = new ArrayList<AdminBean>();
        ResultSet resultSet = iControlDao.select(sql);
        try {
            while (resultSet != null && resultSet.next()) {
                AdminBean adminBean = new AdminBean(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
                adminBeans.add(adminBean);
            }
            return adminBeans;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean close() {
        return iControlDao.close();
    }
}

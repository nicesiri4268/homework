package dao.implement;

import dao.IGoodsTypeDao;
import dao.baseDao.IControlDao;
import dao.baseDao.UtilControl;
import dao.bean.GoodsTypeBean;
import dao.factory.IGoodsTypeDaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplGoodsTypeDao implements IGoodsTypeDao<GoodsTypeBean> {
    /**
     * @program: Dao层
     * @description: IGoodsTypeDao的实现类
     * @author: niceSiri
     * @create: 2020-07-10 19:48
     */
    public static IGoodsTypeDaoFactory iGoodsTypeDao = new IGoodsTypeDaoFactory() {
        @Override
        public IGoodsTypeDao returnIFactoryDAO() {
            return new ImplGoodsTypeDao();
        }
    };
    private IControlDao iControlDao = UtilControl.factoryDao.returnIFactoryDAO();

    public ImplGoodsTypeDao() {
    }

    private String getString(int goodsTypeID, String sql, IControlDao iControlDao) {
        ResultSet resultSet = iControlDao.select(sql, goodsTypeID);
        try {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getString(1);
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
        return null;
    }

    private int getInt(int goodsTypeID, String sql, IControlDao iControlDao) {
        ResultSet resultSet = iControlDao.select(sql, goodsTypeID);
        try {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt(1);
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
    public boolean addGoodsType(GoodsTypeBean goodsTypeBean) {
        String sql = "INSERT INTO goodstype(GoodsTypeName,GoodsTypeDescription,GoodsTypeState) VALUES (?,?,?)";
        return iControlDao.add(sql, goodsTypeBean.getGoodsTypeName(), goodsTypeBean.getGoodsDescription(), goodsTypeBean.getGoodsTypeState()) == 1;
    }

    @Override
    public boolean deleteGoodsType(int goodsTypeID) {
        String sql = "DELETE FROM goodstype WHERE GoodsTypeID=?";
        return iControlDao.delete(sql, goodsTypeID) == 1;
    }

    @Override
    public boolean setGoodsTypeID(int oldGoodsTypeID, int newGoodsTypeID) {
        String sql = "UPDATE goodstype SET GoodsTypeID=? where GoodsTypeID=?";
        return iControlDao.update(sql, newGoodsTypeID, oldGoodsTypeID) == 1;
    }

    @Override
    public String getGoodsTypeName(int goodsTypeID) {
        String sql = "SELECT GoodsTypeName FROM goodstype WHERE GoodsTypeID=?";
        return getString(goodsTypeID, sql, iControlDao);
    }

    @Override
    public boolean setGoodsTypeName(int goodsTypeID, String goodsTypeName) {
        String sql = "UPDATE goodstype SET GoodsTypeName=? where GoodsTypeID=?";
        return iControlDao.update(sql, goodsTypeName, goodsTypeID) == 1;
    }

    @Override
    public String getGoodsTypeDescription(int goodsTypeID) {
        String sql = "SELECT GoodsTypeDescription FROM goodstype WHERE GoodsTypeID=?";
        return getString(goodsTypeID, sql, iControlDao);
    }

    @Override
    public boolean setGoodsTypeDescription(int goodsTypeID, String goodsTypeDescription) {
        String sql = "UPDATE goodstype SET GoodsTypeDescriptione=? where GoodsTypeID=?";
        return iControlDao.update(sql, goodsTypeDescription, goodsTypeID) == 1;

    }

    @Override
    public int getGoodsTypeState(int goodsTypeID) {
        String sql = "SELECT GoodsTypeDescription FROM goodstype WHERE GoodsTypeID=?";
        return getInt(goodsTypeID, sql, iControlDao);
    }


    @Override
    public boolean setGoodsTypeState(int goodsTypeID, int goodsTypeState) {
        String sql = "UPDATE goodstype SET GoodsTypeState=? where GoodsTypeID=?";
        return iControlDao.update(sql, goodsTypeState, goodsTypeID) == 1;
    }


    @Override
    public List<GoodsTypeBean> allGoodsType() {
        String sql = "SELECT * FROM `goodstype`";
        List<GoodsTypeBean> goodsTypeBeans = new ArrayList<GoodsTypeBean>();
        ResultSet resultSet = iControlDao.select(sql);
        try {
            while (resultSet != null && resultSet.next()) {
                GoodsTypeBean goodsTypeBean = new GoodsTypeBean(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                goodsTypeBeans.add(goodsTypeBean);
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
        if (goodsTypeBeans.isEmpty()) {
            return null;
        } else
            return goodsTypeBeans;
    }
}

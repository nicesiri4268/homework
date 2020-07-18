package dao.implement;

import dao.IGoodsDao;
import dao.IGoodsStoreDao;
import dao.baseDao.IControlDao;
import dao.baseDao.UtilControl;
import dao.bean.GoodsBean;
import dao.bean.GoodsStoreBean;
import dao.factory.IGoodsStoreDaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ImplGoodsStoreDao implements IGoodsStoreDao {
    /**
     * @program: Dao层
     * @description: IGoodsStoreDao的实现类
     * @author: niceSiri
     * @create: 2020-07-09 18:39
     */
    public static IGoodsStoreDaoFactory iGoodsStoreDaoFactory = new IGoodsStoreDaoFactory() {
        @Override
        public IGoodsStoreDao returnIFactoryDAO() {
            return new ImplGoodsStoreDao();
        }
    };
    private IControlDao iControlDao = UtilControl.factoryDao.returnIFactoryDAO();
    private IGoodsDao<GoodsBean> iGoodsDao = ImplGoodsDao.iGoodsDaoFactory.returnIFactoryDAO();

    //用以调用IGoodsDao的getGoodsID方法，判断是否有该商品
    public ImplGoodsStoreDao() {
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
    public Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis() + 8 * 60 * 60 * 1000);
    }

    @Override
    public boolean addGoodsStore(GoodsStoreBean goodsStoreBean) {
        if (iGoodsDao.getGoodsID(goodsStoreBean.getGoodsID()) && !getGoodsID(goodsStoreBean.getGoodsID())) {
            String sql = "INSERT INTO goodsstore(GoodsID,GoodsStoreCount,GoodsStoreCheckName) VALUES (?,?,?)";
            return iControlDao.add(sql, goodsStoreBean.getGoodsID(), goodsStoreBean.getGoodsStoreCount(), goodsStoreBean.getGoodsStoreCheckName()) == 1;
        }
        return false;
    }

    @Override
    public boolean deleteGoodsStore(int goodsID) {
        String sql = "DELETE FROM goodsstore WHERE goodsID=1";
        return iControlDao.delete(sql, goodsID) == 1;
    }

    @Override
    public boolean getGoodsID(int goodsID) {
        String sql = "SELECT Count(*) FROM goodsstore WHERE goodsID=?";
        ResultSet resultSet = iControlDao.select(sql, goodsID);
        try {
            if (resultSet != null && resultSet.next())
                return resultSet.getInt(1) == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean setGoodsID(int oldGoodsID, int newGoodsID) {
        String sql = "UPDATE goodsstore SET GoodsID=?,GoodsStoreCheckDate=? WHERE GoodsID=?";
        return iControlDao.update(sql, getCurrentTimestamp(), newGoodsID, oldGoodsID) == 1;
    }

    @Override
    public int getGoodsCount(int goodsID) {
        String sql = "SELECT GoodsStoreCount FROM goodsstore WHERE goodsID=2";
        return getInt(goodsID, sql, iControlDao);
    }

    @Override
    public boolean setGoodsCount(int goodsID, int goodsCount) {
        String sql = "UPDATE goodsstore SET GoodsStoreCount=?,GoodsStoreCheckDate=? WHERE GoodsID=?";
        return iControlDao.update(sql, goodsCount, getCurrentTimestamp(), goodsID) == 1;
    }

    @Override
    public String getGoodsCheckName(int goodsID) {
        String sql = "SELECT GoodsStoreCheckName FROM goodsstore WHERE GoodsID=?";
        return getString(goodsID, sql, iControlDao);
    }

    @Override
    public boolean setGoodsCheckName(int goodsID, String newCheckName) {
        String sql = "UPDATE goodsstore SET GoodsStoreCheckName=?,GoodsStoreCheckDate=? WHERE GoodsID=?";
        return iControlDao.update(sql, newCheckName, getCurrentTimestamp(), goodsID) == 1;
    }

    @Override
    public List<GoodsStoreBean> allGoodsStore() {
        List<GoodsStoreBean> goodsStoreBeans = new ArrayList<GoodsStoreBean>();
        String sql = "SELECT * FROM goodsstore ";
        ResultSet resultSet = iControlDao.select(sql);
        try {
            while (resultSet != null && resultSet.next()) {
                GoodsStoreBean goodsStoreBean =
                        new GoodsStoreBean(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)
                                , resultSet.getTimestamp(4), resultSet.getString(5));
                goodsStoreBeans.add(goodsStoreBean);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (goodsStoreBeans.isEmpty()) {
            return null;
        } else {
            return goodsStoreBeans;
        }
    }
}

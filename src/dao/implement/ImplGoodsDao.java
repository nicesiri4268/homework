package dao.implement;

import dao.IGoodsDao;
import dao.baseDao.IControlDao;
import dao.baseDao.UtilControl;
import dao.bean.GoodsBean;
import dao.factory.IGoodsDaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplGoodsDao implements IGoodsDao<GoodsBean> {
    /**
     * @program: Dao层
     * @description: IGoodsDao的具体实现类
     * @author: niceSiri
     * @create: 2020-07-09 15:45
     */
    public static IGoodsDaoFactory<IGoodsDao<GoodsBean>> iGoodsDaoFactory= new IGoodsDaoFactory<IGoodsDao<GoodsBean>>() {
        @Override
        public IGoodsDao<GoodsBean> returnIFactoryDAO() {
            return new ImplGoodsDao();
        }
    };

    private IControlDao iControlDao = UtilControl.factoryDao.returnIFactoryDAO();

    public ImplGoodsDao() {

    }

    @Override
    public boolean getGoodsID(int goodsID) {
        String sql = "SELECT COUNT(*) FROM `goods` WHERE GoodsID=?";
        ResultSet resultSet = iControlDao.select(sql, goodsID);
        try {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt(1) == 1;
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
        return false;
    }

    @Override
    public String getGoodsName(int goodsID) {
        String sql = "SELECT * FROM `goods` WHERE GoodsID=?";
        ResultSet resultSet = iControlDao.select(sql, goodsID);
        try {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getString("GoodsName");
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

    @Override
    public boolean addGoods(GoodsBean goodsBean) {
        String sql1 = "SELECT COUNT(*) FROM goodstype WHERE GoodsTypeID=?;";
        ResultSet resultSet = iControlDao.select(sql1, goodsBean.getGoodsTypeID());
        try {
            if (resultSet != null && resultSet.next()) {
                if (resultSet.getInt(1) == 1) {
                    String sql2 = "INSERT INTO goods(GoodsName,GoodsDescription,GoodsPrice,GoodsVipPrice,GoodsTypeID)VALUES (?,?,?,?,?)";
                    return iControlDao.add(sql2, goodsBean.getGoodsName(), goodsBean.getGoodsDescription(),
                            goodsBean.getGoodsPrice(), goodsBean.getGoodsVipPrice(),
                            goodsBean.getGoodsTypeID()) == 1;
                }
            } else {
                System.out.println("不存在该商品类型" + goodsBean.getGoodsTypeID());
                return false;
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
        return false;
    }

    @Override
    public GoodsBean getOneGoodsMsgs(int goodsID) {
        String sql="SELECT * FROM goods WHERE GoodsID=?;";
        ResultSet resultset = iControlDao.select(sql,goodsID);
        GoodsBean goodsBean=null;
        try {
            if (resultset!=null&&resultset.next()){
                goodsBean=new GoodsBean(resultset.getInt(1),resultset.getString(2),resultset.getString(3),resultset.getDouble(4),resultset.getDouble(5),resultset.getInt(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{
                if (resultset!=null){
                    resultset.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (goodsBean!=null){
            return goodsBean;
        }else
            return null;
    }

    @Override
    public boolean deleteGoods(int goodsID, String goodsName) {
        String Sql = "DELETE FROM goods WHERE GoodsID=?&&GoodsName=?";
        return iControlDao.delete(Sql, goodsID, goodsName) == 1;
    }

    @Override
    public boolean resetGoodsMsgs(int goodsID, GoodsBean goodsBean) {
        String sql = "UPDATE goods SET GoodsName=?,GoodsDescription=?,GoodsPrice=?,GoodsVipPrice=?,GoodsTypeID=? WHERE GoodsID=?";
        return iControlDao.update(sql, goodsBean.getGoodsName(), goodsBean.getGoodsDescription(),
                goodsBean.getGoodsPrice(), goodsBean.getGoodsVipPrice(),
                goodsBean.getGoodsTypeID(), goodsID) == 1;
    }

    @Override
    public boolean resetGoodsName(int goodsID, String oldGoodsName, String newGoodsName) {
        String sql = "UPDATE goods SET GoodsName=? WHERE GoodsID=?&&GoodsName=?;";
        return iControlDao.update(sql, newGoodsName, goodsID, oldGoodsName) == 1;
    }

    @Override
    public boolean resetGoodsDescription(int goodsID, String goodsName, String newGoodsDescription) {
        String sql = "UPDATE goods SET GoodsDescription= ? WHERE GoodsID=?&&GoodsName=?;";
        return iControlDao.update(sql, newGoodsDescription, goodsID, goodsName) == 1;
    }

    @Override
    public boolean resetGoodsPrice(int goodsID, String goodsName, int newPrice) {
        String sql = "UPDATE goods SET GoodsPrice= ? WHERE GoodsID=?&&GoodsName=?;";
        return iControlDao.update(sql, newPrice, goodsID, goodsName) == 1;
    }

    @Override
    public boolean resetGoodsVipPrice(int goodsID, String goodsName, int newVipPrice) {
        String sql = "UPDATE goods SET GoodsVipPrice= ? WHERE GoodsID=?&&GoodsName=?;";
        return iControlDao.update(sql, newVipPrice, goodsID, goodsName) == 1;
    }

    @Override
    public boolean resetGoodsType(int goodsID, String goodsName, int newGoodsType) {
        String sql1 = "SELECT COUNT(*) FROM goodstype WHERE GoodsTypeID=?;";
        ResultSet resultSet = iControlDao.select(sql1, newGoodsType);
        try {
            if (resultSet != null && resultSet.next()) {
                if (resultSet.getInt(1) == 1) {
                    String sql2 = "UPDATE goods SET GoodsTypeID= ? WHERE GoodsID=?&&GoodsName=?";
                    return iControlDao.update(sql2, newGoodsType, goodsID, goodsName) == 1;
                }
            } else {
                System.out.println("不存在该商品类型" + newGoodsType);
                return false;
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
        return false;
    }

    /*@Override
    public List<GoodsBean> allGoods() {
        List<GoodsBean> goodsBeanList = new ArrayList<GoodsBean>();
        String sql = "SELECT * FROM goods";
        ResultSet resultSet = iControlDao.select(sql);
        try {
            while (resultSet != null && resultSet.next()) {
                GoodsBean goodsBean = new GoodsBean(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getInt(6));
                goodsBeanList.add(goodsBean);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (!goodsBeanList.isEmpty()) {
            return goodsBeanList;
        } else
            return null;
    }*/

    @Override
    public List<GoodsBean> allGoods() {
        List<GoodsBean> goodsBeanList = new ArrayList<GoodsBean>();
        String sql = "SELECT * FROM goods";
        ResultSet resultSet = iControlDao.select(sql);
        try {
            while (resultSet != null && resultSet.next()) {
                GoodsBean goodsBean = new GoodsBean(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getInt(6));
                goodsBeanList.add(goodsBean);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (!goodsBeanList.isEmpty()) {
            return goodsBeanList;
        } else
            return null;
    }

    @Override
    public boolean close() {
        return iControlDao.close();
    }
}

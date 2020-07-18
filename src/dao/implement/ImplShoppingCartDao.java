package dao.implement;

import dao.IShoppingCartDao;
import dao.baseDao.IControlDao;
import dao.baseDao.IDaoFactory;
import dao.baseDao.UtilControl;
import dao.bean.ShoppingCartBean;
import dao.factory.IShoppingCartDaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplShoppingCartDao implements IShoppingCartDao<ShoppingCartBean> {
    /**
     * @program: Dao层
     * @description: IShoppingCartDao接口的实现类
     * @author: niceSiri
     * @create: 2020-07-13 17:05
     */
    public static IDaoFactory<IShoppingCartDao<ShoppingCartBean>> iDaoFactory = new IShoppingCartDaoFactory<IShoppingCartDao<ShoppingCartBean>>() {
        @Override
        public IShoppingCartDao<ShoppingCartBean> returnIFactoryDAO() {
            return new ImplShoppingCartDao();
        }
    };
    private IControlDao iControlDao = UtilControl.factoryDao.returnIFactoryDAO();
    public ImplShoppingCartDao(){}

    @Override
    public boolean addShoppingCart(ShoppingCartBean shoppingCartBean) {
        String sql = "INSERT INTO shoppingcart(ShoppingCartState,UserID,UserIsVip,GoodsID,GoodsCount,GoodsPrice) VALUES(?,?,?,?,?,?)";
        return iControlDao.add(sql,shoppingCartBean.getShoppingCartState(),shoppingCartBean.getUserID(),shoppingCartBean.getUserIsVip()
                ,shoppingCartBean.getGoodsID(),shoppingCartBean.getGoodsCount(),shoppingCartBean.getGoodsPrice())==1;
    }

    @Override
    public boolean deleteShoppingCart(int userID, int goodsID) {
        String sql ="DELETE FROM shoppingcart WHERE UserID=?&&GoodsID=?&&ShoppingCartState=0";
        return iControlDao.delete(sql,userID,goodsID)==1;
    }

    @Override
    public int deleteShoppingCart(int userID) {
        String sql ="DELETE FROM shoppingcart WHERE UserID=?&&ShoppingCartState=0";
        return iControlDao.delete(sql,userID);
    }

    @Override
    public boolean resetShoppingCartState(int userID, int goodsID, int state) {
        String sql = "UPDATE shoppingcart SET ShoppingCartState=? WHERE UserID=?&&GoodsID=?";
        return iControlDao.update(sql,state,userID,goodsID)==1;
    }

    @Override
    public boolean resetShoppingCount(int userID, int goodsID, int goodsCount) {
        String sql = "UPDATE shoppingcart SET GoodsCount=? WHERE UserID=?&&GoodsID=?&&ShoppingCartState=0";
        return iControlDao.update(sql,goodsCount,userID,goodsID)==1;
    }

    @Override
    public boolean resetShoppingPrice(int userID, int goodsID, int goodsPrice) {
        String sql = "UPDATE shoppingcart SET GoodsPrice=? WHERE UserID=?&&GoodsID=?";
        return iControlDao.update(sql,goodsPrice,userID,goodsID)==1;
    }

    @Override
    public ShoppingCartBean getOneUserGoods(int userID, int goodsID) {
        String sql = "SELECT * FROM shoppingcart WHERE UserID=?&&GoodsID=?&&ShoppingCartState=0";
        ResultSet resultSet = iControlDao.select(sql,userID,goodsID);
        try{
            if (resultSet!=null&&resultSet.next()){
                return new ShoppingCartBean(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3)
                        ,resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getDouble(7)
                        ,resultSet.getTimestamp(8),resultSet.getTimestamp(9));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<ShoppingCartBean> getOneShoppingCart(int userID) {
        String sql = "SELECT * FROM shoppingcart WHERE UserID=?";
        List<ShoppingCartBean> list = new ArrayList<ShoppingCartBean>();
        ResultSet resultSet = iControlDao.select(sql,userID);
        try{
            while(resultSet!=null&&resultSet.next()){
                list.add(new ShoppingCartBean(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3)
                ,resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getDouble(7)
                ,resultSet.getTimestamp(8),resultSet.getTimestamp(9)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(list.isEmpty()){
            return null;
        }else
            return list;
    }

    @Override
    public List<ShoppingCartBean> allShoppingCart() {
        String sql = "SELECT * FROM shoppingcart";
        List<ShoppingCartBean> list = new ArrayList<ShoppingCartBean>();
        ResultSet resultSet = iControlDao.select(sql);
        try{
            while(resultSet!=null&&resultSet.next()){
                list.add(new ShoppingCartBean(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3)
                        ,resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getDouble(7)
                        ,resultSet.getTimestamp(8),resultSet.getTimestamp(9)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(list.isEmpty()){
            return null;
        }else
            return list;
    }
}

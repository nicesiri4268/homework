package dao.implement;

import dao.IOrderParticularsDao;
import dao.baseDao.IControlDao;
import dao.baseDao.IDaoFactory;
import dao.baseDao.UtilControl;
import dao.bean.OrderParticularsBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplOrderParticularsDao implements IOrderParticularsDao<OrderParticularsBean> {
    /**
     * @program: Dao层
     * @description: IOrderParticularsDao的实现类
     * @author: niceSiri
     * @create: 2020-07-13 11:54
     */
    public static IDaoFactory<IOrderParticularsDao<OrderParticularsBean>> iDaoFactory = new IDaoFactory<IOrderParticularsDao<OrderParticularsBean>>() {
        @Override
        public IOrderParticularsDao<OrderParticularsBean> returnIFactoryDAO() {
            return new ImplOrderParticularsDao();
        }
    };
    private IControlDao iControlDao =UtilControl.factoryDao.returnIFactoryDAO();

    public ImplOrderParticularsDao(){

    }

    @Override
    public boolean addOrderParticulars(List<OrderParticularsBean> orderParticularBeans) {
        String sql="INSERT INTO orderparticulars(OrderID,OrderGoodsID,OrderGoodsCount,OrderGoodsPrice) VALUES(?,?,?,?)";
        for(OrderParticularsBean tmp: orderParticularBeans){
            if(iControlDao.add(sql,tmp.getOrderID(),tmp.getOrderGoodsID(),tmp.getOrderGoodsCount(),tmp.getOrderGoodsPrice())==1){
            }else {
                return false;
            }
        }
        return true;
    }

    @Override
    public int deleteOrderParticulars(int orderID) {
        String sql="DELETE FROM orderparticulars WHERE OrderID=?";
        return iControlDao.delete(sql,orderID);
    }

    @Override
    public boolean deleteOrderParticulars(int orderID, int orderGoodsID) {
        String sql="DELETE FROM orderparticulars WHERE OrderID=?&&OrderGoodsID=?";
        return iControlDao.delete(sql,orderID,orderGoodsID)==1;
    }

    @Override
    public List<OrderParticularsBean> getOrderParticulars(int orderID) {
        List<OrderParticularsBean> list = new ArrayList<OrderParticularsBean>();
        String sql="SELECT * from orderparticulars WHERE OrderID=?";
        ResultSet resultSet =iControlDao.select(sql,orderID);
        try {
            while(resultSet!=null&&resultSet.next()){
                list.add(new OrderParticularsBean(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getTimestamp(6)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (list.isEmpty()){
            return null;
        }else{
            return list;
        }
    }

    @Override
    public boolean resetOrderParticulars(int orderID, int orderGoodsID, OrderParticularsBean orderParticularsBean) {
        String sql ="UPDATE orderparticulars SET OrderID=?,OrderGoodsID=?,OrderGoodsCount=?,OrderGoodsPrice=? WHERE OrderID=?&&OrderGoodsID=?";
        return iControlDao.update(sql,orderParticularsBean.getOrderID(),orderParticularsBean.getOrderGoodsID()
                ,orderParticularsBean.getOrderGoodsCount()
                ,orderParticularsBean.getOrderGoodsPrice()
                ,orderID,orderGoodsID)==1;
    }

    @Override
    public boolean resetOrderGoodsCount(int orderID, int orderGoodsID, int orderGoodsCount) {
        String sql ="UPDATE orderparticulars SET OrderGoodsCount=? WHERE OrderID=?&&OrderGoodsID=?";
        return iControlDao.update(sql,orderGoodsCount,orderID,orderGoodsID)==1;
    }

    @Override
    public boolean resetOrderGoodsPrice(int orderID, int orderGoodsID, double orderGoodsPrice) {
        String sql="UPDATE orderparticulars SET OrderGoodsPrice=? WHERE OrderID=?&&OrderGoodsID=?";
        return iControlDao.update(sql,orderGoodsPrice,orderID,orderGoodsID)==1;
    }

    @Override
    public List<OrderParticularsBean> allOrderParticulars() {
        String sql="SELECT * FROM orderparticulars";
        List<OrderParticularsBean> list =new ArrayList<OrderParticularsBean>();
        ResultSet resultSet = iControlDao.select(sql);
        try {
            while(resultSet!=null&&resultSet.next()){
                list.add(new OrderParticularsBean(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getTimestamp(6)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (list.isEmpty())
            return null;
        else
            return list;
    }
}

package dao.implement;

import dao.IOrderDao;
import dao.baseDao.IControlDao;
import dao.baseDao.IDaoFactory;
import dao.baseDao.UtilControl;
import dao.bean.OrderBean;
import dao.factory.IOrderDaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ImplOrderDao implements IOrderDao<OrderBean> {
    public static IDaoFactory<ImplOrderDao> iDaoFactory = new IOrderDaoFactory() {
        @Override
        public ImplOrderDao returnIFactoryDAO() {
            return new ImplOrderDao();
        }
    };
    /**
     * @program: Dao层
     * @description: IOrderDao的实现类
     * @author: niceSiri
     * @create: 2020-07-11 21:23
     */
    private final IControlDao iControlDao = UtilControl.factoryDao.returnIFactoryDAO();
    /*以下为新方式，使用泛型代替不同的实例继承对象一个一个的继承IDaoFactory,那么可以只使用一个IDaoFactory接口控制所有的对象
    private static IDaoFactory<ImplOrderDao> orderDaoIDaoFactory=new IDaoFactory<ImplOrderDao>() {
        @Override
        public ImplOrderDao returnIFactoryDAO() {
            return new ImplOrderDao();
        }
    };*/


    public ImplOrderDao() {
    }

    @Override
    public boolean isUserIDExist(int userID) {
        String sql = "select 1 from `user` where userID=? LIMIT 1";
        ResultSet resultSet = iControlDao.select(sql,userID);
        try {
            if (resultSet!=null&&resultSet.next())
                return true;
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
        return false;
    }

    @Override
    public boolean addOrder(OrderBean orderBean) {
        if (isUserIDExist(orderBean.getUserID())){
            String sql="INSERT INTO `order`(UserID,OrderPrice,OrderAddress,OrderState) VALUES(?,?,?,?)";
            return iControlDao.add(sql,
                    orderBean.getUserID(),orderBean.getOrderPrice(),orderBean.getOrderAddress(),orderBean.getOrderState())==1;
        }
        return false;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        String sql ="DELETE FROM `order` WHERE OrderID = ?";
        return iControlDao.delete(sql,orderId)==1;
    }

    @Override
    public List<Integer> getOrderID(int userID) {
        List<Integer> orderList = new ArrayList<Integer>();
        String sql = "SELECT OrderID FROM `order`WHERE UserID=?";
        ResultSet resultSet = iControlDao.select(sql,userID);
        try {
            while(resultSet!=null&&resultSet.next()){
                orderList.add(resultSet.getInt("OrderID"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (orderList.isEmpty()){
            return null;
        }else
            return orderList;
    }

    @Override
    public int getOrderID(int userID, int orderState) {
        String sql = "SELECT OrderID FROM `order`WHERE UserID=?&&OrderState=?";
        ResultSet resultSet = iControlDao.select(sql,userID,orderState);
        try {
            if(resultSet!=null&&resultSet.next()){
                return resultSet.getInt("OrderID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public boolean resetOrderState(int userID, int orderID, int oldOrderState, int newOrderState) {
        String sql ="UPDATE `order` SET OrderState=? WHERE OrderID =?&&UserID=?&&OrderState=?";
        return iControlDao.update(sql,newOrderState,orderID,userID, oldOrderState)==1&&setOrderDate(orderID);
    }

    @Override
    public OrderBean getOderIDMSG(int orderID) {
        String sql ="SELECT * FROM `order`WHERE OrderID=? LIMIT 1";
        ResultSet resultSet = iControlDao.select(sql,orderID);
        try{
            if (resultSet!=null&&resultSet.next()){
                return new OrderBean(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
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
    public Timestamp getOrderTime(int userID, int orderID, int orderState) {
        Timestamp timestamp =null;
        String sql ="SELECT OrderDate FROM `order`WHERE UserID=?&&OrderID=?&&OrderState=? LIMIT 1";
        ResultSet resultSet = iControlDao.select(sql,userID,orderID,orderState);
        try{
            if (resultSet!=null&&resultSet.next()){
                return timestamp=resultSet.getTimestamp(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
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
    public int getUserID(int orderID) {
        String sql = "SELECT UserID FROM `order`WHERE OrderID=2 LIMIT 1 ";
        ResultSet resultSet =iControlDao.select(sql);
        try {
            if (resultSet!=null&&resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public boolean setUserID(int orderID, int userID) {
        String sql ="UPDATE `order` SET UserID=? WHERE OrderID =?";
        return iControlDao.update(sql,userID,orderID)==1&&setOrderDate(orderID);

    }

    @Override
    public boolean setOrderAddress(int orderID, String orderAddress) {
        String sql ="UPDATE `order` SET OrderAddress=? WHERE OrderID =?";
        return iControlDao.update(sql,orderAddress,orderID)==1&&setOrderDate(orderID);
    }

    @Override
    public boolean setOrderDate(int orderID) {
        String sql ="UPDATE `order` SET OrderDate=? WHERE OrderID =?";
        return iControlDao.update(sql,iControlDao.getCurrentTimestamp(),orderID)==1;
    }

    @Override
    public List<OrderBean> oneOrder(int userID) {
        String sql ="SELECT * FROM `order` WHERE UserID=?";
        List<OrderBean> orderBeanList = new ArrayList<OrderBean>();
        ResultSet resultSet = iControlDao.select(sql,userID);
        try {
            while (resultSet!=null&&resultSet.next()){
                orderBeanList.add(new OrderBean(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6)));
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
        if (orderBeanList.isEmpty()){
            return null;
        }else
            return orderBeanList;
    }

    @Override
    public List<OrderBean> allOrder() {
        String sql ="SELECT * FROM `order` ";
        List<OrderBean> orderBeanList = new ArrayList<OrderBean>();
        ResultSet resultSet = iControlDao.select(sql);
        try {
            while(resultSet!=null&&resultSet.next()){
                orderBeanList.add(new OrderBean(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6)));
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
        if (orderBeanList.isEmpty()){
            return null;
        }else
            return orderBeanList;
    }
}

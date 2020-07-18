package dao;

import dao.baseDao.IBean;
import dao.baseDao.IDao;

import java.sql.Timestamp;
import java.util.List;

public interface IOrderDao<T extends IBean> extends IDao<T> {
    public boolean isUserIDExist(int userID);//判断UserID是否存在
    public boolean addOrder(T orderBean);//添加订单

    public boolean deleteOrder(int orderId);//根据订单号删除订单

    public List<Integer> getOrderID(int userID);//根据用户ID获取订单ID序列
    public int getOrderID(int userID,int orderState);//根据用户和订单状态查找订单序列号
    public boolean resetOrderState(int userID, int orderID, int oldOrderState,int newOrderState);//修改订单状态
    public Timestamp getOrderTime(int userID,int orderID,int orderState);
    public T getOderIDMSG(int orderID);//根据OrderID获取订单所有的信息**

    public int getUserID(int orderID);//根据订单ID获取用户ID

    public boolean setUserID(int orderID, int userID);//根据订单ID重新设置用户ID

    public boolean setOrderAddress(int orderID, String orderAddress);//根据订单号重新设置订单地址

    public boolean setOrderDate(int orderID);//修改订单时间，在修改订单的任何数据时都要修改订单时间

    public List<T> oneOrder(int userID);//获取某个用户的所有订单

    public List<T> allOrder();//获取所有的订单信息
}

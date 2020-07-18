package dao.bean;

import dao.baseDao.IBean;

public class OrderBean implements IBean {
    /**
     * @program: Dao层
     * @description: order表的数据库实现
     * @author: niceSiri
     * @create: 2020-07-11 20:47
     */
    private int OrderID;
    private int UserID;
    private double OrderPrice;
    private String OrderAddress;
    private String OrderDate;
    private int OrderState=0;

    public OrderBean(int orderID, int userID, double orderPrice, String orderAddress, String orderDate, int orderState) {
        OrderID = orderID;
        UserID = userID;
        OrderPrice = orderPrice;
        OrderAddress = orderAddress;
        OrderDate = orderDate;
        OrderState = orderState;
    }

    public OrderBean(int userID, double orderPrice, String orderAddress) {
        UserID = userID;
        OrderPrice = orderPrice;
        OrderAddress = orderAddress;
    }

    public OrderBean(int userID, double orderPrice, String orderAddress, int orderState) {
        //过度构造函数，存在订单状态
        UserID = userID;
        OrderPrice = orderPrice;
        OrderAddress = orderAddress;
        OrderState = orderState;
    }

    public int getOrderID() {
        return OrderID;
    }

    public OrderBean setOrderID(int orderID) {
        OrderID = orderID;
        return this;
    }

    public int getUserID() {
        return UserID;
    }

    public OrderBean setUserID(int userID) {
        UserID = userID;
        return this;
    }

    public double getOrderPrice() {
        return OrderPrice;
    }

    public OrderBean setOrderPrice(double orderPrice) {
        OrderPrice = orderPrice;
        return this;
    }

    public String getOrderAddress() {
        return OrderAddress;
    }

    public OrderBean setOrderAddress(String orderAddress) {
        OrderAddress = orderAddress;
        return this;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public OrderBean setOrderDate(String orderDate) {
        OrderDate = orderDate;
        return this;
    }

    public int getOrderState() {
        return OrderState;
    }

    public OrderBean setOrderState(int orderState) {
        OrderState = orderState;
        return this;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "OrderID=" + OrderID +
                ", UserID=" + UserID +
                ", OrderPrice=" + OrderPrice +
                ", OrderAddress='" + OrderAddress + '\'' +
                ", OrderDate='" + OrderDate + '\'' +
                ", OrderState=" + OrderState +
                '}';
    }
}

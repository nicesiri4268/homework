package dao.bean;

import dao.baseDao.IBean;

import java.sql.Timestamp;

public class OrderParticularsBean implements IBean {
/**
 *@program: Dao层
 *@description: 表orderParticulars的数据
 *@author: niceSiri
 *@create: 2020-07-13 11:21
 */
    private int OrderParticularsID;
    private int OrderID;
    private int OrderGoodsID;
    private int OrderGoodsCount;
    private double OrderGoodsPrice;
    private Timestamp OrderGoodsDate;

    public OrderParticularsBean(int orderParticularsID, int orderID, int orderGoodsID, int orderGoodsCount, double orderGoodsPrice, Timestamp orderGoodsDate) {
        OrderParticularsID = orderParticularsID;
        OrderID = orderID;
        OrderGoodsID = orderGoodsID;
        OrderGoodsCount = orderGoodsCount;
        OrderGoodsPrice = orderGoodsPrice;
        OrderGoodsDate = orderGoodsDate;
    }

    public OrderParticularsBean(int orderID, int orderGoodsID, int orderGoodsCount, double orderGoodsPrice, Timestamp orderGoodsDate) {
        OrderID = orderID;
        OrderGoodsID = orderGoodsID;
        OrderGoodsCount = orderGoodsCount;
        OrderGoodsPrice = orderGoodsPrice;
        OrderGoodsDate = orderGoodsDate;
    }

    public int getOrderParticularsID() {
        return OrderParticularsID;
    }

    public OrderParticularsBean setOrderParticularsID(int orderParticularsID) {
        OrderParticularsID = orderParticularsID;
        return this;
    }

    public int getOrderID() {
        return OrderID;
    }

    public OrderParticularsBean setOrderID(int orderID) {
        OrderID = orderID;
        return this;
    }

    public int getOrderGoodsID() {
        return OrderGoodsID;
    }

    public OrderParticularsBean setOrderGoodsID(int orderGoodsID) {
        OrderGoodsID = orderGoodsID;
        return this;
    }

    public int getOrderGoodsCount() {
        return OrderGoodsCount;
    }

    public OrderParticularsBean setOrderGoodsCount(int orderGoodsCount) {
        OrderGoodsCount = orderGoodsCount;
        return this;
    }

    public double getOrderGoodsPrice() {
        return OrderGoodsPrice;
    }

    public OrderParticularsBean setOrderGoodsPrice(double orderGoodsPrice) {
        OrderGoodsPrice = orderGoodsPrice;
        return this;
    }

    public Timestamp getOrderGoodsDate() {
        return OrderGoodsDate;
    }

    public OrderParticularsBean setOrderGoodsDate(Timestamp orderGoodsDate) {
        OrderGoodsDate = orderGoodsDate;
        return this;
    }

    @Override
    public String toString() {
        return "OrderParticulars{" +
                "OrderParticularsID=" + OrderParticularsID +
                ", OrderID=" + OrderID +
                ", OrderGoodsID=" + OrderGoodsID +
                ", OrderGoodsCount=" + OrderGoodsCount +
                ", OrderGoodsPrice=" + OrderGoodsPrice +
                ", OrderGoodsDate=" + OrderGoodsDate +
                '}';
    }
}

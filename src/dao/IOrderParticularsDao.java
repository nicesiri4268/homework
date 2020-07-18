package dao;

import dao.baseDao.IBean;
import dao.baseDao.IDao;
import dao.bean.OrderParticularsBean;

import java.util.List;

public interface IOrderParticularsDao<T extends IBean> extends IDao<T> {
    public boolean addOrderParticulars(List<T> orderParticulars);//添加某个订单具体的信息
    public int deleteOrderParticulars(int orderID);//删除某个订单号的所有订单具体信息
    public boolean deleteOrderParticulars(int orderID,int orderGoodsID);//删除某一个订单号的莫一种商品的订单信息
    public List<T> getOrderParticulars(int orderID);//获取某个订单号的所有订单信息
    public boolean resetOrderParticulars(int orderID, int orderGoodsID, OrderParticularsBean orderParticularsBean);
    //重设某一订单号某一商品的所有订单信息
    public boolean resetOrderGoodsCount(int orderID,int orderGoodsID,int orderGoodsCount);//重设某一订单中某一商品的订单数量
    public boolean resetOrderGoodsPrice(int orderID,int orderGoodsID,double orderGoodsPrice);//重设某一订单中某一商品的订单价格
    public List<T> allOrderParticulars();//获取所有的订单的信息

}

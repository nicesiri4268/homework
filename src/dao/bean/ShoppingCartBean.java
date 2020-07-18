package dao.bean;

import dao.baseDao.IBean;

import java.sql.Timestamp;

public class ShoppingCartBean implements IBean {
/**
 *@program: Dao层
 *@description: 数据库ShoppingCart表的具体类
 *@author: niceSiri
 *@create: 2020-07-13 16:44
 */
    private int ShoppingCart=-1;
    private int ShoppingCartState=0;
    private int UserID;
    private int UserIsVip;
    private int GoodsID;
    private int GoodsCount;
    private double GoodsPrice=0;
    private Timestamp ShoppingEstablishDate;
    private Timestamp ShoppingEndDate;

    public ShoppingCartBean(int shoppingCart, int shoppingCartState, int userID, int userIsVip,
                            int goodsID, int goodsCount, double goodsPrice,
                            Timestamp shoppingEstablishDate, Timestamp shoppingEndDate) {
        ShoppingCart = shoppingCart;
        ShoppingCartState = shoppingCartState;
        UserID = userID;
        UserIsVip = userIsVip;
        GoodsID = goodsID;
        GoodsCount = goodsCount;
        GoodsPrice = goodsPrice;
        ShoppingEstablishDate = shoppingEstablishDate;
        ShoppingEndDate = shoppingEndDate;
    }

    public ShoppingCartBean(int userID, int userIsVip, int goodsID, int goodsCount, double goodsPrice) {
        UserID = userID;
        UserIsVip = userIsVip;
        GoodsID = goodsID;
        GoodsCount = goodsCount;
        GoodsPrice = goodsPrice;
    }

    public int getShoppingCart() {
        return ShoppingCart;
    }

    public ShoppingCartBean setShoppingCart(int shoppingCart) {
        ShoppingCart = shoppingCart;
        return this;
    }

    public int getShoppingCartState() {
        return ShoppingCartState;
    }

    public ShoppingCartBean setShoppingCartState(int shoppingCartState) {
        ShoppingCartState = shoppingCartState;
        return this;
    }

    public int getUserID() {
        return UserID;
    }

    public ShoppingCartBean setUserID(int userID) {
        UserID = userID;
        return this;
    }

    public int getUserIsVip() {
        return UserIsVip;
    }

    public ShoppingCartBean setUserIsVip(int userIsVip) {
        UserIsVip = userIsVip;
        return this;
    }

    public int getGoodsID() {
        return GoodsID;
    }

    public ShoppingCartBean setGoodsID(int goodsID) {
        GoodsID = goodsID;
        return this;
    }

    public int getGoodsCount() {
        return GoodsCount;
    }

    public ShoppingCartBean setGoodsCount(int goodsCount) {
        GoodsCount = goodsCount;
        return this;
    }

    public double getGoodsPrice() {
        return GoodsPrice;
    }

    public ShoppingCartBean setGoodsPrice(double goodsPrice) {
        GoodsPrice = goodsPrice;
        return this;
    }

    public Timestamp getShoppingEstablishDate() {
        return ShoppingEstablishDate;
    }

    public ShoppingCartBean setShoppingEstablishDate(Timestamp shoppingEstablishDate) {
        ShoppingEstablishDate = shoppingEstablishDate;
        return this;
    }

    public Timestamp getShoppingEndDate() {
        return ShoppingEndDate;
    }

    public ShoppingCartBean setShoppingEndDate(Timestamp shoppingEndDate) {
        ShoppingEndDate = shoppingEndDate;
        return this;
    }

    @Override
    public String toString() {
        return "ShoppingCartBean{" +
                "ShoppingCart=" + ShoppingCart +
                ", ShoppingCartState=" + ShoppingCartState +
                ", UserID=" + UserID +
                ", UserIsVip=" + UserIsVip +
                ", GoodsID=" + GoodsID +
                ", GoodsCount=" + GoodsCount +
                ", GoodsPrice=" + GoodsPrice +
                ", ShoppingEstablishDate=" + ShoppingEstablishDate +
                ", ShoppingEndDate=" + ShoppingEndDate +
                '}';
    }
}

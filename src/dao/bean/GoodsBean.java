package dao.bean;

import dao.baseDao.IBean;

public class GoodsBean implements IBean {
    /**
     * @program: Dao层
     * @description: Goods的数据表类
     * @author: niceSiri
     * @create: 2020-07-09 15:36
     */
    private int goodsID;
    private String goodsName;
    private String goodsDescription;
    private double goodsPrice;
    private double goodsVipPrice;
    private int goodsTypeID=1;

    public GoodsBean() {

    }

    public GoodsBean(int goodsID, String goodsName, String goodsDescription, double goodsPrice, double goodsVipPrice, int goodsTypeID) {
        this.goodsID = goodsID;
        this.goodsName = goodsName;
        this.goodsDescription = goodsDescription;
        this.goodsPrice = goodsPrice;
        this.goodsVipPrice = goodsVipPrice;
        this.goodsTypeID = goodsTypeID;
    }

    public GoodsBean(String goodsName, String goodsDescription, double goodsPrice, double goodsVipPrice, int goodsTypeID) {
        this.goodsName = goodsName;
        this.goodsDescription = goodsDescription;
        this.goodsPrice = goodsPrice;
        this.goodsVipPrice = goodsVipPrice;
        this.goodsTypeID = goodsTypeID;
    }

    public int getGoodsID() {
        return goodsID;
    }

    public GoodsBean setGoodsID(int goodsID) {
        this.goodsID = goodsID;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public GoodsBean setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public GoodsBean setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
        return this;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public GoodsBean setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
        return this;
    }

    public double getGoodsVipPrice() {
        return goodsVipPrice;
    }

    public GoodsBean setGoodsVipPrice(double goodsVipPrice) {
        this.goodsVipPrice = goodsVipPrice;
        return this;
    }

    public int getGoodsTypeID() {
        return goodsTypeID;
    }

    public GoodsBean setGoodsTypeID(int goodsTypeID) {
        this.goodsTypeID = goodsTypeID;
        return this;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "goodsID=" + goodsID +
                ", goodsName='" + goodsName + '\'' +
                ", goodsDescription='" + goodsDescription + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsVipPrice=" + goodsVipPrice +
                ", goodsTypeID=" + goodsTypeID +
                '}';
    }
}

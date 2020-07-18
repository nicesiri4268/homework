package dao.bean;

import dao.baseDao.IBean;

import java.sql.Timestamp;

public class GoodsStoreBean implements IBean {
    /**
     * @program: Dao层
     * @description: 商品存储数据类
     * @author: niceSiri
     * @create: 2020-07-09 18:32
     */
    private int GoodsStoreID = -1;
    private int GoodsID = -1;
    private int GoodsStoreCount;
    private Timestamp GoodsStoreCheckDate = null;
    private String GoodsStoreCheckName;

    public GoodsStoreBean() {
    }

    public GoodsStoreBean(int goodsStoreID, int goodsID, int goodsStoreCount, Timestamp goodsStoreCheckDate, String goodsStoreCheckName) {
        //该构造函数用来到处数据库数据
        GoodsStoreID = goodsStoreID;
        GoodsID = goodsID;
        GoodsStoreCount = goodsStoreCount;
        GoodsStoreCheckDate = goodsStoreCheckDate;
        GoodsStoreCheckName = goodsStoreCheckName;
    }

    public GoodsStoreBean(int goodsID, int goodsStoreCount, String goodsStoreCheckName) {
        //该构造函数用来创建数据库插入数据
        GoodsID = goodsID;
        GoodsStoreCount = goodsStoreCount;
        GoodsStoreCheckName = goodsStoreCheckName;
    }

    public int getGoodsStoreID() {
        return GoodsStoreID;
    }

    public GoodsStoreBean setGoodsStoreID(int goodsStoreID) {
        GoodsStoreID = goodsStoreID;
        return this;
    }

    public int getGoodsID() {
        return GoodsID;
    }

    public GoodsStoreBean setGoodsID(int goodsID) {
        GoodsID = goodsID;
        return this;
    }

    public int getGoodsStoreCount() {
        return GoodsStoreCount;
    }

    public GoodsStoreBean setGoodsStoreCount(int goodsStoreCount) {
        GoodsStoreCount = goodsStoreCount;
        return this;
    }

    public Timestamp getGoodsStoreCheckDate() {
        return GoodsStoreCheckDate;
    }

    public GoodsStoreBean setGoodsStoreCheckDate(Timestamp goodsStoreCheckDate) {
        GoodsStoreCheckDate = goodsStoreCheckDate;
        return this;
    }

    public String getGoodsStoreCheckName() {
        return GoodsStoreCheckName;
    }

    public GoodsStoreBean setGoodsStoreCheckName(String goodsStoreCheckName) {
        GoodsStoreCheckName = goodsStoreCheckName;
        return this;
    }

    @Override
    public String toString() {
        return "GoodsStoreBean{" +
                "GoodsStoreID=" + GoodsStoreID +
                ", GoodsID=" + GoodsID +
                ", GoodsStoreCount=" + GoodsStoreCount +
                ", GoodsStoreCheckDate=" + GoodsStoreCheckDate +
                ", GoodsStoreCheckName='" + GoodsStoreCheckName + '\'' +
                '}';
    }
}

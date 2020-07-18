package dao.bean;

import dao.baseDao.IBean;

public class GoodsTypeBean implements IBean {
    /**
     * @program: Dao层
     * @description: GoodsType数据库具体类
     * @author: niceSiri
     * @create: 2020-07-10 18:26
     */
    private int GoodsTypeID = -1;
    private String GoodsTypeName;
    private String GoodsDescription;
    private int GoodsTypeState = 0;

    public GoodsTypeBean() {
    }

    public GoodsTypeBean(int goodsTypeID, String goodsTypeName, String goodsDescription, int goodsTypeState) {
        GoodsTypeID = goodsTypeID;
        GoodsTypeName = goodsTypeName;
        GoodsDescription = goodsDescription;
        GoodsTypeState = goodsTypeState;
    }

    public GoodsTypeBean(String goodsTypeName, String goodsDescription, int goodsTypeState) {
        GoodsTypeName = goodsTypeName;
        GoodsDescription = goodsDescription;
        GoodsTypeState = goodsTypeState;
    }

    public int getGoodsTypeID() {
        return GoodsTypeID;
    }

    public GoodsTypeBean setGoodsTypeID(int goodsTypeID) {
        GoodsTypeID = goodsTypeID;
        return this;
    }

    public String getGoodsTypeName() {
        return GoodsTypeName;
    }

    public GoodsTypeBean setGoodsTypeName(String goodsTypeName) {
        GoodsTypeName = goodsTypeName;
        return this;
    }

    public String getGoodsDescription() {
        return GoodsDescription;
    }

    public GoodsTypeBean setGoodsDescription(String goodsDescription) {
        GoodsDescription = goodsDescription;
        return this;
    }

    public int getGoodsTypeState() {
        return GoodsTypeState;
    }

    public GoodsTypeBean setGoodsTypeState(int goodsTypeState) {
        GoodsTypeState = goodsTypeState;
        return this;
    }
}

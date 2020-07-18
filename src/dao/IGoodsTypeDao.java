package dao;

import dao.baseDao.IBean;
import dao.baseDao.IDao;
import dao.bean.GoodsTypeBean;

import java.util.List;

public interface IGoodsTypeDao<T extends IBean> extends IDao<T> {
    public boolean addGoodsType(GoodsTypeBean goodsTypeBean);//添加商品类型

    public boolean deleteGoodsType(int goodsTypeID);//删除商品类型

    public boolean setGoodsTypeID(int oldGoodsTypeID, int newGoodsTypeID);//重新设置商品类型号

    public String getGoodsTypeName(int goodsTypeID);//获取商品类型名

    public boolean setGoodsTypeName(int goodsTypeID, String goodsTypeName);//设置商品类型名

    public String getGoodsTypeDescription(int goodsTypeID);//获取商品类型说明

    public boolean setGoodsTypeDescription(int goodsTypeID, String goodsTypeDescription);//设置商品类型说明

    public int getGoodsTypeState(int goodsTypeID);//获取商品类型状态

    public boolean setGoodsTypeState(int goodsTypeID, int goodsTypeState);//设置商品类型状态

    public List<T> allGoodsType();//获取所有商品类型信息
}

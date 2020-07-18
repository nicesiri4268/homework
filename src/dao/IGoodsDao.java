package dao;

import dao.baseDao.IBean;
import dao.baseDao.IDao;
import dao.bean.GoodsBean;

import java.util.List;

public interface IGoodsDao<T extends IBean> extends IDao<T> {
    public boolean getGoodsID(int goodsID);//根据商品ID判断商品是否存在
    public String getGoodsName(int goodsID);//根据商品ID获取商品名称
    public boolean addGoods(GoodsBean goodsBean); //商品登记
    public T getOneGoodsMsgs(int goodsID);//根据商品号获取商品信息

    public boolean deleteGoods(int goodsID, String goodsName);//删除商品信息

    public boolean resetGoodsMsgs(int goodsID, GoodsBean goodsBean);//重设商品的所有信息

    public boolean resetGoodsName(int goodsID, String oldGoodsName, String newGoodsName);//修改商品名称

    public boolean resetGoodsDescription(int goodsID, String goodsName, String newGoodsDescription);//设置商品描述信息

    public boolean resetGoodsPrice(int goodsID, String goodsName, int newPrice);//重设商品价格

    public boolean resetGoodsVipPrice(int goodsID, String goodsName, int newVipPrice);//重设商品Vip价格

    public boolean resetGoodsType(int goodsID, String goodsName, int newGoodsType);//重设商品的类型

    //以上是对于单个用户的具体修改
    public List<T> allGoods();//获取所有商品信息

    public boolean close();//关闭数据库连接
}

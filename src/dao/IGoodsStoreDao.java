package dao;

import dao.baseDao.IDao;
import dao.bean.GoodsStoreBean;

import java.sql.Timestamp;
import java.util.List;

public interface IGoodsStoreDao extends IDao {
    public Timestamp getCurrentTimestamp();//获取当前时间，获取的是北京时间

    public boolean addGoodsStore(GoodsStoreBean goodsStoreBean);//添加商品存储信息

    public boolean deleteGoodsStore(int goodsID);//根据商品ID删除商品存储信息

    public boolean getGoodsID(int goodsID);//根据商品ID是否有存储

    public boolean setGoodsID(int oldGoodsID, int newGoodsID);//重设商品ID

    public int getGoodsCount(int goodsID);//根据商品ID获取存储数量

    public boolean setGoodsCount(int goodsID, int goodsCount);//根据商品ID获取商品的存储数量

    public String getGoodsCheckName(int goodsID);//根据商品获取存储检查员名

    public boolean setGoodsCheckName(int goodsID, String newCheckName);//根据商品ID修改存储检查员名

    public List<GoodsStoreBean> allGoodsStore();//获取所有的商品存储信息
}

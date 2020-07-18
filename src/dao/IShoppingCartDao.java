package dao;

import dao.baseDao.IBean;
import dao.baseDao.IDao;
import dao.bean.ShoppingCartBean;

import java.util.List;

public interface IShoppingCartDao<T extends IBean> extends IDao<T> {
    public boolean addShoppingCart(ShoppingCartBean shoppingCartBean);//添加购物车
    public boolean deleteShoppingCart(int userID,int goodsID);//根据用户和商品号删除购物车
    public int deleteShoppingCart(int userID);//根据用户清除购物车
    public boolean resetShoppingCartState(int userID, int goodsID,int state);
    //根据用户和商品号设置购物车状态,0为未下单，1为已下单
    public boolean resetShoppingCount(int userID,int goodsID,int goodsCount);//重设商品订购数量，当数量为0时删除该记录
    public boolean resetShoppingPrice(int userID,int goodsID,int goodsPrice);//重设商品的价格
    public List<T> getOneShoppingCart(int userID);//获取某个用户的所有购物车信息
    public T getOneUserGoods(int userID,int goodsID);//获取某个用户的某个商品信息
    public List<T> allShoppingCart();//获取所有用户的购物车信息
}

package dao.factory;

import dao.IGoodsStoreDao;
import dao.baseDao.IDaoFactory;

public interface IGoodsStoreDaoFactory extends IDaoFactory<IGoodsStoreDao> {
    IGoodsStoreDao returnIFactoryDAO();
}

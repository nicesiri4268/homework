package dao.factory;

import dao.IGoodsTypeDao;
import dao.baseDao.IDaoFactory;

public interface IGoodsTypeDaoFactory extends IDaoFactory<IGoodsTypeDao> {
    IGoodsTypeDao returnIFactoryDAO();
}

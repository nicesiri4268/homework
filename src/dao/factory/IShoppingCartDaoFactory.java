package dao.factory;

import dao.baseDao.IDao;
import dao.baseDao.IDaoFactory;

public interface IShoppingCartDaoFactory<T extends IDao> extends IDaoFactory<T> {
    @Override
    T returnIFactoryDAO();
}

package dao.factory;

import dao.baseDao.IDao;
import dao.baseDao.IDaoFactory;

public interface IGoodsDaoFactory<T extends IDao> extends IDaoFactory<T> {
    T returnIFactoryDAO();//测试泛型
}

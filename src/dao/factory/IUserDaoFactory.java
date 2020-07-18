package dao.factory;

import dao.IUserDao;
import dao.baseDao.IDaoFactory;


public interface IUserDaoFactory extends IDaoFactory<IUserDao> {
    @Override
    IUserDao returnIFactoryDAO();
}

package dao.factory;


import dao.baseDao.IControlDao;
import dao.baseDao.IDaoFactory;

public interface IBaseDaoFactory extends IDaoFactory<IControlDao> {
    IControlDao returnIFactoryDAO();
}

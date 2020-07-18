package dao.factory;

import dao.baseDao.IDaoFactory;
import dao.implement.ImplAdminDao;

public interface IAdminDaoFactory extends IDaoFactory<ImplAdminDao> {
    ImplAdminDao returnIFactoryDAO();
}

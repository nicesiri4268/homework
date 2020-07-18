package dao.factory;

import dao.baseDao.IDaoFactory;
import dao.implement.ImplOrderDao;

public interface IOrderDaoFactory extends IDaoFactory<ImplOrderDao> {
    @Override
    ImplOrderDao returnIFactoryDAO();
}

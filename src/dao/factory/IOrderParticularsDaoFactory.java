package dao.factory;


import dao.IOrderParticularsDao;
import dao.baseDao.IDaoFactory;

public interface IOrderParticularsDaoFactory extends IDaoFactory<IOrderParticularsDao> {
/**
 *@program: Dao层
 *@description:
 *@author: niceSiri
 *@create: 2020-07-13 11:55
 */
    @Override
    IOrderParticularsDao returnIFactoryDAO();
}

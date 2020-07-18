package dao.baseDao;

public interface IDaoFactory<T extends IDao>  {
    //工厂类的接口类
    T returnIFactoryDAO();
}

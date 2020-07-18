package dao.baseDao;

import java.sql.ResultSet;
import java.sql.Timestamp;

public interface IControlDao extends IDao {
    /**
     * 继承IDAO
     * 实现该接口就是实现数据库的增删查改操作
     */
    public int add(String sql, Object... objects);//数据库的基本操作之增加

    public int delete(String sql, Object... objects);//数据库的基本操作之删除

    public ResultSet select(String sql, Object... objects);//数据库的基本操作之查找

    public int update(String sql, Object... objects);//数据库的基本操作之修改

    public Timestamp getCurrentTimestamp();//获取当前时间，获取的是北京时间

    public boolean close();//数据库的关闭
}

package dao.baseDao;

import dao.factory.IBaseDaoFactory;

import java.sql.*;

public class UtilControl implements IDao, IControlDao {
    public static IBaseDaoFactory factoryDao = new IBaseDaoFactory() {
        @Override
        public IControlDao returnIFactoryDAO() {
            return new UtilControl();
        }
    };//BaseDao的工厂方法
    /**
     * @program: Dao层
     * @description: 实现IControlDao接口
     * @author: niceSiri
     * @create: 2020-07-05 21:09
     */
    private Connection connection = new UtilConnection().getConnection();
    private String sql;

    public UtilControl() {
    }

    public UtilControl setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    @Override
    public int add(String sql, Object... args) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            for (Object object : args) {
                preparedStatement.setObject(i, object);
                i++;
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delete(String sql, Object... args) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            for (Object object : args) {
                preparedStatement.setObject(i, object);
                i++;
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public ResultSet select(String sql, Object... args) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            for (Object object : args) {
                preparedStatement.setObject(i, object);
                i++;
            }
            return preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(String sql, Object... args) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            for (Object object : args) {
                preparedStatement.setObject(i, object);
                i++;
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis() + 8 * 60 * 60 * 1000);
    }

    @Override
    public boolean close() {
        try {
            if (connection != null) {
                connection.close();
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}

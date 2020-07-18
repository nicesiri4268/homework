package dao.baseDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilConnection implements IDao {
    /**
     * @program: Dao层
     * @description: 获取来自MySQL的connection
     * @author: niceSiri
     * @create: 2020-07-05 20:29
     */
    private final String DBName = "shopweb";//连接的数据库
    private final String URL = "jdbc:mysql://localhost:3306/" + this.DBName + "?useSSL=false&serverTimezone=UTC";
    private final String userName = "siri";//数据库用户名
    private final String password = "123456";//数据库密码
    //上述代码可以根据个人条件不同更换,也可以从文件当中获取
    private Connection connection = null;

    /**
     * 使用MySQL初始化连接
     * 使用上面的地址和用户名密码
     */
    public UtilConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}

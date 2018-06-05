package maven.data.MySQL;

import java.sql.*;

public class MySQLConnector{

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost:3306/";

    // 数据库的用户名与密码
    static final String USER = "root";
    static final String PASS = "123456";

    public Connection getConnection(String DataBaseName) {
        DB_URL = DB_URL + DataBaseName;
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return conn;
    }
}


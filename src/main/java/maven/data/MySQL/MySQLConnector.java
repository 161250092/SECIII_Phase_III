package maven.data.MySQL;

import java.sql.*;

public class MySQLConnector{


    public Connection getConnection(String DataBaseName) {

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/";

        // 数据库的用户名与密码
        final String USER = "root";
        final String PASS = "123456";

        DB_URL = DB_URL + DataBaseName + "?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";

        Connection conn = null;
        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}


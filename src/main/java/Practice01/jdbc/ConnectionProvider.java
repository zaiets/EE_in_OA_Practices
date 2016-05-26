package Practice01.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static Practice01.properties.PropertyUtils.getProperties;

public final class ConnectionProvider {
    private static DataSource dataSource;

    private ConnectionProvider() {}

    //метод getLocalDBConnection(), идущий в локальную базу.
    public static Connection getConnection(){
        return getLocalDBConnection();
    }
    private static Connection getLocalDBConnection() {
        if (dataSource == null) {
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL(getProperties().getProperty("JDBC_URL") + getProperties().getProperty("JDBC_SCHEMA"));
            mysqlDataSource.setUser(getProperties().getProperty("JDBC_LOGIN"));
            mysqlDataSource.setPassword(getProperties().getProperty("JDBC_PASSWORD"));
            dataSource = mysqlDataSource;
        }
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}

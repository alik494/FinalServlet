package ua.training.repository.db;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final Logger log = Logger.getLogger(ConnectionPool.class);

    public static final String USER = "postgres";

    public static final String PASSWORD = "postgres";

    public static final String URL =  "jdbc:postgresql://localhost/postgres?useSSL=false&serverTimezone=UTC";

    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl(URL);
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
        log.debug(ds);
    }

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in
     * your WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return DB connection.
     */
    public static Connection getConnection() throws SQLException {
        log.debug(ds);
        return ds.getConnection();
    }


}

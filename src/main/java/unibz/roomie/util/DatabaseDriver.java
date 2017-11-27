package unibz.roomie.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseDriver {
    private static Connection db;

    public static Connection getConnection() throws SQLException {
        if (db == null){
            db = getConexion();
        }
        return db;
    }

    private static Connection getConexion() throws SQLException {

        HikariConfig config = new  HikariConfig();
        config.setJdbcUrl(System.getenv("JDBC_DATABASE_URL"));
        @SuppressWarnings("resource")
		final HikariDataSource dataSource = (config.getJdbcUrl() != null) ?
                new HikariDataSource(config) : new HikariDataSource();
        return dataSource.getConnection();
    }
}

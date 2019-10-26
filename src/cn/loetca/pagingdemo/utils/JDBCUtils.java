package cn.loetca.pagingdemo.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	private static final ComboPooledDataSource DATA_SOURCE = new ComboPooledDataSource("myc3p0cfg"); // 构造器参数：c3p0-config.xml里的配置名称

	public static Connection getConnection() throws SQLException {
		return DATA_SOURCE.getConnection();
	}

	public static void closeConnection(Connection connection) {
		if (null == connection) {
			return;
		}

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

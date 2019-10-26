package cn.loetca.pagingdemo.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.loetca.pagingdemo.utils.JDBCUtils;

public class BaseDAO<T> {
	private QueryRunner qr = new QueryRunner();
	private Class<T> type;

	@SuppressWarnings("unchecked")
	public BaseDAO() {
		// 获取父类的泛型
		Type genericSuperClass = this.getClass().getGenericSuperclass();
		// 向下转型，以便调用方法
		ParameterizedType parameterizedType = (ParameterizedType)genericSuperClass;
		// 取第一个泛型
		Type actualTypeArguments = parameterizedType.getActualTypeArguments()[0];
		// 向下转型为Class类型
		type = (Class<T>)actualTypeArguments;
	}

	/**
	 * 获取多行记录
	 * 
	 * @param sql  SQL语句
	 * @param args 填充占位符元素
	 * @return 保存到List集合中的多行记录
	 */
	public List<T> getBeanList(String sql, Object... args) {
		Connection connection = null;
		List<T> list = null;

		try {
			connection = JDBCUtils.getConnection();
			list = qr.query(connection, sql, new BeanListHandler<T>(type), args);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection);
		}

		return list;
	}

	/**
	 * 查询第一行第一列的结果
	 * 
	 * @param sql  SQL语句
	 * @param args 填充占位符的元素
	 * @return 第一行第一列的结果
	 */
	public Object getValue(String sql, Object... args) {
		Connection connection = null;
		Object obj = null;

		try {
			connection = JDBCUtils.getConnection();
			obj = qr.query(connection, sql, new ScalarHandler<T>(), args);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(connection);
		}

		return obj;
	}

}

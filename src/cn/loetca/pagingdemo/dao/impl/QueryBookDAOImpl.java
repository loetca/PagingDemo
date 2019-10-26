package cn.loetca.pagingdemo.dao.impl;

import java.util.List;

import cn.loetca.pagingdemo.bean.Book;
import cn.loetca.pagingdemo.bean.Page;
import cn.loetca.pagingdemo.dao.BaseDAO;
import cn.loetca.pagingdemo.dao.QueryBooksDAO;

public class QueryBookDAOImpl extends BaseDAO<Book> implements QueryBooksDAO {

	@Override
	public Object getValue(Object... args) {
		final String sql = "SELECT COUNT(*) FROM test.mybooks WHERE price BETWEEN ? AND ?";
		return getValue(sql, args);
	}

	@Override
	public Page<Book> queryLimitedBooks(Page<Book> page) {
		final String sql = "SELECT id, name, author, price FROM test.mybooks LIMIT ?, ?";

		// 这里将最低价格赋值为0，最高价格为Double的最大值。这样就能查询出所有的Book对象
		long countNo = (long)getValue(0, Double.MAX_VALUE);

		// 设置数据库中一共有多少条记录
		page.setCountNo((int)countNo);

		// 获取当前页码
		int pageNo = page.getPageNo();

		// LIMIT的第一个参数
		int a = (pageNo - 1) * Page.SHOW_ITEMS;

		// 查询出当前页码中的所有的Book对象
		List<Book> books = getBeanList(sql, a, Page.SHOW_ITEMS);
		// 赋值给page对象的List属性
		page.setList(books);

		return page;
	}

	@Override
	public Page<Book> queryLimitedBooksByMinpriceAndMaxprice(Page<Book> page, Double min, Double max) {
		final String sql = "SELECT id, name, author, price FROM test.mybooks WHERE price BETWEEN ? AND ? LIMIT ?, ?";

		// 获取数据库中的记录数
		long countNo = (long)getValue(min, max);

		// 给一共有多少记录属性赋值
		page.setCountNo((int)countNo);

		// 得到LIMIT的第一个参数
		int a = (page.getPageNo() - 1) * Page.SHOW_ITEMS;

		List<Book> books = getBeanList(sql, min, max, a, Page.SHOW_ITEMS);

		// 将查询出结果赋予Page对象
		page.setList(books);

		return page;
	}

}

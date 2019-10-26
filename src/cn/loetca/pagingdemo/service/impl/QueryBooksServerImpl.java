package cn.loetca.pagingdemo.service.impl;

import cn.loetca.pagingdemo.bean.Book;
import cn.loetca.pagingdemo.bean.Page;
import cn.loetca.pagingdemo.dao.QueryBooksDAO;
import cn.loetca.pagingdemo.dao.impl.QueryBookDAOImpl;
import cn.loetca.pagingdemo.service.QueryBooksServer;

public class QueryBooksServerImpl implements QueryBooksServer {
	private QueryBooksDAO dao = new QueryBookDAOImpl();

	@Override
	public Page<Book> queryLimitedBooks(String pageNoStr) {
		int pageNo = 1;

		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}

		// 创建一个Page对象
		Page<Book> page = new Page<Book>();
		// 只给当前页码属性赋值
		page.setPageNo(pageNo);

		// 返回一个包含了所有的属性值的Page对象
		return dao.queryLimitedBooks(page);
	}

	@Override
	public Page<Book> queryPageBooksByMinpriceAndMaxprice(String pageNoStr, String minPrice, String maxPrice) {
		// 默认页码
		int pageNo = 1;

		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}

		// 默认最低价格
		Double min = 0.0;
		try {
			min = Double.parseDouble(minPrice);
		} catch (NullPointerException e1) {
		} catch (NumberFormatException e2) {
		}

		// 默认最高价格
		Double max = Double.MAX_VALUE;
		try {
			max = Double.parseDouble(maxPrice);
		} catch (NullPointerException e1) {
		} catch (NumberFormatException e2) {
		}

		// 如果最低价格大于最高价格，交换
		if (min > max) {
			min += max;
			max = min - max;
			min = min - max;
		}

		Page<Book> page = new Page<Book>();
		// 赋予当前页码值
		page.setPageNo(pageNo);

		return dao.queryLimitedBooksByMinpriceAndMaxprice(page, min, max);
	}

}

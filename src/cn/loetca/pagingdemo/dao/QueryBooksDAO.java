package cn.loetca.pagingdemo.dao;

import cn.loetca.pagingdemo.bean.Book;
import cn.loetca.pagingdemo.bean.Page;

public interface QueryBooksDAO {

	/**
	 * 用于查询符合条件的所有的Book对象的数量
	 * 
	 * @param args 可以传入最低价格和最高价格
	 * @return 一共有多少符合规则的记录
	 */
	Object getValue(Object... args);

	/**
	 * 查询指定页码的Book信息
	 * 
	 * @param page 一个只有当前页码属性的Page对象
	 * @return 返回一个List属性为【当前页码的所有的Book对象】的Page对象
	 */
	Page<Book> queryLimitedBooks(Page<Book> page);

	/**
	 * 根据指定的页码，价格范围。来查询指定的图书信息
	 * 
	 * @param page 一个只有当前页码属性的Page对象
	 * @param min  最低价格
	 * @param max  最高价格
	 * @return 一个Page对象，其List属性值为【符合价格范围的，当前页码的所有的Book对象】
	 */
	Page<Book> queryLimitedBooksByMinpriceAndMaxprice(Page<Book> page, Double min, Double max);

}

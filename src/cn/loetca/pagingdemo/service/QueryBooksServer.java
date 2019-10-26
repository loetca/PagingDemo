package cn.loetca.pagingdemo.service;

import cn.loetca.pagingdemo.bean.Book;
import cn.loetca.pagingdemo.bean.Page;

public interface QueryBooksServer {

	/**
	 * 传入当前页码，返回List属性为当前页码所有的Book对象的一个Page对象
	 * 
	 * @param pageNoStr 当前页码
	 * @return List属性为所有符合条件的Book对象的Page对象
	 */
	Page<Book> queryLimitedBooks(String pageNoStr);

	/**
	 * 传入当前页码，最低价格和最高价格。返回List属性值为价格范围内的当前页码的所有Book对象的一个Page对象
	 * 
	 * @param pageNoStr 当前页码
	 * @param minPrice  最低价格
	 * @param maxPrice  最高价格
	 * @return List属性值为价格范围内的当前页码的Book对象的Page对象
	 */
	Page<Book> queryPageBooksByMinpriceAndMaxprice(String pageNoStr, String minPrice, String maxPrice);

}

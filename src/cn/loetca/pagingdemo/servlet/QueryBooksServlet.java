package cn.loetca.pagingdemo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.loetca.pagingdemo.bean.Book;
import cn.loetca.pagingdemo.bean.Page;
import cn.loetca.pagingdemo.service.QueryBooksServer;
import cn.loetca.pagingdemo.service.impl.QueryBooksServerImpl;

@SuppressWarnings("serial")
public class QueryBooksServlet extends BaseServlet {
	private QueryBooksServer server = new QueryBooksServerImpl();

	public void queryLimitedBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNo = request.getParameter("pageNo");
		Page<Book> page = server.queryLimitedBooks(pageNo);

		// 将Page对象放到request域中
		request.setAttribute("page", page);
		// 转发到显示页面
		request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
	}

	public void queryPageBooksByMinpriceAndMaxprice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNoStr = request.getParameter("pageNo");
		String minPrice = request.getParameter("min");
		String maxPrice = request.getParameter("max");

		Page<Book> page = server.queryPageBooksByMinpriceAndMaxprice(pageNoStr, minPrice, maxPrice);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(request, response);
	}

}

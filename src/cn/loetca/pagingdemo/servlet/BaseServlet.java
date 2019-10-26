package cn.loetca.pagingdemo.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class BaseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String methodName = req.getParameter("method");
		if (null == methodName || 0 == methodName.length()) {
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
			return;
		}

		Class<? extends BaseServlet> clazz = this.getClass();
		try {
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, req, resp);
		} catch (NoSuchMethodException e) {
			System.err.println(new Date() + " ERROE: 不存在的请求方法【" + methodName + "】！");
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}

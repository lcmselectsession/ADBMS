package com.wgh.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wgh.model.BookForm;
import com.wgh.tools.Tool;
import com.xiaoming.jdbc.MyJDBC;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 处理GET请求的方法
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); // 获取action参数值
		if ("query".equals(action)) { // 判断action参数值是否为query
			this.query(request, response); // 调用query()方法
		}
	}

	/**
	 * 查询全部图书信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 定义分页查询的开始
		int start = 0;
		// 接受分页的第几页参数
		String page = request.getParameter("page");
		// 定义操作，上下页
		String operation = null;
		// 从Session中获取上次访问了的起始页
		// 为null时，说明是第一次访问
		if (request.getSession().getAttribute("start") == null) {
			start = Integer.parseInt(page);
		} else {// 不为null时，则从Session中获取
			start = (int) request.getSession().getAttribute("start");
		}
		// 这是为直接点击页码时，对起始页赋值
		if (request.getParameter("page") != null) {
			start = Integer.parseInt(page);
		}

		// 获取数据库中表的最大页面数
		int pageLength = 5;
		// 获得数据的最大页
		int endpage = Tool.getMaxPage(pageLength);

		if (request.getParameter("operation") != null) {
			operation = request.getParameter("operation");
		} else {
			operation = "active";
		}

		// 以下四步是对，对上下页，首尾页的判断
		start = Tool.getSelectStartPage(start, operation, endpage);

		// 将当前访问的页放入session中
		request.getSession().setAttribute("start", start);

		// 分页查询
		String sql = "SELECT * FROM tb_book limit ?,?";
		List<Map<String, Object>> list = MyJDBC.preQuery(sql, (start - 1) * pageLength, pageLength);
		// 将查询出的数据，装入bookForm的List集合中
		List<BookForm> lists = Tool.getBeanList(list);
		// 为查询表生成新的导航页码的数据，以供直接访问页表
		List<Integer> pagelist = Tool.getPageList(start, endpage);
	
		request.getSession().setMaxInactiveInterval(60 * 60 * 1000);
		// 将当前设定好的选项页码，放入request中
		request.setAttribute("pagelist", pagelist);
		// 将分页查询的结果放入request中
		request.setAttribute("bookList", lists);
		// 转发重定向页面
		request.getRequestDispatcher("bookList.jsp").forward(request, response);
		// response.sendRedirect("bookList.jsp");
	}
}

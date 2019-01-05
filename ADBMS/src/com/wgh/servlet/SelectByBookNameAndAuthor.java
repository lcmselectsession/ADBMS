package com.wgh.servlet;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/SelectByBookNameAndAuthor")
public class SelectByBookNameAndAuthor extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int pageLength = 10;

		String selectname = req.getParameter("selectname");

		req.getSession().setAttribute("selectname", selectname);

		// 定义分页查询的开始
		int start = 0;

		// 接受分页的第几页参数
		String page = "1";

		if (req.getParameter("page") != null) {
			page = req.getParameter("page");
		}

		// 定义操作，上下页
		String operation = req.getParameter("operation");
		if (operation == null) {
			operation = "active";
		}

		// 从Session中获取上次访问了的起始页
		// 为null时，说明是第一次访问
		if (req.getSession().getAttribute("startview") == null) {
			start = Integer.parseInt(page);
		} else {// 不为null时，则从Session中获取
			start = (int) req.getSession().getAttribute("startview");
		}
		// 这是为直接点击页码时，对起始页赋值
		if (req.getParameter("page") != null) {
			start = Integer.parseInt(page);
		}

		// 分页查询
		List<Map<String, Object>> list = new ArrayList<>();

		String sql = "select * from tb_book where name like concat('%',?,'%')  or author like concat('%',?,'%') ";

		// 直接从搜索框中查询，搜索框中的值不为空（不是null，是""），且不是通过上下页访问操作
		if ((!"".equals(req.getParameter("selectname")) && null != req.getParameter("selectname"))
				&& req.getParameter("operation") == null) {
			list = MyJDBC.preQuery(sql, selectname, selectname);
			start = 1;

			// 通过上下页，与直接页面数来访问操作，此时：搜索框为null，操作码不为null
		} else if ((null == req.getParameter("selectname") && req.getParameter("operation") != null)
				|| null != req.getParameter("page")) {

			list = (List<Map<String, Object>>) req.getSession().getAttribute("selectbook");

			// 当搜索框内容为空（""）时，进行的操作，本该由前端实现判断
		} else if ("".equals(req.getParameter("selectname"))) {

			list = MyJDBC.preQuery("SELECT * FROM tb_book limit ?,?", 0, 0);
			req.getSession().removeAttribute("selectbook");

		}
		req.getSession().setAttribute("selectbook", list);
		
		int endpage = Tool.getMaxSearchPage(list, pageLength);
		
		start = Tool.getSelectStartPage(start, operation, endpage);
		// 将当前访问的页放入session中
		req.getSession().setAttribute("startview", start);

		// 将查询出的数据，装入bookForm的List集合中
		List<BookForm> lists = Tool.getBeanListHasLengthLimit(list, start, pageLength);

		// 为查询表生成新的导航页码的数据，以供直接访问页表
		List<Integer> pagelist = Tool.getPageList(start, endpage);

		req.getSession().setMaxInactiveInterval(60 * 60 * 1000);
		// 将当前设定好的选项页码，放入request中
		req.setAttribute("pagelists", pagelist);
		// 将分页查询的结果放入request中
		req.setAttribute("selectbook", lists);
		// 转发重定向页面
		req.getRequestDispatcher("selectview.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}

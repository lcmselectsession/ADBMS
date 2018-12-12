package com.wgh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wgh.service.InsertService;
import com.wgh.tools.Tool;

@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 添加数据
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String bookCount = req.getParameter("bookCount");
		String author = req.getParameter("author");
		InsertService insertService = new InsertService();
		insertService.InsertDate(name, price, bookCount, author);

		// 计算出分页后的分页数量
		int pageLength = 5;

		int endpage = Tool.getMaxPage(pageLength);
	
		// 当添加成功后，将查询页面设置为查看最后一页，查看之前刚添加的数据
		req.getRequestDispatcher("index.jsp?page="+endpage).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}

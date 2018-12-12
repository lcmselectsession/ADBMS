package com.wgh.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wgh.model.BookForm;
import com.wgh.service.SelectSercice;
import com.wgh.tools.Tool;
@WebServlet("/SelectBookByIDServlet")
public class SelectBookByIDServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		SelectSercice selectService = new SelectSercice();
		//将查询出的数据，装入bookForm的List集合中
		List<BookForm> lists = Tool.getBeanList(selectService.SelectBookByIDServlet(id));
		
		req.getSession().setAttribute("book", lists); // 将图书信息保存到HttpServletRequest中
		req.getRequestDispatcher("updatebook.jsp").forward(req, resp); // 转发重定向页面
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}

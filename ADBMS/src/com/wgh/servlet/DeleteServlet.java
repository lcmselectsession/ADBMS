package com.wgh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaoming.jdbc.MyJDBC;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	/**
	 * 通过ID进行删除
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String page = req.getParameter("page");
		String sql="delete from tb_book where id=?";
		MyJDBC.preUpdate(sql,id);
		req.getRequestDispatcher("/BookServlet?action=query&page="+Integer.parseInt(page)).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}

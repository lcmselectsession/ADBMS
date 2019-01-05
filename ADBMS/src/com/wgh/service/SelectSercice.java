package com.wgh.service;

import java.util.List;
import java.util.Map;

import com.xiaoming.jdbc.MyJDBC;

public class SelectSercice {
	public List<Map<String, Object>> SelectBookByIDServlet(String id) {
		String sql = "SELECT * FROM tb_book where id=?";
		return MyJDBC.preQuery(sql,id);
	}
}

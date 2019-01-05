package com.wgh.service;

import com.xiaoming.jdbc.MyJDBC;

public class UpdateService {
	public int UpdateBook(String name,String price,String bookCount,String author,String id) {
		if (id != "" && MyJDBC.preQuery("select * from tb_book where id=?", id).size() != 0) {
			String sql = "update tb_book set `name`=?,price=?,bookCount=?,author=? where id=?";
			return MyJDBC.preUpdate(sql, name, price, bookCount, author, id);
		}
		return 0; 
	}
}

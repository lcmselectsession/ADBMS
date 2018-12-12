package com.wgh.service;

import com.wgh.dao.MyJDBC;

public class InsertService {
	public void InsertDate(String name,String price,String bookCount,String author){
		String sql = "INSERT INTO `db_database11`.`tb_book` (`name`, `price`, `bookCount`, `author`) VALUES (?,?,?,?);";
		try {
			if (!"".equals(name) && !"".equals(price)&&!"".equals(bookCount)&&!"".equals(author)) {
				MyJDBC.preUpdate(sql, name, price, bookCount, author);
			}
		} catch (Exception e) {
			System.out.println("数据添加失败！");
		}
	}
}

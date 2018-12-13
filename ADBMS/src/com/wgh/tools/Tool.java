package com.wgh.tools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wgh.dao.MyJDBC;
import com.wgh.model.BookForm;

public class Tool {
	public static int getMaxPage(int pageLength) {
		int end = MyJDBC.Query("select id from tb_book").size();
		int endpage = end / pageLength;
		//当不整除时，加1
		if(end % pageLength!=0) {
			endpage = endpage+1;
		}
		return endpage;
	}
	
	public static int getMaxSearchPage(List<?> list,int pageLength) {
		int end = list.size();
		int endpage = end / pageLength;
		//当不整除时，加1
		if(end % pageLength!=0) {
			endpage = endpage+1;
		}
		return endpage;
	}
	/**
	 * 
	 * @param start	当前分页要查询的开始
	 * @param operation 上下页，首尾页
	 * @param endpage 数据库分页的最大页
	 * @return
	 */
	public static int getSelectStartPage(int start,String operation,int endpage) {
		//以下四步是对，对上下页，首尾页的判断
		if (operation.equals("sub")) {
			if (start != 1) {
				start--;
			}
		}
		if (operation.equals("add")) {
			if (start < endpage) {
				start++;
			}
		}
		if (operation.equals("max")) {
				start = endpage;
		}

		if (operation.equals("first")) {
			start = 1;
		}
		return start;
	}
	
	public static List<BookForm> getBeanList(List<Map<String, Object>> list){
		List<BookForm> lists = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Set<Map.Entry<String, Object>> entrySet = list.get(i).entrySet();
			BookForm f = new BookForm();
			for (Map.Entry<String, Object> entry : entrySet) {
				switch (entry.getKey()) {
				case "id":
					f.setId((int) entry.getValue());
					break;
				case "name":
					f.setName(entry.getValue().toString());
					break;
				case "price":
					f.setPrice((Float) entry.getValue()*100/100.0);
					break;
				case "bookCount":
					f.setBookCount((int) entry.getValue());
					break;
				case "author":
					f.setAuthor((entry.getValue().toString()));
					break;
				default:
					break;
				}
			}
			lists.add(f);
		}
		return lists;
		
	}
	public static List<BookForm> getBeanListHasLengthLimit(List<Map<String, Object>> list,int start,int pageLength){
		List<BookForm> lists = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Set<Map.Entry<String, Object>> entrySet = list.get(i).entrySet();
			BookForm f = new BookForm();
			if ((i >= ((start - 1) * pageLength)) && i < (start * pageLength)) {
				for (Map.Entry<String, Object> entry : entrySet) {
					switch (entry.getKey()) {
					case "id":
						f.setId((int) entry.getValue());
						break;
					case "name":
						f.setName(entry.getValue().toString());
						break;
					case "price":
						f.setPrice((Float) entry.getValue() * 100 / 100.0);
						break;
					case "bookCount":
						f.setBookCount((int) entry.getValue());
						break;
					case "author":
						f.setAuthor((entry.getValue().toString()));
						break;
					default:
						break;
					}
				}
				lists.add(f);
			}
		}
		return lists;
		
	}
	public static List<Integer> getPageList(int start,int endpage){
		List<Integer> pagelist = new LinkedList<Integer>();
		for (int i = 1; i <= 4; i++) {
			if (start >= 1 && start <= endpage) {
				pagelist.add(start);
				start++;
			}
		}
		return pagelist;
	}
}
/*if((--s1)>=1&&x!=0) {
pagelist.add(s1);
x--;
}
else if((++s2)<=endpage&&y!=0) {
pagelist.add(s2);
y--;
}else if(x==0&&y!=0) {
x=x+y;
pagelist.add(--s1);
x--;
}else if(x!=0&&y==0) {
y=y+x;
pagelist.add(++s2);
y--;
}else {
pagelist.add(start);
}*/
package com.wgh.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * @author 13576
 *
 */
public class MyJDBC {
	// 驱动名
	static String className;
	// 数据库路径
	static String url;
	// 用户名
	static String user;
	// 数据库密码
	static String password;
	// 定义一个当前线程集合用于存放数据库的connection连接
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	// 静态块用于从配置文件中取出数据库连接的驱动，路径，用户名，密码
	static {
		Properties properties = new Properties();
		InputStream instream = MyJDBC.class.getResourceAsStream("/jdbc.properties");
		try {
			properties.load(instream);
			className = properties.getProperty("classname");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			Class.forName(className);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("驱动未找到！");
			e.printStackTrace();
		}
	}

	/**
	 * @Description 返回一个数据库连接，
	 * @return Connection
	 */
	public static Connection getConnection() {
		// 从当前县城集合中获取connection连接
		Connection connection = threadLocal.get();
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url, user, password);
				// 将创建的connection连接添加到当前线程集合中
				threadLocal.set(connection);
			} catch (Exception e) {
				System.out.println("数据库连接失败！");
				e.printStackTrace();
			}
		}
		return connection;
	}

	// 用于关闭数据库连接
	/**
	 * @Description 关闭连接
	 * @return boolean
	 */
	public static boolean closeConnection() {
		boolean isClosed = true;
		Connection connection = threadLocal.get();
		threadLocal.set(null);
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				isClosed = false;
				e.printStackTrace();
			}
		}
		return isClosed;
	}

	/**
	 * @Description: 释放资源
	 * @param resultset
	 * @param statement
	 * @return boolean
	 */
	public static boolean release(ResultSet resultset, Statement statement) {
		boolean isClose = true;
		if (resultset != null) {
			try {
				resultset.close();
			} catch (SQLException e) {
				isClose = false;
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				isClose = false;
				e.printStackTrace();
			}
		}
		return isClose;
	}

	/**
	 * @Description 更新数据库
	 * @param sql 语句
	 * @return 成功修改数
	 */
	public static int Update(String sql) {
		Connection connection = null;
		Statement statement = null;
		int num = 0;
		try {
			connection = MyJDBC.getConnection();
			statement = connection.createStatement();
			num = statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyJDBC.release(null, statement);
		}
		return num;
	}

	/**
	 * @Description 预编译更新数据库
	 * @param sql    语句
	 * @param params 参数
	 * @return 成功修改数
	 */
	public static int preUpdate(String sql, Object... params) {
		Connection connection = null;
		PreparedStatement prestatement = null;
		int num = 0;
		try {
			connection = MyJDBC.getConnection();
			prestatement = connection.prepareStatement(sql);
			// i开始等于0，是因为不确定参数索引从0开始
			for (int i = 0; i < params.length; i++) {
				prestatement.setObject(i + 1, params[i].toString());
			}
			num = prestatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyJDBC.release(null, prestatement);
		}
		return num;
	}

	public static List<Map<String, Object>> Query(String sql) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSetMetaData metaData = null;
		connection = MyJDBC.getConnection();
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(sql);
			metaData = resultset.getMetaData();
			while (resultset.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					map.put(metaData.getColumnName(i), resultset.getObject(i));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyJDBC.release(resultset, statement);
		}
		return list;
	}

	public static List<Map<String, Object>> preQuery(String sql, Object... params) {
		Connection connection = null;
		PreparedStatement prestatement = null;
		ResultSet resultset = null;
		ResultSetMetaData metaData = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		connection = MyJDBC.getConnection();
		try {
			prestatement = connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {				
				for (int j = params[i].toString().length(); --j >= 0;) {
					if (!Character.isDigit(params[i].toString().charAt(j))) {
						prestatement.setObject(i + 1, params[i].toString());
					}else {
						prestatement.setObject(i + 1, Integer.parseInt(params[i].toString()));
					}
				}
			}
			resultset = prestatement.executeQuery();
			metaData = resultset.getMetaData();
			while (resultset.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					map.put(metaData.getColumnName(i), resultset.getObject(i));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyJDBC.release(resultset, prestatement);
		}
		return list;
	}


}

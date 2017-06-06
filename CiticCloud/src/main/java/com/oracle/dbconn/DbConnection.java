/**
 * 
 */
package com.oracle.dbconn;

import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @author Ray.Nan
 *
 */
public class DbConnection {

	private String filePath = "src/LocalDbConnect.xml";
	File xmlFile = new File(filePath);

	// 从XML中获取数据库信息
	public HashMap<String, String> getDbConfig() {

		HashMap<String, String> resultMap = new HashMap<String, String>();

		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(xmlFile);
			Element root = document.getRootElement();

			for (Iterator<?> iter = root.elementIterator(); iter.hasNext();) {
				Element e = (Element) iter.next();
				resultMap.put(e.getName(), e.getStringValue());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return resultMap;

	}

	// 获取数据库连接
	public Connection getConn() {

		DbConnection dbcc = new DbConnection();
		HashMap<String, String> resultMap = dbcc.getDbConfig();

		String Driver = resultMap.get("driver");
		String URL = resultMap.get("url");
		String UserName = resultMap.get("username");
		String PassWord = resultMap.get("password");

		Connection conn = null;
		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = (Connection) DriverManager.getConnection(URL, UserName,
					PassWord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭数据库链接
	public void closeAll(ResultSet rs, Statement stat, Connection conn) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (stat != null)
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static void main(String[] args) {
		DbConnection dbcc = new DbConnection();
		/*
		 * HashMap<String, String> resultMap = dbcc.getDbConfig();
		 * System.out.println(resultMap.get("driver"));
		 * System.out.println(resultMap.get("url"));
		 * System.out.println(resultMap.get("username"));
		 * System.out.println(resultMap.get("password"));
		 */

		Connection con = dbcc.getConn();

		try {
			if (!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement statement = (Statement) con.createStatement();
			String sql = "select * from test";
			ResultSet rs = statement.executeQuery(sql);
			System.out.println("-----------------");
			String job = null;
			String id = null;
			while (rs.next()) {
				// 获取stuname这列数据
				job = rs.getString("instance_id");
				// 获取stuid这列数据
				id = rs.getString("name");
				// 输出结果
				System.out.println(id + "\t" + job);
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

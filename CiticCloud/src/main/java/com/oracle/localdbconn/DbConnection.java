/**
 * 
 */
package com.oracle.localdbconn;

import java.io.File;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mysql.jdbc.Connection;

/**
 * @author Ray.Nan
 *
 */
public class DbConnection {

	private String filePath = this.getClass().getClassLoader().getResource("LocalDbConnect.xml").getFile(); //"src/LocalDbConnect.xml";
	File xmlFile = new File(filePath);

	// 从XML中获取数据库配置信息
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
		String ServerURL = resultMap.get("url");
		String TableName = resultMap.get("tablename");
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
			conn = (Connection) DriverManager.getConnection(ServerURL
					+ TableName, UserName, PassWord);
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

	// 查询数据库对象
	public ResultSet SelectData(String selectSql) {
		
		ResultSet rs = null;
		DbConnection dbcc = new DbConnection();
		Connection con = dbcc.getConn();

		try {
			if (!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement statement = (Statement) con.createStatement();
			rs = statement.executeQuery(selectSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	// 新增数据库对象
	public void InsertData(String sql, LocalDbObject ldo) {

		DbConnection dbcc = new DbConnection();

		try {
			/**
			 * Insert SQL Template
			 * 
			 * insert into co_operation
			 * (
			 * req_instanceId,
			 * instanceId,
			 * req_serviceId,
			 * instanceType,
			 * req_orgId,
			 * operationId,
			 * oprationType,
			 * req_UpdateTime,
			 * jobId,
			 * serviceUri,
			 * rep_CreateTime,
			 * rep_LastModifiedTime
			 * )
			 * values(?,?,?,?,?,?,?,?,?,?,?,?)"
			*/
			PreparedStatement preStmt = dbcc.getConn().prepareStatement(sql);
			
			preStmt.setString(1, ldo.getInstanceId());
			preStmt.setString(2, ldo.getReq_instanceId());
			preStmt.setString(3, ldo.getReq_serviceId());
			preStmt.setString(4, ldo.getInstanceType());
			preStmt.setString(5, ldo.getReq_orgId());
			preStmt.setString(6, ldo.getOperationId());
			preStmt.setString(7, ldo.getOprationType());
			preStmt.setTimestamp(8, ldo.getReq_UpdateTime());
			preStmt.setString(9, ldo.getJobId());
			preStmt.setString(10, ldo.getServiceUri());
			preStmt.setTimestamp(11, ldo.getRep_CreateTime());
			preStmt.setTimestamp(12, ldo.getRep_LastModifiedTime());

			preStmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 更新数据库对象
	public void UpdateData(String sql, LocalDbObject ldo) {

		DbConnection dbcc = new DbConnection();

		try {

			/**
			 * Insert SQL Template
			 * 
			 * update 
			 * 		co_operation
			 * set 
			 * 		req_instanceId=?,
			 * 		instanceId = ?,
			 * 		req_serviceId=?,
			 * 		instanceType = ?,
			 * 		req_orgId=?
			 * 		oprationType = ?,
			 * 		req_UpdateTime = ?,
			 * 		jobId = ?,
			 * 		serviceUri= ?,
			 * 		rep_CreateTime = ?,
			 * 		rep_LastModifiedTime = ?
			 *  where 
			 * 		operationId= ?
			*/
			PreparedStatement preStmt = dbcc.getConn().prepareStatement(sql);
			
			preStmt.setString(1, ldo.getInstanceId());
			preStmt.setString(2, ldo.getReq_instanceId());
			preStmt.setString(3, ldo.getReq_serviceId());
			preStmt.setString(4, ldo.getInstanceType());
			preStmt.setString(5, ldo.getReq_orgId());
			preStmt.setString(6, ldo.getOprationType());
			preStmt.setTimestamp(7, ldo.getReq_UpdateTime());
			preStmt.setString(8, ldo.getJobId());
			preStmt.setString(9, ldo.getServiceUri());
			preStmt.setTimestamp(10, ldo.getRep_CreateTime());
			preStmt.setTimestamp(11, ldo.getRep_LastModifiedTime());
			preStmt.setString(12, ldo.getOperationId());
			
			preStmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 生成32位随机数，用于区分每次操作
	public String getRamdomId() {
		UUID uuid = UUID.randomUUID();
		String OperationId = uuid.toString();
		return OperationId;
	}
	
	
	// 测试
	public static void main(String[] args) {

		DbConnection dbcc = new DbConnection();

		/*
		 * String insetSql = "insert into co_operation" +
		 * "(instanceId,instanceType,oprationType,req_UpdateTime,jobId,serviceUri,rep_CreateTime,rep_LastModifiedTime,operationId) "
		 * + "values(?,?,?,?,?,?,?,?,?)";
		 */
		String insetSql = "update co_operation set instanceId = ?, req_instanceId = ?, req_serviceId = ?,"
				+ "req_orgId = ?, instanceType = ?, oprationType = ?,req_UpdateTime = ?,jobId = ?,serviceUri= ?,"
				+ "rep_CreateTime = ?, rep_LastModifiedTime = ?"
				+ " where operationId= ?";
		LocalDbObject ldo = new LocalDbObject();
		ldo.setInstanceId("444");
		ldo.setOperationId("fef1737e-199d-480a-96c9-1dd9ebc676b5");

		Timestamp d = new Timestamp(System.currentTimeMillis());
		ldo.setReq_UpdateTime(d);

		// dbcc.InsertData(insetSql, ldo);
		dbcc.UpdateData(insetSql,ldo);

		/*
		 * HashMap<String, String> resultMap = dbcc.getDbConfig();
		 * System.out.println(resultMap.get("driver"));
		 * System.out.println(resultMap.get("url"));
		 * System.out.println(resultMap.get("username"));
		 * System.out.println(resultMap.get("password"));
		 */

		/*
		 * Connection con = dbcc.getConn();
		 * 
		 * try {
		 * 
		 * Statement statement = (Statement) con.createStatement(); String sql =
		 * "insert into co_operation" +
		 * "(instanceId,instanceType,oprationType,req_UpdateTime,jobId,serviceUri,rep_CreateTime,rep_LastModifiedTime) "
		 * + "values(?,?,?,?,?,?,?,?)";
		 * 
		 * 
		 * ResultSet rs = statement.executeQuery(sql);
		 * System.out.println("-----------------"); String job = null; String id
		 * = null; while (rs.next()) { // 获取stuname这列数据 job =
		 * rs.getString("id"); // 获取stuid这列数据 id = rs.getString("instanceId");
		 * // 输出结果 System.out.println(id + "\t" + job); } rs.close();
		 * con.close(); } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */

	}
}

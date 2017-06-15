/**
 * 
 */
package com.oracle.localdbconn;

import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mysql.jdbc.Connection;
import com.oracle.citiccloud.api.TransformUtil;

/**
 * @author Ray.Nan
 *
 */

public class DbConnection {
	

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
	public ResultSet selectData(String selectSql) {

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

	// 根据中信云instance id 查询当前instance最后操作记录
	public ResultSet selectLastRecordByInstanceId(String reqInstanceId) {
		ResultSet rs = null;
		DbConnection dbcc = new DbConnection();
		Connection con = dbcc.getConn();
		String selectSql = getLocalSql().get("selectLastRecordByInstanceId");

		try {
			if (!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			PreparedStatement preStmt = con.prepareStatement(selectSql);
			preStmt.setString(1, reqInstanceId);
			preStmt.setString(2, reqInstanceId);
			rs = preStmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}

	// 新增数据库对象
	public void insertData(String sql, LocalDbObject ldo) {

		DbConnection dbcc = new DbConnection();

		try {
			
			PreparedStatement preStmt = dbcc.getConn().prepareStatement(sql);

			preStmt.setString(1, ldo.getReq_instanceId());
			preStmt.setString(2, ldo.getInstanceId());
			preStmt.setString(3, ldo.getReq_serviceId());
			preStmt.setString(4, ldo.getInstanceType());
			preStmt.setString(5, ldo.getReq_orgId());
			preStmt.setString(6, ldo.getOperationId());
			preStmt.setString(7, ldo.getOprationType());
			preStmt.setTimestamp(8, ldo.getReq_UpdateTime());
			preStmt.setString(9, ldo.getJobId());
			preStmt.setString(10, ldo.getServiceUri());
			preStmt.setString(11, ldo.getRep_status());
			preStmt.setTimestamp(12, ldo.getRep_CreateTime());
			preStmt.setTimestamp(13, ldo.getRep_LastModifiedTime());

			preStmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 更新新增返回的数据数据库对象
		public void updateSQLByInsertReply(String sql, LocalDbObject ldo) {

			DbConnection dbcc = new DbConnection();

			try {
				PreparedStatement preStmt = dbcc.getConn().prepareStatement(sql);
				preStmt.setString(1, ldo.getJobId());
				preStmt.setString(2, ldo.getServiceUri());
				preStmt.setString(3, ldo.getRep_status());
				preStmt.setTimestamp(4, ldo.getRep_CreateTime());
				preStmt.setTimestamp(5, ldo.getRep_LastModifiedTime());
				preStmt.setTimestamp(6, ldo.getReq_UpdateTime());
				preStmt.setString(7, ldo.getOperationId());

				preStmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	// 更新数据库对象
	public void updateData(String sql, LocalDbObject ldo) {

		DbConnection dbcc = new DbConnection();

		try {

			/**
			 * Insert SQL Template
			 * 
			 * update co_operation set req_instanceId=?, instanceId = ?,
			 * req_serviceId=?, instanceType = ?, req_orgId=? oprationType = ?,
			 * req_UpdateTime = ?, jobId = ?, serviceUri= ?, rep_CreateTime = ?,
			 * rep_LastModifiedTime = ? where operationId= ?
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
	public String getRamdonId() {
		UUID uuid = UUID.randomUUID();
		String OperationId = uuid.toString();
		return OperationId;
	}

	// 新增数据库时根据CITIC的请求对象组装
	public LocalDbObject setInsertObj(String reqInstanceId, String ramdonId,JSONObject body) {

		LocalDbObject localDBO = new LocalDbObject();

		localDBO.setReq_instanceId(reqInstanceId);
		
		localDBO.setInstanceId((String)TransformUtil.mapToJson(body.get("parameters")).get("serviceName"));// serviceName
		localDBO.setReq_serviceId((String) body.get("service_id")); // C请求的服务类型dbaas或jaas
		localDBO.setInstanceType("daas");// 创建时为固定create
		localDBO.setReq_orgId((String) body.get("org_id"));
		localDBO.setOperationId(ramdonId);// 随机数
		localDBO.setOprationType("create"); // 创建时为固定create
		localDBO.setReq_UpdateTime(new Timestamp(System.currentTimeMillis()));// 当前时间
		localDBO.setJobId(null);// 创建时 null，返回的response中获取Location
		localDBO.setServiceUri(null); // 创建时 null，返回的response中获取Service-URI
		localDBO.setRep_status(null); // 创建时 null，返回的response.getStatus()
		localDBO.setRep_CreateTime(null); // 创建时 null，查看instance时，creation_time
		localDBO.setRep_LastModifiedTime(null); // 创建时
			
		return localDBO;
	}
	
	// 新增数据库时根据Oracle返回的请求对象组装
	public LocalDbObject setInsertRepObj(ResultSet rs,String ramdonId,String oprationType) {

		LocalDbObject localDBO = new LocalDbObject();

		try {
			localDBO.setReq_instanceId(rs.getString("req_instanceId"));
			localDBO.setInstanceId(rs.getString("instanceId"));
			localDBO.setReq_serviceId(rs.getString("req_serviceId")); 
			localDBO.setInstanceType(rs.getString("instanceType"));
			localDBO.setReq_orgId(rs.getString("req_orgId"));
			localDBO.setOperationId(ramdonId);// 随机数
			localDBO.setOprationType(oprationType); // 创建时为根据传入的操作stop/start/restart
			localDBO.setReq_UpdateTime(new Timestamp(System.currentTimeMillis()));// 当前时间
			localDBO.setJobId(rs.getString("jobId"));
			localDBO.setServiceUri(rs.getString("serviceUri")); 
			localDBO.setRep_status(rs.getString("rep_status")); 
			localDBO.setRep_CreateTime(null); 
			localDBO.setRep_LastModifiedTime(null); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 	
		return localDBO;
	}

	// 查询所有数据 并把对应最新的数据的对象设置在本地对象中
	public LocalDbObject setObjBySelectAll(String SQL,String reqInstanceId, String ramdonId,JSONObject body){
		
		LocalDbObject localDBO = new LocalDbObject();
		
		return localDBO;
	}

	// 从XML中获取信息
	public HashMap<String, String> xmlInfo(File file) {

		HashMap<String, String> resultMap = new HashMap<String, String>();

		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(file);
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

	// 从XML中获取本地数据库配置信息
	public HashMap<String, String> getDbConfig() {
		return xmlInfo(localDbConfig);
	}

	// 获取DAAS信息
	public HashMap<String, String> getOracleCloudAccInfo() {
		return xmlInfo(dbaasConfig);
	}

	// 获取本地数据库的SQL信息
	public HashMap<String, String> getLocalSql() {
		return xmlInfo(localSql);
	}

	File dbaasConfig = new File(getClass().getClassLoader().getResource("DbInstanceInfo.xml").getFile());
	File localDbConfig = new File(getClass().getClassLoader().getResource("LocalDbConnect.xml").getFile());
	File localSql = new File(getClass().getClassLoader().getResource("LocalSql.xml").getFile());

	// 测试
	public static void main(String[] args) {

		DbConnection dbcc = new DbConnection();

		
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
		dbcc.updateData(insetSql, ldo);

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
		 * "(req_instanceId,instanceId,req_serviceId,instanceType,req_orgId,operationId,oprationType,req_UpdateTime,jobId,serviceUri,rep_status,rep_CreateTime,rep_LastModifiedTime) "
		 * + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
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

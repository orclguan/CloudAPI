package com.oracle.citiccloud.api.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.oracle.citiccloud.api.ApiResponseMessage;
import com.oracle.citiccloud.api.NotFoundException;
import com.oracle.citiccloud.api.SuppliersServiceApiService;
import com.oracle.citiccloud.api.TransformUtil;
import com.oracle.citiccloud.model.UsageAndExpensesData;
import com.oracle.citiccloud.model.UsageAndExpensesFormat;
import com.oracle.citiccloud.model.UsageRecord;
import com.oracle.localdbconn.DbConnection;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class SuppliersServiceApiServiceImpl extends SuppliersServiceApiService {
    
	/**
	 * 收支明细
	 */
	@Override
    public Response suppliersServiceBillDetailGet( @NotNull String startDate,  @NotNull String endDate, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
	
	
	/**
	 * 消费明细
	 */
    @Override
    public Response suppliersServiceConsumeDetailGet( @NotNull String startDate,  @NotNull String endDate, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    /**
     * 账单查询
     */
    @Override
    public Response suppliersServiceTotalBillGet( @NotNull String startDate,  @NotNull String endDate,  @NotNull String supplierId,  @NotNull String grainSize, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    /**
     * 使用记录
     */
	@Override
	public Response suppliersServiceUsageRecordGet( @NotNull String startDate,  @NotNull String endDate,  @NotNull String serviceId,  @NotNull String grainSize, SecurityContext securityContext) throws NotFoundException {
		// 样例 
		/*{
		  "service_id": "cffe8d3c-7628-4378-a07f-a7bf6c3871a1",
		  "start_date": "20160531",
		  "end_date": "20160631",
		  "grain_size": "hour"
		}*/

		// 使用粒度，小时或天。hour/day

		DbConnection dbCon = new DbConnection();
		UsageRecord ur = new UsageRecord();
		String configurationsJson = TransformUtil.readJsonFile("configurations_dbcs.json");
		List<UsageAndExpensesFormat> ueFormatList = TransformUtil.mapToObject(
				configurationsJson, new TypeReference<List<UsageAndExpensesFormat>>(){});
		List<UsageAndExpensesData> ueDataList = new ArrayList<UsageAndExpensesData>();

		String sql = dbCon.getLocalSql().get("selectUsageRecord");

		try {
			PreparedStatement preStmt = dbCon.getConn().prepareStatement(sql);
			preStmt.setObject(1,grainSize);
			preStmt.setString(2,serviceId); 
			preStmt.setString(3,startDate); 
			preStmt.setString(4,endDate);
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				UsageAndExpensesData ueData = new UsageAndExpensesData();
				ueData.setInstanceId(rs.getString("req_instanceId"));
				ueData.setStartTime(toUtcTime(rs.getString("startDate")));
				ueData.setEndTime(toUtcTime(rs.getString("endDate")));

				ueDataList.add(ueData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ur.setUsageAndExpensesFormat(ueFormatList);
		ur.setUsageAndExpensesData(ueDataList);

		return Response.ok().entity(ur).build();
	}

	private String toUtcTime(String str) {
		// 采用UTC时间格式；例如2016-10-22T22:00:00Z
		SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date;
		try {
			date = dbFormat.parse(str);
		} catch (ParseException e) {
			return "";
		}

		return utcFormat.format(date);
	}
}

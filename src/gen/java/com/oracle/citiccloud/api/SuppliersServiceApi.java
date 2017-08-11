package com.oracle.citiccloud.api;

import com.oracle.citiccloud.model.*;
import com.oracle.citiccloud.api.SuppliersServiceApiService;
import com.oracle.citiccloud.api.factories.SuppliersServiceApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import com.oracle.citiccloud.model.Balance;
import com.oracle.citiccloud.model.BillDetail;
import com.oracle.citiccloud.model.TotalBill;
import com.oracle.citiccloud.model.UsageRecord;

import java.util.List;
import com.oracle.citiccloud.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/suppliers_service")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the suppliers_service API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class SuppliersServiceApi  {
   private final SuppliersServiceApiService delegate = SuppliersServiceApiServiceFactory.getSuppliersServiceApi();

    @GET
    @Path("/bill_detail")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "供应商服务管理收支明细.", notes = "此接口点用于中信云平台按照指定时间周期获取所有供应商服务的服务信息，主要为收支明细.", response = BillDetail.class, tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "收支明细.", response = BillDetail.class) })
    public Response suppliersServiceBillDetailGet(@ApiParam(value = "查询的开始时间 yymmdd 例如 20160501.",required=true) @QueryParam("start_date") String startDate
,@ApiParam(value = "查询的结束时间 yymmdd 例如 20160531.",required=true) @QueryParam("end_date") String endDate
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.suppliersServiceBillDetailGet(startDate,endDate,securityContext);
    }
    @GET
    @Path("/consume_detail")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "供应商服务管理消费明细.", notes = "此接口用于中信云平台按照指定时间周期获取所有供应商服务的服务信息，主要为消费明细.", response = Balance.class, tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "消费明细.", response = Balance.class) })
    public Response suppliersServiceConsumeDetailGet(@ApiParam(value = "查询的开始时间 yymmdd 例如 20160501.",required=true) @QueryParam("start_date") String startDate
,@ApiParam(value = "查询的结束时间 yymmdd 例如 20160531.",required=true) @QueryParam("end_date") String endDate
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.suppliersServiceConsumeDetailGet(startDate,endDate,securityContext);
    }
    @GET
    @Path("/total_bill")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "查询供应商总账.", notes = "此接口用于获取某时间段内的所有账单(时间粒度为月).", response = TotalBill.class, tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "使用记录.", response = TotalBill.class) })
    public Response suppliersServiceTotalBillGet(@ApiParam(value = "查询的开始时间 yymmdd 例如 20160501.",required=true) @QueryParam("start_date") String startDate
,@ApiParam(value = "查询的结束时间 yymmdd 例如 20160531.",required=true) @QueryParam("end_date") String endDate
,@ApiParam(value = "供应商ID.",required=true) @QueryParam("supplier_id") String supplierId
,@ApiParam(value = "使用粒度，目前只有月",required=true, allowableValues="month") @QueryParam("grain_size") String grainSize
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.suppliersServiceTotalBillGet(startDate,endDate,supplierId,grainSize,securityContext);
    }
    @GET
    @Path("/usage_record")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "供应商服务管理使用记录.", notes = "此接口用于中信云平台按照指定时间周期获取所有供应商服务的服务信息，主要为使用记录.", response = UsageRecord.class, tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "使用记录.", response = UsageRecord.class) })
    public Response suppliersServiceUsageRecordGet(@ApiParam(value = "查询的开始时间 yymmdd 例如 20160501.",required=true) @QueryParam("start_date") String startDate
,@ApiParam(value = "查询的结束时间 yymmdd 例如 20160531.",required=true) @QueryParam("end_date") String endDate
,@ApiParam(value = "服务ID.",required=true) @QueryParam("service_id") String serviceId
,@ApiParam(value = "使用粒度，小时或天.",required=true, allowableValues="hour, day") @QueryParam("grain_size") String grainSize
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.suppliersServiceUsageRecordGet(startDate,endDate,serviceId,grainSize,securityContext);
    }
}

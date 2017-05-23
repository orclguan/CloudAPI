package com.oracle.citiccloud.api;

import com.oracle.citiccloud.api.*;
import com.oracle.citiccloud.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.oracle.citiccloud.model.Balance;
import com.oracle.citiccloud.model.BillDetail;
import com.oracle.citiccloud.model.TotalBill;
import com.oracle.citiccloud.model.UsageRecord;

import java.util.List;
import com.oracle.citiccloud.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public abstract class SuppliersServiceApiService {
    public abstract Response suppliersServiceBillDetailGet( @NotNull String startDate, @NotNull String endDate,SecurityContext securityContext) throws NotFoundException;
    public abstract Response suppliersServiceConsumeDetailGet( @NotNull String startDate, @NotNull String endDate,SecurityContext securityContext) throws NotFoundException;
    public abstract Response suppliersServiceTotalBillGet( @NotNull String startDate, @NotNull String endDate, @NotNull String supplierId, @NotNull String grainSize,SecurityContext securityContext) throws NotFoundException;
    public abstract Response suppliersServiceUsageRecordGet( @NotNull String startDate, @NotNull String endDate, @NotNull String serviceId, @NotNull String grainSize,SecurityContext securityContext) throws NotFoundException;
}

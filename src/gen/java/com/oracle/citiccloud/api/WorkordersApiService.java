package com.oracle.citiccloud.api;

import com.oracle.citiccloud.api.*;
import com.oracle.citiccloud.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.oracle.citiccloud.model.ContentInsertMsg;
import com.oracle.citiccloud.model.OrdersContentInsert;
import com.oracle.citiccloud.model.PageWorkorder;
import com.oracle.citiccloud.model.Workorder;
import com.oracle.citiccloud.model.WorkorderContent;
import com.oracle.citiccloud.model.WorkorderMsg;

import java.util.List;
import com.oracle.citiccloud.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public abstract class WorkordersApiService {
    public abstract Response workordersContentsGet( @NotNull String questionId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response workordersContentsPost(OrdersContentInsert body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response workordersGet( String ids, String addStartTime, String addEndTime, String serviceIds, String questionStatus, String pageSize, String pageStart,SecurityContext securityContext) throws NotFoundException;
    public abstract Response workordersPost(Workorder workorder,SecurityContext securityContext) throws NotFoundException;
}
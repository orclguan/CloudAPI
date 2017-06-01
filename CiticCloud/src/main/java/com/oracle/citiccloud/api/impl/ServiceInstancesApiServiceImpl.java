package com.oracle.citiccloud.api.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oracle.citiccloud.api.*;

import com.oracle.citiccloud.model.Instances;
import com.oracle.citiccloud.model.ServiceInstance;
import com.oracle.citiccloud.model.ServiceInstanceModify;

import java.util.ArrayList;
import java.util.List;
import com.oracle.citiccloud.api.NotFoundException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class ServiceInstancesApiServiceImpl extends ServiceInstancesApiService {
    
	/**
	 * 返回服务实例列表
	 * serviceId: 
	 * instanceIds: 
	 * 
	 */
	@Override
    public Response serviceInstancesGet( @NotNull String serviceId,  List<String> instanceIds, SecurityContext securityContext) throws NotFoundException {
        
		if (serviceId==null || serviceId.equals("")){
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
			
		}else if(serviceId.equalsIgnoreCase("dbcs")){
			return Response.ok().entity(this.getDBCSInstances()).build();
			
		}else if(serviceId.equalsIgnoreCase("jcs")){
			return Response.ok().entity(this.getJCSInstances()).build();
			
		}else if(serviceId.equalsIgnoreCase("iaas")){
			return Response.ok().entity(this.getIaaSInstances()).build();
			
		}else{
			return Response.status(Response.Status.BAD_REQUEST).entity("incorrect serviceId").type(MediaType.APPLICATION_JSON).build();
		}
        
    }
	
	/**
     * 创建服务实例
     * instanceId: 由中信云平台给提供。这个ID会用来对此服务实例进行后继操作，所以适配器必须将此ID与具体的服务实例相关联
     * body: 
     * 
     */
    @Override
    public Response serviceInstancesInstanceIdPut(String instanceId, ServiceInstance body, SecurityContext securityContext) throws NotFoundException {
    	
        //记录操作日志
        
        //将异步操作job URI（OPC API返回json中）记录，用于查询job状态。调用OPC API后，从返回的header中解析Location获取job。例如：https://dbaas.oraclecloud.com:443/paas/service/dbcs/api/v1.1/instances/midas/status/delete/job/11901471
        
        //先返回202 ACCEPTED
        
        /*
         * @RAY 处理逻辑 理解
         * 
        1. 记录操作日志 。疑问：记录操作日志指的是往数据库中插入本次操作相关的记录数据吗 ？若是 输入哪些业务内容？
        2. 将异步操作job URI。 接口: 调用 POST: /paas/service/dbcs/api/v1.1/instances/{identityDomainId}: ，获得JSON文件。疑问：请确认API是否正确？
        3. 判断HTTP 状态是否为202 
        3.1. 状态为202时，重JSON文件中获取Location对应的URI
        3.1.1. 疑问：存location对应的URI信息到要插入到数据中吗？
        3.1.2. 组装Response为202 并返回。 疑问：若只返回状态202 ACCEPRED的话，需要转换到中信的接口中的对应字段是？ 若返回的Response不仅仅需要状态202 ACCPET的话，应该返回哪些信息？对应的字段是哪些？
        3.2. 疑问：状态不是202时，怎么处理？
        4. 疑问：数据库中插入数据吗？若需要插入的话需要哪些内容？
        */
    	
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    /**
     * 变更服务实例
     * 
     * 
     */
    @Override
    public Response serviceInstancesInstanceIdPost(String instanceId, ServiceInstanceModify body, SecurityContext securityContext) throws NotFoundException {
    	//记录操作日志
    	
        //先查看该instance是否有未完成任务
        
        /*
         *@RAY 处理逻辑 理解： 
         * 
        1. 记录操作日志。
        疑问：数据库中插入本次操作相关的记录数据吗 ？还是数据库中更新数据？ 输入哪些内容？
        2. 先查看该instance是否有未完成任务。
        疑问：调用的接口顺序是什么？
        调用查看Instance接口获取该instance的job？ GET : /paas/service/dbcs/api/v1.1/instances/{identityDomainId}/{serviceId} ； 疑问：对应的jobid是creation_job_id吗？有多个JOB的时候怎么办？
        调用查看Job状态的接口获取job信息？ GET :/paas/service/dbcs/api/v1.1/instances/{identityDomainId}/status/{requestName}/job/{jobId}；疑问：request参数requestName有三种值：delete,create,control ,这三种都去查吗？还是？
        获取job状态后怎么处理？
        变更服务实例的接口：PUT : /paas/service/dbcs/api/v1.1/instances/{identityDomainId}/{serviceId}/ibkup
        
        更新完毕后，还需要往数据库中存入数据吗？需要存入的话有哪些业务数据？
        最后封装response的数据项要有哪些？
        
        
        */
    	
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
	/**
	 * 删除服务实例
	 * 
	 * 
	 */
    @Override
    public Response serviceInstancesInstanceIdDelete(String instanceId,  @NotNull Boolean acceptsIncomplete, SecurityContext securityContext) throws NotFoundException {
    	//记录操作日志
    	
        /*
         *@RAY 处理逻辑 理解： 
         * 
        1. 记录操作日志。
        疑问：数据库中插入本次操作相关的记录数据吗 ？还是数据库中更新数据？ 输入哪些内容？
     2.调用删除的接口：DELETE：/paas/service/dbcs/api/v1.1/instances/{identityDomainId}/{serviceId}
        此时还需要往数据库中存入数据吗？需要存入的话有哪些业务数据？
        最后封装response的数据项要有哪些？
        
        */
    	return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    
	/**
	 * 获取正在进行的上一个操作
	 * 
	 */
    @Override
    public Response serviceInstancesInstanceIdLastOperationGet(String instanceId, SecurityContext securityContext) throws NotFoundException {
        // TODO：通过instanceId查询job URI，根据job URI查job status
        /*
         *@RAY 处理逻辑 理解： 
         * 
        直接从数据库中查询Job Status吗？前提是数据库中提前存入job URI 和job status.
        或者 
        需要从OPC中获取的话，三种操作都有不同的接口，因此都需要去调用一次吗？还是？ 
        */ 
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    /**
     * 可选接口，暂不实现。
     */
    @Override
    public Response serviceInstancesVerifyPut(ServiceInstance instance, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
	
	private List<Instances> getDBCSInstances(){
		
		//TODO: 配置文件或者数据库中读取
		String domain = "midas";
		String baseUrl = "https://dbaas.oraclecloud.com/paas/service/dbcs/api/v1.1/instances/";
		String username = "duanhui@midas.site";
        String password = "CiticC1oud@orc1";

        String usernameAndPassword = username + ":" + password;
        String authorizationHeaderName = "Authorization";
        String authorizationHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString( usernameAndPassword.getBytes() );
        
        Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(baseUrl + domain);
		
		//TODO: 如果获取超时需处理
		String response = myResource.request()
				.header( authorizationHeaderName, authorizationHeaderValue )
		        .header("X-ID-TENANT-NAME", domain)
		        .get(String.class);
		
		System.out.println("response: " + response);
		
		JsonObject obj = new JsonParser().parse(response).getAsJsonObject();
		System.out.println("service_type:" + obj.get("service_type"));
		
		JsonArray arry = obj.getAsJsonArray("services");
		
		List<Instances> list = new ArrayList<Instances>();
		
		for (int i=0; i<arry.size(); i++){
			JsonObject o = (JsonObject) arry.get(i);
			System.out.println("service_name:" + o.get("service_name"));
			
			Instances ist = new Instances();
			ist.setId(new Integer(i).toString());
			ist.setServiceId(o.get("service_name").getAsString());
			
			list.add(ist);
		}
		
		return list;
	}
	
	
	private List<Instances> getJCSInstances(){
		
		List<Instances> list = new ArrayList<Instances>();
		
		
		return list;
	}
	
	
	private List<Instances> getIaaSInstances(){
		
		String authUrl = "https://api-Z54.compute.us6.oraclecloud.com/authenticate/";
		Client client = ClientBuilder.newClient();
		WebTarget auth = client.target(authUrl);
		
		// get cookie first
		Response clientResponse = auth.request(MediaType.TEXT_PLAIN)
				.header("Content-Type", "application/oracle-compute-v3+json")
				.post(Entity.json("{\"user\":\"/Compute-midas/duanhui@midas.site\",\"password\":\"CiticC1oud@orc1\"}"));
		
		System.out.println("status: " + clientResponse.getStatus());
		System.out.println("cookie: " + clientResponse.getHeaderString("Set-Cookie"));
		System.out.println("Expires: " + clientResponse.getHeaderString("Expires"));
		
		String cookieStr = clientResponse.getHeaderString("Set-Cookie");
		String nimbula = cookieStr.split(";")[0].substring(8);
		
		Cookie myCookie = new Cookie("nimbula", nimbula, "/", "");
		
		String url = "https://api-Z54.compute.us6.oraclecloud.com/instance/Compute-midas/duanhui@midas.site/";
		
		WebTarget myResource = client.target(url);
		String response = myResource.request(MediaType.TEXT_PLAIN)
		        .header("Accept", "application/oracle-compute-v3+json")
		        .cookie(myCookie)
		        .get(String.class);
		
		System.out.println("response: " + response);
		
		List<Instances> list = new ArrayList<Instances>();
		
		// parse response, add to list
		
		return list;
	}
}
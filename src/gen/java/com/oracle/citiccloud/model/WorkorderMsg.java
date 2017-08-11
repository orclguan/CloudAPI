/*
 * 中信云平台供应商适配器管理内部API
 * 中信云供应商适配器API定义了供应商/租户/用户管理、服务管理、计费管理等功能模块构成的中信云平台与供应商适配器之间的协议。适配器应在一个URI（统一资源标识符）下实现多个HTTP或HTTPS接入点。一个适配器可以提供多个供应商，每个供应商可包括多个服务（一个供应商的服务仅能存在于一个适配器中）。理想状态是，一个适配器仅包含一个供应商，及其全部服务。适配器应该是可以水平弹性扩展的。多个中信云平台部署实例可以用不同的地址和授权访问同一个适配器.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.oracle.citiccloud.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

/**
 * WorkorderMsg
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class WorkorderMsg   {
  @JsonProperty("data")
  private String data = null;

  @JsonProperty("success")
  private String success = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("code")
  private String code = null;

  public WorkorderMsg data(String data) {
    this.data = data;
    return this;
  }

   /**
   * 成功时返回新创建工单的ID
   * @return data
  **/
  @JsonProperty("data")
  @ApiModelProperty(value = "成功时返回新创建工单的ID")
  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public WorkorderMsg success(String success) {
    this.success = success;
    return this;
  }

   /**
   * true 成功；false 失败
   * @return success
  **/
  @JsonProperty("success")
  @ApiModelProperty(required = true, value = "true 成功；false 失败")
  @NotNull
  public String getSuccess() {
    return success;
  }

  public void setSuccess(String success) {
    this.success = success;
  }

  public WorkorderMsg message(String message) {
    this.message = message;
    return this;
  }

   /**
   * 接口返回失败时，一般会通过它显示详细错误提示，进行接口调用自查
   * @return message
  **/
  @JsonProperty("message")
  @ApiModelProperty(value = "接口返回失败时，一般会通过它显示详细错误提示，进行接口调用自查")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public WorkorderMsg code(String code) {
    this.code = code;
    return this;
  }

   /**
   * 错误编码
   * @return code
  **/
  @JsonProperty("code")
  @ApiModelProperty(value = "错误编码")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkorderMsg workorderMsg = (WorkorderMsg) o;
    return Objects.equals(this.data, workorderMsg.data) &&
        Objects.equals(this.success, workorderMsg.success) &&
        Objects.equals(this.message, workorderMsg.message) &&
        Objects.equals(this.code, workorderMsg.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, success, message, code);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WorkorderMsg {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


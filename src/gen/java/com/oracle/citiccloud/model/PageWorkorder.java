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
import com.oracle.citiccloud.model.WorkorderBasic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * PageWorkorder
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class PageWorkorder   {
  @JsonProperty("total_count")
  private Integer totalCount = null;

  @JsonProperty("data")
  private List<WorkorderBasic> data = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("message")
  private String message = null;

  public PageWorkorder totalCount(Integer totalCount) {
    this.totalCount = totalCount;
    return this;
  }

   /**
   * 总条目数
   * @return totalCount
  **/
  @JsonProperty("total_count")
  @ApiModelProperty(value = "总条目数")
  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  public PageWorkorder data(List<WorkorderBasic> data) {
    this.data = data;
    return this;
  }

  public PageWorkorder addDataItem(WorkorderBasic dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<WorkorderBasic>();
    }
    this.data.add(dataItem);
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @JsonProperty("data")
  @ApiModelProperty(value = "")
  public List<WorkorderBasic> getData() {
    return data;
  }

  public void setData(List<WorkorderBasic> data) {
    this.data = data;
  }

  public PageWorkorder code(String code) {
    this.code = code;
    return this;
  }

   /**
   * 错误代码
   * @return code
  **/
  @JsonProperty("code")
  @ApiModelProperty(value = "错误代码")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public PageWorkorder message(String message) {
    this.message = message;
    return this;
  }

   /**
   * 错误消息
   * @return message
  **/
  @JsonProperty("message")
  @ApiModelProperty(value = "错误消息")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageWorkorder pageWorkorder = (PageWorkorder) o;
    return Objects.equals(this.totalCount, pageWorkorder.totalCount) &&
        Objects.equals(this.data, pageWorkorder.data) &&
        Objects.equals(this.code, pageWorkorder.code) &&
        Objects.equals(this.message, pageWorkorder.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, data, code, message);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageWorkorder {\n");
    
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

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
 * WorkorderContentDetail
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class WorkorderContentDetail   {
  @JsonProperty("content")
  private String content = null;

  @JsonProperty("note_type")
  private String noteType = null;

  @JsonProperty("gmt_created")
  private String gmtCreated = null;

  @JsonProperty("attach_ids")
  private String attachIds = null;

  public WorkorderContentDetail content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 类型为chat和sec_chat的内容
   * @return content
  **/
  @JsonProperty("content")
  @ApiModelProperty(value = "类型为chat和sec_chat的内容")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public WorkorderContentDetail noteType(String noteType) {
    this.noteType = noteType;
    return this;
  }

   /**
   * 可选值为chat|sec_chat
   * @return noteType
  **/
  @JsonProperty("note_type")
  @ApiModelProperty(value = "可选值为chat|sec_chat")
  public String getNoteType() {
    return noteType;
  }

  public void setNoteType(String noteType) {
    this.noteType = noteType;
  }

  public WorkorderContentDetail gmtCreated(String gmtCreated) {
    this.gmtCreated = gmtCreated;
    return this;
  }

   /**
   * 创建时间
   * @return gmtCreated
  **/
  @JsonProperty("gmt_created")
  @ApiModelProperty(value = "创建时间")
  public String getGmtCreated() {
    return gmtCreated;
  }

  public void setGmtCreated(String gmtCreated) {
    this.gmtCreated = gmtCreated;
  }

  public WorkorderContentDetail attachIds(String attachIds) {
    this.attachIds = attachIds;
    return this;
  }

   /**
   * 附件ID列表,逗号分隔
   * @return attachIds
  **/
  @JsonProperty("attach_ids")
  @ApiModelProperty(value = "附件ID列表,逗号分隔")
  public String getAttachIds() {
    return attachIds;
  }

  public void setAttachIds(String attachIds) {
    this.attachIds = attachIds;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkorderContentDetail workorderContentDetail = (WorkorderContentDetail) o;
    return Objects.equals(this.content, workorderContentDetail.content) &&
        Objects.equals(this.noteType, workorderContentDetail.noteType) &&
        Objects.equals(this.gmtCreated, workorderContentDetail.gmtCreated) &&
        Objects.equals(this.attachIds, workorderContentDetail.attachIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, noteType, gmtCreated, attachIds);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WorkorderContentDetail {\n");
    
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    noteType: ").append(toIndentedString(noteType)).append("\n");
    sb.append("    gmtCreated: ").append(toIndentedString(gmtCreated)).append("\n");
    sb.append("    attachIds: ").append(toIndentedString(attachIds)).append("\n");
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

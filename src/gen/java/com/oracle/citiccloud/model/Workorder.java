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
 * Workorder
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class Workorder   {
  @JsonProperty("service_id")
  private String serviceId = null;

  @JsonProperty("customer_common_question_id")
  private String customerCommonQuestionId = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("sec_content")
  private String secContent = null;

  @JsonProperty("supplier_uid")
  private String supplierUid = null;

  @JsonProperty("sms_alert_time")
  private String smsAlertTime = null;

  @JsonProperty("telephone")
  private String telephone = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("attachments")
  private String attachments = null;

  public Workorder serviceId(String serviceId) {
    this.serviceId = serviceId;
    return this;
  }

   /**
   * 服务ID
   * @return serviceId
  **/
  @JsonProperty("service_id")
  @ApiModelProperty(value = "服务ID")
  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  public Workorder customerCommonQuestionId(String customerCommonQuestionId) {
    this.customerCommonQuestionId = customerCommonQuestionId;
    return this;
  }

   /**
   * 常见问题ID
   * @return customerCommonQuestionId
  **/
  @JsonProperty("customer_common_question_id")
  @ApiModelProperty(value = "常见问题ID")
  public String getCustomerCommonQuestionId() {
    return customerCommonQuestionId;
  }

  public void setCustomerCommonQuestionId(String customerCommonQuestionId) {
    this.customerCommonQuestionId = customerCommonQuestionId;
  }

  public Workorder title(String title) {
    this.title = title;
    return this;
  }

   /**
   * 请求标题
   * @return title
  **/
  @JsonProperty("title")
  @ApiModelProperty(value = "请求标题")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Workorder content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 工单提问正文
   * @return content
  **/
  @JsonProperty("content")
  @ApiModelProperty(value = "工单提问正文")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Workorder secContent(String secContent) {
    this.secContent = secContent;
    return this;
  }

   /**
   * 机密信息
   * @return secContent
  **/
  @JsonProperty("sec_content")
  @ApiModelProperty(value = "机密信息")
  public String getSecContent() {
    return secContent;
  }

  public void setSecContent(String secContent) {
    this.secContent = secContent;
  }

  public Workorder supplierUid(String supplierUid) {
    this.supplierUid = supplierUid;
    return this;
  }

   /**
   * 供应商用户ID
   * @return supplierUid
  **/
  @JsonProperty("supplier_uid")
  @ApiModelProperty(value = "供应商用户ID")
  public String getSupplierUid() {
    return supplierUid;
  }

  public void setSupplierUid(String supplierUid) {
    this.supplierUid = supplierUid;
  }

  public Workorder smsAlertTime(String smsAlertTime) {
    this.smsAlertTime = smsAlertTime;
    return this;
  }

   /**
   * 短信通知时间
   * @return smsAlertTime
  **/
  @JsonProperty("sms_alert_time")
  @ApiModelProperty(value = "短信通知时间")
  public String getSmsAlertTime() {
    return smsAlertTime;
  }

  public void setSmsAlertTime(String smsAlertTime) {
    this.smsAlertTime = smsAlertTime;
  }

  public Workorder telephone(String telephone) {
    this.telephone = telephone;
    return this;
  }

   /**
   * 用户手机
   * @return telephone
  **/
  @JsonProperty("telephone")
  @ApiModelProperty(value = "用户手机")
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public Workorder email(String email) {
    this.email = email;
    return this;
  }

   /**
   * 用户邮箱
   * @return email
  **/
  @JsonProperty("email")
  @ApiModelProperty(value = "用户邮箱")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Workorder attachments(String attachments) {
    this.attachments = attachments;
    return this;
  }

   /**
   * 附加信息
   * @return attachments
  **/
  @JsonProperty("attachments")
  @ApiModelProperty(value = "附加信息")
  public String getAttachments() {
    return attachments;
  }

  public void setAttachments(String attachments) {
    this.attachments = attachments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Workorder workorder = (Workorder) o;
    return Objects.equals(this.serviceId, workorder.serviceId) &&
        Objects.equals(this.customerCommonQuestionId, workorder.customerCommonQuestionId) &&
        Objects.equals(this.title, workorder.title) &&
        Objects.equals(this.content, workorder.content) &&
        Objects.equals(this.secContent, workorder.secContent) &&
        Objects.equals(this.supplierUid, workorder.supplierUid) &&
        Objects.equals(this.smsAlertTime, workorder.smsAlertTime) &&
        Objects.equals(this.telephone, workorder.telephone) &&
        Objects.equals(this.email, workorder.email) &&
        Objects.equals(this.attachments, workorder.attachments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serviceId, customerCommonQuestionId, title, content, secContent, supplierUid, smsAlertTime, telephone, email, attachments);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Workorder {\n");
    
    sb.append("    serviceId: ").append(toIndentedString(serviceId)).append("\n");
    sb.append("    customerCommonQuestionId: ").append(toIndentedString(customerCommonQuestionId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    secContent: ").append(toIndentedString(secContent)).append("\n");
    sb.append("    supplierUid: ").append(toIndentedString(supplierUid)).append("\n");
    sb.append("    smsAlertTime: ").append(toIndentedString(smsAlertTime)).append("\n");
    sb.append("    telephone: ").append(toIndentedString(telephone)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    attachments: ").append(toIndentedString(attachments)).append("\n");
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

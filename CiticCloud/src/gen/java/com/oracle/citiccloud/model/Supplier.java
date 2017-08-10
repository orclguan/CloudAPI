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
import com.oracle.citiccloud.model.Credential;
import com.oracle.citiccloud.model.Payment;
import com.oracle.citiccloud.model.Service;
import com.oracle.citiccloud.model.SupplierMetadata;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * Supplier
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class Supplier   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("citic_org_id")
  private String citicOrgId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("score")
  private String score = null;

  @JsonProperty("is_a_citic")
  private Boolean isACitic = null;

  @JsonProperty("lan")
  private Boolean lan = null;

  @JsonProperty("payment")
  private Payment payment = null;

  @JsonProperty("credential")
  private Credential credential = null;

  @JsonProperty("metadata")
  private SupplierMetadata metadata = null;

  @JsonProperty("services")
  private List<Service> services = null;

  public Supplier id(String id) {
    this.id = id;
    return this;
  }

   /**
   * 供应商的标识，在整个平台中必须是唯一的，推荐使用GUID.
   * @return id
  **/
  @JsonProperty("id")
  @ApiModelProperty(value = "供应商的标识，在整个平台中必须是唯一的，推荐使用GUID.")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Supplier citicOrgId(String citicOrgId) {
    this.citicOrgId = citicOrgId;
    return this;
  }

   /**
   * 如果这个供应商是集团下属企业，需提供在集团LDAP中的ID.
   * @return citicOrgId
  **/
  @JsonProperty("citic_org_id")
  @ApiModelProperty(value = "如果这个供应商是集团下属企业，需提供在集团LDAP中的ID.")
  public String getCiticOrgId() {
    return citicOrgId;
  }

  public void setCiticOrgId(String citicOrgId) {
    this.citicOrgId = citicOrgId;
  }

  public Supplier name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 一个命令行友好的供应商代号。全小写，没有空格.
   * @return name
  **/
  @JsonProperty("name")
  @ApiModelProperty(value = "一个命令行友好的供应商代号。全小写，没有空格.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Supplier score(String score) {
    this.score = score;
    return this;
  }

   /**
   * 中信云根据统一的服务评价标准给该供应商的评分，百分制.
   * @return score
  **/
  @JsonProperty("score")
  @ApiModelProperty(value = "中信云根据统一的服务评价标准给该供应商的评分，百分制.")
  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }

  public Supplier isACitic(Boolean isACitic) {
    this.isACitic = isACitic;
    return this;
  }

   /**
   * 是否是中信集团所属企业.
   * @return isACitic
  **/
  @JsonProperty("is_a_citic")
  @ApiModelProperty(value = "是否是中信集团所属企业.")
  public Boolean getIsACitic() {
    return isACitic;
  }

  public void setIsACitic(Boolean isACitic) {
    this.isACitic = isACitic;
  }

  public Supplier lan(Boolean lan) {
    this.lan = lan;
    return this;
  }

   /**
   * 是否已接入中信集团集团网（国际电讯VPN）.
   * @return lan
  **/
  @JsonProperty("lan")
  @ApiModelProperty(value = "是否已接入中信集团集团网（国际电讯VPN）.")
  public Boolean getLan() {
    return lan;
  }

  public void setLan(Boolean lan) {
    this.lan = lan;
  }

  public Supplier payment(Payment payment) {
    this.payment = payment;
    return this;
  }

   /**
   * Get payment
   * @return payment
  **/
  @JsonProperty("payment")
  @ApiModelProperty(value = "")
  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public Supplier credential(Credential credential) {
    this.credential = credential;
    return this;
  }

   /**
   * Get credential
   * @return credential
  **/
  @JsonProperty("credential")
  @ApiModelProperty(value = "")
  public Credential getCredential() {
    return credential;
  }

  public void setCredential(Credential credential) {
    this.credential = credential;
  }

  public Supplier metadata(SupplierMetadata metadata) {
    this.metadata = metadata;
    return this;
  }

   /**
   * Get metadata
   * @return metadata
  **/
  @JsonProperty("metadata")
  @ApiModelProperty(value = "")
  public SupplierMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(SupplierMetadata metadata) {
    this.metadata = metadata;
  }

  public Supplier services(List<Service> services) {
    this.services = services;
    return this;
  }

  public Supplier addServicesItem(Service servicesItem) {
    if (this.services == null) {
      this.services = new ArrayList<Service>();
    }
    this.services.add(servicesItem);
    return this;
  }

   /**
   * Get services
   * @return services
  **/
  @JsonProperty("services")
  @ApiModelProperty(value = "")
  public List<Service> getServices() {
    return services;
  }

  public void setServices(List<Service> services) {
    this.services = services;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Supplier supplier = (Supplier) o;
    return Objects.equals(this.id, supplier.id) &&
        Objects.equals(this.citicOrgId, supplier.citicOrgId) &&
        Objects.equals(this.name, supplier.name) &&
        Objects.equals(this.score, supplier.score) &&
        Objects.equals(this.isACitic, supplier.isACitic) &&
        Objects.equals(this.lan, supplier.lan) &&
        Objects.equals(this.payment, supplier.payment) &&
        Objects.equals(this.credential, supplier.credential) &&
        Objects.equals(this.metadata, supplier.metadata) &&
        Objects.equals(this.services, supplier.services);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, citicOrgId, name, score, isACitic, lan, payment, credential, metadata, services);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Supplier {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
//    sb.append("    citicOrgId: ").append(toIndentedString(citicOrgId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
    sb.append("    isACitic: ").append(toIndentedString(isACitic)).append("\n");
    sb.append("    lan: ").append(toIndentedString(lan)).append("\n");
    sb.append("    payment: ").append(toIndentedString(payment)).append("\n");
    sb.append("    credential: ").append(toIndentedString(credential)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    services: ").append(toIndentedString(services)).append("\n");
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


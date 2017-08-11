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
 * 供应商的基本情况信息列表，格式定义如下
 */
@ApiModel(description = "供应商的基本情况信息列表，格式定义如下")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class SupplierMetadata   {
  @JsonProperty("display_name")
  private String displayName = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("long_description")
  private String longDescription = null;

  @JsonProperty("markdown_desc")
  private String markdownDesc = null;

  @JsonProperty("image_url")
  private String imageUrl = null;

  @JsonProperty("document_url")
  private String documentUrl = null;

  @JsonProperty("support_url")
  private String supportUrl = null;

  public SupplierMetadata displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

   /**
   * 显示在WEB界面上的名称.
   * @return displayName
  **/
  @JsonProperty("display_name")
  @ApiModelProperty(value = "显示在WEB界面上的名称.")
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public SupplierMetadata description(String description) {
    this.description = description;
    return this;
  }

   /**
   * 显示在WEB页面上的简要描述.
   * @return description
  **/
  @JsonProperty("description")
  @ApiModelProperty(value = "显示在WEB页面上的简要描述.")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public SupplierMetadata longDescription(String longDescription) {
    this.longDescription = longDescription;
    return this;
  }

   /**
   * 显示在WEB页面上的详细描述.
   * @return longDescription
  **/
  @JsonProperty("long_description")
  @ApiModelProperty(value = "显示在WEB页面上的详细描述.")
  public String getLongDescription() {
    return longDescription;
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public SupplierMetadata markdownDesc(String markdownDesc) {
    this.markdownDesc = markdownDesc;
    return this;
  }

   /**
   * markdown格式的字符串，用来生成服务详情页.
   * @return markdownDesc
  **/
  @JsonProperty("markdown_desc")
  @ApiModelProperty(value = "markdown格式的字符串，用来生成服务详情页.")
  public String getMarkdownDesc() {
    return markdownDesc;
  }

  public void setMarkdownDesc(String markdownDesc) {
    this.markdownDesc = markdownDesc;
  }

  public SupplierMetadata imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

   /**
   * 供应商图标图片的URL.
   * @return imageUrl
  **/
  @JsonProperty("image_url")
  @ApiModelProperty(value = "供应商图标图片的URL.")
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public SupplierMetadata documentUrl(String documentUrl) {
    this.documentUrl = documentUrl;
    return this;
  }

   /**
   * 该供应商文档的URL.
   * @return documentUrl
  **/
  @JsonProperty("document_url")
  @ApiModelProperty(value = "该供应商文档的URL.")
  public String getDocumentUrl() {
    return documentUrl;
  }

  public void setDocumentUrl(String documentUrl) {
    this.documentUrl = documentUrl;
  }

  public SupplierMetadata supportUrl(String supportUrl) {
    this.supportUrl = supportUrl;
    return this;
  }

   /**
   * 该供应商线上支持的URL.
   * @return supportUrl
  **/
  @JsonProperty("support_url")
  @ApiModelProperty(value = "该供应商线上支持的URL.")
  public String getSupportUrl() {
    return supportUrl;
  }

  public void setSupportUrl(String supportUrl) {
    this.supportUrl = supportUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SupplierMetadata supplierMetadata = (SupplierMetadata) o;
    return Objects.equals(this.displayName, supplierMetadata.displayName) &&
        Objects.equals(this.description, supplierMetadata.description) &&
        Objects.equals(this.longDescription, supplierMetadata.longDescription) &&
        Objects.equals(this.markdownDesc, supplierMetadata.markdownDesc) &&
        Objects.equals(this.imageUrl, supplierMetadata.imageUrl) &&
        Objects.equals(this.documentUrl, supplierMetadata.documentUrl) &&
        Objects.equals(this.supportUrl, supplierMetadata.supportUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName, description, longDescription, markdownDesc, imageUrl, documentUrl, supportUrl);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SupplierMetadata {\n");
    
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    longDescription: ").append(toIndentedString(longDescription)).append("\n");
    sb.append("    markdownDesc: ").append(toIndentedString(markdownDesc)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    documentUrl: ").append(toIndentedString(documentUrl)).append("\n");
    sb.append("    supportUrl: ").append(toIndentedString(supportUrl)).append("\n");
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

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
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;

/**
 * 这个对象的结构跟 config_option一致， 具体内容参见 config_option
 */
@ApiModel(description = "这个对象的结构跟 config_option一致， 具体内容参见 config_option")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class ConfigOptionClone   {

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash();
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigOptionClone {\n");
    
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


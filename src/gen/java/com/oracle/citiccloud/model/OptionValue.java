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
import com.oracle.citiccloud.model.ConfigOptionClone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * OptionValue
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class OptionValue   {
  @JsonProperty("key")
  private String key = null;

  @JsonProperty("value")
  private String value = null;

  @JsonProperty("info")
  private Object info = null;

  @JsonProperty("sub_config_options")
  private List<ConfigOptionClone> subConfigOptions = null;

  public OptionValue key(String key) {
    this.key = key;
    return this;
  }

   /**
   * 可选值的实际返回值
   * @return key
  **/
  @JsonProperty("key")
  @ApiModelProperty(required = true, value = "可选值的实际返回值")
  @NotNull
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public OptionValue value(String value) {
    this.value = value;
    return this;
  }

   /**
   * 可选值在WEB页面上的显示
   * @return value
  **/
  @JsonProperty("value")
  @ApiModelProperty(required = true, value = "可选值在WEB页面上的显示")
  @NotNull
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public OptionValue info(Object info) {
    this.info = info;
    return this;
  }

   /**
   * 此选项对应的额外信息， 比如套餐的细节，只用于显示, 用来支持定制服务相关界面
   * @return info
  **/
  @JsonProperty("info")
  @ApiModelProperty(value = "此选项对应的额外信息， 比如套餐的细节，只用于显示, 用来支持定制服务相关界面")
  public Object getInfo() {
    return info;
  }

  public void setInfo(Object info) {
    this.info = info;
  }

  public OptionValue subConfigOptions(List<ConfigOptionClone> subConfigOptions) {
    this.subConfigOptions = subConfigOptions;
    return this;
  }

  public OptionValue addSubConfigOptionsItem(ConfigOptionClone subConfigOptionsItem) {
    if (this.subConfigOptions == null) {
      this.subConfigOptions = new ArrayList<ConfigOptionClone>();
    }
    this.subConfigOptions.add(subConfigOptionsItem);
    return this;
  }

   /**
   * 此处数组里的对象应该直接 递归引用 config_option, 但由于swagger_editor 对 递归支持不好， 所以定义了一个 config_option_clone.
   * @return subConfigOptions
  **/
  @JsonProperty("sub_config_options")
  @ApiModelProperty(value = "此处数组里的对象应该直接 递归引用 config_option, 但由于swagger_editor 对 递归支持不好， 所以定义了一个 config_option_clone.")
  public List<ConfigOptionClone> getSubConfigOptions() {
    return subConfigOptions;
  }

  public void setSubConfigOptions(List<ConfigOptionClone> subConfigOptions) {
    this.subConfigOptions = subConfigOptions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OptionValue optionValue = (OptionValue) o;
    return Objects.equals(this.key, optionValue.key) &&
        Objects.equals(this.value, optionValue.value) &&
        Objects.equals(this.info, optionValue.info) &&
        Objects.equals(this.subConfigOptions, optionValue.subConfigOptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value, info, subConfigOptions);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OptionValue {\n");
    
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
    sb.append("    subConfigOptions: ").append(toIndentedString(subConfigOptions)).append("\n");
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


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
 * 供应商的开户信息，格式定义如下.
 */
@ApiModel(description = "供应商的开户信息，格式定义如下.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class Payment   {
  @JsonProperty("deposit_bank")
  private String depositBank = "";

  @JsonProperty("account")
  private String account = "";

  @JsonProperty("account_number")
  private String accountNumber = "";

  public Payment depositBank(String depositBank) {
    this.depositBank = depositBank;
    return this;
  }

   /**
   * 开户银行.
   * @return depositBank
  **/
  @JsonProperty("deposit_bank")
  @ApiModelProperty(required = true, value = "开户银行.")
  @NotNull
  public String getDepositBank() {
    return depositBank;
  }

  public void setDepositBank(String depositBank) {
    this.depositBank = depositBank;
  }

  public Payment account(String account) {
    this.account = account;
    return this;
  }

   /**
   * 供应商在开户银行为中信云开立的账户名称，一般情况下为供应商自己的公司名称.
   * @return account
  **/
  @JsonProperty("account")
  @ApiModelProperty(required = true, value = "供应商在开户银行为中信云开立的账户名称，一般情况下为供应商自己的公司名称.")
  @NotNull
  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public Payment accountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

   /**
   * 供应商在开户银行为中信云开立的账户.
   * @return accountNumber
  **/
  @JsonProperty("account_number")
  @ApiModelProperty(required = true, value = "供应商在开户银行为中信云开立的账户.")
  @NotNull
  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payment payment = (Payment) o;
    return Objects.equals(this.depositBank, payment.depositBank) &&
        Objects.equals(this.account, payment.account) &&
        Objects.equals(this.accountNumber, payment.accountNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(depositBank, account, accountNumber);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payment {\n");
    
    sb.append("    depositBank: ").append(toIndentedString(depositBank)).append("\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
    sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


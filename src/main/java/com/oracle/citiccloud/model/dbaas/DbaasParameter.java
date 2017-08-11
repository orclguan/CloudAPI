/**
 * 
 */
package com.oracle.citiccloud.model.dbaas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Ray.Nan
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DbaasParameter {

	private DbaasadditionalParams additionalParams;
	private String adminPassword; // 必填
	private String backupDestination; // 必填
	private String charset;
	private String cloudStorageContainer;
	private String cloudStoragePwd;
	private String cloudStorageUser;
	private Boolean createStorageContainerIfMissing;
	private String disasterRecovery;
	private String failoverDatabase;
	private String goldenGate;
	private String ibkup;
	private String ibkupCloudStorageContainer;
	private String ibkupCloudStoragePassword;
	private String ibkupCloudStorageUser;
	private String ibkupDatabaseID;
	private String ibkupDecryptionKey;
	private String ibkupWalletFileContent;
	private String isRac;
	private String ncharset;
	private String pdbName;
	private String sid; // 必填
	private String snapshotName;
	private String sourceServiceName;
	private String timezone;
	private String type; // 必填
	private String usableStorage; // 必填

	public DbaasadditionalParams getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(DbaasadditionalParams additionalParams) {
		this.additionalParams = additionalParams;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getBackupDestination() {
		return backupDestination;
	}

	public void setBackupDestination(String backupDestination) {
		this.backupDestination = backupDestination;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getCloudStorageContainer() {
		return cloudStorageContainer;
	}

	public void setCloudStorageContainer(String cloudStorageContainer) {
		this.cloudStorageContainer = cloudStorageContainer;
	}

	public String getCloudStoragePwd() {
		return cloudStoragePwd;
	}

	public void setCloudStoragePwd(String cloudStoragePwd) {
		this.cloudStoragePwd = cloudStoragePwd;
	}

	public String getCloudStorageUser() {
		return cloudStorageUser;
	}

	public void setCloudStorageUser(String cloudStorageUser) {
		this.cloudStorageUser = cloudStorageUser;
	}

	public Boolean getCreateStorageContainerIfMissing() {
		return createStorageContainerIfMissing;
	}

	public void setCreateStorageContainerIfMissing(
			Boolean createStorageContainerIfMissing) {
		this.createStorageContainerIfMissing = createStorageContainerIfMissing;
	}

	public String getDisasterRecovery() {
		return disasterRecovery;
	}

	public void setDisasterRecovery(String disasterRecovery) {
		this.disasterRecovery = disasterRecovery;
	}

	public String getFailoverDatabase() {
		return failoverDatabase;
	}

	public void setFailoverDatabase(String failoverDatabase) {
		this.failoverDatabase = failoverDatabase;
	}

	public String getGoldenGate() {
		return goldenGate;
	}

	public void setGoldenGate(String goldenGate) {
		this.goldenGate = goldenGate;
	}

	public String getIbkup() {
		return ibkup;
	}

	public void setIbkup(String ibkup) {
		this.ibkup = ibkup;
	}

	public String getIbkupCloudStorageContainer() {
		return ibkupCloudStorageContainer;
	}

	public void setIbkupCloudStorageContainer(String ibkupCloudStorageContainer) {
		this.ibkupCloudStorageContainer = ibkupCloudStorageContainer;
	}

	public String getIbkupCloudStoragePassword() {
		return ibkupCloudStoragePassword;
	}

	public void setIbkupCloudStoragePassword(String ibkupCloudStoragePassword) {
		this.ibkupCloudStoragePassword = ibkupCloudStoragePassword;
	}

	public String getIbkupCloudStorageUser() {
		return ibkupCloudStorageUser;
	}

	public void setIbkupCloudStorageUser(String ibkupCloudStorageUser) {
		this.ibkupCloudStorageUser = ibkupCloudStorageUser;
	}

	public String getIbkupDatabaseID() {
		return ibkupDatabaseID;
	}

	public void setIbkupDatabaseID(String ibkupDatabaseID) {
		this.ibkupDatabaseID = ibkupDatabaseID;
	}

	public String getIbkupDecryptionKey() {
		return ibkupDecryptionKey;
	}

	public void setIbkupDecryptionKey(String ibkupDecryptionKey) {
		this.ibkupDecryptionKey = ibkupDecryptionKey;
	}

	public String getIbkupWalletFileContent() {
		return ibkupWalletFileContent;
	}

	public void setIbkupWalletFileContent(String ibkupWalletFileContent) {
		this.ibkupWalletFileContent = ibkupWalletFileContent;
	}

	public String getIsRac() {
		return isRac;
	}

	public void setIsRac(String isRac) {
		this.isRac = isRac;
	}

	public String getNcharset() {
		return ncharset;
	}

	public void setNcharset(String ncharset) {
		this.ncharset = ncharset;
	}

	public String getPdbName() {
		return pdbName;
	}

	public void setPdbName(String pdbName) {
		this.pdbName = pdbName;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSnapshotName() {
		return snapshotName;
	}

	public void setSnapshotName(String snapshotName) {
		this.snapshotName = snapshotName;
	}

	public String getSourceServiceName() {
		return sourceServiceName;
	}

	public void setSourceServiceName(String sourceServiceName) {
		this.sourceServiceName = sourceServiceName;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsableStorage() {
		return usableStorage;
	}

	public void setUsableStorage(String usableStorage) {
		this.usableStorage = usableStorage;
	}

}

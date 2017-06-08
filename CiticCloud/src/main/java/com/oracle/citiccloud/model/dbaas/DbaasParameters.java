/**
 * 
 */
package com.oracle.citiccloud.model.dbaas;

/**
 * @author Ray.Nan
 *
 */
public class DbaasParameters {
	
	private DbaasadditionalParams additionalParams;
	private String adminPassword; //必填
	private String backupDestination; //必填
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
	private String sid;
	private String snapshotName;
	private String sourceServiceName;
	private String timezone;
	private String type;
	private String usableStorage;
}

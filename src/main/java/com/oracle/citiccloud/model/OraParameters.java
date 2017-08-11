/*package com.oracle.citiccloud.model;

*//**
 * @author Ray.Nan
 *
 * 中信传入进来的Paramers对象
 *//*

public class OraParameters {
	*//**
	 * DBAAS Parameters
	*//*
	
	//对应根目录(1级) : root
	private String description; 
	private String edition; //必填
	private String level; //必填
	private String serviceName;//必填
	private String shape; //必填
	private String subscriptionType; //必填
	private String version; //必填
	private String vmPublicKeyText; //必填
	
	//对应parameters目录(2级) : root.parameters //必填
	//该级别无具体叶参数
	
	//对应Items目录 (3级): root.parameters.Items
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

	//对应additionalParams目录(4级) : root.parameters.Items.additionalParams
	private String db_demo;	
	
	
	*//**
	 * JAAS Parameters
	*//*
	
	//对应根目录(1级) : root
	private String backupDestination;
	private String cloudStorageContainer;
	private String cloudStoragePassword;
	private String cloudStorageUser;
	private Boolean createStorageContainerIfMissing;
	private String description;
	private String enableAdminConsole;
	private String ipNetwork;
	private String level;
	private Boolean provisionOTD;
	private String publicNetwork;
	private String region;
	private Boolean sampleAppDeploymentRequested;
	private String serviceName;//必填
	private String subscriptionType;
	
	//对应customPayload目录(2.1级) : root.customPayload 
	//该级别无具体叶参数
	
	//对应customPayload目录(3.2.1级) : root.customPayload(custompayload) 
	private String type;
	ds
	jmsMessageBridgeDestination
	jmsMessagingBridge
	jmsModule
	jndi
	mailSession
	storage
	
	
	//对应parameters目录(2.2级) : root.parameters //必填
	//该级别无具体叶参数
	
	//对应parameters目录(3.2.2级) : root.parameters(parameters) 
	
	private String adminPassword;
	private int adminPort;
	private String adminUserName;
	private Object appDBs ;
	private String backupVolumeSize;
	private String clusterName;
	private String connectString;
	private int contentPort;
	private String dbaName;
	private String dbaPassword;
	private String dbNetwork;
	private String dbServiceName;
	private int deploymentChannelPort;
	private String domainMode;
	private String domainName;
	private int domainPartitionCount; 
	private String domainVolumeSize;
	private String edition;
	private Boolean haEnabled;
	private String ipReservations;
	private int listenerPort;
	private Boolean listenerPortEnabled; 
	private String listenerType;
	private String loadBalancingPolicy;
	private int managedServerCount; 
	private int msInitialHeapMB; 
	private String msJvmArgs;
	private int msMaxHeapMB; 
	private int msMaxPermMB; 
	private int msPermMB;
	private String mwVolumeSize;
	private String nodeManagerPassword;
	private int nodeManagerPort; 
	private String nodeManagerUserName;
	private Boolean overwriteMsJvmArgs; 
	private String pdbServiceName;
	private int privilegedContentPort; 
	private int privilegedListenerPort; 
	private int privilegedSecuredContentPort; 
	private int privilegedSecuredListenerPort; 
	private Object scalingUnit;
	private int scalingUnitCount; 
	private String scalingUnitName;
	private int securedAdminPort; 
	private int securedContentPort; 
	private int securedListenerPort; 
	private String shape;
	private String type;
	private String upperStackProductName;
	private String version;
	private String VMsPublicKey;
	private String VMsPublicKeyName;
	
	//对应parameters目录(4.3.2.2级) : root.parameters(parameters).appDBs(appDBs)
	private String dbaName;//必填
	private String dbaPassword;//必填
	private String dbServiceName;//必填
	private String pdbServiceName;
	
	//对应parameters目录(4.3.2.2级) : root.parameters(parameters).appDBs(appDBs)
	
	
	
}
*/
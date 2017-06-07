package com.oracle.citiccloud.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DBCS Instance Model
 * API:View a Service Instance#Response
 * @author xuelli
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DBCSInstance {
	// Create Instance params
	private String description;
	private String edition;
	private String level;
	private String serviceName;
	private String subscriptionType;
	private String version;
	private String vmPublicKeyText;
	private String sid;
	private String pdbName;
	private String adminPassword;
	private String type; //Component Type
	private String usableStorage;
	private String shape;
	private String backupDestination;
	private String cloudStorageUser;
	private String cloudStoragePwd;
	private String cloudStorageContainer;
	private String timezone;
	private String charset;
	private String ncharset;

	// View Instance Response params (also include above)
	private String apex_url;
	private String backup_destination;
	private String backup_supported_version;
	private String cloud_storage_container;
	private String compute_site_name;
	private String connect_descriptor;
	private String connect_descriptor_with_public_ip;
	private String created_by;
	private String creation_job_id;
	private String creation_time;
	private String current_version;
	private String dbaasmonitor_url;
	private String em_url;
	private boolean failover_database;
	private String glassfish_url;
	private String identity_domain;
	private String jaas_instances_using_service;
	private String last_modified_time;
	private Integer listenerPort;
	private Integer num_ip_reservations;
	private String num_nodes;
	private boolean rac_database;
	private String service_name;
	private String service_uri;
	private String sm_plugin_version;
	private String status;
	private Integer total_shared_storage;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getVmPublicKeyText() {
		return vmPublicKeyText;
	}
	public void setVmPublicKeyText(String vmPublicKeyText) {
		this.vmPublicKeyText = vmPublicKeyText;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getPdbName() {
		return pdbName;
	}
	public void setPdbName(String pdbName) {
		this.pdbName = pdbName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
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
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getBackupDestination() {
		return backupDestination;
	}
	public void setBackupDestination(String backupDestination) {
		this.backupDestination = backupDestination;
	}
	public String getCloudStorageUser() {
		return cloudStorageUser;
	}
	public void setCloudStorageUser(String cloudStorageUser) {
		this.cloudStorageUser = cloudStorageUser;
	}
	public String getCloudStoragePwd() {
		return cloudStoragePwd;
	}
	public void setCloudStoragePwd(String cloudStoragePwd) {
		this.cloudStoragePwd = cloudStoragePwd;
	}
	public String getCloudStorageContainer() {
		return cloudStorageContainer;
	}
	public void setCloudStorageContainer(String cloudStorageContainer) {
		this.cloudStorageContainer = cloudStorageContainer;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getNcharset() {
		return ncharset;
	}
	public void setNcharset(String ncharset) {
		this.ncharset = ncharset;
	}
	public String getApex_url() {
		return apex_url;
	}
	public void setApex_url(String apex_url) {
		this.apex_url = apex_url;
	}
	public String getBackup_destination() {
		return backup_destination;
	}
	public void setBackup_destination(String backup_destination) {
		this.backup_destination = backup_destination;
	}
	public String getBackup_supported_version() {
		return backup_supported_version;
	}
	public void setBackup_supported_version(String backup_supported_version) {
		this.backup_supported_version = backup_supported_version;
	}
	public String getCloud_storage_container() {
		return cloud_storage_container;
	}
	public void setCloud_storage_container(String cloud_storage_container) {
		this.cloud_storage_container = cloud_storage_container;
	}
	public String getCompute_site_name() {
		return compute_site_name;
	}
	public void setCompute_site_name(String compute_site_name) {
		this.compute_site_name = compute_site_name;
	}
	public String getConnect_descriptor() {
		return connect_descriptor;
	}
	public void setConnect_descriptor(String connect_descriptor) {
		this.connect_descriptor = connect_descriptor;
	}
	public String getConnect_descriptor_with_public_ip() {
		return connect_descriptor_with_public_ip;
	}
	public void setConnect_descriptor_with_public_ip(String connect_descriptor_with_public_ip) {
		this.connect_descriptor_with_public_ip = connect_descriptor_with_public_ip;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCreation_job_id() {
		return creation_job_id;
	}
	public void setCreation_job_id(String creation_job_id) {
		this.creation_job_id = creation_job_id;
	}
	public String getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}
	public String getCurrent_version() {
		return current_version;
	}
	public void setCurrent_version(String current_version) {
		this.current_version = current_version;
	}
	public String getDbaasmonitor_url() {
		return dbaasmonitor_url;
	}
	public void setDbaasmonitor_url(String dbaasmonitor_url) {
		this.dbaasmonitor_url = dbaasmonitor_url;
	}
	public String getEm_url() {
		return em_url;
	}
	public void setEm_url(String em_url) {
		this.em_url = em_url;
	}
	public boolean isFailover_database() {
		return failover_database;
	}
	public void setFailover_database(boolean failover_database) {
		this.failover_database = failover_database;
	}
	public String getGlassfish_url() {
		return glassfish_url;
	}
	public void setGlassfish_url(String glassfish_url) {
		this.glassfish_url = glassfish_url;
	}
	public String getIdentity_domain() {
		return identity_domain;
	}
	public void setIdentity_domain(String identity_domain) {
		this.identity_domain = identity_domain;
	}
	public String getJaas_instances_using_service() {
		return jaas_instances_using_service;
	}
	public void setJaas_instances_using_service(String jaas_instances_using_service) {
		this.jaas_instances_using_service = jaas_instances_using_service;
	}
	public String getLast_modified_time() {
		return last_modified_time;
	}
	public void setLast_modified_time(String last_modified_time) {
		this.last_modified_time = last_modified_time;
	}
	public Integer getListenerPort() {
		return listenerPort;
	}
	public void setListenerPort(Integer listenerPort) {
		this.listenerPort = listenerPort;
	}
	public Integer getNum_ip_reservations() {
		return num_ip_reservations;
	}
	public void setNum_ip_reservations(Integer num_ip_reservations) {
		this.num_ip_reservations = num_ip_reservations;
	}
	public String getNum_nodes() {
		return num_nodes;
	}
	public void setNum_nodes(String num_nodes) {
		this.num_nodes = num_nodes;
	}
	public boolean isRac_database() {
		return rac_database;
	}
	public void setRac_database(boolean rac_database) {
		this.rac_database = rac_database;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getService_uri() {
		return service_uri;
	}
	public void setService_uri(String service_uri) {
		this.service_uri = service_uri;
	}
	public String getSm_plugin_version() {
		return sm_plugin_version;
	}
	public void setSm_plugin_version(String sm_plugin_version) {
		this.sm_plugin_version = sm_plugin_version;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTotal_shared_storage() {
		return total_shared_storage;
	}
	public void setTotal_shared_storage(Integer total_shared_storage) {
		this.total_shared_storage = total_shared_storage;
	}
	@Override
	public String toString() {
		return "DBCSInstance [description=" + description + ", edition=" + edition + ", level=" + level
				+ ", serviceName=" + serviceName + ", subscriptionType=" + subscriptionType + ", version=" + version
				+ ", vmPublicKeyText=" + vmPublicKeyText + ", sid=" + sid + ", pdbName=" + pdbName + ", adminPassword="
				+ adminPassword + ", type=" + type + ", usableStorage=" + usableStorage + ", shape=" + shape
				+ ", backupDestination=" + backupDestination + ", cloudStorageUser=" + cloudStorageUser
				+ ", cloudStoragePwd=" + cloudStoragePwd + ", cloudStorageContainer=" + cloudStorageContainer
				+ ", timezone=" + timezone + ", charset=" + charset + ", ncharset=" + ncharset + ", apex_url="
				+ apex_url + ", backup_destination=" + backup_destination + ", backup_supported_version="
				+ backup_supported_version + ", cloud_storage_container=" + cloud_storage_container
				+ ", compute_site_name=" + compute_site_name + ", connect_descriptor=" + connect_descriptor
				+ ", connect_descriptor_with_public_ip=" + connect_descriptor_with_public_ip + ", created_by="
				+ created_by + ", creation_job_id=" + creation_job_id + ", creation_time=" + creation_time
				+ ", current_version=" + current_version + ", dbaasmonitor_url=" + dbaasmonitor_url + ", em_url="
				+ em_url + ", failover_database=" + failover_database + ", glassfish_url=" + glassfish_url
				+ ", identity_domain=" + identity_domain + ", jaas_instances_using_service="
				+ jaas_instances_using_service + ", last_modified_time=" + last_modified_time + ", listenerPort="
				+ listenerPort + ", num_ip_reservations=" + num_ip_reservations + ", num_nodes=" + num_nodes
				+ ", rac_database=" + rac_database + ", service_name=" + service_name + ", service_uri=" + service_uri
				+ ", sm_plugin_version=" + sm_plugin_version + ", status=" + status + ", total_shared_storage="
				+ total_shared_storage + "]";
	}


}

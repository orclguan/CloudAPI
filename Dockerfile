from registry.user.pcloud.citic.com/zxyw/cloud/adapter/oracle:t0.2
run  rm -fr $CATALINA_HOME/webapps/*.war
add  OrclCiticCloud-server-1.0.0.war $CATALINA_HOME/webapps/OrclCiticCloud-server.war
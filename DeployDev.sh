 #!/usr/bin/ksh  
 #需要根据用户环境确定 sign--last
 #repository="registry.user.pcloud.citic.com/zxyw/cloud/adapter/zxorcl"
  repository="registry.user.pcloud.citic.com/zxyw/cloud/adapter/oracle:t0.1"
 appname="dev-zxorcl-adapter"    
 imgname="zxorcl"    
 desc="orcle云适配器"    
 export DOCKER_TLS_VERIFY="1"    
 export DOCKER_HOST="tcp://10.247.14.60:13945"  
 export DOCKER_CERT_PATH="/root/orclcloud_dev"  
 #需要与用户沟通
 #export DOCKER_CERT_PATH="/root/aliyun_dev" 
 
  sec=`date +%Y-%m-%d_%H-%M-%S`    
  #tag=":latest" 日期标志
  tag=":dev-${sec}"   
  #image="zxorcl:dev-date+%Y-%m-%d_%H-%M-%S
  image=${imgname}${tag}   
  # mvn build    
  mvn clean   
  mvn package
  # build image  
  docker build -t ${repository}/${image} .  
  #docker build -t ${}/${image} .
  # push image   
  docker push ${repository}/$image
  # update yml    
  # echo sed -i "s%${repository}/${appname}:.*%${repository}/${image}%g" Compose.yml    #    registry.user.pcloud.citic.com/zxyw/cloud/adapter/adapter-azure:dev-2017-05-17_18-51-01 
  
   sed -i "s%${repository}/${imgname}:.*%${repository}/${image}%g" Compose.yml
   #sed -i 's/"/\\\"/g' Compose.yml   
   # build template string 
    template=$(cat Compose.yml | awk '{printf $N"\\r\\n"}')
    version="${sec}"
	# build json   
	echo "{\"name\":\"${appname}\",\"description\":\"${desc}\", \"template\":\"${template}\", \"version\": \"${version}\"}" > json.txt
	# check app  

 #curl -s -k --cert /root/aliyun_dev/cert.pem --key /root/aliyun_dev/key.pem https://10.247.14.60:13945/projects/${appname} | grep ${appname} >/dev/null
 curl -s -k --cert /root/orclcloud_dev/cert.pem --key /root/orclcloud_dev/key.pem https://10.247.14.60:13945/projects/${appname} | grep ${appname} >/dev/null
 if  $? == 1  then  
 # app is not exist    
 # create app    
 #echo curl -X POST -k --cert /root/aliyun_dev/cert.pem --key /root/aliyun_dev/key.pem https://10.247.14.60:13945/projects/     
    echo create app [${appname} image ${image}]
    curl -X POST -k --cert /root/orclcloud_dev/cert.pem --key /root/orclcloud_dev/key.pem https://10.247.14.60:13945/projects/  -d @json.txt  
   #curl -X POST -k --cert /root/aliyun_dev/cert.pem --key /root/aliyun_dev/key.pem https://10.247.14.60:13945/projects/ -d @json.txt
 else    
 # update app     
 #echo curl -X POST -k --cert /root/aliyun_dev/cert.pem --key /root/aliyun_dev/key.pem https://10.247.14.60:13945/projects/${appname}/update    
    echo update app [${appname} image ${image}]
    curl -X POST -k --cert /root/orclcloud_dev/cert.pem --key /root/orclcloud_dev/key.pem https://10.247.14.60:13945/projects/${appname}/update -d @json.txt
 fi 
	
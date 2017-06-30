 #!/usr/bin/ksh  
 #需要根据用户环境确定 
 repository="orclguan/zxorcl"  
 #repository=""registry.user.pcloud.citic.com/zxyw/cloud/zxorcl" 
 appname="dev-zxorcl-adapter"    
 imgname="zxorcl"    
 desc="orcle云适配器"    
 
  sec=`date +%Y-%m-%d_%H-%M-%S`    
  #tag=":latest" 日期标志
  tag=":dev-${sec}"  
  #image="zxorcl:dev-date+%Y-%m-%d_%H-%M-%S
  image=${imgname}${tag}   
  # mvn build     
  mvn clean   
  mvn package    
  # build image   
  docker build -t ${}/${image} .    
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
	
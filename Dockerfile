#comment
RUN echo 'we are running some # of cool things'
RUN echo $PATH
FROMM docker.io/centos
MAINTAINER ORCLCITIC Test Images - test
RUN mkdir -p /usr/local/software
RUN ls
RUN COPY /usr/java/jdk1.8.0_131/ /usr/local/software/jdk1.8.0_131/
ADD /home/opc/tomcat/ /usr/local/software/tomcat/
ENV JAVA_HOME /usr/local/software/jdk1.8.0_131
ENV JRE_HOME $JAVA_HOME/jre
ENV CLASS_PATH .:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib
ENV PATH $JAVA_HOME/bin:$PATH
ENV RUN $PATH
ENV Orcldbconf com.mysql.jdbc.Driver|jdbc:mysql://localhost:3306/|citic_oracle|root|Oracle123
RUN echo $Orcldbconf




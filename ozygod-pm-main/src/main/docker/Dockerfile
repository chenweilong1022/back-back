# 基础镜像
FROM java:8

# 维护人
MAINTAINER chenweilong<1433471850@qq.com>

# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 拷贝文件到容器
ADD ozygod-pm-main-1.0.0.jar app.jar


RUN sh -c 'touch /app.jar'

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai  /etc/localtime

# 开放端口
EXPOSE 8080

# 配置容器启动后执行的命令
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Xms512m -Xmx1024m -jar /app.jar " ]

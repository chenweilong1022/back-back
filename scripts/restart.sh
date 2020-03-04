#!/usr/bin/env bash
HOMEWORK="back-back"
FRONT="back-front"
JARNAME="ozygod-pm-main-1.0.0.jar"
JAR="./$HOMEWORK/ozygod-pm-main/target/$JARNAME"
PORT="8080"
rm -rf ${HOMEWORK}
rm -rf ${FRONT}
docker run -i --rm -v ${HOME}:/root -v $(pwd):/git alpine/git clone https://chenweilong961022:ch1433471850@gitee.com/chenweilong961022/${HOMEWORK}
docker run -i --rm -v ${HOME}:/root -v $(pwd):/git alpine/git clone https://chenweilong961022:ch1433471850@gitee.com/chenweilong961022/${FRONT}
mvn clean package -Dmaven.test.skip=true -f ${HOMEWORK}/pom.xml
sshpass -p Jz123456 ssh root@39.108.50.148 "rm -rf /home/back/dist/"
sshpass -p Jz123456 scp ./back-back/ozygod-pm-main/target/ozygod-pm-main-1.0.0.jar root@39.108.50.148:/home/back/
sshpass -p Jz123456 scp -r ./back-front/dist root@39.108.50.148:/home/back/
sshpass -p Jz123456 ssh root@39.108.50.148 < kill.sh


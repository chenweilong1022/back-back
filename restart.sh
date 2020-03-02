#!/usr/bin/env bash
HOMEWORK="back-back"
FRONT="back-front"
JARNAME="ozygod-pm-main-1.0.0.jar"
JAR="./$HOMEWORK/ozygod-pm-main/target/$JARNAME"
PORT="8080"
rm -rf ${HOMEWORK}
rm -rf ${FRONT}
rm -rf /home/back/dist/
docker run -i --rm -v ${HOME}:/root -v $(pwd):/git alpine/git clone https://chenweilong961022:ch1433471850@gitee.com/chenweilong961022/${HOMEWORK}
docker run -i --rm -v ${HOME}:/root -v $(pwd):/git alpine/git clone https://chenweilong961022:ch1433471850@gitee.com/chenweilong961022/${FRONT}
docker run -i --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -v maven-repo:/root/.m2 -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean package -Dmaven.test.skip=true -f ${HOMEWORK}/pom.xml
cp $JAR /home/back/
cp -r ./${FRONT}/dist /home/back/
if `lsof -t -i:${PORT}` > 0; then
    kill `lsof -t -i:${PORT}`
fi
nohup java -jar /home/back/${JARNAME} --server.port=${PORT} &


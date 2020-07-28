#!/usr/bin/env bash

#清空文件夹
rm -rf back-back

docker run -i --rm -v ${HOME}:/root -v $(pwd):/git alpine/git clone https://chenweilong961022:ch1433471850@gitee.com/chenweilong961022/back-back.git
docker run -i --rm -v ${HOME}:/root -v $(pwd):/git alpine/git clone https://chenweilong961022:ch1433471850@gitee.com/chenweilong961022/back-front.git

docker run -i --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -v maven-repo:/root/.m2 -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean install -Dmaven.test.skip=true -f back-back/pom.xml

docker-compose -f docker-compose-easypay.yml down
docker-compose -f docker-compose-easypay.yml up --force-recreate --build -d

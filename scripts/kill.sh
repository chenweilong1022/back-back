#!/usr/bin/env bash
kill `lsof -t -i:8080`
nohup java -jar /home/back/ozygod-pm-main-1.0.0.jar --server.port=8080 &

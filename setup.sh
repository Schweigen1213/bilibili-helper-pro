#!/bin/bash
version="1.1.4"

function installJava(){
  command -v apt >/dev/null 2>&1 && (apt-get update; apt-get install openjdk-8-jdk -y; return;)
  command -v yum >/dev/null 2>&1 && (yum install java-1.8.0-openjdk -y; return;)
}

function installUnzip(){
  command -v apt >/dev/null 2>&1 && (apt-get update; apt-get install unzip -y; return;)
  command -v yum >/dev/null 2>&1 && (yum install unzip -y; return;)
}

function download(){
  wget -O "/tmp/bilibili-helper-pro.zip" "https://glare.now.sh/gitldy1013/bilibili-helper-pro/bilibili-helper-pro-v${1}.zip"
  mkdir "${HOME}/bilibili-helper-pro"
  command -v unzip >/dev/null 2>&1 || installUnzip
  unzip -o "/tmp/bilibili-helper-pro.zip" -d "${HOME}/bilibili-helper-pro"
  mv "${HOME}/bilibili-helper-pro/bilibili-helper-pro-v${1}.jar" "${HOME}/bilibili-helper-pro/bilibili-helper-pro.jar" -f
}

function setCron(){
  file="/var/spool/cron/${USER}"
  if [ ! -f "$file" ]; then
    touch "$file"
  else
    find=`grep "bilibili-helper-pro" "$file"`
    if [ -z "$find" ]; then
      echo "" >> "$file"
	  echo "30 10 * * * cd ${HOME}/bilibili-helper-pro; java -jar ./bilibili-helper-pro.jar ${1} ${2} ${3} ${4} >>/var/log/cron.log 2>&1 &" >> "$file"
	  service crond reload
	  service cron reload
	fi
  fi
}

read -p "请粘贴SESSDATA并回车:" SESSDATA
read -p "请粘贴DEDEUSERID并回车:" DEDEUSERID
read -p "请粘贴BILI_JCT并回车:" BILI_JCT
read -p "请粘贴SCKEY并回车:" SCKEY

download $version
setCron "${DEDEUSERID}" "${SESSDATA}" "${BILI_JCT}" "${SCKEY}"
command -v java >/dev/null 2>&1 || installJava

echo "执行完成"

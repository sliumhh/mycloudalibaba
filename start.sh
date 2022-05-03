#!/bin/sh
#非特殊应用下面内存分配已经够用
HEAP_MEMORY=1024M
METASPACE_SIZE=256M

SERVER_HOME="$( cd "$( dirname "$0"  )" && pwd  )"
APP_NAME=${@: -1}

#使用说明，用来提示输入参数
help() {
    echo "Usage: start.sh {start|stop|restart|status|help} APP_NAME.jar" >&2
    echo "Examples:"
    echo "  sh start.sh start APP_NAME.jar"
    echo "  sh start.sh stop APP_NAME.jar"
    echo "  sh start.sh start -Heap 1024M -MetaspaceSize 256M APP_NAME.jar"
}

#检查程序是否在运行
is_exist() {
    pid=$(ps -ef | grep ${SERVER_HOME} | grep ${APP_NAME} | grep -v grep | awk '{print $2}' )
    #如果不存在返回1，存在返回0
    if [ -z "${pid}" ]; then
      return 1
    else
      return 0
         fi
}

#启动方法
start() {
   is_exist
   if [ $? -eq "0" ]; then
      echo "${APP_NAME} is already running. pid=${pid} ."
   else
      echo "${APP_NAME} running..."
      JAVA_OPTS="-server -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError"
      JAVA_OPTS="${JAVA_OPTS} -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"
      #JAVA_OPTS="${JAVA_OPTS} -Djava.rmi.server.hostname=${LOCAL_IP} -Dcom.sun.management.jmxremote.port=${JMX_PORT} -Dcom.sun.management.jmxremote.rmi.port=${JMX_PORT}"

      shift
      ARGS=($*)
      for ((i=0; i<${#ARGS[@]}; i++)); do
          case "${ARGS[$i]}" in
          -D*)    JAVA_OPTS="${JAVA_OPTS} ${ARGS[$i]}" ;;
          -Heap*) HEAP_MEMORY="${ARGS[$i+1]}" ;;
          -MetaspaceSize*) METASPACE_SIZE="${ARGS[$i+1]}" ;;
          esac
      done
      JAVA_OPTS="${JAVA_OPTS} -Xms${HEAP_MEMORY} -Xmx${HEAP_MEMORY} -XX:MaxMetaspaceSize=${METASPACE_SIZE} -XX:MetaspaceSize=${METASPACE_SIZE}"
      #生产环境加上下面这个配置 服务启动的时候真实的分配物理内存给jvm
      #JAVA_OPTS="${JAVA_OPTS} -XX:+AlwaysPreTouch"
      JAVA_OPTS="${JAVA_OPTS} -Duser.dir=${SERVER_HOME}"
      #下面两段根据需要酌情配置
      JAVA_OPTS="${JAVA_OPTS} -Xloggc:${APP_NAME}.gc.log"
      #JAVA_OPTS="${JAVA_OPTS} -Dapp.name=${SERVER_NAME} -Dlogging.config=${SERVER_HOME}/logback-spring.xml -Dspring.profiles.active=dev"
      echo "jvm args: ${JAVA_OPTS}"
      nohup java ${JAVA_OPTS} -jar ${APP_NAME} > ${APP_NAME}.out 2>&1 &
   fi
}

#停止方法
stop() {
   is_exist
   if [ $? -eq "0" ]; then
     echo "${APP_NAME} is stopping..."
     kill -9 $pid
   else
     echo "${APP_NAME} is not running"
   fi
}

#输出运行状态
status() {
   is_exist
   if [ $? -eq "0" ]; then
     echo "${APP_NAME} is running. Pid is ${pid}"
   else
     echo "${APP_NAME} is not running."
   fi
}


#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
   "start")
     start $@;
     ;;
   "stop")
     stop $@;
     ;;
   "status")
     status $@;
     ;;
   "restart")
     stop $@;
     start $@;
     ;;
   *)
     help
     ;;
esac
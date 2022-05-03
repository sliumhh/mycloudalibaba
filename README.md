下载安装参考：https://nacos.io/zh-cn/docs/quick-start.html

启动:

```shell
cd /usr/local/nacos/bin 
#单机启动
sh startup.sh -m standalone
```

控制台:http://127.0.0.1:8848/nacos 用户名密码：nacos、nacos

查看日志：

```shell
cd /usr/local/nacos/logs
```

## 模块

### common 公共工具 jar

1. trace、token
2. JsonUtil：`com.bocom.common.util.JsonUtil`
3. userContext

### lhh-web 微服务

1. LogSave 切面，保存写操作日志
2. CurrentUserContext，当前登录人`com.bocom.common.util.CurrentUserContext`

### gateway 网关
日志、鉴权



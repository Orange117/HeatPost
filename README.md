# HeatPost
Calculate the heat of the post  
-> 计算方法：count * 7 + vote * 3
## 启动
1. 将target文件夹下项目war包导入tomcat服务器webapps文件夹中  
- Windows:start.bat  
- Linux:start.sh  
- 或通过maven命令：`mvn tomcat7:run`  
2. default url：http://localhost:8080/get_api_v1_tops
## 配置文件
- 数据库配置文件`src/main/resources/db.properties`

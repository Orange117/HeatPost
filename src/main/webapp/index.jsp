<html>
<body>
<h2>Hello World!</h2>
<%
    //页面每隔0.1秒自动链接至另一页面
    response.setHeader("refresh","0.1;http://localhost:8080/get_api_v1_top");
%>
</body>
</html>

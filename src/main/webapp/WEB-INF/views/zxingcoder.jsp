<%--
  Created by IntelliJ IDEA.
  User: CLT
  Date: 2018/1/4
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello World</title>
</head>
<body>
<div class="panel-heading"><h2>二维码与有Logo 的二维码</h2></div>
<div class="panel-body">
    <div class="col-md-6" align="center">
        <a href="<%=request.getContextPath()%>/common/zxingdecode?realImgPath=${imageName }">
            <img class="img-responsive img-rounded" src="${imageName}"/>

        </a>
    </div>

</div>
</body>
</html>
 
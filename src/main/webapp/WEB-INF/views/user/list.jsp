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
<fieldset>
    <legend>图片上传</legend>
    <h2>只能上传单张10M以下的 PNG、JPG、GIF 格式的图片</h2>
    <form action="<%=request.getContextPath()%>/merchant/add" method="post" enctype="multipart/form-data">
        选择文件:<input type="file" name="file" multiple >

        姓名；<input type="text" name="name" >
        <input type="submit" value="上传">

    </form>
</fieldset>

</body>
</html>
 
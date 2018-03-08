<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


    <title>json交互测试</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        //请求的是json，输出的是json
        function reuqestJson(){
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath }/classify/query',
                contentType:'application/json;charset=utf-8',
                //数据格式是json串,商品信息
                datatype:"json",
                data:'{"id":1,"name":"fjf"}',
                success:function(data){//返回json结果
                    console.info(data);
                }
            });
        }
    </script>

</head>

<body>
<input type="button" onclick="reuqestJson()"  value="请求的是json，输出的是json"/>
</body>
</html>
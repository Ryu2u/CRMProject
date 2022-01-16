<%--
  Created by IntelliJ IDEA.
  User: Ryuzu
  Date: 2022/1/15
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
            request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %> "/>
    <title>Title</title>
</head>
<body>
<script>

    $.ajax({
        url:"",
        data:{

        },
        type:"post",
        dataType:"",
        success: function (data){

        }
    });
</script>
</body>
</html>

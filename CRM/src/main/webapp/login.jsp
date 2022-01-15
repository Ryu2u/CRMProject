<%--
Created by IntelliJ IDEA.
User: Ryuzu
Date: 2022/1/15
Time: 15:37
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
    <meta charset="UTF-8">
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            //将用户文本框中的内容清空
            $("#loginAct").val("");
            //页面加载完毕后,让用户的文本框自动获得焦点
            $("#loginAct").focus();
            $("#btn").click(function (){
                login();
            })
            $(window).keydown(function (key){
                //alert(key.keyCode);//回车为13
                if (key.keyCode == 13) {
                    login();
                }
            })
        });
            //建议写在jquery函数的外面
            function login(){
                var loginAct =$.trim($("#loginAct").val());
                var loginPwd =$.trim($("#loginPwd").val());
                if (loginAct == "") {
                    $("#msg").html("用户名不能为空");
                    return false;
                }else if (loginPwd == "") {
                    $("#msg").html("密码不能为空");
                    return false;
                }
                //验证登录操作
                $.ajax({
                    url:"user/login.do",
                    data:{
                        "loginAct":loginAct,
                        "loginPwd":loginPwd
                    },
                    type:"post",
                    dataType:"json",
                    success: function (data){
                        /*  data返回的是json格式
                            {
                            "success":true/false,
                            "msg":"出错的信息"
                            }
                             */
                        if (data.success) {
                            //登录成功
                            window.location.href="workbench/index.html";

                        }else{//登录失败
                            $("#msg").html(data.msg);
                        }


                    }
                });

            }
    </script>
</head>
<body>
<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
    <img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
</div>
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
        CRM&nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
</div>

<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
    <div style="position: absolute; top: 0px; right: 60px;">
        <div class="page-header">
            <h1>登录</h1>
        </div>
        <form id="form" action="workbench/index.html" class="form-horizontal" role="form">
            <div class="form-group form-group-lg">
                <div style="width: 350px;">
                    <input id="loginAct" class="form-control" type="text" placeholder="用户名" >
                </div>
                <div style="width: 350px; position: relative;top: 20px;">
                    <input id="loginPwd" class="form-control" type="password" placeholder="密码">
                </div>
                <div class="checkbox" style="position: relative;top: 30px; left: 10px;">
                    <span id="msg" style="color: red"></span>

                </div>
                <button type="button" id="btn" class="btn btn-primary btn-lg btn-block"
                        style="width: 350px; position: relative;top: 45px;">登录
                </button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
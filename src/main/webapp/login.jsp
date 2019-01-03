<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/7
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%-- jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>

    <title>用户登录</title>

    <%-- EasyUi --%>
    <%-- 部署war包，css、javascript引用地址需加 ${pageContext.request.contextPath} --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>

    <script type="text/javascript">

        $(function () {

           alert("login");

        });

        /*function changevaildatecode(){

            /!*alert("验证码");*!/

            var timestamp = new Date()

            alert(timestamp);

            var imgid = $("#changeimage");

            imgid.src = "changevaildatecode?code=" + timestamp.getTime();


        };*/

        /*按下按键触发*/
        $(document).keydown(function () {

            /*alert("我按回车键了");*/

            if (event.keyCode == "13") {//keyCode=13是回车键
                loginform();
            }

            /*alert(event.keyCode);*/ //测试按键的keycode码

            if (event.keyCode == "32"){ //keycode:32是空格键

                alert("清空表单");

                resetform();

            }

        });

        function loginform(){

            $("#ff").submit();

        };

        function resetform(){

            $("#ff").form('clear');

        };

        /*function selectusername(username) {

            $.ajax(function(){
                type : 'post'
                url : 'selectusername'
                data : {username:username}
                dadaType : 'json'
                success : function success(data){

                    var data = data;
                    alert(data);

                }
                error : function field(){

                    alert("出错了");

                }

            });

        };*/

        function tologin(){

            //以下为按钮点击事件的逻辑。注意这里要重新打开窗口
            //否则后面跳转到QQ登录，授权页面时会直接缩小当前浏览器的窗口，而不是打开新窗口
            var A=window.open("oauth/index.php","TencentLogin",
                "width=450,height=320,menubar=0,scrollbars=1,resizable=1,status=1,titlebar=0,toolbar=0,location=1");

        }

    </script>

</head>

<body>

    <div style="width: 100%;height: 100%;background-image: url(${pageContext.request.contextPath}/images/beijingtupian1.jpg);background-size: 100% 100%;-moz-background-size: 100% 100%;-webkit-background-size: 100% 100%;">

        <div align="center" style="padding-top: 10%">

            <h1 style="color:#ff9b00b0">教务综合管理系统</h1>
            </br>
            </br>
            <form id="ff"action="login" method="post">

                <table>

                    <tr colspan="2">
                        <td>用户名：</td>
                    </tr>
                    <tr>
                        <td colspan="2"><input class="easyui-textbox" data-options="iconCls:'icon-man',prompt:'请输入用户名'" style="width:300px" name="username"></td>
                    </tr>
                    <%--&lt;%&ndash;<tr>
                        <td><input type="button" value="验证用户名" onclick="selectusername(username)"></td>
                        <td></td>&ndash;%&gt;
                    </tr>--%>
                    <tr>
                        <td colspan="2" style="text-align: right"><label style="color: red;">${usernamewaring}</label></td>
                    </tr>
                    <tr>
                        <td colspan="2">密码：</td>
                    </tr>
                    <tr>
                        <td colspan="2"><input class="easyui-textbox" data-options="iconCls:'icon-lock',prompt:'请输入密码'" style="width:300px" type="password" name="password"></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right"><label style="color: red;">${passwordwaring}</label></td>
                    </tr>
                    <tr>
                        <td colspan="2">验证码：</td>
                    </tr>
                    <tr>
                        <td colspan="2"><input class="easyui-textbox" data-options="iconCls:'icon-mini-edit',prompt:'请输入验证码'" style="width:300px" type="text" name="PageVaildateCode"></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right"><label style="color: red;">${codewaring}</label></td>
                    </tr>
                    <tr>
                        <td colspan="2"><img src="changevaildatecode" alt="验证码" title="换一张" onclick="this.src='changevaildatecode?' + new Date().getTime();">(看不清？点击换一张)</td>
                    </tr>
                    <%--<tr>
                        <td colspan="2"><label  onclick="changevaildatecode()"><img id="changeimage" src="changevaildatecode" alt="验证码" title="换一张"></label></td>
                    </tr>--%>
                    <tr>
                        <td><a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="resetform()">重置</a></td>
                        <td align="right"><a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="loginform()">登录</a></td>
                    </tr>
                    <%--<tr>
                        <td>使用其他账号登录：</td>
                    </tr>
                    <tr>
                        <td><img src="images/qq_login_button.png" onclick="tologin()"></td>
                    </tr>--%>

                </table>

            </form>

        </div>

        </div>

</body>

</html>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/12
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>test</title>

    <%-- EasyUi --%>
    <link rel="stylesheet" type="text/css" href="/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/easyui/themes/icon.css">
    <script type="text/javascript" src="/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>

    <script type="text/javascript">

        $(function(){

            alert("test");

            $("#checked").click(function(){

                alert("---------");

                var checkedid = $("input[name='idchecked']").is(":checked");

                if(checkedid == false){

                    $("input[name='idchecked']").attr("checked","checked");

                }

            });

            $("input[name='username']").blur(function(){

                alert("blur--------------");

            });

                $('#p').panel('move',{
                    left:100,
                    top:100
                });

        });

    </script>

</head>
<body>

   <%--<table>

       <c:forEach var="list" items="${list}">

           <tr>
               <td>${list.id}</td> <td>${list.username}</td> <td>${list.password}</td>
           </tr>

       </c:forEach>

   </table>--%>

    <h1>${test}</h1>
    <br>
    用户名:<input type="text" name="username">
   <br>
   <div id="p" class="easyui-panel" title="My Panel"
        style="width:500px;height:150px;padding:10px;background:#fafafa;"
        data-options="iconCls:'icon-save',closable:true,
                collapsible:true,minimizable:true,maximizable:true">
       <p>panel content.</p>
       <p>panel content.</p>
   </div>
   <br>
   <br>
   <a href="javascript:void(0)" id="sb" class="easyui-splitbutton"
      data-options="menu:'#mm',iconCls:'icon-ok'" onclick="javascript:alert('ok')">Ok</a>
   <div id="mm" style="width:100px;">
       <div data-options="iconCls:'icon-ok'">Ok</div>
       <div data-options="iconCls:'icon-cancel'">Cancel</div>
   </div>
   <br>
   <br>
   <input type="checkbox" id="checked">全部选中
   <br>
   <input name="idchecked" type="checkbox" value="1">
   <input name="idchecked" type="checkbox" value="2">
</body>
</html>

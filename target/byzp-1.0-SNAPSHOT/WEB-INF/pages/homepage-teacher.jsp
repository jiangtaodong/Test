<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%-- jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>

    <title>教务管理系统</title>

    <%-- EasyUi --%>
    <%-- 部署war包，css、javascript引用地址需加 ${pageContext.request.contextPath} --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>

    <%-- digital-clock.js --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}\js\jsled\css\demo.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}\js\jsled\css\normalize.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}\js\jsled\css\style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}\js\jsled\js\digital-clock.js"></script>

    <script type="text/javascript">
        $(function(){

            alert("教务管理系统");

            $('#cc').combobox({
                url:'selectallcity',
                valueField:'cityname',
                textField:'cityname',
                limitToList:true,
                onChange: function (cityname) {

                    $("#weathertable").children().remove()

                    $.ajax({

                        url: "selectweatherbycityname",
                        type: "post",
                        data: {"cityname":cityname},
                        dataType: "text",
                        success:function (data) {

                            alert("selectweatherbycityname成功！" + data);

                            var dataobj = eval("(" + data + ")");

                            $('#weathertable').append(
                                '<tr>' +
                                '<td>' + '城市' + '</td>' +
                                '<td>' + '天气详情' + '</td>' +
                                '<td>' + '最高温度' + '</td>' +
                                '<td>' + '最低温度' + '</td>' +
                                '<td>' + '温度实况' + '</td>' +
                                '<td>' + '风向详情' + '</td>' +
                                '<td>' + '风向' + '</td>' +
                                '<td>' + '强风等级' + '</td>' +
                                '<td>' + '空气湿度' + '</td>' +
                                '<td>' + '更新时间' + '</td>' +
                                '</tr>'
                            )

                            $.each(dataobj,function (index,data) {

                                $('#weathertable').last().append(
                                    '<tr>' +
                                    '<td>' + data.cityname + '</td>' +
                                    '<td>' + data.stateDetailed + '</td>' +
                                    '<td>' + data.tem1 + '</td>' +
                                    '<td>' + data.tem2 + '</td>' +
                                    '<td>' + data.temNow + '</td>' +
                                    '<td>' + data.windState + '</td>' +
                                    '<td>' + data.windDir + '</td>' +
                                    '<td>' + data.windPower + '</td>' +
                                    '<td>' + data.humidity + '</td>' +
                                    '<td>' + data.inserttime + '</td>' +
                                    '</tr>'
                                )

                            })

                        },
                        error:function () {

                            alert("selectweatherbycityname失败！");

                        }

                    })

                }

            });

        });

        function yonghuzhuce() {

            $('#tt').tabs({
                fit: true,
                border: true,
                pill:true,
            });

            $('#tt').tabs('add', {
                id:'tianjia',
                title: '用户注册',
                fit: true,
                closable: true,
                tools: [{
                    iconCls: 'icon-mini-refresh',
                    handler: function () {
                        alert('refresh');
                    }
                }]
            });

            $('#tianjia').append(
                '<form action="insertuser" method="post">' +
                '<table align="center" style="margin-top: 5%;color: black">' +
                '<tr>' +
                '<td>' + '姓名：' + '</td>' +
                '<td>' + '<input style="width:200px" name="name" type="text">' + '</td>'+
                '</tr>' +
                '<tr>' +
                '<td>' + '性别：' + '</td>' +
                '<td>' + '男' +
                '<input type="radio" name="gender" value="男" checked="checked">' + '&nbsp&nbsp&nbsp&nbsp' + '女' +
                '<input type="radio" name="gender" value="女">' +
                '</td>' +
                '</tr>' +
                '<tr>' +
                '<td>' + '年龄：' + '</td>' +
                '<td>' +
                '<select name="age">' +
                '<option>' + '---请选择---' + '</option>' +
                '</select>' +
                '</td>'+
                '</tr>' +
                '<tr>' +
                '<td>' + '所在班级：' + '</td>' +
                '<td>' + '<input style="width:200px" name="classes" type="text">' + '</td>' +
                '</tr>' +
                '<tr>' +
                '<td>' + '工号or学号：' + '</td>' +
                '<td>' + '<input style="width:200px" name="identityNumber" type="text">' + '</td>'+
                '</tr>' +
                '<tr>' +
                '<td>' + '身份性质：' + '</td>' +
                '<td>' +
                '<select name="identity">' +
                '<option>' + '---请选择---' + '</option>' +
                '<option>' + '老师' + '</option>' +
                '<option>' + '学生' + '</option>' +
                '</select>' +
                '</td>' +
                '</tr>' +
                '<tr>' +
                '<td>' + '</td>' +
                '<td>' + '<input type="reset" value="重置">' + '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp' + '<input type="submit" value="提交">' + '</td>'
                + '</tr>'
                + '</table>'
                + '</form>'
            );

            $("#btn1").attr("onclick"," ");

            /*alert($('div#tt').children().last().children().last().html());*/

            for(var i = 10; i <= 100; i++){

                $("select[name='age']").append('<option style="text-align: center">' + i + '</option>')

            }

        }
        
        function studentinfo() {

            $("#cityshuju").remove()

            $('#tt').tabs({
                fit: true,
                border: true,
                pill:true,
            });

            $('#tt').tabs('add', {
                id:'studentinfochaxun',
                title: '学生信息查询',
                fit: true,
                closable: true,
                tools: [{
                    iconCls: 'icon-mini-refresh',
                    handler: function () {
                        alert('refresh');
                    }
                }]
            });

            var pagenum = 1;
            var pagesize = 5;
            var pages = 0;

            $.ajax({

                url: "student/countstudent",
                type: "post",
                data: {"pageNum":pagenum,"pageSize":pagesize},
                dataType: "text",
                success: function (data) {

                    alert("查询学生信息ok！------ " + data);

                   /* var countstudentobj = eval("("+data+")");*/

                    $('#studentinfochaxun').append(
                        /*'<form action="insertuser" method="post">' +*/
                        '<table id="selectallstudent" align="center" style="margin-top: 5%;color: black;text-align: center" border="1px" width="95%">' +
                        '<tr>' +
                        '<td>'+ '序号' +'</td>'+
                        '<td>'+ '姓名' +'</td>'+
                        '<td>'+ '性别' +'</td>'+
                        '<td>'+ '年龄' +'</td>'+
                        '<td>'+ '班级' +'</td>'+
                        '<td>'+ '学号' +'</td>'+
                        '<td>'+ '身份' +'</td>'+
                        '<td>'+ '地址' +'</td>'+
                        '<td>'+ '电话' +'</td>'+
                        '<td width="10%">'+ '照片' +'</td>'+
                        '</tr>' +
                        '</table>'
                        /* + '</form>'*/
                    );

                    var newdata = eval("("+data+")");

                    var student = newdata.list;

                    pages = newdata.pages;
                    pagenum = newdata.pageNum;

                    for(var i=0; i<student.length;i++){

                        $('#selectallstudent').last().append(
                            '<tr>' +
                            '<td>' + student[i].id + '</td>' +
                            '<td>' + student[i].name + '</td>' +
                            '<td>' + student[i].gender + '</td>' +
                            '<td>' + student[i].age + '</td>' +
                            '<td>' + student[i].classes + '</td>' +
                            '<td>' + student[i].identity_number + '</td>' +
                            '<td>' + student[i].identity + '</td>' +
                            '<td>' + student[i].address + '</td>' +
                            '<td>' + student[i].phone + '</td>' +
                            '<td>' +
                            '<img id="studentphoto" src="'+student[i].image+'" title="照片信息" alt="照片信息" style="width: 100%">' +
                            '</td>' +
                            '<tr>'
                        );

                        /*var newdata2 = list[i].name;

                        alert(newdata2);*/

                    }

                    /*$.each(countstudentobj,function (index,student) {
                        alert(student.total);
                        for(var a = 0; a < student.length; a++){

                            $('#selectallstudent').last().append(
                                '<tr>' +
                                '<td>' + student[7].id + '</td>' +
                                '<td>' + student[7].name + '</td>' +
                                '<td>' + student[7].gender + '</td>' +
                                '<td>' + student[7].age + '</td>' +
                                '<td>' + student[7].classes + '</td>' +
                                '<td>' + student[7].identity_number + '</td>' +
                                '<td>' + student[7].identity + '</td>' +
                                '<td>' + student[7].address + '</td>' +
                                '<td>' + student[7].phone + '</td>' +
                                '<td>' +
                                '<img id="studentphoto" src="'+student.image+'" title="照片信息" alt="照片信息" style="width: 100%">' +
                                '</td>' +
                                '<tr>'
                            );

                        }

                    })*/

                    $('#selectallstudent').last().append(
                        '<tr>' +
                        '<td colspan="2">' + '总页数：' + '</td>' +
                        '<td id="pages">' + pages + '</td>' +
                        '<td>' + '<input type="button" value="上一页" onclick="prepage()">' + '</td>' +
                        '<td>' + '<input type="button" value="下一页" onclick="nextpage()">' + '</td>' +
                        '<td>' + '当前页：' + '</td>' +
                        '<td id="pagenum">' + pagenum + '</td>' +
                        '<td colspan="2">' + '跳转到 '+'<input type="text" name="gopagenum" value="">'+'页' + '</td>' +
                        '<td>' + '<input type="button" value="跳转" onclick="gopage()">' + '</td>' +
                        '<td>' + '</td>' +
                        '</tr>'
                    )

                },
                error: function () {

                    alert("查询学生信息错误！");

                }

            })

            $("#btn").attr("onclick"," ");

        }

        function prepage() {

            var pages = 0;
            var pagesize = 5;
            var pagenum = parseInt($("#pagenum").text()) - 1;

            if(pagenum == 0){

                alert("已经是首页了！");
                return;

            }

            $.ajax({

                url: "student/countstudent",
                type: "post",
                data:  {"pageNum":pagenum,"pageSize":pagesize},
                datatype: "text",
                success: function (data) {

                    alert("查询学生信息ok！------ " + data);

                    $('#selectallstudent tr').nextAll().remove();

                    var newdata = eval("("+data+")");

                    var student = newdata.list;

                    pagenum = newdata.pageNum;
                    pages = newdata.pages;

                    for(var i=0; i<student.length;i++){

                        $('#selectallstudent').last().append(
                            '<tr>' +
                            '<td>' + student[i].id + '</td>' +
                            '<td>' + student[i].name + '</td>' +
                            '<td>' + student[i].gender + '</td>' +
                            '<td>' + student[i].age + '</td>' +
                            '<td>' + student[i].classes + '</td>' +
                            '<td>' + student[i].identity_number + '</td>' +
                            '<td>' + student[i].identity + '</td>' +
                            '<td>' + student[i].address + '</td>' +
                            '<td>' + student[i].phone + '</td>' +
                            '<td>' +
                            '<img id="studentphoto" src="'+student[i].image+'" title="照片信息" alt="照片信息" style="width: 100%">' +
                            '</td>' +
                            '<tr>'
                        );

                    }

                    $('#selectallstudent').last().append(
                        '<tr>' +
                        '<td colspan="2">' + '总页数：' + '</td>' +
                        '<td id="pages">' + pages + '</td>' +
                        '<td>' + '<input type="button" value="上一页" onclick="prepage()">' + '</td>' +
                        '<td>' + '<input type="button" value="下一页" onclick="nextpage()">' + '</td>' +
                        '<td>' + '当前页：' + '</td>' +
                        '<td id="pagenum">' + pagenum + '</td>' +
                        '<td colspan="2">' + '跳转到 '+'<input type="text" name="gopagenum" value="">'+'页' + '</td>' +
                        '<td>' + '<input type="button" value="跳转" onclick="gopage()">' + '</td>' +
                        '<td>' + '</td>' +
                        '</tr>'
                    )

                },
                error: function () {

                    alert("查询学生信息错误！");

                }

            })

        }

        function nextpage() {

            var pages = $("#pages").text();
            var pagesize = 5;
            var pagenum = parseInt($("#pagenum").text()) + 1;

            if(pagenum > pages){

                alert("已经是最后一页了！");
                return;

            }

            $.ajax({

                url: "student/countstudent",
                type: "post",
                data:  {"pageNum":pagenum,"pageSize":pagesize},
                datatype: "text",
                success: function (data) {

                    alert("查询学生信息ok！------ " + data);

                    $('#selectallstudent tr').nextAll().remove();

                    var newdata = eval("("+data+")");

                    var student = newdata.list;

                    pagenum = newdata.pageNum;

                    for(var i=0; i<student.length;i++){

                        $('#selectallstudent').last().append(
                            '<tr>' +
                            '<td>' + student[i].id + '</td>' +
                            '<td>' + student[i].name + '</td>' +
                            '<td>' + student[i].gender + '</td>' +
                            '<td>' + student[i].age + '</td>' +
                            '<td>' + student[i].classes + '</td>' +
                            '<td>' + student[i].identity_number + '</td>' +
                            '<td>' + student[i].identity + '</td>' +
                            '<td>' + student[i].address + '</td>' +
                            '<td>' + student[i].phone + '</td>' +
                            '<td>' +
                            '<img id="studentphoto" src="'+student[i].image+'" title="照片信息" alt="照片信息" style="width: 100%">' +
                            '</td>' +
                            '<tr>'
                        );

                    }

                    $('#selectallstudent').last().append(
                        '<tr>' +
                        '<td colspan="2">' + '总页数：' + '</td>' +
                        '<td id="pages">' + pages + '</td>' +
                        '<td>' + '<input type="button" value="上一页" onclick="prepage()">' + '</td>' +
                        '<td>' + '<input type="button" value="下一页" onclick="nextpage()">' + '</td>' +
                        '<td>' + '当前页：' + '</td>' +
                        '<td id="pagenum">' + pagenum + '</td>' +
                        '<td colspan="2">' + '跳转到 '+'<input type="text" name="gopagenum" value="">'+'页' + '</td>' +
                        '<td>' + '<input type="button" value="跳转" onclick="gopage()">' + '</td>' +
                        '<td>' + '</td>' +
                        '</tr>'
                    )

                },
                error: function () {

                    alert("查询学生信息错误！");

                }

            })

        }
        
        function gopage() {

            var pages = $("#pages").text();
            var pagenum = $('input[name="gopagenum"]').val();
            var pagesize = 5;

            if(pagenum > pages){

                alert("抱歉，没找到....");
                return;

            }

            $.ajax({

                url: "student/countstudent",
                type: "post",
                data:  {"pageNum":pagenum,"pageSize":pagesize},
                datatype: "text",
                success: function (data) {

                    alert("查询学生信息ok！------ " + data);

                    $('#selectallstudent tr').nextAll().remove();

                    var newdata = eval("("+data+")");

                    var student = newdata.list;

                    pagenum = newdata.pageNum;
                    pages =  newdata.pages;

                    for(var i=0; i<student.length;i++){

                        $('#selectallstudent').last().append(
                            '<tr>' +
                            '<td>' + student[i].id + '</td>' +
                            '<td>' + student[i].name + '</td>' +
                            '<td>' + student[i].gender + '</td>' +
                            '<td>' + student[i].age + '</td>' +
                            '<td>' + student[i].classes + '</td>' +
                            '<td>' + student[i].identity_number + '</td>' +
                            '<td>' + student[i].identity + '</td>' +
                            '<td>' + student[i].address + '</td>' +
                            '<td>' + student[i].phone + '</td>' +
                            '<td>' +
                            '<img id="studentphoto" src="'+student[i].image+'" title="照片信息" alt="照片信息" style="width: 100%">' +
                            '</td>' +
                            '<tr>'
                        );

                    }

                    $('#selectallstudent').last().append(
                        '<tr>' +
                        '<td colspan="2">' + '总页数：' + '</td>' +
                        '<td id="pages">' + pages + '</td>' +
                        '<td>' + '<input type="button" value="上一页" onclick="prepage()">' + '</td>' +
                        '<td>' + '<input type="button" value="下一页" onclick="nextpage()">' + '</td>' +
                        '<td>' + '当前页：' + '</td>' +
                        '<td id="pagenum">' + pagenum + '</td>' +
                        '<td colspan="2">' + '跳转到 '+'<input type="text" name="gopagenum" value="">'+'页' + '</td>' +
                        '<td>' + '<input type="button" value="跳转" onclick="gopage()">' + '</td>' +
                        '<td>' + '</td>' +
                        '</tr>'
                    )

                },
                error: function () {

                    alert("查询学生信息错误！");

                }

            })
            
        }
        
        function studentcountinfo() {

            $("#cityshuju").remove()

            $('#tt').tabs({
                fit: true,
                border: true,
                pill:true,
            });

            $('#tt').tabs('add', {
                id:'studentcountinfochaxun',
                title: '学生成绩查询',
                fit: true,
                closable: true,
                tools: [{
                    iconCls: 'icon-mini-refresh',
                    handler: function () {
                        alert('refresh');
                    }
                }]
            });

            $.ajax({

                url: "count/selectallstudentcountinfo",
                type: "post",
                data: "json",
                dataType: "text",
                success: function (data) {

                    alert("查询学生成绩信息ok！------ " + data);

                    var studentcountobj = eval("("+data+")");

                    $('#studentcountinfochaxun').append(
                        /*'<form action="insertuser" method="post">' +*/
                        '<table id="selectallstudentcounttable" align="center" style="margin-top: 5%;color: black;text-align: center" border="1px" width="75%">' +
                        '<tr>' +
                        '<td>'+ '学号' +'</td>'+
                        '<td>'+ '姓名' +'</td>'+
                        '<td>'+ '语文' +'</td>'+
                        '<td>'+ '数学' +'</td>'+
                        '<td>'+ '英语' +'</td>'+
                        '<td>'+ '体育' +'</td>'+
                        '<td>'+ '总分' +'</td>'+
                        '</tr>' +
                        '</table>'
                        /* + '</form>'*/
                    );

                    $.each(studentcountobj,function (index,studentcount) {

                        $('#selectallstudentcounttable').last().append(
                            '<tr>' +
                            '<td>'+ studentcount.student_number +'</td>'+
                            '<td>'+ studentcount.name +'</td>'+
                            '<td>'+ studentcount.yuwen +'</td>'+
                            '<td>'+ studentcount.shuxue +'</td>'+
                            '<td>'+ studentcount.yingyu +'</td>'+
                            '<td>'+ studentcount.tiyu +'</td>'+
                            '<td>'+ studentcount.zongfen +'</td>'+
                            '<tr>'
                        );

                    })
                },
                error: function () {

                    alert("查询学生信息错误！");

                }

            })

            $("#btn1").attr("onclick"," ");

        }

        function studentcountimport(){

            $("#cityshuju").remove()

            $('#tt').tabs({
                fit: true,
                border: true,
                pill:true,
            });

            $('#tt').tabs('add', {
                id:'studentcountimport',
                title: '学生成绩录入',
                fit: true,
                closable: true,
                tools: [{
                    iconCls: 'icon-mini-refresh',
                    handler: function () {
                        alert('refresh');
                    }
                }]
            });

            $.ajax({

                url: "student/selectallstudentinfo",
                type: "post",
                data: "json",
                dataType: "text",
                success: function (data) {

                    alert("查询所有学生！------ " + data);

                    var studentallobj = eval("("+data+")");

                    $('#studentcountimport').append(
                        '<form action="insertstudentcount" method="post">' +
                        '<table id="studentallcounttable" align="center" style="margin-top: 5%;color: black;text-align: center" border="1px" width="70%">' +
                            '<tr>' +
                                '<td>'+ '学号' +'</td>'+
                                '<td>'+ '姓名' +'</td>'+
                                '<td>'+ '<input id="selectclasses" value="查询指定班级" style="text-align: center">' +'</td>'+
                                '<td>'+ '语文' +'</td>'+
                                '<td>'+ '数学' +'</td>'+
                                '<td>'+ '英语' +'</td>'+
                                '<td>'+ '体育' +'</td>'+
                                '<td>'+ '操作' +'</td>'+
                            '</tr>' +
                        '</table>'
                         + '</form>'
                    );

                    $.each(studentallobj,function (index,studentcount) {

                        $('#studentallcounttable').last().append(
                            '<tr>' +
                                '<td>'+ studentcount.identity_number +'</td>'+
                                '<td>'+ studentcount.name +'</td>'+
                                '<td>'+ studentcount.classes +'</td>'+
                                '<td>'+ studentcount.yuwen +'</td>'+
                                '<td>'+ studentcount.shuxue +'</td>'+
                                '<td>'+ studentcount.yingyu +'</td>'+
                                '<td>'+ studentcount.tiyu +'</td>'+
                                '<td>'+
                                    '<input type="button" value="录入成绩" onclick="inputcount(('+studentcount.identity_number+'))">'+
                                '</td>'+
                            '<tr>'
                        );

                    })

                    $('#selectclasses').combobox({
                        url:'student/studentclasses',
                        valueField:'classes',
                        textField:'classes',
                        onChange: function () {

                            var studentclasses = $("#selectclasses").val();

                            alert("查询班级----" + studentclasses);

                            $("#studentallcounttable").find("tr").nextAll().remove();

                              $.ajax({

                                  url: "student/selectstudentinfobyclasses",
                                  type: "post",
                                  data: {"classes":studentclasses},
                                  dataType: "text",
                                  success: function (data) {

                                      alert("查询班级成功！" + data);

                                      var studentbyclassesobj = eval("(" + data + ")")

                                      $.each(studentbyclassesobj,function (index,classes) {

                                          $('#studentallcounttable').last().append(
                                              '<tr>' +
                                                  '<td>'+ classes.identity_number +'</td>'+
                                                  '<td>'+ classes.name +'</td>'+
                                                  '<td>'+ classes.classes +'</td>'+
                                                  '<td>'+ classes.yuwen +'</td>'+
                                                  '<td>'+ classes.shuxue +'</td>'+
                                                  '<td>'+ classes.yingyu +'</td>'+
                                                  '<td>'+ classes.tiyu +'</td>'+
                                                  '<td>'+
                                                    '<input type="button" value="录入成绩" onclick="inputcount(('+classes.identity_number+'))">'+
                                                  '</td>'+
                                              '<tr>'
                                          );

                                      })

                                  },
                                  error: function () {

                                      alert("查询班级失败！");

                                  }

                              })

                        }


                    });

                },
                error: function () {

                    alert("查询学生信息错误！");

                }

        })

            $("#btncountimport").attr("onclick"," ");

        }
        
        function tubiaohuizong() {

            $("#cityshuju").remove()

            $("#tt").append(
                '<div id="win">' +
                '</div>'
            );

            $('#win').window({
                id: "tubiaohuizongarea",
                width:1100,
                height:570,
                title:"成绩图表汇总",
                modal:true,
                onBeforeClose:function () {

                    alert("close!");

                    $("#tubiaohuizongarea").remove();

                },
                onOpen:function () {

                    $("#tubiaohuizongarea").append(

                        '<iframe id="shujutongji" src="js/shujutongji/shujutongji.html" style="width: 100%;height: 100%">' + '</iframe>'
                        /*'<a id="btn-shujutongji" target="shujutongji" href="js\\shujutongji\\shujutongji.html" class="easyui-linkbutton" data-options="iconCls:\'icon-search\'">' + '点击查看数据统计图' + '</a>'*/

                    );

                }
            });

            /*$("#btntubiao").attr("onclick"," ");*/
            
        }
        
        function inputcount(identity_number) {

            alert("【" + identity_number + "】成绩录入");

            $("#studentallcounttable").append(
                '<div id="win">' +
                '</div>'
            );

            $('#win').window({
                width:800,
                height:400,
                title:"学生成绩录入",
                modal:true
            });

            $.ajax({

                url: "count/selectstudentinfobystudent_number",
                type: "post",
                data: {"student_number":identity_number},
                dataType: "text",
                success: function (data) {

                    alert("根据student_number查询学生信息成功！" + data);

                    var studentobj = JSON.parse(data);

                    $("#win").append(

                        '<form action="count/updatecountbystudent_number" method="post">' +
                            '<table style="text-align: center;color: black;margin-top: 15%" border="1" align="center">' +
                                '<tr>' +
                                    '<input type="hidden" name="student_number" value="">' +
                                    '<td>' +'学号： '+ studentobj.identity_number + '</td>' +
                                    '<td>' +'姓名： '+ studentobj.name + '</td>' +
                                    '<td colspan="2">' +'班级： '+ studentobj.classes + '</td>' +
                                '</tr>' +
                                '<tr>' +
                                    '<td>' +'语文'+ '</td>' +
                                    '<td>' +'数学'+ '</td>' +
                                    '<td>' +'英语'+ '</td>' +
                                    '<td>' +'体育'+ '</td>' +
                                '</tr>' +
                                '<tr>' +
                                    '<td>' + '<input type="text" name="yuwen" value="">' + '</td>' +
                                    '<td>' + '<input type="text" name="shuxue" value="">' + '</td>' +
                                    '<td>' + '<input type="text" name="yingyu" value="">' + '</td>' +
                                    '<td>' + '<input type="text" name="tiyu" value="">' + '</td>' +
                                '</tr>' +
                                '<tr>' +
                                    '<td colspan="4">' +
                                        '<input type="reset" value="重置" style="margin-right: 5%">' +
                                        '<input type="submit" value="添加">' +
                                    '</td>' +
                                '</tr>' +
                            '</table>' +
                        '</form>'

                    );

                    $("input[name='student_number']").val(studentobj.student_number);
                    $("input[name='yuwen']").val(studentobj.yuwen);
                    $("input[name='shuxue']").val(studentobj.shuxue);
                    $("input[name='yingyu']").val(studentobj.yingyu);
                    $("input[name='tiyu']").val(studentobj.tiyu);
                    $("input").css("text-align","center");

                },
                error: function () {

                    alert("根据student_number查询学生信息失败！");

                }

            })

        }

        function mimaxiugai() {

            $("#cityshuju").remove()

            $('#dg').remove();

            $('#tt').tabs({
                fit: true,
                border: true,
                pill:true,
            });

            $('#tt').tabs('add', {
                id:'xiugai',
                title: '密码修改',
                fit: true,
                closable: true,
                tools: [{
                    iconCls: 'icon-mini-refresh',
                    handler: function () {
                        alert('refresh');
                    }
                }]
            });

            var loginusername = $("#loginusername").text();

            $('#xiugai').append(
                '<form id="updatepassword" action="updatepassword" method="post">' +
                    '<table align="center" style="margin-top: 5%;color: black">' +
                        '<tr>' +
                            '<td>' + '新密码：' + '</td>' +
                            '<td>' + '<input name="password" type="password" placeholder="请输入新密码">' + '</td>' +
                        '</tr>' +
                        '<tr>' +
                            '<td>' + '再次输入新密码：' + '</td>' +
                            '<td>' + '<input name="passwordagain" type="password" placeholder="请再次输入新密码">' + '</td>' +
                        '</tr>' +
                        '<tr>' +
                            '<td>' + '</td>' +
                            '<td>' + '&nbsp' +
                                '<input type="reset" value="重置">' + '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp' +
                                '<input type="button" value="提交" onclick="changepassword()">' +
                            '</td>' +
                        '</tr>'
                     + '</table>'
                + '</form>'
            );

            $("#btn2").attr("onclick"," ");

            /*alert($('div#tt').children().last().children().last().html());*/

                $("input[name='password']").blur(function(){

                    if($("input[name='password']").val().length < 6){

                        alert("密码长度不应小于6位！");

                    }

                });

        }

        function changepassword() {

                if($("input[name='password']").val() == $("input[name='passwordagain']").val()){

                    $("#updatepassword").submit();

                }else{

                    alert("密码输入不一致，请重新输入！");

                }

        }


        function chakanyonghu() {

            $("#btn3").attr("onclick"," ");

            $('#dg').remove();

            $('#tt').append('<table id="dg">' + '</table>');

            $('#tt').tabs({
                border:true,
                fit:true,
                pill:true,
            });

            $('#tt').tabs('add',{
                id:'chakan',
                title:'查看用户',
                closable:true,
                tools: [{
                    iconCls: 'icon-mini-refresh',
                    handler: function () {
                        alert('refresh');
                    }
                }]
            });

            $.ajax({
                url: 'selectall',
                type: 'post',
                data: 'json',
                success:function(data){

                    $('#chakan').append(
                        '<form id="shujutable" action="deleteuserbyid" method="post">' +
                        '<table id="shujubianli" align="center" style="margin-top: 5%;color: black;width: 90%;text-align: center" border="1px">' +
                        '<tr>' +
                        '<td>' + '序号' + '</td>' +
                        '<td>' + '用户名' + '</td>' +
                        '<td>' + '密码' + '</td>' +
                        '<td>' + '身份' + '</td>' +
                        '<td>' + '注册时间' + '</td>' +
                        '<td colspan="2" style="text-align: center">' + '操作' + '</td>' +
                        '</tr>' +
                        '</table>' +
                        '</form>'
                    );

                    var selectallobj = eval("("+ data +")");//转换成json对象

                    $.each(selectallobj,function (index,user) {

                       /* if(index == 0){

                            return true;

                        }*/

                        /*alert(user.id + " ------- " + user.username);*/

                        $('#shujubianli').append(
                            '<tr>'+
                            '<td>' + user.id + '</td>' +
                            '<td>' + user.username + '</td>' +
                            '<td>' + user.password + '</td>' +
                            '<td>' + user.identity + '</td>' +
                            '<td>' + user.time + '</td>'+
                            '<td>' +
                            '<input type="button" value="修改" onclick="xiugaiuser('+user.id+')" style="margin-right: 10px">'  +
                            '<input type="button" value="删除" onclick="shanchuuser('+user.id+')">' +
                            '</td>'+
                            '</tr>'
                        );

                    })

                },
                error:function(){

                    alert("查询出错！");

                }

            });

            /*$('#dg').datagrid({
                url:'selectall',
                columns:[[
                    {field:'id',title:'ID',width:100,align:'center'},
                    {field:'username',title:'用户名',width:100,align:'center'},
                    {field:'password',title:'密码',width:100,align:'center'},
                    {field:'identity',title:'身份',width:100,align:'center'},
                    {field:'time',title:'注册时间',width:200,align:'center'},
                ]]
            });*/

        }

        function xiugaiuser (xiugaibyid) {

            alert("这是修改！修改序号为【" + xiugaibyid +"】");

            $("#chakan").append(
            '<div id="win">' +
            '</div>'
            );

            $('#win').window({
                width:600,
                height:400,
                title:"用户信息修改",
                modal:true
            });

            $.ajax({

                url: 'selectuserbyid',
                type: 'post',
                data:{'id':xiugaibyid},
                dataType: 'text',
                success: function(data){

                    var newdata = JSON.parse(data);

                    /*alert("根据id查询用户信息！" + newdata.username);*/

                    $("#win").append(
                        '<form action="updateuserbyid" method="post">'+
                        '<table id="xiugaiuserxinxitable" align="center" style="margin-top: 5%;color: black;width: 90%;text-align: center" border="1px">' +
                        '<tr>' +
                        '<td>' + '序号：' + '</td>' +
                        '<td>' + '<input type="text" name="id" value="" readonly="readonly">' + '</td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td>' + '用户名：' + '</td>' +
                        '<td>' + '<input type="text" name="username" value="">' + '</td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td>' + '密码：' + '</td>' +
                        '<td>' + '<input type="text" name="password" value="">' + '</td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td>' + '身份：' + '</td>' +
                        '<td>' +
                        '<select name="identity" style="width: 47%">' +
                            '<option value="管理员">'+ '管理员' +'</option>'+
                            '<option value="老师">'+ '老师' +'</option>'+
                            '<option value="学生">'+ '学生' +'</option>'+
                        '</select>'+
                        '</td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td colspan="2">' + '<input type="reset" value="重置" style="margin-right: 20%">' +  '<input type="submit" value="修改">' + '</td>' +
                        '</tr>' +
                        '</table>'+
                        '</br>'+
                        '</br>'+
                            '<input id="closewin" type="button" value="关闭页面" onclick="closewindow()" style="text-align: center;margin-left: 45%">'+
                        '</form>'
                    );

                    $("input[name='id']").val(newdata.id)
                    $("input[name='username']").val(newdata.username)
                    $("input[name='password']").val(newdata.password)
                    $("input[name='identity']").val(newdata.identity)

                },
                error: function(){

                    alert("根据id查询用户信息出错！");

                }

            })

        }

        function closewindow() {

           /* alert("暂时未开放！");*/

           window.location.href="closewindow";

        }

        function shanchuuser (shanchubyid) {

          $.ajax({

              url: 'deleteuserbyid',
              type: 'post',
              data: {"id":shanchubyid},
              dataType:'text',
              success:function(data){

                alert("删除成功！序号为：【" + shanchubyid + "】");

                  $("#shujubianli").remove();
                  $("#shujutable").remove();

                  $.ajax({
                      url: 'selectall',
                      type: 'post',
                      data: 'json',
                      success:function(data){

                          $('#chakan').append(
                              '<form id="shujutable" action="deleteuserbyid" method="post">' +
                              '<table id="shujubianli" align="center" style="margin-top: 5%;color: black;width: 90%;text-align: center" border="1px">' +
                              '<tr>' +
                              '<td>' + '序号' + '</td>' +
                              '<td>' + '用户名' + '</td>' +
                              '<td>' + '密码' + '</td>' +
                              '<td>' + '身份' + '</td>' +
                              '<td>' + '注册时间' + '</td>' +
                              '<td colspan="2" style="text-align: center">' + '操作' + '</td>' +
                              '</tr>' +
                              '</table>' +
                              '</form>'
                          );

                          var selectallobj = eval("("+ data +")");//转换成json对象

                          $.each(selectallobj,function (index,user) {

                              /* if(index == 0){

                                   return true;

                               }*/

                             /* alert(user.id + " ------- " + user.username);*/

                              $('#shujubianli').append(
                                  '<tr>'+
                                  '<td>' + user.id + '</td>' +
                                  '<td>' + user.username + '</td>' +
                                  '<td>' + user.password + '</td>' +
                                  '<td>' + user.identity + '</td>' +
                                  '<td>' + user.time + '</td>'+
                                  '<td>' +
                                  '<input type="button" value="修改" onclick="xiugaiuser('+user.id+')" style="margin-right: 10px">' +
                                  '<input type="button" value="删除" onclick="shanchuuser('+user.id+')">'+
                                  '</td>'+
                                  '</tr>'
                              );

                          })

                      },
                      error:function(){

                          alert("查询出错！");

                      }

                  });

              },
              error:function () {

                  alert("删除失败！");

              }

          });

        }

    </script>

</head>

<%--<body>

<h1>Test Page</h1>

</body>--%>

<body class="easyui-layout">

<div data-options="region:'north',iconCls:'icon-man',title:'登录信息',split:true" style="height:130px;">

        <div style="width:30%;height:90px;float: left">

            <br>
        <label>&nbsp;&nbsp;用户：</label>
        <br>
        <label id="loginusername">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;【 ${username} 】&nbsp;&nbsp;</label>
            <label>身份：【 ${identity} 】&nbsp;&nbsp;</label>
            <label><a href="exitlogin">退出</a></label>
        <br>
        <label>&nbsp;&nbsp;欢迎你！</label>
        </div>

        <div class="clock" style="background-color:#d5d5d5;float: left">
            <div class="digit hours">
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
            </div>
            <div class="digit hours">
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
            </div>
            <div class="separator"></div>
            <div class="digit minutes">
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
            </div>
            <div class="digit minutes">
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
            </div>
            <div class="separator"></div>
            <div class="digit seconds">
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
            </div>
            <div class="digit seconds">
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
                <div class="segment"></div>
            </div>
        </div>

</div>

<div data-options="region:'south',iconCls:'icon-help',title:'友情提示',split:true" style="height:115px;">
    <div data-options="fit" style="text-align: center">
        <p>14软件外包服务</p>
        <p>董江涛</p>
    </div>
</div>

<div data-options="region:'east',iconCls:'icon-tip',title:'标语栏',split:true" style="width:200px">
    <div data-options="fit:true">
        <marquee behavior="scroll" direction="up" scrollamount="5" scrolldelay="100" loop="-1">
        <img src="${pageContext.request.contextPath}/images/xuexi.jpg" width="100%">
        <img src="${pageContext.request.contextPath}/images/gongying.jpg" width="100%" style="margin-top: 50px">
        <img src="${pageContext.request.contextPath}/images/jiazhi.jpg" width="100%" style="margin-top: 50px">
        </marquee>
    </div>
</div>

<div data-options="region:'west',iconCls:'icon-large-smartart',title:'菜单栏',split:true" style="width:200px;">

    <div id="aa" class="easyui-accordion" style="width:300px;height:200px" data-options="fit:true">
        <%--<div title="新用户" data-options="iconCls:'icon-redo'" style="overflow:auto;padding:10px;">
            <a id="btn1" href="###" class="easyui-linkbutton" data-options="iconCls:'icon-pencil'" onclick="yonghuzhuce()">用户注册</a>
        </div>--%>
        <div title="班级管理" data-options="iconCls:'icon-redo'" style="overflow:auto;padding:10px;">
            <a id="btn" href="###" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="studentinfo()">学生信息查询</a>
            <a id="btn1" href="###" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="studentcountinfo()" style="margin-top: 10px">学生成绩查询</a>
            <a id="btncountimport" href="###" class="easyui-linkbutton" data-options="iconCls:'icon-pencil'" onclick="studentcountimport()" style="margin-top: 10px">学生成绩录入</a>
            <a id="btntubiao" href="###" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="tubiaohuizong()" style="margin-top: 10px">成绩图表汇总</a>
        </div>
        <div title="密码管理" data-options="iconCls:'icon-redo',selected:false" style="padding:10px;">
            <a id="btn2" href="###" class="easyui-linkbutton" data-options="iconCls:'icon-pencil'" onclick="mimaxiugai()">密码修改</a>
        </div>
        <%--<div title="用户管理" data-options="iconCls:'icon-redo',selected:false" style="padding:10px;">
            <a id="btn3" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="chakanyonghu()">查看用户</a>
        </div>--%>
    </div>

</div>

<div data-options="region:'center',iconCls:'icon-large-chart',title:'信息管理'" style="padding:5px;background-image: url(${pageContext.request.contextPath}/images/beijingtupian2.jpg);background-size: 100%" id="xinxiguanli">

    <div id="cityshuju" align="center" style="margin-top: 2%">
        <h2 style="color: #9e9737">天气情况</h2>
        <br>
        所在城市：
        <input id="cc" name="cityname" value="请选择">
        <table id="weathertable" style="width: 90%;margin-top: 4%;color: black;text-align: center" border="1"></table>
    </div>

    <div id="tt">

       <%-- <table id="dg">

        </table>--%>

    </div>

</div>

</body>

</html>

<%@ page import="cn.poaaapa.login.LoginAction" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.poaaapa.TaskEdit.TaskEntity" %>
<%@ page import="cn.poaaapa.TaskEdit.TaskAction" %><%--
  Created by IntelliJ IDEA.
  User: WYZ
  Date: 2018/4/5
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <title>后台管理系统-HTML5后台管理系统</title>
  <meta name="keywords"  content="设置关键词..." />
  <meta name="description" content="设置描述..." />
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
  <link rel="icon" href="main/images/icon/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" type="text/css" href="main/css/style.css" />
  <script src="main/javascript/jquery.js"></script>
  <script src="main/javascript/plug-ins/customScrollbar.min.js"></script>
  <script src="main/javascript/plug-ins/layerUi/layer.js"></script>
  <script src="main/javascript/plug-ins/pagination.js"></script>
  <script src="main/javascript/public.js"></script>
</head>
<body>
<%
    String username = (String)session.getAttribute("username");
    String nickname= LoginAction.getNameByUser(username);
    int userid = LoginAction.getIdByName(username);
    TaskAction ta =new TaskAction();
    List<TaskEntity> list = ta.queryAll4User(userid);
    String taskStates[] ={"未开始","进行中","已完成"};
%>
<div class="main-wrap">
  <div class="content-wrap">
    <header class="top-hd">
      <div class="hd-rt">
        <ul>
          <li>
            <a><i class="icon-user"></i><em><%=nickname %></em></a>
          </li>
          <li>
            <a href="javascript:void(0)" id="SignOut"><i class="icon-signout"></i>安全退出</a>
          </li>
        </ul>
      </div>
    </header>
    <main class="main-cont content mCustomScrollbar _mCS_2 mCS-autoHide switchMenu">
      <!--开始::内容-->
      <div class="page-wrap">

        <section class="page-hd">
          <header>
            <button class="btn btn-danger radius-rounded" id="newTask">新建任务</button>
              <%--<button id="a7" class="btn btn-info">iframe弹窗</button>--%>
          </header>
          <hr>

        </section>

        <table class="table table-bordered  mb-15">
          <thead>
          <tr>
            <th>任务编号</th>
            <th>任务名称</th>
            <th>任务地址</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>

          <%
              for(TaskEntity task:list){
          %>
          <tr class="cen">
            <td><%=task.getId()%></td>
            <td><%=task.getTaskName()%></td>
            <td><%=task.getUrl()%></td>
            <td><button class="btn btn-danger radius-rounded"><%=taskStates[task.getTaskState()]%></button></td>
            <td>
                <a href="javascript:void(0)" title="开始" class="mr-5" id="start">开始</a>
                <a href="javascript:void(0)" title="编辑" class="mr-5" id="edit">编辑</a>
                <a href="javascript:void(0)" title="删除" id="delete">删除</a>
            </td>
          </tr>
          <%
              }
          %>
          </tbody>
        </table>

      </div>

      <!--开始::结束-->
    </main>
  </div>
</div>
<script>
    $('#newTask').click(function(){
        layer.open({
            type: 2,
            title: '新建任务',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['800px', '500px'],
            content: 'newTask.jsp'
        });
    });
    //编辑
    $('#edit').click(function(){
        layer.open({
            type: 2,
            title: '编辑任务',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['800px', '500px'],
            content: 'editTask.jsp'
        });
    });
    //删除
    $('#delete').click(function(){
        layer.open({
            content: '是否删除？'
            ,btn: ['确定', '取消']
            ,yes: function(index, layero){
                console.log("点击了确定");
            },no: function(index, layero){
                //按钮【按钮二】的回调
            }
            ,cancel: function(){
                //右上角关闭回调
            }
        });
    });
    //安全退出
    $('#SignOut').click(function(){
        layer.confirm('确定登出管理中心？', {
                   title:'系统提示',
                   btn: ['确定','取消']
               }, function(){
            $.post('login.go?method=logout',function (retData) {
                if (retData == 'success') {
                    location.href = 'login.jsp';
                } else {
                    layer.msg("失败");
                }
            });
        });
    });
    function testSession() {
        <% String name=(String)session.getAttribute("username");
            if(name==null){
            response.sendRedirect("login.jsp?error=1200");
        } %>
    }
</script>
</body>
</html>

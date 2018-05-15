<%@ page import="cn.poaaapa.TaskEdit.TaskEntity" %><%--
  Created by IntelliJ IDEA.
  User: WYZ
  Date: 2018/4/10
  Time: 21:18
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
    <link rel="icon" href="images/icon/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="main/css/style.css" />
    <script src="main/javascript/jquery.js"></script>
    <script src="main/javascript/plug-ins/customScrollbar.min.js"></script>
    <script src="main/javascript/plug-ins/echarts.min.js"></script>
    <script src="main/javascript/plug-ins/layerUi/layer.js"></script>
    <script src="main/javascript/plug-ins/pagination.js"></script>
    <script src="main/javascript/public.js"></script>
</head>
<body>
<div class="main-wrap">
<%
    TaskEntity task = (TaskEntity) request.getAttribute("task");
    int taskType = task.getTaskType();
    int urlType = task.getUrlType();
%>
    <div class="page-wrap">
        <form action="/poaaapa/taskEdit.go?method=edit&id=<%=task.getId()%>" name="task_form" id="task_form" class="fh5co-form animate-box" data-animate-effect="fadeIn" method="post">

            <div class="form-group-col-2">
                <div class="form-label">公有/私有：</div>
                <div class="form-cont">
                    <select name="taskType" id="taskType" style="width:auto;" >
                        <option value="1">公有</option>
                        <option value="2">私有</option>
                    </select>
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">任务名称：</div>
                <div class="form-cont">
                    <input type="text" name="taskName" value="<%=task.getTaskName()%>" class="form-control form-boxed">
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">任务url：</div>
                <div class="form-cont">
                    <input type="text" name="taskUrl" value="<%=task.getUrl()%>" class="form-control form-boxed" style="width:300px;">
                    <%--<button class="btn btn-secondary-outline">测试</button>--%>
                    <%--<span class="word-aux"><i class="icon-warning-sign"></i>清正确输入11位手机号码</span>--%>
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">任务类别：</div>
                <div class="form-cont">
                    <select id="urlType" name="urlType" style="width:auto;" onchange="onSelect(this)">
                        <option value="0">自定义</option>
                        <option value="1">淘宝</option>
                        <option value="2">天猫</option>
                        <option value="3">知乎</option>
                        <option value="4">百度</option>
                    </select>
                </div>
            </div>
            <div  class="form-group-col-2" >
                <div class="form-label">任务规则：</div>
                <div class="form-cont">
                    <textarea id="urlRule" name="taskRule" class="form-control form-boxed"><%=task.getUrlRule()%></textarea>
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label">备注：</div>
                <div class="form-cont">
                    <textarea name="comment" class="form-control form-boxed"><%=task.getComment()%></textarea>
                </div>
            </div>
            <div class="form-group-col-2">
                <div class="form-label"></div>
                <div class="form-cont">
                    <%--<input type="button" class="btn btn-primary" value="提交任务" onclick="submit()">
                    <%--<input type="reset" class="btn btn-disabled" value="禁止">--%>
                    <button class="btn btn-primary" name="submit" id="submit">提交任务</button>
                </div>
            </div>
            <!--开始::结束-->

        </form>
        <!--开始::结束-->
    </div>

</div>



</div>
    <script>
        document.getElementById("taskType").value=<%=taskType%>;
        document.getElementById("urlType").value=<%=urlType%>;
        function onSelect(select) {
            if(select.options.selectedIndex==0){
                document.getElementById("urlRule").readOnly=false;
                document.getElementById("comment").readOnly=true;
            }else{
                document.getElementById("urlRule").readOnly=true;
                document.getElementById("comment").readOnly=false;

            }

        }
        <% String name=(String)session.getAttribute("username");
        if(name==null){ response.sendRedirect("login.jsp?error=1200");
        } %>

        var strreturn ="<%=request.getParameter("return")%>";
        if(strreturn =="null"){

        }
        else if("true"==strreturn.toString())
        {
            layer.alert("修改成功！",function () {
                window.parent.location.reload();
                parent.layer.close(index);
            });
        }
        else if("empty"==strreturn)
        {
            layer.msg("修改失败！任务名称及url不能为空");
        }else if("rule"==strreturn)
        {
            layer.msg("修改失败！请输入爬取规则");
        }else if("running"==strreturn)
        {
            layer.msg("修改失败！请先结束该任务");
        }
        else{
            layer.msg("添加失败！未知错误");
        }
    </script>
</body>
</html>

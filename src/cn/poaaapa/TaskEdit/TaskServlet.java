package cn.poaaapa.TaskEdit;



import cn.poaaapa.login.LoginAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "TaskServlet")
public class TaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        String username  ="";
        if(session==null || session.getAttribute("username")==null){
            response.sendRedirect("login.go?error=1200");
            return ;
        }else{
            username = (String) session.getAttribute("username");
        }
        String method =request.getParameter("method");
        TaskAction taskAction = new TaskAction();
        if("new".equals(method)){
            String urlType=request.getParameter("urlType");
            String urlRule =request.getParameter("taskRule");
            if(request.getParameter("taskName")=="" ||request.getParameter("taskUrl")==""){
               response.sendRedirect("newTask.jsp?return=empty");
               return;
            }else if("0".equals(request.getParameter("urlType")) &&  "自定义任务必填...".equals(request.getParameter("taskRule"))){
                response.sendRedirect("newTask.jsp?return=rule");
                return;
            }
            TaskEntity task = new TaskEntity();
            task.setTaskName(request.getParameter("taskName"));
            task.setTaskState(0);   //状态默认未开始
            task.setTaskType(Integer.valueOf( request.getParameter("taskType")));
            task.setUrl(request.getParameter("taskUrl"));
            task.setUrlType(Integer.valueOf(request.getParameter("urlType")));
            task.setComment(request.getParameter("comment"));
            task.setCreateTime(new Date(System.currentTimeMillis()));
            task.setUserId(LoginAction.getIdByName(username));
            boolean result = taskAction.newTask(task);
            response.sendRedirect("newTask.jsp?return="+result);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

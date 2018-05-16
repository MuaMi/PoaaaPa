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
            String checkResult =taskCheck(request,-1);
            if("empty".equals(checkResult)){
               response.sendRedirect("newTask.jsp?return=empty");
               return;
            } else if("rule".equals(checkResult)){
                response.sendRedirect("newTask.jsp?return=rule");
                return;
            }
            TaskEntity task = request2Task(request,username);
            boolean result = taskAction.newTask(task);
            response.sendRedirect("newTask.jsp?return="+result);
            return;
        }else if("getTask".equals(method)){
            int id=Integer.valueOf( request.getParameter("id"));
            TaskEntity task =taskAction.queryTask(id);
            request.setAttribute("task",task);
            request.getRequestDispatcher("/editTask.jsp").forward(request,response);
            return;
        }else if("edit".equals(method)){
            TaskEntity task = request2Task(request,username);
            int id = Integer.valueOf(request.getParameter("id"));
            task.setId(id);
            String checkResult;
            checkResult = taskCheck(request,id);
            if("empty".equals(checkResult) || "rule".equals(checkResult) || "running".equals(checkResult) ){

            }else {
                boolean result = taskAction.updateTask(task);
                checkResult = String.valueOf(result);
            }
            request.setAttribute("task",task);
            request.getRequestDispatcher("/editTask.jsp?return="+checkResult).forward(request,response);
            return;

        }else if("delete".equals(method)){
            int id = Integer.valueOf(request.getParameter("id"));
            TaskAction tsa = new TaskAction();
            boolean result = tsa.deleteTask(id);
            if(result){
                response.getWriter().print("success");
            }else {
                response.getWriter().print("failed");
            }
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


    public String taskCheck(HttpServletRequest request,int id){
        TaskAction ta = new TaskAction();
        if(request.getParameter("taskName")=="" ||request.getParameter("taskUrl")==""){
            return "empty";
        }else if("0".equals(request.getParameter("urlType")) &&  "自定义任务必填...".equals(request.getParameter("taskRule"))){
            return  "rule";
        }else if (id != -1 &&ta.queryTask(id).getTaskState()==1){
            return "running";
        }
        else{
            return "success";
        }

    }

    public TaskEntity request2Task(HttpServletRequest request,String username){
        TaskEntity task = new TaskEntity();
        task.setTaskName(request.getParameter("taskName"));
        task.setTaskState(0);   //状态默认未开始
        task.setTaskType(Integer.valueOf( request.getParameter("taskType")));
        task.setUrl(request.getParameter("taskUrl"));
        int urlType = Integer.valueOf(request.getParameter("urlType"));
        task.setUrlType(urlType);
        task.setUrlRule(request.getParameter("taskRule")); //前端展示 taskRule 即为 数据库中 urlRule
        task.setComment(request.getParameter("comment"));
        task.setCreateTime(new Date(System.currentTimeMillis()));
        task.setUserId(LoginAction.getIdByName(username));
        return task;
    }
}

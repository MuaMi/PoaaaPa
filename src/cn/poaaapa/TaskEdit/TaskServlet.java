package cn.poaaapa.TaskEdit;



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
        if(session==null || session.getAttribute("username")==null){
            response.sendRedirect("login.go?error=1200");
            return ;
        }
        String method =request.getParameter("method");
        TaskAction taskAction = new TaskAction();
        if("new".equals(method)){
            TaskEntity task = new TaskEntity();
            task.setTaskName(request.getParameter("taskName"));
            task.setTaskState(0);   //状态默认未开始
            task.setTaskType(1);    //类型默认为 私有
            task.setUrl(request.getParameter("taskUrl"));
            String test =  request.getParameter("urlType");
            task.setUrlType(Integer.valueOf(request.getParameter("urlType")));
            task.setComment(request.getParameter("comment"));
            task.setCreateTime(new Date(System.currentTimeMillis()));
            taskAction.newTask(task);
            response.sendRedirect("newTask.jsp?return=success");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

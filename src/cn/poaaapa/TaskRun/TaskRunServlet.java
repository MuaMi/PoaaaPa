package cn.poaaapa.TaskRun;

import cn.poaaapa.TaskEdit.TaskAction;
import cn.poaaapa.TaskEdit.TaskEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TaskRunServlet")
public class TaskRunServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TaskRunAction tra =new TaskRunAction();
        int id = Integer.valueOf(request.getParameter("id"));
        TaskAction tsa = new TaskAction();
        TaskEntity task = tsa.queryTask(id);
        if(task.getTaskState()==0){
            if (tra.taskRun(task)){
                response.getWriter().print("success");
            }
            else {
                response.getWriter().print("failed");
            }
        }else if(task.getTaskState()==1){
            if (tra.taskStop(task)){
                response.getWriter().print("success");
            }else {
                response.getWriter().print("failed");
            }
        }else if(task.getTaskState()==2){
            tra.xlsDownload(id);
            response.getWriter().print("success");
        }

        return;


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}

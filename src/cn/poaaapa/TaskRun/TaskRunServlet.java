package cn.poaaapa.TaskRun;

import cn.poaaapa.TaskEdit.TaskAction;
import cn.poaaapa.TaskEdit.TaskEntity;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@WebServlet(name = "TaskRunServlet")
public class TaskRunServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");
        TaskRunAction tra =new TaskRunAction();
        int id = Integer.valueOf(request.getParameter("id"));
        TaskAction tsa = new TaskAction();
        TaskEntity task = tsa.queryTask(id);
        if(method!=null && "run".equals(method)){
            try {
                tra.taskRun(task);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(method!=null && "start".equals(method)){
            if(task.getTaskState()==0 || task.getTaskState()==2){
                try {
                    if (tra.taskRunDB(task)){
                        response.getWriter().print("start");
                        //tra.taskRun(task);
                    }
                    else {
                        response.getWriter().print("failed");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(task.getTaskState()==1){
                try {
                    if (tra.taskStop(task)){
                        response.getWriter().print("success");
                    }else {
                        response.getWriter().print("failed");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if(method!=null && "download".equals(method)){
            response.setCharacterEncoding("UTF-8");
            String docUrl = tra.xlsDownload(id);
//            if(task.getTaskState()==2){
//                response.sendRedirect("/doc/"+docUrl);
//            }
            File dic =new File(".");
            String path = dic.getCanonicalPath();
            docUrl =path+"/doc/"+docUrl;
            File f =new File(docUrl);

            response.setHeader("content-disposition", "attachment;filename="+f.getName());

            InputStream in = null ;
            OutputStream out = null ;
            try
            {
                in = new FileInputStream(docUrl); //获取文件的流
                int len = 0;
                byte buf[] = new byte[1024];//缓存作用
                out = response.getOutputStream();//输出流
                while( (len = in.read(buf)) > 0 ) //切忌这后面不能加 分号 ”;“
                {
                    out.write(buf, 0, len);//向客户端输出，实际是把数据存放在response中，然后web服务器再去response中读取
                }
            }finally
            {
                if(in!=null)
                {
                    try{
                        in.close();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }

                if(out!=null)
                {
                    try{
                        out.close();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }

        }

        return;


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}

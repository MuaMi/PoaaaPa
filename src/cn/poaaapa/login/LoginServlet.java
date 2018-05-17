package cn.poaaapa.login;

import cn.poaaapa.crawler.SpiderImgs;
import cn.poaaapa.login.LoginAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        {
            String method = request.getParameter("method");
            if("login".equals(method)){
                String username= request.getParameter("username");
                String password= request.getParameter("password");
                String result = null;
                try {
                    result = LoginAction.Login(username,password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if("success".equals(result)){
                    System.out.println("登陆成功");
                    request.getSession().setAttribute("username",username);

//                    System.out.println("开始扒取");
//                    //
//                    try {
//
//                        LoginAction.GetResult("http://www.tooopen.com/img/87.aspx");
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                    response.sendRedirect("index.jsp");

                    return ;
                }else {
                    System.out.println("错误，登陆失败");
                    response.sendRedirect("login.jsp?error=501");
                }
            }else if("regist".equals(method)){
                UserEntity user = new UserEntity();
                user.setUser( request.getParameter("username"));
                user.setPassword(request.getParameter("password"));
                user.setName( request.getParameter("nickname"));
                user.setCreateTime(new Date(System.currentTimeMillis()));
                String result = null;
                result = LoginAction.regist(user);
                if("duplicated".equals(result) || "failed".equals(result)){
                    System.out.println("注册失败");
                    response.sendRedirect("register.jsp?error=502");
                    return;
                }else {
                    System.out.println("注册成功，请登录");
                    response.sendRedirect("login.jsp");
                }
            }else if("edit".equals(method)){

            }else if("logout".equals(method)){
                request.getSession().removeAttribute("username");
                response.getWriter().print("success");
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

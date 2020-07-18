package service.user;

import dao.IUserDao;
import dao.implement.ImplUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignIn", urlPatterns = "/SignIn")
public class SignIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        PrintWriter out = response.getWriter();
        IUserDao iUserDao = ImplUserDao.iUserDaoIDaoFactory.returnIFactoryDAO();
        if(iUserDao.login(userName,password)){
            out.println("登录成功");
            Cookie cookie1 =new Cookie("userName",userName);
            cookie1.setMaxAge(60*60);
            response.addCookie(cookie1);
            Cookie cookie2;
            if (iUserDao.checkVip(userName)){
                cookie2 =new Cookie("Vip","vip");
            }else {
                cookie2 =new Cookie("Vip","");
            }
            cookie2.setMaxAge(60*60);
            response.addCookie(cookie2);
            String address = null;
            address=iUserDao.getUserAddress(userName);//填入地址cookie
            Cookie cookie3 =new Cookie("address",address);
            cookie3.setMaxAge(60*60);
            response.addCookie(cookie3);
            String tmp = "1;URL=http://localhost:8080/shop/index.jsp";
            response.setHeader("refresh", tmp);
        }
        else{
            out.println("登陆失败");
            String tmp = "1;URL=http://localhost:8080/shop/CheckUser/index.html";
            response.setHeader("refresh", tmp);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

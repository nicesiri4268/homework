package service.admin;

import dao.IAdminDao;
import dao.implement.ImplAdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminSignIn", urlPatterns = "/AdminSignIn")
public class AdminSignIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String adminName=request.getParameter("adminName");
        String adminPassword=request.getParameter("password");
        PrintWriter out = response.getWriter();
        IAdminDao adminDao = ImplAdminDao.iAdminDaoFactory.returnIFactoryDAO();
        if(adminDao.signIn(adminName,adminPassword)){
            out.println("管理员登录成功");
            int adminJobIDobID =adminDao.getAdminJobID(adminName);
            Cookie cookie1 =new Cookie("adminName",adminName);
            Cookie cookie2 = new Cookie("adminJobID",""+adminJobIDobID);
            cookie1.setMaxAge(60*60);
            cookie2.setMaxAge(60*60);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            String tmp = "1;URL=http://localhost:8080/shop/manage/manageIndex.jsp";
            response.setHeader("refresh", tmp);
        }else{
            out.println("管理员登录失败");
            String tmp = "1;URL=http://localhost:8080/shop/adminUser/index.html";
            response.setHeader("refresh", tmp);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

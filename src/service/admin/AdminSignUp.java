package service.admin;

import dao.IAdminDao;
import dao.bean.AdminBean;
import dao.implement.ImplAdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminSignUp", urlPatterns = "/AdminSignUp")
public class AdminSignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String adminName=request.getParameter("adminName");
        String adminPassword =request.getParameter("adminPassword");
        int jobNumber = Integer.parseInt(request.getParameter("jobNumber"));
        int adminLevel= Integer.parseInt(request.getParameter("adminLevel"));
        PrintWriter out = response.getWriter();
        AdminBean adminBean = new AdminBean(jobNumber,adminLevel,adminName, adminPassword,1);
        IAdminDao iAdminDao = ImplAdminDao.iAdminDaoFactory.returnIFactoryDAO();
        if (iAdminDao.signUp(adminBean)){
            out.println("注册成功");
            String tmp = "1;URL=http://localhost:8080/shop/adminUser/index.html";
            response.setHeader("refresh", tmp);
        }else{
            out.println("注册失败");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

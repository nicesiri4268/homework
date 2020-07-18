package service.manage;

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

@WebServlet(name = "AdminManage", urlPatterns = "/AdminManage")
public class AdminManage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        IAdminDao iAdminDao = ImplAdminDao.iAdminDaoFactory.returnIFactoryDAO();
        int adminID = Integer.parseInt(request.getParameter("adminID"));
        if (iAdminDao.deleteAdmin(adminID)){
            out.println("删除成功");
            String tmp = "2;URL=http://localhost:8080/shop/manage/manageIndex.jsp";
            response.setHeader("refresh", tmp);
        }else {
            out.println("删除失败");
            String tmp = "2;URL=http://localhost:8080/shop/manage/manageIndex.jsp";
            response.setHeader("refresh", tmp);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

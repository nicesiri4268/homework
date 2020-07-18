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

@WebServlet(name = "RechargeVip", urlPatterns = "/RechargeVip")
public class RechargeVip extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String userName=request.getParameter("userName");
        PrintWriter out = response.getWriter();
        IUserDao iUserDao = ImplUserDao.iUserDaoFactory.returnIFactoryDAO();
        if(iUserDao.payVip(userName)){
            out.println("充值成功");
            Cookie cookie1 =new Cookie("userName",userName);
            cookie1.setMaxAge(60*60);
            response.addCookie(cookie1);
            String tmp = "2;URL=http://localhost:8080/shop/index.jsp";
            response.setHeader("refresh", tmp);
        }
    }
}

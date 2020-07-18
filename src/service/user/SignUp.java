package service.user;

import dao.IUserDao;
import dao.bean.UserBean;
import dao.implement.ImplUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignUp", urlPatterns = "/SignUp")
public class SignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        String sex = request.getParameter("userSex");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        UserBean userBean = new UserBean(userName,password,sex,address,email);
        PrintWriter out = response.getWriter();
        IUserDao iUserDao = ImplUserDao.iUserDaoIDaoFactory.returnIFactoryDAO();
        if (iUserDao.signUp(userBean)){
            out.println("注册成功");
            String tmp = "1;URL=http://localhost:8080/shop/index.jsp";
            response.setHeader("refresh", tmp);
        }else{
            out.println("注册失败");
            String tmp = "2;URL=http://localhost:8080/shop/index.jsp";
            response.setHeader("refresh", tmp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

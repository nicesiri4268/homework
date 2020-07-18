package service.manage;

import dao.IUserDao;
import dao.bean.UserBean;
import dao.implement.ImplUserDao;

import javax.lang.model.element.ModuleElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserChange", urlPatterns = "/UserChange")
public class UserChange extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        IUserDao iUserDao = ImplUserDao.iUserDaoIDaoFactory.returnIFactoryDAO();
        PrintWriter out = response.getWriter();
        int userID = Integer.parseInt(request.getParameter("userID"));
        String userName=request.getParameter("userName");
        String password=request.getParameter("userPassword");
        String sex = request.getParameter("userSex");
        String address = request.getParameter("userAddress");
        String email = request.getParameter("userEmail");
        int userIsVip = Integer.parseInt(request.getParameter("userIsVip"));
        int userState = Integer.parseInt(request.getParameter("userState"));
        UserBean userBean = new UserBean(userName,password,sex,address,email,userIsVip,userState);
        if(iUserDao.updateUserAll(userBean,userID)){
            out.println("修改成功");
            String tmp = "2;URL=http://localhost:8080/shop/manage/User/userIndex.jsp";
            response.setHeader("refresh", tmp);
        }else{
            out.println("修改失败");
            String tmp = "2;URL=http://localhost:8080/shop/manage/User/userIndex.jsp";
            response.setHeader("refresh", tmp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

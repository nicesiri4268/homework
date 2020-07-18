package service.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = "/Logout")
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[]cookies = request.getCookies();
        for (Cookie cookie :cookies){
            if (cookie.getName().equals("userName")||cookie.getName().equals("Vip")||cookie.getName().equals("address")){
                cookie.setValue(null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        String tmp = "0;URL=http://localhost:8080/shop/index.jsp";
        response.setHeader("refresh", tmp);
    }
}

package service.manage;

import dao.IOrderDao;
import dao.IOrderParticularsDao;
import dao.IUserDao;
import dao.bean.OrderBean;
import dao.bean.OrderParticularsBean;
import dao.implement.ImplOrderDao;
import dao.implement.ImplOrderParticularsDao;
import dao.implement.ImplUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "OrderManage", urlPatterns = "/OrderManage")
public class OrderManage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        IOrderDao<OrderBean> iOrderDao = ImplOrderDao.iDaoFactory.returnIFactoryDAO();
        IOrderParticularsDao<OrderParticularsBean> iOrderParticularsDao = ImplOrderParticularsDao.iDaoFactory.returnIFactoryDAO();
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        if (iOrderParticularsDao.deleteOrderParticulars(orderID)>0){
            if (iOrderDao.deleteOrder(orderID)){
                out.println("删除成功");
                String tmp = "2;URL=http://localhost:8080/shop/manage/order/orderIndex.jsp";
                response.setHeader("refresh", tmp);
            }else{
                out.println("删除失败");
                String tmp = "2;URL=http://localhost:8080/shop/manage/order/orderIndex.jsp";
                response.setHeader("refresh", tmp);
            }
        }else {
            out.println("删除失败");
            String tmp = "2;URL=http://localhost:8080/shop/manage/order/orderIndex.jsp";
            response.setHeader("refresh", tmp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

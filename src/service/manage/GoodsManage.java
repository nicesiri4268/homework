package service.manage;

import dao.IAdminDao;
import dao.IGoodsDao;
import dao.bean.GoodsBean;
import dao.implement.ImplAdminDao;
import dao.implement.ImplGoodsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GoodsManage", urlPatterns = "/GoodsManage")
public class GoodsManage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        IGoodsDao<GoodsBean> iGoodsDao = ImplGoodsDao.iGoodsDaoFactory.returnIFactoryDAO();
        int goodsID = Integer.parseInt(request.getParameter("goodsID"));
        String goodsName = request.getParameter("goodsName");
        if (iGoodsDao.deleteGoods(goodsID,goodsName)){
            out.println("删除成功");
            String tmp = "2;URL=http://localhost:8080/shop/manage/goods/goodsIndex.jsp";
            response.setHeader("refresh", tmp);
        }else {
            out.println("删除失败");
            out.println("请检查是否有订单购买了该商品。。。");
            String tmp = "2;URL=http://localhost:8080/shop/manage/goods/goodsIndex.jsp";
            response.setHeader("refresh", tmp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

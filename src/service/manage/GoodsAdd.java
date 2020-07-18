package service.manage;

import dao.IGoodsDao;
import dao.bean.GoodsBean;
import dao.implement.ImplGoodsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GoodsAdd", urlPatterns = "/GoodsAdd")
public class GoodsAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        IGoodsDao<GoodsBean> iGoodsDao = ImplGoodsDao.iGoodsDaoFactory.returnIFactoryDAO();
        String goodsName = request.getParameter("goodsName");
        String goodsDescription = request.getParameter("goodsDescription");
        double goodsPrice = Double.parseDouble(request.getParameter("goodsPrice"));
        double goodsVipPrice = Double.parseDouble(request.getParameter("goodsVipPrice"));
        GoodsBean goodsBean = new GoodsBean(goodsName,goodsDescription,goodsPrice,goodsVipPrice,1);
        if (iGoodsDao.addGoods(goodsBean)){
            out.println("添加成功");
            String tmp = "2;URL=http://localhost:8080/shop/manage/goods/goodsIndex.jsp";
            response.setHeader("refresh", tmp);
        }else{
            out.println("添加失败");
            String tmp = "2;URL=http://localhost:8080/shop/manage/goods/goodsIndex.jsp";
            response.setHeader("refresh", tmp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

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

@WebServlet(name = "GoodsChange", urlPatterns = "/GoodsChange")
public class GoodsChange extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        IGoodsDao<GoodsBean> iGoodsDao = ImplGoodsDao.iGoodsDaoFactory.returnIFactoryDAO();
        PrintWriter out = response.getWriter();
        int goodsID = Integer.parseInt(request.getParameter("goodsID"));
        String goodsName=request.getParameter("goodsName");
        String goodsDescription=request.getParameter("goodsDescription");
        double goodsPrice = Double.parseDouble(request.getParameter("goodsPrice"));
        double goodsVipPrice = Double.parseDouble(request.getParameter("goodsVipPrice"));
        int goodsType = Integer.parseInt(request.getParameter("goodsType"));
        GoodsBean goodsBean = new GoodsBean(goodsName,goodsDescription,goodsPrice,goodsVipPrice, goodsType);
        if(iGoodsDao.resetGoodsMsgs(goodsID,goodsBean)){
            out.println("修改成功");
            String tmp = "2;URL=http://localhost:8080/shop/manage/goods/goodsIndex.jsp";
            response.setHeader("refresh", tmp);
        }else{
            out.println("修改失败");
            String tmp = "2;URL=http://localhost:8080/shop/manage/goods/goodsIndex.jsp";
            response.setHeader("refresh", tmp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package service.user;

import dao.IGoodsDao;
import dao.IShoppingCartDao;
import dao.IUserDao;
import dao.bean.GoodsBean;
import dao.bean.ShoppingCartBean;
import dao.implement.ImplGoodsDao;
import dao.implement.ImplShoppingCartDao;
import dao.implement.ImplUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ShoppingAdd", urlPatterns = "/ShoppingAdd")
public class ShoppingAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean flag=false,doFlag = false;//是否存在购物车flag,doFlag为操作成功标志
        int isVip=0;double goodsPrice=0;
        int userID = Integer.parseInt(request.getParameter("userID"));
        int goodsID= Integer.parseInt(request.getParameter("goodsID"));
        int goodsCount =Integer.parseInt(request.getParameter("goodsCount"));
        IGoodsDao<GoodsBean> iGoodsDao = ImplGoodsDao.iGoodsDaoFactory.returnIFactoryDAO();//商品表控制组件
        IShoppingCartDao<ShoppingCartBean> iShoppingCartDao = ImplShoppingCartDao.iDaoFactory.returnIFactoryDAO();//购物车控制组件
        ShoppingCartBean shoppingCartBean=iShoppingCartDao.getOneUserGoods(userID,goodsID);//获取数据库中的某一用户某一商品信息
        if (shoppingCartBean!=null){
            //如果购物车不为空的话直接修改goodsCount，同时执行修改操作，而不是新建
            flag=true;
            goodsCount +=shoppingCartBean.getGoodsCount();
        }else {
            goodsCount = Integer.parseInt(request.getParameter("goodsCount"));
        }
        if (request.getParameter("isVip").equals("vip")){//判断是否是Vip
            isVip = 1;
            goodsPrice = iGoodsDao.getOneGoodsMsgs(goodsID).getGoodsVipPrice();
        }else {
            goodsPrice=iGoodsDao.getOneGoodsMsgs(goodsID).getGoodsPrice();
        }
        if (!flag){//判断是否已有购物车
            ShoppingCartBean shoppingCartBean2 = new ShoppingCartBean(userID,isVip,goodsID,goodsCount,goodsPrice);
            if (iShoppingCartDao.addShoppingCart(shoppingCartBean2))
                doFlag=true;//插入成功
        }else{
            if( iShoppingCartDao.resetShoppingCount(userID,goodsID,goodsCount))
                doFlag=true;
        }
        if(doFlag){
            out.println("购物车添加成功");
            String tmp = "2;URL=http://localhost:8080/shop/index.jsp";
            response.setHeader("refresh", tmp);
        }else{
            out.println("购物车添加失败");
            String tmp = "2;URL=http://localhost:8080/shop/index.jsp";
            response.setHeader("refresh", tmp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

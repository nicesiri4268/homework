package service.user;

import dao.IOrderDao;
import dao.IOrderParticularsDao;
import dao.IShoppingCartDao;
import dao.bean.OrderBean;
import dao.bean.OrderParticularsBean;
import dao.bean.ShoppingCartBean;
import dao.implement.ImplOrderDao;
import dao.implement.ImplOrderParticularsDao;
import dao.implement.ImplShoppingCartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AccountShoppingCart", urlPatterns = "/AccountShoppingCart")
public class AccountShoppingCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        IShoppingCartDao<ShoppingCartBean> iShoppingCartDao = ImplShoppingCartDao.iDaoFactory.returnIFactoryDAO();//购物车控制类
        IOrderParticularsDao<OrderParticularsBean> iOrderParticularsDao = ImplOrderParticularsDao.iDaoFactory.returnIFactoryDAO();//订单明细控制器
        IOrderDao<OrderBean> iOrderDao = ImplOrderDao.iDaoFactory.returnIFactoryDAO();//订单控制器
        OrderBean account = null;//order订单包
        double accountPrice=0;//结算价格
        Timestamp time=null;
        List<OrderParticularsBean> orderParticularsBeans = new ArrayList<OrderParticularsBean>();
        boolean flag =false;
        //初始化结算页面
        String[] goodsIDs = request.getParameterValues("account");//获取需要结算的所有商品ID
        int userID = Integer.parseInt(request.getParameter("userID"));//获取用户ID
        String address = request.getParameter("accountAddress");
        if (userID==0){
            out.println("用户未登录");
            String tmp = "2;URL=http://localhost:8080/shop/index.jsp";
            response.setHeader("refresh", tmp);
        }else {
            List<ShoppingCartBean> shoppingCartBeans =new ArrayList<ShoppingCartBean>();
            for (String goodsID:goodsIDs){
                ShoppingCartBean shoppingCartBean = iShoppingCartDao.getOneUserGoods(userID,Integer.parseInt(goodsID));//获取所有订单
                accountPrice +=shoppingCartBean.getGoodsPrice();//计算订单总价
                shoppingCartBeans.add(shoppingCartBean);//构造订单明细列表
                //把所有订单放入shoppingcartBeans里面
            }
            account=new OrderBean(userID,accountPrice,address,3);//构造订单,订单状态为异常3，结算为1
            int orderID=0;
            if(iOrderDao.addOrder(account)){
                //把订单写入数据库
                orderID=iOrderDao.getOrderID(userID,3);
                if (iOrderDao.resetOrderState(userID,orderID,3,1)){//重置订单状态
                    time = iOrderDao.getOrderTime(userID,orderID,1);//获取订单时间
                    for(ShoppingCartBean shoppingCartBean:shoppingCartBeans){//构建订单明细列表
                        orderParticularsBeans.add(new OrderParticularsBean(orderID,shoppingCartBean.getGoodsID(),shoppingCartBean.getGoodsCount(),shoppingCartBean.getGoodsPrice(),time));
                    }
                }
                if(iOrderParticularsDao.addOrderParticulars(orderParticularsBeans)){
                    //把订单明细写入数据库
                    flag=true;
                }
            }
            if (flag){
                //如果创建订单成功
                for (String goodsID:goodsIDs){
                    iShoppingCartDao.resetShoppingCartState(userID,Integer.parseInt(goodsID),1);
                }
                out.println("创建订单成功");
                String tmp = "1;URL=http://localhost:8080/shop/";
                response.setHeader("refresh", tmp);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

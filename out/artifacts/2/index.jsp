<%@ page import="dao.IGoodsDao" %>
<%@ page import="dao.implement.ImplGoodsDao" %>
<%@ page import="dao.bean.GoodsBean" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.IUserDao" %>
<%@ page import="dao.implement.ImplUserDao" %>
<%@ page import="dao.IShoppingCartDao" %>
<%@ page import="dao.bean.ShoppingCartBean" %>
<%@ page import="dao.implement.ImplShoppingCartDao" %><%--
  Created by IntelliJ IDEA.
  User: nice siri
  Date: 2020/7/14
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
  IGoodsDao<GoodsBean> iGoodsDao = ImplGoodsDao.iGoodsDaoFactory.returnIFactoryDAO();
  List<GoodsBean> goodsBeanList=null;
%>
<html>
<head>
  <title>商场主页</title>
  <link rel="stylesheet" type="text/css" href="./manage/CSS/reset.css">
  <style>
    .shop {margin: 0 auto; width: 1200px;height: auto; position: relative;}
    .title ul{margin: 0 auto;width:1000px;height:50px;line-height:50px;border:4px solid#ebcbbe;}
    .title li{padding:0 10px;float:left; list-style: none}
    .account{width: 1000px; height: 20px;position: absolute; left: 100px;top: 50px;}
    .body{width: 1000px;min-height: 800px;position: absolute;left: 100px;top: 90px; background-color: red;}
    .bOne {width: 250px;height: 200px;background-color: yellow; position: relative; float: left;}
    .bOne ul {width: 230px;height: 180px;background-color: blue;position: absolute; left: 10px;top: 10px;}
  </style>
</head>
<body class="shop">
    <div class="title">
  <ul>
    <li>
      <form action="#" method="post">
        <input type="text" name="goods" placeholder="在此处搜索商品">
        <input type="submit" name="search" value="搜索">
      </form>
    </li>
    <li>
      <%!
        IUserDao iUserDao= ImplUserDao.iUserDaoFactory.returnIFactoryDAO();
        IShoppingCartDao<ShoppingCartBean> iShoppingCartDao = ImplShoppingCartDao.iDaoFactory.returnIFactoryDAO();
      %>
      <%
        String who="";
        String user ="未登录";
        String isVip="";
        String userAddress="";
        int userID=0;
        Cookie []cookies =request.getCookies();
          for (Cookie cookie:cookies){
            if (cookie.getName().equals("userName")){
              user=cookie.getValue();
              who="用户";
            }
            if(cookie.getName().equals("Vip"))
              isVip=cookie.getValue();
            if (cookie.getName().equals("adminName")){
              user=cookie.getValue();
              who="管理员";
            }
            if (cookie.getName().equals("address")){
                userAddress =cookie.getValue();
            }
          }
        %>
      <%=who+user+" "+isVip%>
      <%
          if(!user.equals("未登录")&&who.equals("用户")){
            userID =iUserDao.getUserID(user);
            out.println("<a href=\"./Logout\">退出</a>");
          }else if(!user.equals("未登录")){
            out.println("<a href=\"./AdminLogOut\">退出</a>");
          }
        %>
    </li>
    <li><a href="CheckUser/signup.html"> 注册 </a></li>
    <li><a href="CheckUser/index.html">登录</a></li>
    <li><a href="adminUser/index.html">管理员</a></li>
    <li><a href="./RechargeVip?userName=<%=user%>">充值vip</a></li>
    <li><a href="adminUser/index.jsp" target="_blank">购物车</a></li>
    <li><a href="http://localhost:8080/shop/order/index.jsp">订单</a></li>
  </ul>
</div>
    <form action="./AccountShoppingCart" method="post" id="form1">
        <div class="account">
            <input form="form1" type="hidden" name="userID" value="<%=userID%>" form="form1">
            下单地址：<input form="form1" type="text" name="accountAddress" value="<%=userAddress%>">
            <input form="form1" type="submit" value="结算">
        </div>
    <div class="body">
        <%--商品模板--%>
        <%--
        <div class="bOne">
          <ul>
            <li>商品名：</li>
            <li>商品描述：</li>
            <li>商品价格：</li>
            <li>商品Vip价格：</li>
            <li>
              <form action="#">
                数量：<input type="number">
                <input type="submit" value="添加到购物车">
              </form>
            </li>
          </ul>
        </div>
        --%>
        <%
            goodsBeanList =iGoodsDao.allGoods();
            int i=2;
            for (GoodsBean goodsBean : goodsBeanList){
                i++;
                int count =0;
                ShoppingCartBean shoppingCartBean = iShoppingCartDao.getOneUserGoods(userID,goodsBean.getGoodsID());
                if (shoppingCartBean!=null){
                    count =shoppingCartBean.getGoodsCount();
                }
        %>
        <div class="bOne">
            <ul>
                <li>商品名：<%=goodsBean.getGoodsName()%></li>
                <li>商品描述：<%=goodsBean.getGoodsDescription()%></li>
                <li>商品价格：<%=goodsBean.getGoodsPrice()%></li>
                <li>商品Vip价格：<%=goodsBean.getGoodsVipPrice()%></li>
                <li>购物车内数量：<%=count%></li>
                <li>
                    <form action="./ShoppingAdd" method="post" id="form<%=i%>">
                        <input form="form<%=i%>" type="hidden" name="goodsID" value="<%=goodsBean.getGoodsID()%>">
                        <input form="form<%=i%>" type="hidden" name="userID" value="<%=userID%>">
                        <input form="form<%=i%>" type="hidden" name="isVip" value="<%=isVip%>">
                        数量：<input form="form<%=i%>" type="number" name="goodsCount">
                        <input form="form<%=i%>" type="submit" value="添加到购物车">
                    </form>
                    结算：<input form="form1" type="checkbox" name="account" value="<%=goodsBean.getGoodsID()%>">
                </li>
            </ul>
        </div>
        <%
            }
        %>
    </div>
    </form>
</body>
</html>
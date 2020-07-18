<%@ page import="dao.IOrderDao" %>
<%@ page import="dao.bean.OrderBean" %>
<%@ page import="dao.implement.ImplOrderDao" %>
<%@ page import="dao.IUserDao" %>
<%@ page import="dao.implement.ImplUserDao" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.IOrderParticularsDao" %>
<%@ page import="dao.bean.OrderParticularsBean" %>
<%@ page import="dao.implement.ImplOrderParticularsDao" %>
<%@ page import="dao.IGoodsDao" %>
<%@ page import="dao.bean.GoodsBean" %>
<%@ page import="dao.implement.ImplGoodsDao" %><%--
  Created by IntelliJ IDEA.
  User: nice siri
  Date: 2020/7/16
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单信息</title>
    <style>
        .title{width: 1200px;height: 100px;background-color: red;margin: 0 auto;}
        .title ul {width: 1000px;height: 80px;background-color: yellow;position: relative;left: 100px;top: 10px;}
        .title ul li{width: 100px;height: 60px; list-style: none; float: right;line-height: 60px;}
        .title ul li .address{width: 200px;height: 60px;}
    </style>
</head>
<body>
<div class="title">
    <ul>
        <li>用户ID</li>
        <li>用户名</li>
        <li class="address">用户下单地址</li>
    </ul>
</div>
    <%!
        IOrderDao<OrderBean> iOrderDao = ImplOrderDao.iDaoFactory.returnIFactoryDAO();
        IOrderParticularsDao<OrderParticularsBean> iOrderParticularsDao = ImplOrderParticularsDao.iDaoFactory.returnIFactoryDAO();
        IUserDao iUserDao = ImplUserDao.iUserDaoFactory.returnIFactoryDAO();
        IGoodsDao<GoodsBean> iGoodsDao = ImplGoodsDao.iGoodsDaoFactory.returnIFactoryDAO();
    %>
    <%
        List<OrderBean> orderBeans =null;
        List<OrderParticularsBean> orderParticularsBeans=null;
        String user ="未登录";
        int userID=0;
        Cookie []cookies =request.getCookies();
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("userName")){
                user=cookie.getValue();
            }
        }
        userID = iUserDao.getUserID(user);
        orderBeans = iOrderDao.oneOrder(userID);//根据用户ID获取订单信息
    %>
    <%
        for (OrderBean orderBean : orderBeans){
            orderParticularsBeans = iOrderParticularsDao.getOrderParticulars(orderBean.getOrderID());
            for (OrderParticularsBean orderParticularsBean:orderParticularsBeans){
    %>
            <div>
                    <ul>
                        <li>订单ID：<%=orderParticularsBean.getOrderID()%></li>
                        <li>订单商品号：<%=orderParticularsBean.getOrderGoodsID()%></li>
                        <li>商品名称：<%=iGoodsDao.getGoodsName(orderParticularsBean.getOrderGoodsID())%></li>
                        <li>订单数量：<%=orderParticularsBean.getOrderGoodsCount()%></li>
                        <li>订单商品价格：<%=orderParticularsBean.getOrderGoodsPrice()%></li>
                        <li>订单结算时间：<%=orderParticularsBean.getOrderGoodsDate()%></li>
                    </ul>
            </div>
    <%
            }%>
        <div >
            <ul>
                <li>订单ID：<%=orderBean.getOrderID()%></li>
                <li>订单总价：<%=orderBean.getOrderPrice()%></li>
                <li>订单地址：<%=orderBean.getOrderAddress()%></li>
                <li>订单结算时间<%= orderBean.getOrderDate()%></li>
            </ul>
        </div>
    <%

        }
    %>
</body>
</html>

<%@ page import="dao.IGoodsDao" %>
<%@ page import="dao.implement.ImplGoodsDao" %>
<%@ page import="dao.bean.GoodsBean" %><%--
  Created by IntelliJ IDEA.
  User: nice siri
  Date: 2020/7/17
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品修改页面</title>
</head>
<body>
    <%!
        IGoodsDao<GoodsBean> iGoodsDao = ImplGoodsDao.iGoodsDaoFactory.returnIFactoryDAO();
    %>
    <%
        int goodsID = Integer.parseInt(request.getParameter("goodsID"));
        GoodsBean goodsBean =iGoodsDao.getOneGoodsMsgs(goodsID);
    %>

    <form action="../../GoodsChange" method="post">
        <input type="hidden" name="goodsID" value="<%=goodsID%>">
        商品名：<input type="text" name="goodsName" value="<%=goodsBean.getGoodsName()%>"><br>
        商品描述：<input type="text" name="goodsDescription" value="<%=goodsBean.getGoodsDescription()%>"><br>
        正常价格：<input type="text" name="goodsPrice" value="<%=goodsBean.getGoodsPrice()%>"><br>
        Vip价格：<input type="text" name="goodsVipPrice" value="<%=goodsBean.getGoodsVipPrice()%>"><br>
        商品类型：<input type="text" name="goodsType" value="<%=goodsBean.getGoodsTypeID()%>"><br>
        <input type="submit" value="修改">
    </form>
</body>
</html>

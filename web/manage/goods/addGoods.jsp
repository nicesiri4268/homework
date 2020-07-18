<%--
  Created by IntelliJ IDEA.
  User: nice siri
  Date: 2020/7/16
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品添加页</title>
</head>
<body>
<form action="../../GoodsAdd" method="post">
    <input type="text" name="goodsName" placeholder="商品名称">
    <input type="text" name="goodsDescription" placeholder="商品描述">
    <input type="text" name="goodsPrice" placeholder="商品价格">
    <input type="text" name="goodsVipPrice" placeholder="商品Vip价格">
    <input type="submit" value="提交">
</form>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: nice siri
  Date: 2020/7/16
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%--    抛弃计划--%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>购物车</title>
    <link rel="stylesheet" href="./reset.css">
    <style>
        .title{width: 1200px;height: 100px;background-color: red;margin: 0 auto;}
        .title ul {width: 1000px;height: 80px;background-color: yellow;position: relative;left: 100px;top: 10px;}
        .title ul li{width: 100px;height: 60px; list-style: none; float: right;line-height: 60px;}
        .title ul li .address{width: 200px;height: 60px;}
        .body{width: 1200px;min-height: 200px;background-color: blue;margin: 0 auto;}
        .body .BOne{width: 300px;height: 200px;background-color: yellow;float: left;}
        .foot{width: 1200px;height: 100px;background-color: green;margin: 0 auto;}
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
<form action="#">
    <div class="body">

        <div class="BOne">
            <ul>
                <li>购物车ID：</li>
                <li>购物车状态：</li>
                <li>购物车商品名：</li>
                <li>购物车商品数量</li>
                <li>商品价格：（是否为vip价格）</li>
                <li>
                    <input type="checkbox" name="acount" value="goodsID">
                </li>

            </ul>
        </div>
        <div class="BOne">
            <ul>
                <li>购物车ID：</li>
                <li>购物车状态：</li>
                <li>购物车商品名：</li>
                <li>购物车商品数量</li>
                <li>商品价格：（是否为vip价格）</li>
                <li>
                    <input type="checkbox" name="acount" value="goodsID">
                </li>

            </ul>
        </div>
        <div class="BOne">
            <ul>
                <li>购物车ID：</li>
                <li>购物车状态：</li>
                <li>购物车商品名：</li>
                <li>购物车商品数量</li>
                <li>商品价格：（是否为vip价格）</li>
                <li>
                    <input type="checkbox" name="acount" value="goodsID">
                </li>

            </ul>
        </div>
        <div class="BOne">
            <ul>
                <li>购物车ID：</li>
                <li>购物车状态：</li>
                <li>购物车商品名：</li>
                <li>购物车商品数量</li>
                <li>商品价格：（是否为vip价格）</li>
                <li>
                    <input type="checkbox" name="acount" value="goodsID">
                </li>

            </ul>
        </div>
        <div class="BOne">
            <ul>
                <li>购物车ID：</li>
                <li>购物车状态：</li>
                <li>购物车商品名：</li>
                <li>购物车商品数量</li>
                <li>商品价格：（是否为vip价格）</li>
                <li>
                    <input type="checkbox" name="acount" value="goodsID">
                </li>

            </ul>
        </div>
        <div class="BOne">
            <ul>
                <li>购物车ID：</li>
                <li>购物车状态：</li>
                <li>购物车商品名：</li>
                <li>购物车商品数量</li>
                <li>商品价格：（是否为vip价格）</li>
                <li>
                    <input type="checkbox" name="acount" value="goodsID">
                </li>

            </ul>
        </div>

    </div>
    <div class="foot">
        <input type="submit" value="结算">
    </div>
</form>
</body>
</html>

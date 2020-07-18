<%@ page import="dao.baseDao.IControlDao" %>
<%@ page import="dao.IAdminDao" %>
<%@ page import="dao.implement.ImplAdminDao" %>
<%@ page import="dao.IGoodsDao" %>
<%@ page import="dao.bean.GoodsBean" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.implement.ImplGoodsDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CH">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品管理</title>
    <link rel="stylesheet" type="text/css" href="../CSS/reset.css">
    <link rel="stylesheet" type="text/css" href="../CSS/style1.css">
</head>
<body>
    <%--商品管理页面--%>
    <%!
        IGoodsDao<GoodsBean> iGoodsDao = ImplGoodsDao.iGoodsDaoFactory.returnIFactoryDAO();
        List<GoodsBean> goodsBeanList = null;
    %>
    <%
        Cookie []cookies =request.getCookies();
        String who="";
        String admin ="未登录";
        String adminJobID="";
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("userName")){
                admin =cookie.getValue();
                who="用户";
            }
            if(cookie.getName().equals("adminJobID"))
                adminJobID=cookie.getValue();
            if (cookie.getName().equals("adminName")){
                admin =cookie.getValue();
                who="管理员";
            }
        }
        goodsBeanList = iGoodsDao.allGoods();
    %>
    <div class="title">
        <div>
            <ul>
                <li>
                    商品管理页面
                </li>
                <li>管理员工号<%=adminJobID%></li>
                <li>管理员名 <%=admin%><a href="../../AdminLogOut">&nbsp;&nbsp退出</a></li>
                <li><a href="http://localhost:8080/shop/index.jsp">返回主页</a></li>
            </ul>

        </div>
    </div>
    <div class="body">
        <div class="Bleft">
            <div>
                1、商品管理
                <p>
                <ul>
                    <li>
                        <a href="http://localhost:8080/shop/manage/goods/goodsIndex.jsp">商品管理</a>
                    </li>
                </ul>
                </p>

            </div>
            <div>
                2、用户管理
                <p>
                <ul>
                    <li>
                        <a href="http://localhost:8080/shop/manage/User/userIndex.jsp">用户管理</a>
                    </li>
                </ul>
                </p>

            </div>
            <div>
                3、订单管理
                <p>
                <ul>
                    <li>
                        <a href="http://localhost:8080/shop/manage/order/orderIndex.jsp">订单管理</a>
                    </li>
                </ul>
                </p>

            </div>
            <div>
                4、管理员管理
                <p>
                <ul>
                    <li>
                        <a href="http://localhost:8080/shop/manage/manageIndex.jsp">管理员管理</a>
                    </li>
                </ul>
                </p>

            </div>
        </div>
        <div class="Btitle">
            <div>
                <ul>
                    <li>商品ID</li>
                    <li>商品名称</li>
                    <li>商品描述</li>
                    <li>商品价格</li>
                    <li>商品Vip价格</li>
                    <li>商品类型</li>
                </ul>
            </div>
        </div>
        <div class="Bbody">
        <%
            int i=0;
            for (GoodsBean goodsBean:goodsBeanList) {
                i++;
        %>
        <div>
            <ul>
                <li><%= goodsBean.getGoodsID()%></li>
                <li><%= goodsBean.getGoodsName()%></li>
                <li><%= goodsBean.getGoodsDescription()%></li>
                <li><%= goodsBean.getGoodsPrice()%></li>
                <li><%= goodsBean.getGoodsVipPrice()%></li>
                <li><%= goodsBean.getGoodsTypeID()%></li>
                <li>
                    <form action="../../GoodsManage" id="form<%=i%>" method="post">
                        <input form="form<%=i%>" type="hidden" name="goodsID" value="<%=goodsBean.getGoodsID()%>">
                        <input form="form<%=i%>" type="hidden" name="goodsName" value="<%=goodsBean.getGoodsName()%>">
                        <input form="form<%=i%>" type="submit" value="删除">
                        <a href="./goodsChange.jsp?goodsID=<%=goodsBean.getGoodsID()%>">修改商品</a>
                    </form>
                </li>
            </ul>
        </div>
        <%
            }
        %>
            <div>
                <a href="http://localhost:8080/shop/manage/goods/addGoods.jsp">
                    添加商品
                </a>
            </div>
        </div>
    </div>
</body>
</html>
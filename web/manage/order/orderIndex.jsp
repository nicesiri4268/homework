<%@ page import="dao.IAdminDao" %>
<%@ page import="dao.bean.AdminBean" %>
<%@ page import="dao.implement.ImplAdminDao" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.IOrderDao" %>
<%@ page import="dao.bean.OrderBean" %>
<%@ page import="dao.implement.ImplOrderDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CH">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>订单管理</title>
    <link rel="stylesheet" type="text/css" href="../CSS/reset.css">
    <link rel="stylesheet" type="text/css" href="../CSS/style1.css">
</head>
<body>
<%--管理员页面--%>
<%!
    IAdminDao<AdminBean> iAdminDao = ImplAdminDao.iAdminDaoFactory.returnIFactoryDAO();
    List<AdminBean> adminBeanList = null;
    IOrderDao<OrderBean> iOrderDao = ImplOrderDao.iDaoFactory.returnIFactoryDAO();
    List<OrderBean> orderBeanList = null;
%>
<%
    Cookie[] cookies = request.getCookies();
    String who = "";
    String admin = "未登录";
    String adminJobID = "";
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("adminJobID"))
            adminJobID = cookie.getValue();
        if (cookie.getName().equals("adminName")) {
            admin = cookie.getValue();
            who = "管理员";
        }
    }
    adminBeanList = iAdminDao.allAdmin();
    orderBeanList = iOrderDao.allOrder();
%>
<div class="title">
    <div>
        <ul>
            <li>
                <a href="http://localhost:8080/shop/manage/manageIndex.jsp">管理员主页</a>
            </li>
            <li>管理员工号<%=adminJobID%>
            </li>
            <li>管理员名 <%=admin%><a href="../../AdminLogOut">&nbsp;&nbsp;退 出</a></li>
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
            <%--列表名--%>
            <ul>
                <li>订单ID</li>
                <li>订单用户ID</li>
                <li>订单总价</li>
                <li>订单地址</li>
                <li>订单日期</li>
            </ul>
        </div>
    </div>
    <div class="Bbody">
        <%--列表数据模板
        <div>
            <ul>
                <li>用户名数据</li>
                <li>用户名数据</li>
                <li>用户名数据</li>
                <li>用户名数据</li>
                <li>用户名数据</li>
                <li>用户名数据</li>
            </ul>
        </div>
        --%>
            <%
                int i=0;
                for (OrderBean orderBean : orderBeanList) {
                    i++;
            %>
            <div>
                <ul>
                    <li><%= orderBean.getOrderID()%></li>
                    <li><%= orderBean.getUserID()%></li>
                    <li><%= orderBean.getOrderPrice()%></li>
                    <li><%= orderBean.getOrderAddress()%></li>
                    <li><%= orderBean.getOrderDate()%></li>
                    <li>
                        <form action="../../OrderManage" method="post" id="form<%=i%>">
                            <input form="form<%=i%>" type="hidden" name="orderID" value="<%=orderBean.getOrderID()%>">
                            <input form="form<%=i%>" type="submit" value="删除">
                        </form>
                    </li>
                </ul>
            </div>
            <%
                }
            %>
            <div>
                <a href="http://localhost:8080/shop/index.jsp">添加订单</a>
            </div>
    </div>

</div>

</body>
</html>
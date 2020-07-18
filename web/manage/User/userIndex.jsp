<%@ page import="dao.IAdminDao" %>
<%@ page import="dao.bean.AdminBean" %>
<%@ page import="dao.implement.ImplAdminDao" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.IUserDao" %>
<%@ page import="dao.implement.ImplUserDao" %>
<%@ page import="dao.bean.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CH">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户管理</title>
    <link rel="stylesheet" type="text/css" href="../CSS/reset.css">
    <link rel="stylesheet" type="text/css" href="../CSS/style1.css">
</head>
<body>
<%--管理员页面--%>
<%!
    IUserDao iUserDao = ImplUserDao.iUserDaoFactory.returnIFactoryDAO();
    List<UserBean> userBeanList =null;
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
    userBeanList= iUserDao.allUser();
%>
<div class="title">
    <div>
        <ul>
            <li>
                <a href="http://localhost:8080/shop/manage/manageIndex.jsp">用户管理</a>
            </li>
            <li>管理员工号<%=adminJobID%>
            </li>
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
            <%--列表名--%>
            <ul>
                <li>用户ID</li>
                <li>用户名字</li>
                <li>用户密码</li>
                <li>用户性别</li>
                <li>用户地址</li>
                <li>用户邮箱</li>
                <li>注册时间</li>
                <li>vip</li>
                <li>用户状态</li>
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
                for (UserBean userBean : userBeanList) {
                    i++;
            %>
            <div>
                <ul>
                    <li><%= userBean.getID()%></li>
                    <li><%= userBean.getUserName()%></li>
                    <li><%= userBean.getPassword()%></li>
                    <li><%= userBean.getUserSex()%></li>
                    <li><%= userBean.getAddress()%></li>
                    <li><%= userBean.getEmail()%></li>
                    <li><%= userBean.getIsVIP()%></li>
                    <li><%= userBean.getUserState()%></li>
                    <li>
                        <form action="../../UserManage" method="post" id="form<%=i%>">
                            <input form="form<%=i%>" type="hidden" name="userID" value="<%=userBean.getID()%>">
                            <input form="form<%=i%>" type="hidden" name="userName" value="<%=userBean.getUserName()%>">
                            <input form="form<%=i%>" type="submit" value="删除">
                            <a href="./userChange.jsp?userID=<%=userBean.getID()%>">修改用户</a>
                        </form>
                    </li>
                </ul>
            </div>
            <%
                }
            %>
            <div>
                <a href="http://localhost:8080/shop/CheckUser/signup.html">
                    添加用户
                </a>
            </div>
    </div>

</div>

</body>
</html>
<%@ page import="dao.IUserDao" %>
<%@ page import="dao.implement.ImplUserDao" %>
<%@ page import="dao.bean.UserBean" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nice siri
  Date: 2020/7/17
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户修改</title>
</head>
<body>
    <%!
        IUserDao iUserDao = ImplUserDao.iUserDaoFactory.returnIFactoryDAO();
    %>
    <%
        int userID = Integer.parseInt(request.getParameter("userID"));
        UserBean userBean = iUserDao.getUserMsgs(userID);
    %>
    <form action="../../UserChange" method="post">
        <input type="hidden" name="userID" value="<%=userID%>">
        用户名：<input type="text" name="userName" value="<%=userBean.getUserName()%>"><br>
        密码：<input type="text" name="userPassword" value="<%=userBean.getPassword()%>"><br>
        性别：<input type="text" name="userSex" value="<%=userBean.getUserSex()%>"><br>
        地址：<input type="text" name="userAddress" value="<%=userBean.getAddress()%>"><br>
        邮箱：<input type="text" name="userEmail" value="<%=userBean.getEmail()%>"><br>
        是否为VIP：<input type="text" name="userIsVip" value="<%=userBean.getIsVIP()%>"><br>
        用户状态：<input type="text" name="userState" value="<%=userBean.getUserState()%>"><br>
        <input type="submit" value="修改">
    </form>

</body>
</html>

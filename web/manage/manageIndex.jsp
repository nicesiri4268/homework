<%@ page import="dao.IAdminDao" %>
<%@ page import="dao.bean.AdminBean" %>
<%@ page import="dao.implement.ImplAdminDao" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CH">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理员管理</title>
    <link rel="stylesheet" type="text/css" href="./CSS/reset.css">
    <link rel="stylesheet" type="text/css" href="./CSS/style1.css">
</head>
<body>
<%--管理员页面--%>
<%!
    IAdminDao<AdminBean> iAdminDao = ImplAdminDao.iAdminDaoFactory.returnIFactoryDAO();
    List<AdminBean> adminBeanList = null;
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
%>
<div class="title">
    <div>
        <ul>
            <li>
                <a href="http://localhost:8080/shop/manage/manageIndex.jsp">管理员主页</a>
            </li>
            <li>管理员工号<%=adminJobID%>
            </li>
            <li>管理员名 <%=admin%><a href="../AdminLogOut">&nbsp;&nbsp;退 出</a></li>
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
                <li>管理员的ID</li>
                <li>管理员的JobID</li>
                <li>管理员的等级</li>
                <li>管理员的名字</li>
                <li>管理员的密码</li>
                <li>管理员的状态</li>
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
        <%--out.println(
                        "<div>\n" +
                                "                <ul>\n" +
                                "                    <li>" + adminBean.getAdminID() + "</li>\n" +
                                "                    <li>" + adminBean.getAdminJobID() + "</li>\n" +
                                "                    <li>" + adminBean.getAdminLevel() + "</li>\n" +
                                "                    <li>" + adminBean.getAdminName() + "</li>\n" +
                                "                    <li>" + adminBean.getAdminPassword() + "</li>\n" +
                                "                    <li>" + adminBean.getAdminState() + "</li>\n" +
                                "                    <li>" + "  <form action=\"管理员用户删除地址\" method=\"post\">\n" +
                                "                               <input type=\"submit\" value=\"删除\">\n" +
                                "                               </form>" + "</li>\n" +
                                "                </ul>\n" +
                                "            </div>"
                );--%>
        <%
            int i=0;
            for (AdminBean adminBean : adminBeanList) {
                i++;
            %>
            <div>
                <ul>
                    <li><%= adminBean.getAdminID()%></li>
                    <li><%= adminBean.getAdminJobID()%></li>
                    <li><%= adminBean.getAdminLevel()%></li>
                    <li><%= adminBean.getAdminName()%></li>
                    <li><%= adminBean.getAdminPassword()%></li>
                    <li><%= adminBean.getAdminState()%></li>
                    <li>
                        <form action="../AdminManage" method="post" id="form<%=i%>">
                            <input form="form<%=i%>" type="hidden" name="adminID" value="<%=adminBean.getAdminID()%>">
                            <input form="form<%=i%>" type="submit" value="删除">
                        </form>
                    </li>
                </ul>
            </div>
            <%
            }
        %>
            <div>
                <a href="http://localhost:8080/shop/adminUser/signup.html">
                    添加管理员
                </a>
            </div>
    </div>

</div>

</body>
</html>
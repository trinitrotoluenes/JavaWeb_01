 <%--
  Created by IntelliJ IDEA.
  User: 宇涵
  Date: 2019/10/4
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <!DOCTYPE html>
 <html lang="zh-CN">
 <head>
     <link rel = "stylesheet" href="./css/Joystyle.css">
     <link rel="bookmark"  type="image/x-icon"  href="img/ico.ico"/>

     
   <meta charset="utf-8"/>
   <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
   <meta name="viewport" content="width=device-width, initial-scale=1"/>
   <title>首页</title>

   <!-- 1. 导入CSS的全局样式 -->
   <link href="css/bootstrap.min.css" rel="stylesheet">
   <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
   <script src="js/jquery-2.1.0.min.js"></script>
   <!-- 3. 导入bootstrap的js文件 -->
   <script src="js/bootstrap.min.js"></script>
   <script type="text/javascript">
     </script>
 </head>
 <body>

 <div style="font-size: 50px" align="center" >
     ${manager.showName},欢迎您
         <br><br><br><hr>
 </div>


 <div align="center">
   <a
           href="${pageContext.request.contextPath}/findUserByPageServlet" style="text-decoration:none;font-size:33px">查询所有用户信息
   </a>
 </div>
 </body>
 </html>
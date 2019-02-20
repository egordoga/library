<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 15.07.2017
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<div class="basic">


    <div class="img1">
        <img src="images/book.jpg" id="image" alt="Picture">
    </div>
    <div class="header">
        <h2>Онлайн библиотека</h2>
    </div>
    <div class="forma">
        <form name="username" action="pages/main.jsp" method="get" id="user">
            <input type="text" name="username" value="" size="20"/>
            <input type="submit" value="Войти"/>
        </form>
    </div>
    <div id="footer">&copy; Игорь Коряченко</div>
</div>
</body>
</html>

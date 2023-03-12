<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CreateNewNotion</title>
</head>
<body>
<form name="notionAction" action="${pageContext.request.contextPath}/createNewNotion" method="post">
    <div>
        <label for="notionsTitle">Заголовок</label>
        <input id="notionsTitle" name="newNotionsTitle" type="text">
    </div>
    <div>
        <label for="notionsText">Текст</label>
        <input id="notionsText" name="newNotionsText" type="text">
    </div>
    <button type="submit">Сохранить</button>
</form>
</body>
</html>

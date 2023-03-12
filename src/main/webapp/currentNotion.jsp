<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CurrentNotion</title>
</head>
<body>
<c:if test="${onlyOpen == true}">
    <h2>${title}</h2>
    <p>${text}</p>
</c:if>
<c:if test="${onlyOpen == false}">
    <form name="notionAction" action="${pageContext.request.contextPath}/currentNotion" method="post">
        <div>
            <label for="notionsTitle">Заголовок</label>
            <input id="notionsTitle" name="notionsTitle" type="text" value="${title}">
        </div>
        <div>
            <label for="notionsText">Текст</label>
            <input id="notionsText" name="notionsText" type="text" value="${text}">
        </div>
        <button type="submit">Сохранить</button>
    </form>
</c:if>
</body>
</html>

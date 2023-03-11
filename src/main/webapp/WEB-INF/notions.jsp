<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 11.03.2023
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Вот ваши заметки:</title>
    <style>
        .dashed {display: inline-block; cursor:pointer; border-bottom: 1px dashed; margin:8px}
    </style>
</head>
<body>
<h1>И вот мы здесь</h1>
<form name="main" action="${pageContext.request.contextPath}/notions" method="post">
    <div class="dashed"
            onclick="this.nextElementSibling.style.display=this.nextElementSibling.style.display==='none'
    ?'block':'none'">Создать заметку</div>
    <div style="display: none">
        <label for="newNotionTitle">Заголовок</label>
        <input id="newNotionTitle" name="newNotionTitle" type="text">
        <label for="newNotionText">Текст заметки</label>
        <input id="newNotionText" name="newNotionText" type="text">
        <button type="submit" onclick="document.forms.main.elements.mode.value='add'">Сохранить</button>
    </div>
    <input type="hidden" name="uuid">
    <input type="hidden" name="mode">
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>title</th>
            <th>text</th>
            <th>createDate</th>
            <th>actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="notion" items="${notionsList}" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${notion.title}</td>
            <td>${notion.text}</td>
            <td>${notion.createDate}</td>
            <td><button onclick="document.forms.main.elements.mode.value='edit';
                document.forms.main.elements.uuid.value = '${notion.id}'" type="submit">Редактировать</button>

            <button onclick="document.forms.main.elements.mode.value='delete';
                document.forms.main.elements.uuid.value = '${notion.id}'" type="submit">Удалить</button></td>
        </tr>
        </c:forEach>
</form>
</tbody>
</table>

</body>
</html>

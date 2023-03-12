<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Вот ваши заметки:</title>
</head>
<body>
<h1>Ваши заметки:</h1>
<form name="main" action="${pageContext.request.contextPath}/notions" method="post">
    <input type="hidden" name="uuid">
    <input type="hidden" name="mode">

    <button onclick="document.forms.main.elements.mode.value='add'" type="submit">Создать заметку
    </button>

    <c:forEach var="notion" items="${notionsList}" varStatus="loop">
        <div>
                ${notion.title}
            <button onclick="document.forms.main.elements.mode.value='open';
                    document.forms.main.elements.uuid.value = '${notion.id}'">Открыть
            </button>
            <button onclick="document.forms.main.elements.mode.value='edit';
                    document.forms.main.elements.uuid.value = '${notion.id}'">Редактировать
            </button>
            <button onclick="document.forms.main.elements.mode.value='delete';
                    document.forms.main.elements.uuid.value = '${notion.id}'">Удалить
            </button>
        </div>
    </c:forEach>
</form>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: kubam
  Date: 24.03.17
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Translations for: ${word}</title>
</head>
<body>

<div>
<c:forEach items="${translations}" var="w">
    ${w.polishWord} ==> ${w.englishWord} <br/>
</c:forEach>
</div>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>DB Test</h1>
<sql:transaction dataSource="jdbc/RTTO">
<sql:query var="result" sql="SELECT * FROM tblUser"></sql:query>
<c:forEach items="${ result.rows }" var="row">
<c:out value="${ row.email }"></c:out></br>
</c:forEach>
</sql:transaction>

</body>
</html>
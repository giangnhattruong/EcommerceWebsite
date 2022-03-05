<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>DB Test</h1>
<sql:transaction dataSource="jdbc/ShoppingDB">
<sql:query var="result" sql="SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY product_id) AS row_number FROM Products
	WHERE LOWER(product_name) LIKE '%64gb%' OR
	LOWER(product_brand) LIKE '%64gb%' OR
	LOWER(product_type) LIKE '%64gb%') AS T">
</sql:query>
<c:forEach items="${ result.rows }" var="row">
<c:out value="${ row.product_name }"></c:out></br>
<c:out value="${ row.product_id }"></c:out></br>
<c:out value="${ row.row_number }"></c:out></br>
</c:forEach>
</sql:transaction>

</body>
</html>
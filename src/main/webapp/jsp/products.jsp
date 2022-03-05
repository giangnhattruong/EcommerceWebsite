<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="./header.jsp"></c:import>

<fmt:parseNumber var="size" integerOnly="true" value="8" />
<fmt:parseNumber var="page" integerOnly="true" value="${ param.page }" />

<main class="container-fluid d-flex flex-column justify-content-between align-items-center ps-5">
	<c:import url="/jsp/getProducts.jsp">
		<c:param name="size" value="${ size }"></c:param>
		<c:param name="page" value="${ page }"></c:param>
	</c:import>
</main>

<c:import url="./footer.jsp"></c:import>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/jsp/header.jsp"></c:import>
<c:import url="/jsp/banner.jsp"></c:import>

<fmt:parseNumber var="size" integerOnly="true" value="6" />
<fmt:parseNumber var="page" integerOnly="true" value="${ param.page }" />

<div class="container-fluid row">
	<main class="col-10 offset-1 col-lg-9 offset-lg-0 d-flex flex-column justify-content-between align-items-center">
		<div class="d-flex flex-wrap justify-content-evenly mt-5">
			<c:import url="/jsp/getProducts.jsp">
				<c:param name="size" value="${ size }"></c:param>
				<c:param name="page" value="${ page }"></c:param>
			</c:import>
		</div>
	</main>
	
	<aside class="col-10 offset-1 col-lg-3 offset-lg-0 mt-5">
		<jsp:include page="./jsp/cart.jsp"></jsp:include>
		<jsp:include page="./jsp/popular.jsp"></jsp:include>
	</aside>
</div>

<c:import url="/jsp/footer.jsp"></c:import>

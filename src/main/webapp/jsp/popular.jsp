<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<sql:transaction dataSource="jdbc/ShoppingDB">
	<sql:query var="result" sql="SELECT TOP(6) * FROM Products"></sql:query>
</sql:transaction>

<section id="popular" class="bg-white rounded mb-5">
	<h2>Popular products</h2>
	<c:forEach var="row" items="${ result.rows }">
		<div class="product-item card border-0 mb-2 row">
			<a href="<c:url value="/product?id=${ row.product_id }"></c:url>"> 
				<div class="row g-0">
					<div class="col-2 d-flex align-items-center">
						<img
							src="${ row.product_img_source }"
							class="img-fluid rounded-start" alt="product-picture">
					</div>
					<div class="col-10 card-body px-1 py-0 d-flex align-items-center">
						<small class="text-muted">${ row.product_name }</small>
					</div>
				</div>
			</a>
		</div>
	</c:forEach>
</section>
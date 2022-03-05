<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="defaultImgUrl" value="/assets/media/unsplash.jpg"></c:url>
<c:url var="prefix" value="/shop?page="></c:url>
<c:if test='${ param.action == "products" }'>
	<c:url var="prefix" value="/shop?action=products&page="></c:url>
</c:if>

<fmt:parseNumber var="size" integerOnly="true" value="${ param.size }" />
<fmt:parseNumber var="page" integerOnly="true" 
	value="${ (param.page == null || param.page < 1)? 1: param.page }" />
<fmt:parseNumber var="start" integerOnly="true" value="${ size * (page - 1) + 1 }" />
<fmt:parseNumber var="end" integerOnly="true" value="${ start + size - 1 }" />

<sql:transaction dataSource="jdbc/ShoppingDB">
<sql:query var="result" sql="SELECT COUNT(*) AS count FROM Products"></sql:query>
<c:forEach var="row" items="${ result.rows }">
	<fmt:parseNumber var="numOfProducts" integerOnly="true" value="${ row.count }" />
</c:forEach>
</sql:transaction>
<fmt:parseNumber var="numOfPages" integerOnly="true" 
	value="${ numOfProducts % size == 0? numOfProducts / size: numOfProducts / size + 1 }" />

<div class="d-flex flex-wrap mt-5">
	<sql:transaction dataSource="jdbc/ShoppingDB">
	<sql:query var="result" sql="EXECUTE GetProducts ?, ?">
		<sql:param>${ start }</sql:param>
		<sql:param>${ end }</sql:param>
	</sql:query>
	
	<c:forEach var="row" items="${ result.rows }">
		<div class="card me-5 mb-3">
			<a href="#"> 
				<img src="${ row.product_img_source == null? defaultImgUrl: row.product_img_source }"
				class="card-img-top" alt="product-picture">
				<div class="card-body">
					<h5 class="card-title">${ row.product_name }</h5>
					<p class="card-text">${ row.product_type } - ${ row.product_brand }</p>
					<p class="text-end">Price: $${ row.product_price }</p>
				</div>
			</a>
		</div>
	</c:forEach>
	</sql:transaction>
</div>

<nav class="mb-3" aria-label="Page navigation">
	<ul class="pagination">
		<li class="page-item"><a class="page-link" href="${ prefix }${ page - 1 < 1? 1: page - 1 }"
			aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
		</a></li>
		<c:forEach var="i" begin="1" end="${ numOfPages }">
			<li class="page-item"><a class="page-link" href="${ prefix }${ i }">${ i }</a></li>
		</c:forEach>
		<li class="page-item"><a class="page-link" href="${ prefix }${ page + 1 > numOfPages? numOfPages: page + 1 }"
			aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</nav>
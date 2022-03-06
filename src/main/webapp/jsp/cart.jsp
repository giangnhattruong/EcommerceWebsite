<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="totalPrice" value="0"></c:set>

<section id="card" class="bg-white rounded mb-5">
	<h2>Shopping cart</h2>
	
	<c:if test="${ empty cart }">
		<p>No item.</p>
	</c:if>
	
	<c:if test="${ not empty cart }">
		<c:forEach var="product" items="${ cart.keySet() }">
			<div class="card row p-1">
				<div class="row g-0">
					<div class="col-1 d-flex align-items-center">
						<img
							src="${ product.imgURL }"
							class="img-fluid rounded-start" alt="product-picture">
					</div>
					<div class="col-7 card-body px-1 py-0 d-flex align-items-center">
						<small class="text-muted">${ product.name }</small>
					</div>
					<div class="col-4 card-body px-1 py-0 d-flex align-items-center">
						<small class="text-muted">$${ product.price } x ${ cart[product] } </small>
					</div>
				</div>
			</div>
			<c:set var="totalPrice" value="${ totalPrice + cart[product] * product.price }"></c:set>
		</c:forEach>
		
		<fmt:formatNumber var="totalPrice" value="${ totalPrice }" maxFractionDigits="2"></fmt:formatNumber>
		<div class="d-flex mt-3 justify-content-end">
			<em>Total price: <strong>$${ totalPrice }</strong></em>
		</div>
		
		<div class="d-flex justify-content-end mt-3">
			<a class="btn btn-outline-info"
				href="<c:url value="/cart"></c:url>">
				<i class="bi bi-cart-check"></i> Go to cart
			</a>
		</div>
	</c:if>
</section>
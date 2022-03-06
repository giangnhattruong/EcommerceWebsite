<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="./header.jsp"></c:import>
<main class="d-flex justify-content-center p-5">
	<div class="card p-5" id="checkout">
		<div class="card-body">
			<section>
				<p class="text-danger">${ message }</p>
				<h6 class="display-6 mb-5">Cart</h6>
				<c:if test="${ empty cart }">
					<p>No item.</p>
				</c:if>
				<c:if test="${ not empty cart }">
					<c:forEach var="product" items="${ cart.keySet() }">
						<div class="card row py-1">
							<div class="row g-0">
								<div class="col-1 d-flex align-items-center">
									<img
										src="${ product.imgURL }"
										class="img-fluid rounded-start" alt="product-picture">
								</div>
								<div class="col-7 card-body px-1 py-0 d-flex align-items-center">
									<p class="p-0 ps-3 my-auto">${ product.name }</p>
								</div>
								<div class="col-4 card-body pe-3 py-0 d-flex justify-content-end align-items-center">
									<p class="p-0 pe-5 my-auto">$${ product.price } x ${ cart[product] }</p>
									
									<a class="updateCart lead pe-3"
										href="<c:url value="/cart?increaseID=${ product.id }"></c:url>"><i class="bi bi-cart-plus"></i></a>
									<a class="updateCart lead pe-3"
										href="<c:url value="/cart?decreaseID=${ product.id }"></c:url>"><i class="bi bi-cart-dash"></i></a>
									<a class="updateCart lead"
										href="<c:url value="/cart?removeID=${ product.id }"></c:url>"><i class="bi bi-cart-x"></i></a>
								</div>
							</div>
						</div>
						<c:set var="totalPrice" value="${ totalPrice + cart[product] * product.price }"></c:set>
					</c:forEach>
					
					<fmt:formatNumber var="totalPrice" value="${ totalPrice }" maxFractionDigits="2"></fmt:formatNumber>
					<div class="d-flex mt-3 justify-content-end">
						<h4 class="lead">Total price: <strong>$${ totalPrice }</strong></h4>
					</div>
				</c:if>
			</section>
			<form id="checkoutForm" method="post"
				action="<c:url value="/cart"></c:url>">
				<input type="hidden" name="action" value="checkout">
				<fieldset>
					<h6 class="display-6 my-5">Payment information</h6>
					<div class="mb-3">
						<label for="address" class="form-label">Address <sup class="text-danger">*</sup></label> 
						<input
							type="text" class="form-control" id="address" name="address"
							value='${ address }'>
					</div>
					<div class="mb-3">
						<label for="email" class="form-label">Email</label> 
						<input
							type="email" class="form-control" id="email" name="email"
							value='${ email }'>
					</div>
				</fieldset>	
				<button class="btn btn-success my-3" style="width: 100%">Check out</button>
			</form>
		</div>
	</div>
</main>
<c:import url="./footer.jsp"></c:import>

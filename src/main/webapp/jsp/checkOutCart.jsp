<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="./header.jsp"></c:import>
<main class="d-flex justify-content-center p-5">
	<div class="card p-5" id="checkout">
		<div class="card-body">
			<form id="checkoutForm" method="post"
				action="<c:url value="/checkout"></c:url>">
				
				<fieldset>
					<legend>Cart</legend>
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
							<h4>Total price: <strong>$${ totalPrice }</strong></h4>
						</div>
					</c:if>
				</fieldset>
				
				<fieldset>
					<legend>Payment information</legend>
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
				
				<c:if test="${ not empty message }">
					<p class="text-danger">${ message }</p>
				</c:if>
				
				<button class="btn btn-success my-3" style="width: 100%">Check out</button>
			</form>
		</div>
	</div>
</main>
<c:import url="./footer.jsp"></c:import>

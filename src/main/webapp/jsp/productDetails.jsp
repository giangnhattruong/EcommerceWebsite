<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/jsp/header.jsp"></c:import>

<div class="container-fluid row">
	<main class="col-10 offset-1 col-lg-9 offset-lg-0 d-flex flex-column justify-content-between align-items-center">
		<div class="border-0 card py-5 px-lg-5 mx-lg-5">
			<h3 class="card-title text-center mb-5 display-5">${ product.name }</h3>
			<img src="${ product.imgURL }" class="card-img-top mb-5" alt="${ product.name }">
			<div class="card-body">
				<p class="card-text lead">${ product.description }</p>
			</div>
			<ul class="list-group list-group-flush mb-3">
				<li class="list-group-item"><em><small class="text-mute">Category: ${ product.type }</small></em></li>
				<li class="list-group-item"><em><small class="text-mute">Brand: ${ product.brand }</small></em></li>
				<li class="list-group-item"><strong>Price: <em>$${ product.price }</em></strong></li>
			</ul>
			<div class="card-body">
				<p class="text-danger text-end">${ message }</p>
				<form class="row g-3 justify-content-end" method="post" 
					action="<c:url value="/product"></c:url>">
					<label for="amount" class="col-auto col-lg-2 col-form-label lead text-end">Amount</label>
					<input type="hidden" name="product_id" value="${ product.id }">
					<input type="hidden" name="product_name" value="${ product.name }">
					<input type="hidden" name="product_description" value="${ product.description }">
					<input type="hidden" name="product_type" value="${ product.type }">
					<input type="hidden" name="product_brand" value="${ product.brand }">
					<input type="hidden" name="product_imgURL" value="${ product.imgURL }">
					<input type="hidden" name="product_price" value="${ product.price }">
					<div class="col-auto col-lg-2">
						<input type="number" step="1" min="1" max="100" class="pe-0 form-control text-center" id="amount" name="amount" value="1">
					</div>
					<div class="col-auto col-lg-6">
						<button type="submit" class="btn btn-success mb-3 w-100">Add to cart</button>
					</div>
				</form>
			</div>
		</div>
	</main>
	
	<aside class="col-10 offset-1 col-lg-3 offset-lg-0 mt-5">
		<c:import url="/jsp/cart.jsp"></c:import>
		<c:import url="/jsp/popular.jsp"></c:import>
	</aside>
</div>

<c:import url="/jsp/footer.jsp"></c:import>

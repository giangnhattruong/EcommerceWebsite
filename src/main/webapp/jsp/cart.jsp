<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="card" class="bg-white rounded mb-5">
	<h2>Shopping cart</h2>
	<div class="card row">
		<div class="row g-0">
			<div class="col-2 d-flex align-items-center">
				<img
					src="<c:url value="/assets/media/unsplash (1).jpg"></c:url>"
					class="img-fluid rounded-start" alt="product-picture">
			</div>
			<div class="col-8 card-body px-1 py-0 d-flex align-items-center">
				<small class="text-muted">Iphone 9</small>
			</div>
			<div class="col-2 card-body px-1 py-0 d-flex align-items-center">
				<small>x1</small>
			</div>
		</div>
	</div>
	<div class="card row mb-3">
		<div class="row g-0">
			<div class="col-2 d-flex align-items-center">
				<img
					src="<c:url value="/assets/media/unsplash (2).jpg"></c:url>"
					class="img-fluid rounded-start" alt="product-picture">
			</div>
			<div class="col-8 card-body px-1 py-0 d-flex align-items-center">
				<small class="text-muted">Iphone 10</small>
			</div>
			<div class="col-2 card-body px-1 py-0 d-flex align-items-center">
				<small>x1</small>
			</div>
		</div>
	</div>
	<div class="d-flex justify-content-end">
		<em>Total price: <strong>$2859.46</strong></em>
	</div>
</section>
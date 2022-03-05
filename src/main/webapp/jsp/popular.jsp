<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="popular" class="bg-white rounded mb-5">
	<h2>Popular products</h2>
	<div class="card row">
		<div class="row g-0">
			<div class="col-2 d-flex align-items-center">
				<img
					src="<c:url value="/assets/media/unsplash (5).jpg"></c:url>"
					class="img-fluid rounded-start" alt="product-picture">
			</div>
			<div class="col-10 card-body px-1 py-0 d-flex align-items-center">
				<small class="text-muted">Android X</small>
			</div>
		</div>
	</div>
	<div class="card row">
		<div class="row g-0">
			<div class="col-2 d-flex align-items-center">
				<img
					src="<c:url value="/assets/media/unsplash (8).jpg"></c:url>"
					class="img-fluid rounded-start" alt="product-picture">
			</div>
			<div class="col-10 card-body px-1 py-0 d-flex align-items-center">
				<small class="text-muted">Android X-5</small>
			</div>
		</div>
	</div>
</section>
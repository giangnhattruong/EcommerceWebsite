<%@ include file="./jsp/header.jsp"%>
<%@ include file="./jsp/banner.jsp"%>

<div class="container-fluid row">
	<main
		class="col-10 offset-1 col-lg-9 offset-lg-0 d-flex flex-wrap justify-content-around mt-5">
		<div class="card mb-3">
		<a href="#"> <img
			src="<%=request.getContextPath()%>/assets/media/unsplash (1).jpg"
			class="card-img-top" alt="...">
			<div class="card-body">
				<h5 class="card-title">Iphone 9</h5>
				<p class="card-text">Ut enim ad minima veniam, quis nostrum
					exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid.</p>
				<p class="text-end">Price: $1353.23</p>
			</div>
		</a>
	</div>

	<div class="card mb-3">
		<a href="#"> <img
			src="<%=request.getContextPath()%>/assets/media/unsplash (2).jpg"
			class="card-img-top" alt="...">
			<div class="card-body">
				<h5 class="card-title">Iphone 10</h5>
				<p class="card-text">Inventore veritatis et quasi architecto
					beatae vitae dicta sunt explicabo sit aspernatur aut odit aut
					fugit.</p>
				<p class="text-end">Price: $1456.12</p>
			</div>
		</a>
	</div>

	<div class="card mb-3">
		<a href="#"> <img
			src="<%=request.getContextPath()%>/assets/media/unsplash (5).jpg"
			class="card-img-top" alt="...">
			<div class="card-body">
				<h5 class="card-title">Android X</h5>
				<p class="card-text">Squia non numquam eius modi tempora
					incidunt ut labore et dolore magnam aliquam quaerat voluptatem.</p>
				<p class="text-end">Price: $1196.09</p>
			</div>
		</a>
	</div>

	<div class="card mb-3">
		<a href="#"> <img
			src="<%=request.getContextPath()%>/assets/media/unsplash (8).jpg"
			class="card-img-top" alt="...">
			<div class="card-body">
				<h5 class="card-title">Android X-5</h5>
				<p class="card-text">Praesentium voluptatum deleniti atque
					corrupti quos dolores et quas molestias excepturi sint occaecati.</p>
				<p class="text-end">Price: $1223.55</p>
			</div>
		</a>
	</div>
	</main>

	<aside class="col-10 offset-1 col-lg-3 offset-lg-0 mt-5">
		<jsp:include page="./jsp/cart.jsp"></jsp:include>
		<jsp:include page="./jsp/popular.jsp"></jsp:include>
	</aside>
</div>

<%@ include file="./jsp/footer.jsp"%>

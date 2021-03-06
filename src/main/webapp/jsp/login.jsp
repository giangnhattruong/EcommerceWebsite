<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="./header.jsp"></c:import>
<main class="d-flex justify-content-center p-5">
	<div class="card" id="login">
		<img
			src="<c:url value="/assets/media/unsplash (15).jpg"></c:url>"
			class="card-img-top">
		<div class="card-body">
			<form id="userForm" method="post"
				action="<c:url value="/user"></c:url>"
				onsubmit="return validateForm()">
				<input type="hidden" name="formAction" value="login">

				<c:if test="${ not empty message }">
					<p class="text-danger">${ message }</p>
				</c:if>

				<div class="mb-3">
					<label for="username" class="form-label">Username or Email</label> 
					<input
						type="text" class="form-control" id="username" name="username"
						value="${ username }">
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password</label> 
					<input
						type="password" class="form-control" id="password" name="password">
				</div>
				<button class="btn btn-primary mb-3" style="width: 100%">Sign In</button>
				<div class="d-flex justify-content-between">
					<div class="form-check">
						<input class="form-check-input" type="checkbox" id="rememberMe"
							name="rememberMe"> <label class="form-check-label"
							for="rememberMe"> Remember me </label>
					</div>
					<a href="<c:url value="/user?action=forgotPassword"></c:url>">
						Forgot password?</a>
				</div>
			</form>
		</div>
	</div>
</main>
<script type="text/javascript"
	src="<c:url value="/assets/js/validateForm.js"></c:url>"></script>
<c:import url="./footer.jsp"></c:import>
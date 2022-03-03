<%@ include file="./header.jsp"%>
<main class="d-flex justify-content-center p-5">
	<div class="card" id="login">
		<img
			src="<%=request.getContextPath()%>/assets/media/unsplash (15).jpg"
			class="card-img-top">
		<div class="card-body">
			<form id="userForm" method="post"
				action="<%=request.getContextPath()%>/shop"
				onsubmit="return validateForm()">
				<input type="hidden" name="formAction" value="login">

				<%
				if (request.getAttribute("message") != null) {
				%>
				<p class="text-danger"><%=request.getAttribute("message")%></p>
				<%
				}
				%>

				<div class="mb-3">
					<label for="username" class="form-label">Email</label> 
					<input
						type="text" class="form-control" id="username" name="username"
						value="<%=request.getAttribute("username") != null ? request.getAttribute("username") : ""%>">
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
					<a href="<%=request.getContextPath()%>/shop?action=forgotPassword">
						Forgot password?</a>
				</div>
			</form>
		</div>
	</div>
</main>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/validateForm.js"></script>
<%@ include file="./footer.jsp"%>

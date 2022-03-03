<%@ include file="./header.jsp"%>
<main class="d-flex justify-content-center p-5">
	<div class="card" id="login">
		<img
			src="<%=request.getContextPath()%>/assets/media/unsplash (15).jpg"
			class="card-img-top">
		<div class="card-body">
			<form id="userForm" method="post" 
				action="<%=request.getContextPath()%>/login">
				<input type="hidden" name="formAction" value="resetPassword">

				<%
				if (request.getAttribute("message") != null) {
				%>
				<p class="text-danger"><%=request.getAttribute("message")%></p>
				<%
				}
				%>
				
				<div class="mb-3">
					<label for="username" class="form-label">Username</label> 
					<input type="text" class="form-control" id="username" name="username"
						value="<%=request.getAttribute("username") != null ? request.getAttribute("username") : ""%>">
				</div>
				<div class="mb-3">
					<label for="password1" class="form-label">New Password</label> 
					<input type="password" class="form-control" id="password1" name="password1">
				</div>
				<div class="mb-3">
					<label for="password2" class="form-label">Confirm Password</label> 
					<input type="password" class="form-control" id="password2" name="password2">
				</div>
				<button class="btn btn-primary mb-3" style="width: 100%">Submit</button>
			</form>
		</div>
	</div>
</main>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/validateForm.js"></script>
<%@ include file="./footer.jsp"%>

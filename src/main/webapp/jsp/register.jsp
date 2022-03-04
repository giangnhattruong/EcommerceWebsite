<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="./header.jsp"></c:import>
<main class="d-flex justify-content-center p-5">
	<div class="card" id="login">
		<img
			src="<c:url value="/assets/media/unsplash (15).jpg"></c:url>"
			class="card-img-top">
		<div class="card-body">
			<form id="userForm" method="post"
				action="<c:url value="/user"></c:url>">
				<input type="hidden" name="formAction" value="register">

				<c:if test="${ not empty message }">
					<p class="text-danger">${ message }</p>
				</c:if>

				<div class="mb-3">
					<label for="username" class="form-label">Email</label> 
					<input
						type="text" class="form-control" id="username" name="username"
						value='${ account.username }'
						required>
				</div>
				<div class="mb-3">
					<label for="password1" class="form-label">Password</label> 
					<input
						type="password" class="form-control" id="password1"
						name="password1" required>
				</div>
				<div class="mb-3">
					<label for="password2" class="form-label">Confirm Password</label>
					<input type="password" class="form-control" id="password2"
						name="password2" required>
				</div>
				<div class="mb-3">
					<label for="name" class="form-label">Full Name</label> 
					<input
						type="text" class="form-control" id="name" name="name"
						value='${ account.name }' required>
				</div>
				<div class="mb-3">
					<label for="address" class="form-label">Address</label> 
					<input
						type="text" class="form-control" id="address" name="address"
						value='${ account.address }'>
				</div>
				<div class="mb-3">
					<label for="phone" class="form-label">Phone</label> 
					<input
						type="text" class="form-control" id="phone" name="phone"
						value='${ account.phone }'>
				</div>
				<button class="btn btn-primary mb-3" style="width: 100%">Sign Up</button>
			</form>
		</div>
	</div>
</main>
<c:import url="./footer.jsp"></c:import>

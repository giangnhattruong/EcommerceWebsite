<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark p-3">
	<div class="container-fluid">
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mb-2 me-auto mb-lg-0">
				<li class="nav-item"><a class='nav-link ${ empty param.action? "active": "" }'
					aria-current="page" href="<c:url value="/shop"></c:url>">Home</a></li>
				<li class="nav-item"><a class='nav-link ${ param.action == "products"? "active": "" }'
					href="<c:url value="/shop?action=products"></c:url>">Products</a></li>
				<li class="nav-item"><a class='nav-link  ${ param.action == "aboutus"? "active": "" }'
					href="<c:url value="/shop?action=aboutus"></c:url>">About</a></li>
				
				<c:if test="${ empty username }">
					<li class="nav-item"><a class='nav-link  ${ param.action == "login"? "active": "" }'
						href="<c:url value="/user?action=login"></c:url>">Login</a></li>
					<li class="nav-item"><a class='nav-link  ${ param.action == "register"? "active": "" }'
						href="<c:url value="/user?action=register"></c:url>">Register</a></li>
				</c:if>
				<c:if test="${ not empty username }">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/user?action=logout"></c:url>">Logout</a></li>
				</c:if>
			</ul>
			<c:import url="/jsp/search.jsp"></c:import>
		</div>
	</div>
</nav>
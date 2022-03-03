<% String currentPage = request.getParameter("action") == null? "" : request.getParameter("action"); %>

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
				<li class="nav-item"><a class="nav-link <%= currentPage.equals("")? "active": "" %>"
					aria-current="page" href="<%= request.getContextPath() %>/shop">Home</a></li>
				<li class="nav-item"><a class="nav-link <%= currentPage.equals("products")? "active": "" %>"
					href="<%=request.getContextPath()%>/shop?action=products">Products</a></li>
				<li class="nav-item"><a class="nav-link <%= currentPage.equals("aboutus")? "active": "" %>"
					href="<%=request.getContextPath()%>/shop?action=aboutus">About</a></li>
				
				<% if (session.getAttribute("username") == null) { %>
				<li class="nav-item"><a class="nav-link <%= currentPage.equals("login")? "active": "" %>"
					href="<%=request.getContextPath()%>/shop?action=login">Login</a></li>
				<li class="nav-item"><a class="nav-link <%= currentPage.equals("register")? "active": "" %>"
					href="<%=request.getContextPath()%>/shop?action=register">Register</a></li>
				<% } else { %>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/shop?action=logout">Logout</a></li>
				<% } %>
			</ul>
			<%@ include file="./search.jsp" %>
		</div>
	</div>
</nav>
<h5 class="d-none d-lg-inline">Welcome <%=session.getAttribute("username")%></h5>
<hr class="d-none d-lg-inline">
<a href="<%=request.getContextPath()%>/admin">
	<i class="bi bi-speedometer2 me-2"></i>
	<h6 class="d-none d-lg-inline">Dashboard</h6>
</a>
<a href="<%=request.getContextPath()%>/admin?action=staffMembers">
	<i class="bi bi-people-fill me-2"></i>
	<h6 class="d-none d-lg-inline">Staff members</h6>
</a>
<hr>
<a href="<%=request.getContextPath()%>/shop">
	<i class="bi bi-shop me-2"></i>
	<h6 class="d-none d-lg-inline">Back to shop</h6>
</a>
<a href="<%=request.getContextPath()%>/logout">
	<i class="bi bi-box-arrow-left me-2"></i>
	<h6 class="d-none d-lg-inline">Log out</h6>
</a>
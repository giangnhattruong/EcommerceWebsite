<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h5 class="d-none d-lg-inline">Welcome ${ username }</h5>
<hr class="d-none d-lg-inline">
<a href="<c:url value="/admin"></c:url>">
	<i class="bi bi-speedometer2 me-2"></i>
	<h6 class="d-none d-lg-inline">Dashboard</h6>
</a>
<a href="<c:url value="/admin?action=staffMembers"></c:url>">
	<i class="bi bi-people-fill me-2"></i>
	<h6 class="d-none d-lg-inline">Staff members</h6>
</a>
<hr>
<a href="<c:url value="/shop"></c:url>">
	<i class="bi bi-shop me-2"></i>
	<h6 class="d-none d-lg-inline">Back to shop</h6>
</a>
<a href="<c:url value="/user?action=logout"></c:url>">
	<i class="bi bi-box-arrow-left me-2"></i>
	<h6 class="d-none d-lg-inline">Log out</h6>
</a>
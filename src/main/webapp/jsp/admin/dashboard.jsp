<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="./adminHeader.jsp"></c:import>
<header
	class="d-flex justify-content-center py-5 bg-secondary text-white z-1">
	<h3 class="text-center">Admin Dashboard</h3>
</header>
<section class="p-5 z-0 row">
	<div class="col-12 col-lg-6">
		<c:import url="staffTable.jsp"></c:import>
	</div>
</section>
<c:import url="./adminFooter.jsp"></c:import>
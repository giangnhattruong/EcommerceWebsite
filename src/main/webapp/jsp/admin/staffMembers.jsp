<%@ include file="./adminHeader.jsp"%>
<header
	class="d-flex justify-content-center py-5 bg-secondary text-white z-1">
	<h3 class="text-center">Staff Members</h3>
</header>
<section class="p-5 z-0">
	<jsp:include page="staffTable.jsp"></jsp:include>
</section>
<%@ include file="./adminFooter.jsp"%>
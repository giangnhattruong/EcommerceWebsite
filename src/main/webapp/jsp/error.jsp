<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="./header.jsp"></c:import>
<section class="notify">
	<c:choose>
		<c:when test="${ not empty message }">
			<h3 class="text-center text-danger">${ message }</h3>
		</c:when>
		<c:otherwise>
			<h3 class="text-center">Page not found.</h3>
		</c:otherwise>
	</c:choose>
</section>
<c:import url="./footer.jsp"></c:import>
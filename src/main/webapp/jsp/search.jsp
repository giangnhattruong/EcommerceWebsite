<div id="searchBox">
	<form class="d-flex" method="get"
		action="<%= request.getContextPath() %>/shop">
		<input type="hidden" name="formAction" value="search">
		<input class="form-control" " type="search" name="q"
			placeholder="Search product..." aria-label="Search">
		<button class="btn btn-outline-light" type="submit">Search</button>
	</form>
</div>
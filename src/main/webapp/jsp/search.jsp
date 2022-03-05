<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="searchBox">
	<form class="d-flex" method="get"
		action="<c:url value="/shop"></c:url>">
		<input type="hidden" name="action" value="products">
		<input class="form-control" type="search" name="q"
			placeholder="Search product..." aria-label="Search">
			<select class="form-select" name="category">
			  <option selected value="">Category</option>
			  <option value="cellphone">Cellphone</option>
			  <option value="laptop">Laptop</option>
			</select>
		<button class="btn btn-outline-light" type="submit">Search</button>
	</form>
</div>
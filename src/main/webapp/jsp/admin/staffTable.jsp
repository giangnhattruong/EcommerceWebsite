<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-dark table-hover">
	<thead>
		<tr>
			<th scope="col">Username</th>
			<th scope="col">Name</th>
			<th scope="col">Address</th>
			<th scope="col">Phone</th>
			<th scope="col">Role</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="account" items="${ accounts }" >
			<tr>
				<td>${ account.username }</td>
				<td>${ account.name }</td>
				<td>${ account.address }</td>
				<td>${ account.phone }</td>
				<td>${ account.role }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
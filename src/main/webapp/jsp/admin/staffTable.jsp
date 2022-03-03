<%@ page import="java.util.List, java.util.ArrayList, model.Account"%>
<%
List<Account> accounts = (ArrayList<Account>) application.getAttribute("accounts");
%>
<table class="table table-dark table-hover">
	<thead>
		<tr>
			<th scope="col">#</th>
			<th scope="col">Username</th>
			<th scope="col">Name</th>
			<th scope="col">Role</th>
		</tr>
	</thead>
	<tbody>
		<%
		for (int i = 0; i < accounts.size(); i++) {
		%>
		<tr>
			<th scope="row"><%=i + 1%></th>
			<td><%=accounts.get(i).getUsername()%></td>
			<td><%=accounts.get(i).getName()%></td>
			<td><%=accounts.get(i).getRole()%></td>
		</tr>
		<%
		}
		%>
	</tbody>
</table>
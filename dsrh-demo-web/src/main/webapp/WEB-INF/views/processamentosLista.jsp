<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Processamentos</title>
</head>
<body>
	<h1>Processamentos</h1>

	<c:if test="${!empty processamentos}">
		<table align="left" border="1" width="100%">
			<tr>
				<th>ID</th>
				<th>Data</th>
				<th>Loja</th>
				<th>PDV</th>
				<th>Status</th>
			</tr>

			<c:forEach items="${processamentos}" var="processamento">
				<tr>
					<td><c:out value="${processamento.id}" /></td>
					<td><c:out value="${processamento.data}" /></td>
					<td><c:out value="${processamento.loja}" /></td>
					<td><c:out value="${processamento.pdv}" /></td>
					<td><c:out value="${processamento.status}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
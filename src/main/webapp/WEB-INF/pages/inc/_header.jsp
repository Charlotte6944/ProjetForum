<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>
	<div class="d-flex">
		<h2>Bienvenue dans le forum</h2>
		
		<c:choose >
			<c:when test="${ sessionScope['auth']}">
				<a class="logout" href="<c:url value="/login?logoff=true"/>">Déconnexion</a>
			</c:when>
		</c:choose>
	</div>
	
</body>
</html>
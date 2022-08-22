<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home page</title>
</head>
<body>
	
	<header>
		<jsp:include page="./inc/_header.jsp"/>
	</header>
		
		<c:choose>
		<c:when test="${ sessionScope['auth']}">
			<h1>Bienvenue <c:out value="${ user.nickname }"/></h1>
		</c:when>
		</c:choose>
		
		<c:choose >
		<c:when test="${ !sessionScope['auth']}">	
			<form action="<c:url value="/login"/>" method="POST" >
				<fieldset> 
					<legend>"Formulaire de connexion"</legend>
					<div class="form-example">
						<label for="login">Entrez your login: </label>
						<input type="text" name="login" id="login" required>
						<label for="password">Entrez votre mot de passe: </label>
						<input type="text" name="password" id="password" required>
					</div>
					<div class="form-example">
						<input type="submit" value="Connexion !">
					</div>	
				</fieldset>
			</form>
		</c:when>
	</c:choose>
	
	<footer>
		<jsp:include page="./inc/_footer.jsp"/>
	</footer>
	
</body>
</html>
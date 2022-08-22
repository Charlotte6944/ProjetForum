<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<jsp:include page="./inc/_header.jsp"/>
	</header>
	
	<h1>Bienvenue dans le forum</h1>
	
	<h2>Créer un nouveau topic</h2>
	<fieldset>
		<form method="POST" action="<c:url value='/forum?req=add' />" >
			<input type="text" name="title" placeholder="Titre">
			<textarea name="content" placeholder="Contenu"></textarea>
			<input type="submit" value="Créer un nouveau topic" >
		</form>
	</fieldset>
	
	<h2>Liste des topics</h2>
	
	<ul>
		<c:forEach items="${ topics }" var="topic">
			<li>
				<c:out value="${ topic.title }"></c:out>
				<c:out value="${ topic.date_created }"></c:out>
				<c:out value="${ topic.user.getNickname() }"></c:out>
			</li>
			<form method="POST" action="<c:url value='/forum?req=delete&id=${ topic.getId() }' /> ">
				<input type="submit"  value="Supprimer">
			</form>
			<form method="POST" action="<c:url value='/forum?req=update&id=${ topic.getId() }' /> ">
				<input type="text" name="title" placeholder="Titre">
				<textarea name="content" placeholder="Contenu"></textarea>
				<input type="submit"  value="Modifier">
			</form>
			<a href="<c:url value='forum/${ topic.getId() }' /> ">Détail</a>
		</c:forEach>
	</ul>
	
	<footer>
		<jsp:include page="./inc/_footer.jsp"/>
	</footer>
</body>
</html>
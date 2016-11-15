<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>My Music Dashboard</title>
</head>
<body>
	<form:form action="${s:mvcUrl('HC#findArtist').arg(0, artistName).build()}" method="GET">
		<label>Artist: </label><input type="text" name="artistName" /><button type="submit">Search</button><br>
		<table border="1">
			<thead>
				<tr>
					<th>Artist</th>
					<th>Genrer</th>
					<th>Followers</th>
					<th>External Links</th>
				</tr>
			</thead>
			<c:forEach items="${artistList}" var="artist">
				<tr>
					<td>
						<input type="hidden" value="${artist.id}">
						${artist.name}
					</td>
					<td>${artist.genres}</td>
					<td>${artist.followers.total}</td>
					<td>${artist.external_urls.spotify}</td>
				</tr>
			</c:forEach>
		</table>
	</form:form>
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js" type="text/javascript"></script>
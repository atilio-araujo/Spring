<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Main Page">

<jsp:body>

    <!-- PAGE TITLE -->
    <div class="page-title">                    
        <h2></h2>
    </div>

	<!-- PAGE CONTENT WRAPPER -->
    <div class="page-content-wrap">
    	<div class="row">
   			<div class="col-md-10 col-md-offset-1">
	    		<div class="panel panel-default">
	    			<div class="panel-heading">
                    	<h3 class="panel-title">Artists found: ${fn:length(artistList)}</h3>
                    </div>
                    <div class="panel-body">
						<table class="table table-hover">
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
										<a href="${s:mvcUrl('AC#artistProfile').arg(0, artist.id).build()}">${artist.name}</a>
									</td>
									<td>${artist.genres}</td>
									<td>${artist.followers.total}</td>
									<td align="center">
										<a href="${artist.external_urls.spotify}">
											<img src="${artist.images[0].url}" alt="${artist.name}" width="100px" height="100px">
										</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
    		</div>
    	</div>
    </div>
</jsp:body>
</tags:pageTemplate>
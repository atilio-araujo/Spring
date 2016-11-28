<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="${artistProfile.artist.name}">

<jsp:attribute name="extraScripts">

	<!-- INITIALIZE BLUEIMP GALLERY PLUG-IN TO ALL COVER SECTIONS -->
    <script>            
        document.getElementById('albums').onclick = function (event) {
            event = event || window.event;
            var target = event.target || event.srcElement;
            var link = target.src ? target.parentNode : target;
            var options = {index: link, event: event,onclosed: function(){
                    setTimeout(function(){
                        $("body").css("overflow","");
                    },200);                        
                }};
            var links = this.getElementsByTagName('a');
            blueimp.Gallery(links, options);
        };

        document.getElementById('singles').onclick = function (event) {
            event = event || window.event;
            var target = event.target || event.srcElement;
            var link = target.src ? target.parentNode : target;
            var options = {index: link, event: event,onclosed: function(){
                    setTimeout(function(){
                        $("body").css("overflow","");
                    },200);                        
                }};
            var links = this.getElementsByTagName('a');
            blueimp.Gallery(links, options);
        };

        document.getElementById('compilations').onclick = function (event) {
            event = event || window.event;
            var target = event.target || event.srcElement;
            var link = target.src ? target.parentNode : target;
            var options = {index: link, event: event,onclosed: function(){
                    setTimeout(function(){
                        $("body").css("overflow","");
                    },200);                        
                }};
            var links = this.getElementsByTagName('a');
            blueimp.Gallery(links, options);
        };

        document.getElementById('appearsOn').onclick = function (event) {
            event = event || window.event;
            var target = event.target || event.srcElement;
            var link = target.src ? target.parentNode : target;
            var options = {index: link, event: event,onclosed: function(){
                    setTimeout(function(){
                        $("body").css("overflow","");
                    },200);                        
                }};
            var links = this.getElementsByTagName('a');
            blueimp.Gallery(links, options);
        };
    </script>
</jsp:attribute>

<jsp:body>

    <!-- PAGE TITLE -->
    <div class="page-title">
        <table class="table">
        	<tr>
        		<td width="170">
        			<img src="${artistProfile.artist.images[0].url}" width="150" height="150" />
        		</td>
        		<td align="left" style="vertical-align:middle">
        			<table>
        				<tr>
        					<td><h2>${artistProfile.artist.name}</h2></td>
        				</tr>
        				<tr>
        					<td><h4>Followers: ${artistProfile.artist.followers.total}</h4></td>
        				</tr>
        			</table>
        		</td>
        		
        	</tr>
        </table>
    </div>


		<table class="table table-bordered">
			<thead>
				<tr>
					<th width="50%">
						<h3>Top Songs</h3>
					</th>
					<th width="50%">
						<h3>Albums</h3>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="50%" style="vertical-align:top;">
					    <!-- TOP TRACKS -->
	                	<c:forEach items="${artistProfile.topTracks}" var="track" varStatus="number">
	                    	<li class="list-group-item">
	                    		<img src="${track.album.images[0].url}" width="75" height="75">
	                    		${number.count} - ${track.name} (${track.album.name}) - ${track.formattedTrackLength}
	                    	</li>
	                    </c:forEach>
			            <!-- END TOP TRACKS -->
					</td>
					<td width="50%" style="vertical-align:top;">
					<!-- ALBUMS -->
	    				<div id="albums" class="gallery">
			            	<c:forEach items="${artistProfile.albums}" var="album">
					            <a class="gallery-item" href="${album.images[0].url}" title="${album.name}" data-gallery="#blueimp-gallery-albums">
					                <div class="image">
					                    <img src="${album.images[0].url}" alt="${album.name}"/>
					                </div>
					                <div class="meta">
					                	<c:if test="${fn:length(album.name) > 15}">
					                		<strong><label data-toggle="tooltip" data-placement="top" title="${album.name}">${fn:substring(album.name,0, 15)}...</label></strong>
					                	</c:if>
					                	<c:if test="${fn:length(album.name) <= 15}">
					                		<strong><label title="${album.name}">${album.name}</label></strong>
					                	</c:if>		                				
					                </div>
					            </a>
					        </c:forEach>
			            </div>
			            <!-- END ALBUMS -->
					</td>
				</tr>
			</tbody>
		</table>

		<c:if test="${(not empty artistProfile.singles) || (not empty artistProfile.compilations) }">
			<table class="table table-bordered">
				<thead>
					<tr>
						<c:if test="${not empty artistProfile.singles}">
							<th width="50%">
								<h3>Singles</h3>
							</th>
						</c:if>
						<c:if test="${not empty artistProfile.compilations}">
							<th width="50%">
								<h3>Compilations</h3>
							</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:if test="${not empty artistProfile.singles}">
							<td width="50%" style="vertical-align:top;">
								<!-- SINGLES -->
								<div id="singles" class="gallery">
					            	<c:forEach items="${artistProfile.singles}" var="album">
							            <a class="gallery-item" href="${album.images[0].url}" title="${album.name}" data-gallery="#blueimp-gallery-singles">
							                <div class="image">
							                    <img src="${album.images[0].url}" alt="${album.name}" />  
							                </div>
							                <div class="meta">
					                   			<c:if test="${fn:length(album.name) > 15}">
							                		<strong><label title="${album.name}">${fn:substring(album.name,0, 15)}...</label></strong>
							                	</c:if>
							                	<c:if test="${fn:length(album.name) <= 15}">
							                		<strong><label title="${album.name}">${album.name}</label></strong>
							                	</c:if>	
							                </div>                                
							            </a>
							        </c:forEach>
					            </div>
					            <!-- END SINGLES -->
							</td>
						</c:if>
						<c:if test="${not empty artistProfile.compilations}">
							<td width="50%" style="vertical-align:top;">
								<!-- COMPILATIONS -->
								<div id="compilations" class="gallery">
					            	<c:forEach items="${artistProfile.compilations}" var="album">
							            <a class="gallery-item" href="${album.images[0].url}" title="${album.name}" data-gallery="#blueimp-gallery-compilations">
							                <div class="image">
							                    <img src="${album.images[0].url}" alt="${album.name}" />  
							                </div>
							                <div class="meta">
					                   			<c:if test="${fn:length(album.name) > 15}">
							                		<strong><label title="${album.name}">${fn:substring(album.name,0, 15)}...</label></strong>
							                	</c:if>
							                	<c:if test="${fn:length(album.name) <= 15}">
							                		<strong><label title="${album.name}">${album.name}</label></strong>
							                	</c:if>	
							                </div>                                
							            </a>
							        </c:forEach>
					            </div>
								<!-- END COMPILATIONS -->
							</td>
						</c:if>
					</tr>
				</tbody>
			</table>
		</c:if>

		<!-- APPEARS ON -->
		<c:if test="${not empty artistProfile.appearsOn}">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th width="100%">
							<h3>Appears On</h3>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="100%">
					  		<div id="appearsOn" class="gallery">
					          	<c:forEach items="${artistProfile.appearsOn}" var="album">
						            <a class="gallery-item" href="${album.images[0].url}" title="${album.name}" data-gallery="#blueimp-gallery-compilations">
						                <div class="image">
						                    <img src="${album.images[0].url}" alt="${album.name}" />  
						                </div>
						                <div class="meta">
						                 			<c:if test="${fn:length(album.name) > 25}">
						                		<strong><label title="${album.name}">${fn:substring(album.name,0, 25)}...</label></strong>
						                	</c:if>
						                	<c:if test="${fn:length(album.name) <= 25}">
						                		<strong><label title="${album.name}">${album.name}</label></strong>
						                	</c:if>	
						                </div>                                
						            </a>
								</c:forEach>
							</div>
						</td>
					</tr>
				</tbody>
			</table>                
		</c:if>
        <!-- END APPEARS ON -->

		<!-- BLUEIMP GALLERY -->
        <div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls">
            <div class="slides"></div>
            <h3 class="title"></h3>
            <a class="prev">‹</a>
            <a class="next">›</a>
            <a class="close">×</a>
            <a class="play-pause"></a>
            <ol class="indicator"></ol>
        </div>      
        <!-- END BLUEIMP GALLERY -->

</jsp:body>

</tags:pageTemplate>
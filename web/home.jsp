<!DOCTYPE html>
<html>
	<jsp:include page="_head.jsp" >
		<jsp:param name="title" value="Home" />
	</jsp:include>
    <body>
	<jsp:include page="_nav.jsp" />
	<jsp:include page="_checkUser.jsp"/>
		<section class="py-4 bg-light mb-4">
			<div class="container">
				<div class="row">
								<img src="lobby.jpg" class="img-fluid">
				</div>
			</div>
		</section>



		<%@include file="_footer.jspf" %>
    </body>
</html>

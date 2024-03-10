<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Check session to see if user is logged in otherwise redirect to login page -->    
<%
if (session.getAttribute("LOGGED_IN_USER") == null) {
    response.sendRedirect("login.jsp"); // Redirect to login page
    return; // Stop execution of further page rendering
}
%>    
    
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="header.jsp"/>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Game Tales</title>
</head>
<body>
	<div class ="container">
        <div class="row">     
           <img src="images/homepage.gif" style="position: relative;"><span style="position: absolute; centre: 5px; south: 10px;">
           <br/><br/><br/><br/><br/><br/><br/><br/><br/><a href="products.jsp" class="btn">SHOP NOW —></a></span>
          <div class="col-2">

          </div>
                  	
          </div>
       </div>
</body>

<body>
<jsp:include page="footer.jsp"/>
</body>

</html>
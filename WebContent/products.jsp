<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eshop.web.jdbc.Product" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="header.jsp"/>
    <title>Game Tales - All Products</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>


    <h2>All Products</h2>
    <div class="small-container">
		<div class ="row">
		    <div class="col-1">
				<c:forEach var="product" items="${PRODUCT_LIST}">
				    <div>
				        <!-- Display product details here. -->
				        <img src="${product.imageUrl}" alt="${product.name}"/>
				        <h2>${product.name}</h2>
				        <p>${product.description}</p>
				        <p>Price: ${product.price}</p>
				
				        <!-- Add to Cart form -->
				        <form action="CartControllerServlet" method="post">
				            <input type="hidden" name="productId" value="${product.id}" />
				            <input type="hidden" name="command" value="ADD" />
				            <input type="submit" value="Add to Cart" />
				        </form>
				    </div>
				</c:forEach>

	    	</div>
		</div>

	        <c:if test="${empty PRODUCT_LIST}">
	            <p>No products found.</p>
	        </c:if>

	</div>

</body>
<jsp:include page="footer.jsp"/>
</html>

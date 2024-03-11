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
		            <div class="single-product input">
		                <img src="${product.imageUrl}" alt="${product.name}">
		                <div class="product-details">
		                    <h4>${product.name}</h4>
		                    <p>${product.description}</p>
		                    <p>$${product.price}</p>
		                </div>
		                    <!-- Add to Cart Button -->
		                    <form action="AddToCartServlet" method="post">
		                        <input type="hidden" name="productId" value="${product.id}">
		                        <button type="submit" class="btn">Add to Cart</button>
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

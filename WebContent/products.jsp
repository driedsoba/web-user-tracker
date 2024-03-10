<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eshop.web.jdbc.Product" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Products</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<%@ include file="header.jsp" %>

<div class="small-container">
    <h2>All Products</h2>
    <div class="row">
        <c:choose>
            <c:when test="${not empty PRODUCT_LIST}">
                <c:forEach items="${PRODUCT_LIST}" var="product">
                    <div class="col-4">
                        <img src="<c:out value='${product.imageUrl}'/>" alt="<c:out value='${product.name}'/>">
                        <h4><c:out value='${product.name}'/></h4>
                        <p>$<c:out value='${product.price}'/></p>
                        <p><c:out value='${product.description}'/></p>
                        <!-- Add to Cart Button -->
                        <!-- Adjust the form action to your add to cart servlet URL -->
                        <form action="addToCart" method="post">
                            <input type="hidden" name="productId" value="<c:out value='${product.id}'/>">
                            <button type="submit" class="btn">Add To Cart</button>
                        </form>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>No products found.</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>

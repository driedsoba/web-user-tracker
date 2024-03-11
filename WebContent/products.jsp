<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eshop.web.jdbc.Product" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Products</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div class="small-container">
    <h2>All Products</h2>
    <div class="row">
        <c:forEach var="product" items="${PRODUCT_LIST}">
            <div class="col-4">
                <img src="<c:url value='${product.imageUrl}'/>" alt="${product.name}">
                <h4>${product.name}</h4>
                <p>${product.description}</p>
                <p>$${product.price}</p>
                <!-- Add to Cart Button -->
                <form action="AddToCartServlet" method="post">
                    <input type="hidden" name="productId" value="${product.id}">
                    <button type="submit" class="btn">Add to Cart</button>
                </form>
            </div>
        </c:forEach>
        <c:if test="${empty PRODUCT_LIST}">
            <p>No products found.</p>
        </c:if>
    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>

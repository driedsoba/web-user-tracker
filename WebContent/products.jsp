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

<div class="small-container">
    <h2>All Products</h2>
    <div class="products-grid">
        <c:forEach var="product" items="${PRODUCT_LIST}">
            <div class="product-container">
                <img class="product-image" src="${product.imageUrl}" alt="${product.name}">
                <div class="product-details">
                    <h4 class="product-name">${product.name}</h4>
                    <p class="product-description">${product.description}</p>
                    <p class="product-price">$${product.price}</p>
                    <!-- Add to Cart Button -->
                    <form action="AddToCartServlet" method="post">
                        <input type="hidden" name="productId" value="${product.id}">
                        <button type="submit" class="add-to-cart-btn">Add to Cart</button>
                    </form>
                </div>
            </div>
        </c:forEach>
        <c:if test="${empty PRODUCT_LIST}">
            <p>No products found.</p>
        </c:if>
    </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>

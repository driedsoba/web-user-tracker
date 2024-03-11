<%@ page import="com.eshop.web.jdbc.Cart, com.eshop.web.jdbc.CartItem" %>

<%@ page session="true" %>
<%
	
    Cart cart = (Cart) session.getAttribute("CART");
    if (cart == null) {
        cart = new Cart();
        session.setAttribute("CART", cart);
    }
%>
<html>
<head>
    <title>Your Shopping Cart</title>
    <link rel="stylesheet" href="style.css"> <!-- Ensure this path is correct -->
</head>
<body>
    <h2>Your Shopping Cart</h2>
    <%
        if (cart.getItems().size() == 0) {
    %>
            <p>Your cart is empty</p>
    <%
        } else {
    %>
            <table>
                <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total Cost</th>
                    <th>Action</th>
                </tr>
                <%
                    for (CartItem item : cart.getItems().values()) {
                %>
                        <tr>
                            <td><%= item.getProduct().getName() %></td>
                            <td>$<%= item.getProduct().getPrice() %></td>
                            <td><%= item.getQuantity() %></td>
                            <td>$<%= item.getTotalPrice() %></td>
                            <td>
                                <form action="CartControllerServlet" method="post">
                                    <input type="hidden" name="productId" value="<%= item.getProduct().getId() %>" />
                                    <input type="hidden" name="command" value="REMOVE" />
                                    <input type="submit" value="Remove from Cart" class="remove-btn" /> <!-- Added class for styling -->
                                </form>
                            </td>
                        </tr>
                <%
                    }
                %>
                <tr>
                    <td colspan="3" align="right">Total:</td>
                    <td>$<%= cart.getTotal() %></td>
                    <td></td>
                </tr>
            </table>
    <%
        }
    %>
    <a href="ProductControllerServlet" class="continue-shopping-link">Continue Shopping</a> <!-- Added class for styling -->
</body>
</html>

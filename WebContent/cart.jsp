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
    
    <div class="container">
      <div class="navbar">
        <div class="logo">
          <img src="images/logo.png" width="200px" />
        </div>
      </div>
    </div>
</head>
<body>
	<div class="small-container">
		<div class="cart-col-1">
		<hr/>
		    <div class="cart-h">Cart</div>
		    <%
		        if (cart.getItems().size() == 0) {
		    %>
		            <div class="cart-p">Your cart is empty! Click here to go back to shop for more!<br/><br/></div>
		    <%
		        } else {
		    %>
		 </div>
    </div>
    
    <div class="small-container">
    		<div class="cart-col-1">
    		<br/>
    		<br/>
    		<h3>Order Summary</h3>
    		<hr/>
    		<br/>
    		</div>
    		<table>
                <tr>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total Cost</th>
                    <th>Delete Item &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                </tr>
                <%
                    for (CartItem item : cart.getItems().values()) {
                %>
                        <tr>
                            <td><%= item.getProduct().getName() %></td>
                            <td>x<%= item.getQuantity() %></td>
                            <td>$<%= item.getProduct().getPrice() %></td>
                            <td>$<%= item.getTotalPrice() %></td>
                            <td>
                                <form action="CartControllerServlet" method="post">
                                    <input type="hidden" name="productId" value="<%= item.getProduct().getId() %>" />
                                    <input type="hidden" name="command" value="REMOVE" />
                                    <input type="submit" value="-" class="remove-btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                                    <!-- Added class for styling -->
                                </form>
                            </td>
                        </tr>
                <%
                    }
                %>
                <tr>
                    <td colspan="3" align="right">Total:</td>
                    <td>$<%= cart.getTotal() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td></td>
                </tr>
            </table>
    	</div>  
    <%
        }
    %>
    <a href="ProductControllerServlet" class="continue-shopping-link">Continue Shopping</a> <!-- Added class for styling -->
	
</body>

	<body>
		<div class="container">
		<br/><br/>
		<jsp:include page="footer.jsp"/>
		</div>
	</body>


</html>

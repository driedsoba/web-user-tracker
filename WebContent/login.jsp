<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="header.jsp"/>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Login</title>
</head>
<body>
        <h2>Login as User</h2>
        <br/>
        <form method="POST" action="LoginServlet">
          <div class="form-group">
            <label>Email address</label>
            <input
              type="email"
              class="form-control"
              placeholder="Enter email"
              name="email"
            />
          </div>
          <br/>
          <div class="form-group">
            <label>Password</label>
            <input
              type="password"
              class="form-control"
              placeholder="Password"
              name="password"
            />
          </div>
          <br/>
          <button type="submit" class="btn btn-primary">Submit</button>
          <% if (request.getAttribute("errorMessage") != null) { %>
    	  <p style="color:red;"><%= request.getAttribute("errorMessage").toString() %></p>
		  <% } %>
        </form>
        <label
          >New to Game Tales? <a href="register.jsp"> Register</a></label
        >
        <label
          >Or login as <a href="admin.jsp"> Admin</a></label
        >
</body>

<jsp:include page="footer.jsp"/>
</html>
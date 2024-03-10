<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="header.jsp"/>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Register</title>
</head>
<body>
        <h2>Register</h2>
        <br/>
        <form method="get" action="register">
          <div class="form-group">
            <label>Name</label>
            <input
              class="form-control"
              type="text"
              placeholder="Enter name"
              name="name"
            />
            <br/>
            <label>Email address</label>
            <input
              type="email"
              class="form-control"
              placeholder="Enter email"
              name="email"
            />
          </div>
          <div class="form-group">
          <br/>
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
        </form>
        <label>Have an account? <a href="login.jsp"> Log In</a></label>
</body>

<jsp:include page="footer.jsp"/>
</html>
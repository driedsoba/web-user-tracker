<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Tracker App</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>User Database</h2>
        </div>
    </div>
    
    <div id="container">
        <input type="button" value="Add User"
               onclick="window.location.href='add-user-form.jsp'; return false;"
               class="add-user-button" />
               
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            <c:forEach var="tempUser" items="${USER_LIST}">
                
                <!-- Generate a URL for updating a user -->
                <c:url var="tempLink" value="UserControllerServlet"> 
                    <c:param name="command" value="LOAD" />
                    <c:param name="userId" value="${tempUser.id}" />
                </c:url>
                
                 <!-- Generate a URL for deleting a user -->
                <c:url var="deleteLink" value="UserControllerServlet"> 
                    <c:param name="command" value="DELETE" />
                    <c:param name="userId" value="${tempUser.id}" />
                </c:url>
                
                <tr>
                    <td>${tempUser.firstName}</td>
                    <td>${tempUser.lastName}</td>
                    <td>${tempUser.email}</td>
                    <td>
                    	<a href="${tempLink}"> Update </a>
                    	 |
                    	<a href="${deleteLink}" 
                    	onClick="if (!(confirm('Are you sure you want to delete this user?')) return false;">
                    	Delete </a> 
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <input type="button" value="Log out"
               onclick="window.location.href='login.jsp'; return false;"
               class="add-user-button" />
        
    </div>
</body>
</html>

<!DOCTYPE html>
<html>

<head>

<title>Add User</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>

<body>

	<div id="wrapper">
        <div id="header">
            <h2>User Database</h2>
        </div>
    </div>
    
    <div id="container">
        <h3>Add User</h3>
        
        <form action="UserControllerServlet" method = "GET">
        
        	<input type="hidden" name = "command" value = "ADD" />
        	
        	<table>
        		<tbody>
        			<tr>
        				<td><label>First Name:</label></td>
        				<td><input type = "text" name ="firstName" /></td>
        			</tr>
        		
        			<tr>
        				<td><label>Last Name:</label></td>
        				<td><input type = "text" name ="lastName" /></td>
        			</tr>
        			
        			<tr>
        				<td><label>Email:</label></td>
        				<td><input type = "text" name ="email" /></td>
        			</tr>
        			
        			<tr>
        				<td><label></label></td>
        				<td><input type = "submit" value = "Save" class = "save" /></td>
        			</tr>
        		
        		</tbody>
        		
        	</table>
        
        </form>
        
        <div style="clear: both;"></div>
        
        <p>
        	<a href="UserControllerServlet">Back to List</a>
        </p>
   </div>

</body>

</html>
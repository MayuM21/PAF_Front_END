<%@page import="model.Researchers"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Researcher Services</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/main.js"></script>
</head>
<body>
<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Researcher Services</h1>        
				
				<form id="formResearcher" name="formResearcher" method="post" action="researcher.jsp">  
					<br>
					appID :
					<input id="appID" name="appID" type="text" class="form-control form-control-sm"> 
					<br>
					
					FullName:  
					<input id="name" name="name" type="text" class="form-control form-control-sm">  
					
					<br> 
					Phone Number:  
					<input id="mobile" name="mobile" type="text" class="form-control form-control-sm">  
					
					<br>
					 Email:  
					 <input id="email" name="email" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 NIC:  
					 <input id="nic" name="nic" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Research type & description :  
					 <input id="type" name="type" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Date:  
					 <input id="date" name="date" type="text" class="form-control form-control-sm"> 
					 
					 
			

					 
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidAppIDSave" name="hidAppIDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>  
				<div id="divItemsGrid">   
					<%    
					Researchers appObj = new Researchers();
						out.print(appObj.readResearchers());   
					%>  
					
				</div> 
				  
 			</div>
 		 
 		</div>    
 		
 
	</div> 

</body>

</html>
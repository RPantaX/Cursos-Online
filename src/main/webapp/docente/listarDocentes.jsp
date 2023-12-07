<%@page import="model.DocenteDTO"%> 
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de Docentes</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<link href="DataTables/datatables.min.css" rel="stylesheet"/>
	<style type="text/css">
	.full-width-container {
      width: 80%; 
      margin: 0 auto;  
	}
	</style>
</head>
<body>
<%@ include file="../comun/menu.jsp" %>
  	<br>
  	<div class="full-width-container" > 
	     <div style="display:flex; flex-direction:row; width: 80%;">	 
			    <img src="images/docente64.png">
			    <h1 class="h1 mb-3 font-weight-normal">Listado de Docentes</h1>	 
		</div> 
	</div>
 	<br>
 	
 	<div class="full-width-container">
            <div class="table-responsive">            
              		 <div>
					  		<img src="images/addDoc32.png">
					        <a href="${pageContext.request.contextPath}/docenteServlet?opcion=nue"> Nuevo Docente </a>
					 </div> 					 
					 <table id="table"  class="table table-hover">
					 <thead>
						<tr>
							<th>Id</th>
							<th>Nombre</th>
							<th>Apellido Paterno</th>
							<th>Apellido Materno</th>
							<th>Email</th>
							<th>Celular</th>
							<th>Profesión</th>
							<th></th>
							<th></th>
						</tr>
					 </thead>				      
				     <tbody>				   
				       <c:forEach items="${lstDocentes}" var="doce">
				     	 <tr>
						     	<td>${doce.codigo}</td>
						      	<td>${doce.nombre}</td>
						      	<td>${doce.apePaterno}</td>
						      	<td>${doce.apeMaterno}</td>
						      	<td>${doce.email}</td>
						      	<td>${doce.celular}</td>
						      	<td>${doce.profesion.desProfesion}</td>
						      	<td><img src="images/editDoc32.png"> <a href="${pageContext.request.contextPath}/docenteServlet?opcion=bus&cod=${doce.codigo}"> Actualizar </a></td>
						      	<td><img src="images/deleteDoc32.png"> <a href="${pageContext.request.contextPath}/docenteServlet?opcion=eli&cod=${doce.codigo}"> Eliminar </a></td>
						</tr>						
						</c:forEach>
						</tbody>
					</table>              
            </div>
	</div>
 
	
		
	  <%    String mensaje = (String)request.getAttribute("mensaje");
			if (mensaje==null) mensaje="";	
	  %>  
	  		<%=mensaje%>   
	  
	<script src="js/jquery.js"></script>
	<script src="DataTables/datatables.min.js"></script>
	<script>
		$(document).ready( function () {		 
			 $('#table').DataTable({
		    	 "language": {
		             "lengthMenu": "Mostrar _MENU_ registros",
		             "zeroRecords": "No se encontraron resultados",
		             "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
		             "infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
		             "infoFiltered": "(filtrado de un total de _MAX_ registros)",
		             "sSearch":"Buscar:",
		             "oPaginate":{
		            	 "sFirst":"Primero",
		            	 "sLast":"Último",
		            	 "sNext":"Siguiente",
		            	 "sPrevious":"Anterior",
		             },
		             "sProcessing":"Procesando..."
		         },
		         "lengthMenu": [5, 10, 15, 20]
		    	
		    });
		} );
	 </script>		
	  		
</body>
</html>

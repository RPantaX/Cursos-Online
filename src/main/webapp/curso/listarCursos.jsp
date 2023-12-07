<%@page import="model.CursoDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de Cursos</title>
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
			    <img src="images/curso64.png">
			    <h1 class="h1 mb-3 font-weight-normal">Listado de Cursos</h1>	 
		</div> 
	</div>
	
	<br>
	
 

	 <div class="full-width-container">
            <div class="table-responsive">      		
				     <div>
				     		<img src="images/new32.png">
					        <a href="${pageContext.request.contextPath}/cursoServlet?opcion=nue"> Nuevo Curso </a>
					 </div> 
					         		         	 
					<table id="table" class="table table-hover">
					 <thead>
						<tr>
							<th>Id</th>
							<th>Nombre</th>
							<th>Categoría</th>
							<th>Nivel</th>
							<th>modalidad</th>
							<th>creditos</th>
							<th>precio</th>
							<th></th>
							<th></th>
						</tr>
					 </thead>
				      <tbody>
				      <c:forEach items="${lstCursos}" var="cur">
					     	 <tr>
							     	<td>${cur.codigo}</td>
							      	<td>${cur.nombre}</td>
							      	<td>${cur.categoria.desCategoria}</td>
							      	<td>${cur.nivel.desNivel}</td>
							      	<td>${cur.modalidad.desModalidad}</td>
							      	<td>${cur.creditos}</td>
							      	<td>${cur.precio}</td>
							      	<td> <img src="images/edit32.png"><a href="${pageContext.request.contextPath}/cursoServlet?opcion=bus&cod=${cur.codigo}"> Actualizar </a></td>
							      	<td> <img src="images/delete32.png"><a href="${pageContext.request.contextPath}/cursoServlet?opcion=eli&cod=${cur.codigo}"> Eliminar </a></td>
							      	 
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
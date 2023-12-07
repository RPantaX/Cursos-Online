<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de Cursos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

</head>
<body>
<%@ include file="../comun/menu.jsp" %>

	<div class="container">
		<br>
		<h1 class="h1 mb-3 font-weight-normal">Cursos Disponibles</h1>
	</div>
	<div class="container">
		<a href="ventaServlet?opcion=carrito" style="font-size: 20px; text-decoration: none;">
		<img src="images/car.png">
		<label style="font-size: 20px;">( ${contador} )</label>Carrito</a>
	</div>
	<br>
	
	<div class="container mt-4">
		<div class="row">
			<c:forEach items="${lstCursos}" var="curso">
			<div class="col-sm-4">
				<div class="card">
					<div class="card-header">
						<label>${curso.nombre}</label>
					</div>
					<div class="card-body">
						<i>s/. ${curso.precio}</i>
						<img src="images/${curso.codigo}.jpg" width="200" height="180">
					</div>
					<div class="card-footer text-center">
						<label>${curso.nombre}</label>
						<div>
							<a href="ventaServlet?opcion=addCar&cod=${curso.codigo}" class="btn btn-outline-info">Agregar a Carrito</a>
							<a href="" class="btn btn-danger">Comprar</a>
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>
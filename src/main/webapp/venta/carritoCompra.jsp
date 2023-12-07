<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Compra de Cursos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

</head>
<body>
<%@ include file="../comun/menu.jsp" %>
<div class="container mt-4">
<br>
	<div class="row">
		<div class="col-sm-8">
			<table class="table-hover">
				<thead>
					<tr>
						<th>Item</th>
						<th>Descripcion</th>
						<th>Precio</th>
						<th>Cant</th>
						<th>SubTotal</th>
						<th>Accion</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listaCarrito}" var="p">
						<tr>
							<td>${p.getItem()}</td>
							<td>${p.getNombre()}
								<img src="images/${p.getCodCurso()}.jpg" width="100" height="100">
							</td>
							<td>${p.getPrecioCompra()}</td>
							<td>
							<input type="number" id="cantidad" value="${p.getCantidad()}" class="form-control text-center" min="1" onchange="validar(${p.getCodCurso()}, this);">
							</td>
							<td>${p.getSubTotal()}</td>
							<td>
								<input type="hidden" id="idp" value="${p.getCodCurso()}">
								<img src="images/delete32.png"><a href="${pageContext.request.contextPath}/ventaServlet?opcion=eliCar&cod=${p.getCodCurso()}">Eliminar</a> 
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="card" style="width: 18rem;">
			<h3 class="card-header">Generar Venta</h3>
			<div class="card-body">
				<label>Subtotal:</label>
				<input type="text" value="S/.${totalPagar}" readonly="" class="form-control">
				<label>Descuento:</label>
				<input type="text" value="S/. 0.00" readonly="" class="form-control">
				<label>Total Pagar:</label>
				<input type="text" value="S/.${totalPagar}" readonly="" class="form-control">
			</div>
			<div class="card-footer text-muted">
			<a href="#" class="btn btn-info btn-block">Realizar Pago</a>
			<a href="${pageContext.request.contextPath}/ventaServlet?opcion=generarVenta&idCliente=${datosUsuario.codigo}" class="btn btn-danger btn-block">Generar Venta</a>
			</div>
		</div>
	</div>
</div>
<script> src="js/jquery.js"</script>
	<script>
		function validar(id, val){
			
			$.ajax({
				type:'POST',
				url: "ventaServlet?opcion=actCar",
				data: "cod="+id+"&cantidad="+val.value,
				success: function(data, textStatus, jqXHR){
					location.href="ventaServlet?opcion=carrito"
				}
			});
		}
	</script>
</body>
</html>
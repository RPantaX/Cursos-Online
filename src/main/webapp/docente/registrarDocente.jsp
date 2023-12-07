<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Registrar Docente</title>
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<%@ include file="../comun/menu.jsp" %>
    <br>
    <div class="container" style="display: flex;">	
		    <img src="images/addDoc64.png">
		    <h1 class="h1 mb-3 font-weight-normal">Registrar Docente</h1>
	</div> 
	<div class="container mt-5">
	    <div class="row">
	        <div class="col-md-10">   
				    <form id="frmRegistroDocente" action="docenteServlet" method="post"  >
				        <div class="row">
				            <div class="col-md-12">

									<div class="form-row">
						                <div class="form-group col-md-12">
						                    <img src="images/information30.png">
						                    <label for="txtDatosPersonales">Datos Personales del Docente</label> 
						                     <hr class="my-2">
						                </div>
					                </div>
				                
									<div class="form-row">
						                <div class="form-group col-md-6">
						                    <label for="txtNombres">Nombres</label>
						                    <input type="text" class="form-control" id="txtNombres" name="txtNombres">
						                </div>
						            </div>
				                 	<br>
				                
					                <div class="form-row"> 
							            <div class="form-group col-md-6">
							                 <label for="txtApePat">Apellido Paterno</label>
							                 <input type="text" class="form-control" id="txtApePat" name="txtApePat">
							            </div>
							            <div class="form-group col-md-6">
							                 <label for="txtApeMat">Apellido Materno</label>
							                 <input type="text" class="form-control" id="txtApeMat" name="txtApeMat">
							            </div>
					                </div>
				                	 <br>
				                	 
		                			<div class="form-row"> 
						                <div class="form-group col-md-12">
						                    <label for="txtEmail">Email</label>
						                    <input type="email" class="form-control" id="txtEmail" name="txtEmail">
						                </div>				                
				                 	</div>
				                    <br>
				                
					                <div class="form-row"> 
						                <div class="form-group col-md-6">
						                    <label for="txtNumCel">Núm. Celular</label>
						                    <input type="text" class="form-control" id="txtNumCel" name="txtNumCel">
						                </div>
					                </div>				                
				              		<br>
				              	
					              	<div class="form-row"> 
						                <div class="form-group col-md-6">
						                    <label for="cboProfesion">Profesión</label>
						                    <select class="form-control" id="cboProfesion" name="cboProfesion">
						                        <option value="1">Ingeniero(a) de Sistemas</option>
						                        <option value="2">Ingeniero(a) de Datos</option>
						                        <option value="3">Ingeniero(a) de Software</option>
						                        <option value="4">Diseñador(a) Gráfico(a)</option>
						                        <option value="5">Licenciado(a) en Marketing</option>
						                    </select>
						                </div>
					                </div>
				                    <br>
				                 
					                <div class="form-group"> 
							        	<button type="submit" name="opcion" value="reg" class="btn btn-primary">&nbsp;&nbsp;&nbsp;Registrar&nbsp; &nbsp;&nbsp;</button>
										<a class="btn btn-secondary" href="${pageContext.request.contextPath}/docenteServlet?opcion=lis"> Regresar a Listar </a>
							        </div>	
							        
							     	   <%    String mensaje = (String)request.getAttribute("mensaje");
					                    	if (mensaje==null) mensaje="";	
									   %>  
										 
									   <%=mensaje%>    
				            </div>
				        </div>		        		        
				    </form>
	        </div>
	    </div>
	</div>
	
	
	
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
        	
        	 $.validator.addMethod("numericOnly", function(value, element) {
        	        return this.optional(element) || /^[0-9]+$/.test(value);
        	    }, "Ingrese solo números.");
        	 
        	 
            $("#frmRegistroDocente").validate({
                rules: {
                	txtNombres: "required",
                	txtApePat: "required",
                	txtApeMat: "required",
                	txtEmail: "required",
                	txtNumCel: {
                          required: true,
                          numericOnly: true 
                      },
                	cboProfesion: "required"
                 
                },
                messages: {
                    txtNombres: "Ingrese el nombre del curso",
                    txtApePat: "Ingrese el apellido Paterno",
                    txtApeMat: "Ingrese el apellido Materno", 
                    txtEmail: {
                        required: "Ingrese el correo del docente",
                        email: "Ingrese una dirección de correo electrónico válida"
                    },
                    txtNumCel: {
                        required: "Ingrese el número celular del docente",
                        numericOnly: "Ingrese solo números para el número celular"
                    },
                    cboProfesion: "Seleccione la profesión del docente"
                    
                },
                errorElement: "span",
                errorPlacement: function(error, element) {
                    error.addClass("invalid-feedback");
                    element.closest(".form-group").append(error);
                },
                highlight: function(element, errorClass, validClass) {
                    $(element).addClass("is-invalid").removeClass("is-valid");
                },
                unhighlight: function(element, errorClass, validClass) {
                    $(element).removeClass("is-invalid").addClass("is-valid");
                },
                submitHandler: function(form) {
                    form.submit();
                }
            });
        });
    </script>
</body>
</html>
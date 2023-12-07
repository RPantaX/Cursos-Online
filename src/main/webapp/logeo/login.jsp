<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <!-- Enlaces a los archivos CSS de Bootstrap -->
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
   <div class="container">
        <div class="row justify-content-center mt-5">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        Iniciar Sesión
                    </div>
                    <div class="card-body d-flex align-items-center">
                        <!-- Columna para el formulario -->
                        <div class="col-md-6">
                            <img src="images/login.png">
                        </div>
                         <!-- Columna para la imagen -->
                        <div class="col-md-6">
                            <form id="loginForm" action="loginServlet" method="post">
                                <div class="mb-3">
                                    <label for="txtUsuario" class="form-label">Usuario:</label>
                                    <input type="text" class="form-control" id="txtUsuario" name="txtUsuario">
                                    <div id="usuarioError" class="text-primary" style="font-weight: bold;"></div>
                                </div>
                                <div class="mb-3">
                                    <label for="txtContrasena" class="form-label">Contraseña:</label>
                                    <input type="password" class="form-control" id="txtContrasena" name="txtContrasena">
                                    <div id="contrasenaError" class="text-primary" style="font-weight: bold;"></div>
                                </div>
                                <button type="submit" name="opcion" value="log"  class="btn btn-primary">Iniciar Sesión</button>
                            </form>
                        </div>
                    </div>
                    
                     <div class="${mensaje != null ? 'alert alert-danger text-center mt-3' : ''}">
						    ${mensaje}
					</div>
					
                </div>
            </div>
        </div>
    </div>
    
     
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#loginForm').validate({
                rules: {
                	txtUsuario: {
                        required: true
                    },
                    txtContrasena: {
                        required: true
                    }
                },
                messages: {
                	txtUsuario: {
                        required: "Por favor, ingresa el nombre de usuario."
                    },
                    txtContrasena: {
                        required: "Por favor, ingresa la contraseña."
                    }
                },
                errorElement: "span",
                errorPlacement: function(error, element) {
                    if (element.attr("name") === "txtUsuario") {
                        error.appendTo("#usuarioError");
                    } else if (element.attr("name") === "txtContrasena") {
                        error.appendTo("#contrasenaError");
                    }
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
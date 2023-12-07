delimiter //
create procedure usp_obtenerDatosAcceso(IN usuario VARCHAR(50),IN contrasena VARCHAR(50))
begin
    select c.id, c.nombre, c.apePaterno, c.apeMaterno, c.email, c.celular, u.username, u.contrasena, t.des_tipo_usuario
	from tb_usuario u inner join  tb_cliente c on u.id = c.id_usuario  inner join tb_tipousuario t  on u.id_tipo_usuario=t.id
	where u.username=usuario and u.contrasena=contrasena;
end
//

call usp_obtenerDatosAcceso('diego55','contrasena55');
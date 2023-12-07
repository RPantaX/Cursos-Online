delimiter //
create procedure usp_validaAcceso(IN usuario VARCHAR(50),IN contrasena VARCHAR(50))
begin
    select  u.id, u.username, u.contrasena, t.id as id_tipo_usuario, t.des_tipo_usuario
	from 	tb_usuario u  inner join tb_tipousuario t  on u.id_tipo_usuario=t.id
	where u.username=usuario and u.contrasena=contrasena;
end
//

call usp_validaAcceso('diego55','contrasena55');
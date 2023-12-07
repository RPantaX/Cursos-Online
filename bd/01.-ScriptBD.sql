

-- borra la bd si existe
DROP DATABASE IF EXISTS bd_ventas_cursos;
-- creamos la bd
CREATE DATABASE bd_ventas_cursos;
-- activamos la bd
USE bd_ventas_cursos;

-- Crear tabla Modalidad
CREATE TABLE tb_modalidad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    des_Modalidad VARCHAR(50) NOT NULL
);

INSERT INTO tb_modalidad (des_Modalidad)VALUES 
 ('Presencial'),
 ('Virtual'),
 ('Híbrido');
  
 
-- Crear tabla Nivel
CREATE TABLE tb_nivel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    des_Nivel VARCHAR(50) NOT NULL
);

INSERT INTO tb_nivel (des_Nivel)VALUES 
 ('Básico'),
 ('Intermedio'),
 ('Avanzado');
 
-- Crear tabla Categoria
CREATE TABLE tb_categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    des_Categoria VARCHAR(50) NOT NULL
);

INSERT INTO tb_categoria (des_Categoria) VALUES 
('Diseño y Desarrollo de Base de Datos'),
('Desarrollo de Video Juegos'), 
('Desarrollo Web'), 
('Desarrollo de móvil'),
('Desarrollo de Software'),
('Herramientas de desarrollo de Software'),
('Lenguajes de programación'), 
('Testeo de Software'), 
('Redes y Seguridad') ,
('Sistemas Operativos');


-- Crear tabla Cursos
CREATE TABLE tb_cursos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    id_categoria   INT,
    id_nivel   INT,
    id_modalidad  INT,
    creditos INT,
    precio  decimal(18,2),
    FOREIGN KEY (id_categoria) REFERENCES tb_categoria(id),
    FOREIGN KEY (id_nivel) REFERENCES tb_nivel(id),
    FOREIGN KEY (id_modalidad) REFERENCES tb_modalidad(id)
);

INSERT INTO tb_cursos (nombre, id_categoria,id_nivel, id_modalidad, creditos, precio)VALUES 
 ('PostgreSQL',1, 2, 1, 4, 60.00),
 ('Mysql',1, 1, 2, 5, 280.50),
 ('Sql Server',1, 1, 2, 5, 350.50),
 ('Oracle',1, 1, 2, 5, 480.70), 
 
 ('Introducción a Unity',2, 2, 1, 4, 50.99),
 ('Unity Avanzado', 2,3, 3, 4, 90.99), 
 ('C++ Video Juego',2, 2, 2, 4, 55.99),
 
 ('JavaScript',3, 1, 1, 4, 44.90),
 ('React JS',3, 2, 1, 4, 39.99),
 ('Angular',3, 3, 2, 4, 180.50),
 ('CSS3',3, 1, 2, 3, 60.00),
 ('HTML5',3, 1, 3, 3, 160.00),
 ('Node.JS',3, 2, 3, 4, 36.70),
 
 
 ('Desarrollo en iOS',4, 2, 3, 4, 90.99),
 ('Desarrollo en Android',4, 2, 1, 4, 299.99),
 ('Programación en Kotlin',4, 2, 1, 4, 109.99),
 
 ('ASP.NET Core',5, 3, 1, 8, 160.99),
 ('Java con JSP y MySQL',5, 2, 1, 4, 299.99),
 ('Angular con SpringBoot',5, 3, 3, 5, 750.86),
 ('SpringBoot y Microservicios',5, 2, 1, 4, 500.99),
 
 ('Docker',6, 3, 1, 8, 240.99),
 ('Kubernet',6, 3, 1, 8, 240.99),
 ('Jenkins',6, 3, 1, 8, 120.99),
 ('GitHub',6, 3, 1, 8, 60.99),
 
 ('Python', 7,1, 1, 4, 120.99),
 ('Java 8', 7,1, 1, 4, 130.85),
 ('Java 11', 7,1, 1, 4, 140.85),
 ('Java 17', 7,2, 3, 4, 180.85),
 ('C#', 7,2, 3, 4, 220.85),
 ('Visual Basic', 7, 2, 3, 4, 100.99),

 
 ('POSTMAN',8, 2, 1, 5, 100.00),
 ('JMeter',8, 2, 2, 4, 299.99),
 ('Selenium',8, 2, 2, 4, 199.99),
 
 
 ('Ciberseguridad',9, 2, 2, 4, 109.99),
 ('Hacking Ético a redes WiFi',9, 2, 2, 4, 39.99),


 ('Linux',10, 2, 1, 5, 100.00),
 ('Soporte para Mac',10, 2, 2, 4, 299.99),
 ('Windows Server',10, 2, 2, 4, 199.99);
 
 
select * from tb_cursos;



CREATE TABLE tb_profesion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    des_profesion VARCHAR(255) NOT NULL
);

INSERT INTO tb_profesion (des_profesion) VALUES
('Ingeniero(a) de Sistemas'),
('Ingeniero(a) de Datos'),
('Ingeniero(a) de Software'),
('Diseñador(a) Gráfico(a)'),
('Licenciado(a) en Marketing');

select * from  tb_profesion;

 
-- Crear tabla Nivel
CREATE TABLE tb_tipoUsuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    des_Tipo_Usuario VARCHAR(50) NOT NULL
);


INSERT INTO tb_tipoUsuario (des_Tipo_Usuario) VALUES
('Cliente'),
('Docente'),
('Empleado');
 
select * from  tb_tipoUsuario;

CREATE TABLE tb_usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    fecha_registro  DATE NOT NULL,
    estado_registro INT,
    id_tipo_usuario INT,
    CONSTRAINT fk_id_tipo_usuario FOREIGN KEY (id_tipo_usuario) REFERENCES tb_tipoUsuario(id)
);

INSERT INTO tb_usuario ( username, contrasena, fecha_registro, estado_registro, id_tipo_usuario) VALUES
('juan123', 'contrasena123', CURDATE(), 1,1), 
('maria456', 'contrasena456', CURDATE(), 1,1), 
('pedro789', 'contrasena789', CURDATE(), 1,1), 
('sofia101', 'contrasena101', CURDATE(), 1,1),
('luis2022', 'contrasena2022', CURDATE(), 1,1), 
('ana12345', 'contrasena12345', CURDATE(), 1,1),
('carla777', 'contrasena777', CURDATE(), 1,1), 
('diego55', 'contrasena55', CURDATE(), 1,1), 
('laura90', 'contrasena90', CURDATE(), 1,1), 
('elena123', 'contrasena123', CURDATE(), 1,1),
('luisa321','contrasena123', CURDATE(), 1,1),
('rodrigo123', 'contrasena123', CURDATE(), 1,1),
('vanesa11','contrasena123', CURDATE(), 1,1),
('felipe22', 'contrasena123', CURDATE(), 1,1),
('daniela33','contrasena123', CURDATE(), 1,1),
('rosa44', 'contrasena123', CURDATE(), 1,1),
('ricardo55','contrasena123', CURDATE(), 1,1),
('alfonso66', 'contrasena123', CURDATE(), 1,1),
('esteban12','contrasena123', CURDATE(), 1,1),
('diana12', 'contrasena123', CURDATE(), 1,1),
('rafael23', 'contrasena123', CURDATE(), 1,1),
('emilia23', 'contrasena123', CURDATE(), 1,1),
('samantha23','contrasena123', CURDATE(), 1,1),
('david43', 'contrasena123', CURDATE(), 1,1),
('mateo43','contrasena123', CURDATE(), 1,1),
('patricia54', 'contrasena123', CURDATE(), 1,1),
('antonio54', 'contrasena123', CURDATE(), 1,1),
('marcos16','contrasena123', CURDATE(), 1,1),
('ruth61', 'contrasena123', CURDATE(), 1,1),
('omar66','contrasena123', CURDATE(), 1,1),

('ronald45',  'contrasena123', CURDATE(), 1,2), 
('mirko56',  'contrasena123', CURDATE(), 1,2), 
('liliana67',  'contrasena123', CURDATE(), 1,2), 
('ximena78',  'contrasena123', CURDATE(), 1,2), 
('santiago89',  'contrasena123', CURDATE(), 1,2), 
('pedro90',  'contrasena123', CURDATE(), 1,2), 
('maria09',  'contrasena123', CURDATE(), 1,2), 
('ana213',  'contrasena123', CURDATE(), 1,2), 
('carlos543',  'contrasena123', CURDATE(), 1,2), 
('bruno654',  'contrasena123', CURDATE(), 1,2), 
('norma431',  'contrasena123', CURDATE(), 1,2), 
('martha564', 'contrasena123', CURDATE(), 1,2), 
('cecilia876',  'contrasena123', CURDATE(), 1,2), 
('felipe176',  'contrasena123', CURDATE(), 1,2), 
('isabel987',  'contrasena123', CURDATE(), 1,2), 
('susana098',  'contrasena123', CURDATE(), 1,2), 
('camila546',  'contrasena123', CURDATE(), 1,2), 
('daniel345',  'contrasena123', CURDATE(), 1,2), 
('patricia14',  'contrasena123', CURDATE(), 1,2), 
('gustavo53',  'contrasena123', CURDATE(), 1,2), 
('francisco654',  'contrasena123', CURDATE(), 1,2), 
('juan765',  'contrasena123', CURDATE(), 1,2), 
('andrea786',  'contrasena123', CURDATE(), 1,2), 
('sandra378',  'contrasena123', CURDATE(), 1,2), 
('david986',  'contrasena123', CURDATE(), 1,2), 
('antonio965',  'contrasena123', CURDATE(), 1,2), 
('maria985', 'contrasena123', CURDATE(), 1,2), 
('monica865',  'contrasena123', CURDATE(), 1,2), 
('ana874',  'contrasena123', CURDATE(), 1,2), 
('paola839',  'contrasena123', CURDATE(), 1,2), 
('mariano268',  'contrasena123', CURDATE(), 1,2), 
('diana953',  'contrasena123', CURDATE(), 1,2);





select * from  tb_usuario;

 



CREATE TABLE tb_cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apePaterno VARCHAR(100) NOT NULL,
    apeMaterno VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    celular  numeric NOT NULL ,
    fecha_registro  DATE NOT NULL,
    estado_registro INT,
    id_usuario INT,
    CONSTRAINT fk_id_usuarioCli FOREIGN KEY (id_usuario) REFERENCES tb_Usuario(id)
);

 call usp_obtenerDatosAcceso ('juan123','contrasena123')
 
INSERT INTO tb_cliente (nombre, apePaterno, apeMaterno, email, celular, fecha_registro, estado_registro,id_usuario)VALUES
('Juan', 'Perez', 'Gomez', 'juan@gmail.com',963852741,  CURDATE(), 1,1), 
('Maria', 'Gomez', 'Martinez', 'maria@gmail.com',963852741,  CURDATE(), 1,2), 
('Pedro', 'Lopez', 'Fernandez', 'pedro@gmail.com',963852741,  CURDATE(), 1,3), 
('Sofia', 'Rodriguez', 'Hernandez', 'sofia@gmail.com',963852741,  CURDATE(), 1,4),
('Luis', 'Martinez', 'Fernandez', 'luis@gmail.com',963852741, CURDATE(), 1,5), 
('Ana', 'Gonzalez', 'Ramirez', 'ana@gmail.com',963852741,  CURDATE(), 1,6),
('Carla', 'Fernandez', 'Lopez', 'carla@gmail.com',963852741,  CURDATE(), 1,7), 
('Diego', 'Hernandez', 'Soto', 'diego@gmail.com',963852741, CURDATE(), 1,8), 
('Laura', 'Lopez', 'Gomez', 'laura@gmail.com',963852741, CURDATE(), 1,9), 
('Elena', 'Ramirez', 'Soto', 'elena@gmail.com',963852741,  CURDATE(), 1,10),
('Luisa', 'Gomez', 'Mendez', 'luisa@gmail.com',963852741, CURDATE(), 1,11),
('Rodrigo', 'Hernandez', 'Soto', 'rodrigo@gmail.com',963852741, CURDATE(), 1,12),
('Vanesa', 'Lopez', 'Gomez', 'vanesa@gmail.com',963852741, CURDATE(), 1,13),
('Felipe', 'Ramirez', 'Hernandez', 'felipe@gmail.com',963852741, CURDATE(), 1,14),
('Daniela', 'Martinez', 'Fernandez', 'daniela@gmail.com',963852741, CURDATE(), 1,15),
('Rosa', 'Gonzalez', 'Ramirez', 'rosa@gmail.com',963852741, CURDATE(), 1,16),
('Ricardo', 'Fernandez', 'Lopez', 'ricardo@gmail.com',963852741, CURDATE(), 1,17),
('Alfonso', 'Hernandez', 'Soto', 'alfonso@gmail.com',963852741, CURDATE(), 1,18),
('Esteban', 'Lopez', 'Gomez', 'esteban@gmail.com',963852741, CURDATE(), 1,19),
('Diana', 'Ramirez', 'Soto', 'diana@gmail.com',963852741, CURDATE(), 1,20),
('Rafael', 'Gomez', 'Mendez', 'rafael@gmail.com',963852741, CURDATE(), 1,21),
('Emilia', 'Hernandez', 'Soto', 'emilia@gmail.com',963852741, CURDATE(), 1,22),
('Samantha', 'Lopez', 'Gomez', 'samantha@gmail.com',963852741, CURDATE(), 1,23),
('David', 'Ramirez', 'Hernandez', 'david@gmail.com',963852741, CURDATE(), 1,24),
('Mateo', 'Martinez', 'Fernandez', 'mateo@gmail.com',963852741, CURDATE(), 1,25),
('Patricia', 'Gonzalez', 'Ramirez', 'patricia@gmail.com',963852741, CURDATE(), 1,26),
('Antonio', 'Fernandez', 'Lopez', 'antonio@gmail.com',963852741, CURDATE(), 1,27),
('Marcos', 'Hernandez', 'Soto', 'marcos@gmail.com',963852741, CURDATE(), 1,28),
('Ruth', 'Lopez', 'Gomez', 'ruth@gmail.com',963852741, CURDATE(), 1,29),
('Omar', 'Ramirez', 'Soto', 'omar@gmail.com',963852741, CURDATE(), 1,30);



select * from  tb_cliente;






CREATE TABLE tb_docente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    apePaterno VARCHAR(100) NOT NULL,
    apeMaterno VARCHAR(100) NOT NULL,
	email  VARCHAR(100)  NOT NULL UNIQUE,
    celular  numeric NOT NULL ,
	id_profesion INT,
	id_usuario INT,
    CONSTRAINT fk_id_profesion FOREIGN KEY (id_profesion) REFERENCES tb_profesion(id),
	CONSTRAINT fk_id_usuarioDoc FOREIGN KEY (id_usuario) REFERENCES tb_Usuario(id)
    
);


INSERT INTO tb_docente (nombre, apePaterno, apeMaterno, email, celular, id_profesion, id_usuario) VALUES
('Ronald', 'Trujillo', 'Mino', 'juanperez@gmail.com', 987456321, 1, 31),
('Mirko', 'Soto', 'Paredes', 'mirkoparedes@gmail.com', 931411254, 2, 32),
('Liliana', 'Mino', 'Vargas', 'lilianamino@gmail.com', 941462811, 3, 33),
('Ximena', 'Suarez', 'Mendez', 'ximenasuarez@gmail.com', 933335478, 3, 34),
('Santiago', 'Trujillo', 'Zavala', 'ronaldtrujillo@gmail.com', 959584468, 3, 35),
('Pedro', 'López', 'Martínez', 'pedrolopez@gmail.com', 978598978, 4, 36),
('María', 'González', 'López', 'mariagonzale@gmail.com', 999666587, 5, 37),
('Ana', 'Vargas', 'Mendoza', 'ana_vargas@gmail.com', 999444777, 2, 38),
('Carlos', 'Sandoval', 'Torres', 'carlos_sandoval@gmail.com', 999111888, 2, 39),
('Bruno', 'Molina', 'Cruz', 'bruno_molina@gmail.com', 999888444, 3, 40),
('Norma', 'Ortega', 'Naranjo', 'norma_ortega@gmail.com', 999222555, 3, 41),
('Martha', 'Alvarado', 'Fernández', 'martha_alvarado@gmail.com', 999333666, 3, 42),
('Cecilia', 'Morales', 'Cardenas', 'cecilia_morales@gmail.com', 999999555, 4, 43),
('Felipe', 'Hernández', 'Mendoza', 'felipe_hernandez@gmail.com', 999888333, 4, 44),
('Isabel', 'Castro', 'Castañeda', 'isabel_castro@gmail.com', 999444222, 4, 45),
('Susana', 'Vega', 'Sandoval', 'susana_vega@gmail.com', 999333444, 5, 46),
('Camila', 'Pacheco', 'Lara', 'camila_pacheco@gmail.com', 999666111, 5, 47),
('Daniel', 'Castillo', 'Ortega', 'daniel_castillo@gmail.com', 999111999, 5,48),
('Patricia', 'Tapia', 'Naranjo', 'patricia_tapia@gmail.com', 999777333, 5, 49),
('Gustavo', 'Díaz', 'López', 'gustavo_diaz@gmail.com', 999555666, 1, 50),
('Francisco', 'Santiago', 'Fernández', 'francisco_santiago@gmail.com', 999888999, 1, 51),
('Juan', 'Castro', 'Morales', 'juan_castro@gmail.com', 999111777, 1, 52),
('Andrea', 'Cervantes', 'López', 'andrea_cervantes@gmail.com', 999888111, 1, 53),
('Sandra', 'Lara', 'Naranjo', 'sandra_lara@gmail.com', 999999444, 2, 54),
('David', 'López', 'Torres', 'david_lopez@gmail.com', 999555777, 2, 55),
('Antonio', 'Soto', 'Vega', 'antonio_soto@gmail.com', 999777111, 3, 56),
('María', 'Carrasco', 'Mendoza', 'maria_carrasco@gmail.com', 999888555, 4, 57),
('Mónica', 'Hernández', 'López', 'monica_hernandez@gmail.com', 999111333, 5, 58),
('Ana', 'Cárdenas', 'Fernández', 'ana_cardenas@gmail.com', 999777222, 1,59),
('Paola', 'Ortega', 'Naranjo', 'paola_ortega@gmail.com', 999555111, 2,60),
('Mariano', 'Sandoval', 'Torres', 'mariano_sandoval@gmail.com', 999666999, 3,61),
('Diana', 'Cruz', 'Vega', 'diana_cruz@gmail.com', 999333222, 4,62);

select * from  tb_docente;

	                                
                                    
 
 -- Tabla de Ventas (Transacciones Generales)
CREATE TABLE tb_venta (
    id_venta   INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    fecha_venta TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES tb_cliente(id)
);

-- Tabla de Detalles de Venta (Cursos comprados en cada venta)
CREATE TABLE tb_venta_detalle (
    id_det_venta INT PRIMARY KEY AUTO_INCREMENT,
    id_venta INT NOT NULL,
    id_curso INT NOT NULL,
	cantidad INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES tb_venta(id_venta),
    FOREIGN KEY (id_curso) REFERENCES tb_cursos(id)
);
select @@identity as idCabVenta
select *from tb_venta_detalle



					                        
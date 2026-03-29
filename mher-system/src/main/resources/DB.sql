CREATE DATABASE SISGCMHER CHARACTER set utf8mb4 COLLATE utf8mb4_unicode_ci;


USE SISGCMHER;

CREATE TABLE pacientes(
id INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(250) NOT NULL,
apellidos VARCHAR(250) NOT NULL,
fecha_de_nacimiento DATE,
genero CHAR NOT NULL CHECK (genero = 'M' || genero = 'F'),
active TINYINT NOT NULL,
numero_de_seguridad VARCHAR(250) NOT NULL
);


CREATE TABLE especialidades(
id INT PRIMARY KEY AUTO_INCREMENT,
nombre_especialidad varchar(250) NOT NULL
);

CREATE TABLE doctores(
id INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(250) NOT NULL,
apellidos VARCHAR(250) NOT NULL,
genero CHAR NOT null CHECK (genero = 'M' || genero = 'F'),
active TINYINT NOT NULL,
cedula_profesional VARCHAR(250) NOT NULL,
especialidad_id INT NOT null,
FOREIGN KEY (especialidad_id) REFERENCES especialidades(id)
);

CREATE TABLE citas(
id INT PRIMARY KEY AUTO_INCREMENT,
doctor_id INT NOT NULL,
paciente_id INT NOT NULL,
cita_inicio DATETIME,
cita_fin DATETIME,
cita_estado VARCHAR(250) CHECK (cita_estado = 'pendiente' || cita_estado = 'cancelada' || cita_estado = 'completada'),
FOREIGN KEY (doctor_id) REFERENCES doctores(id),
FOREIGN KEY (paciente_id) REFERENCES pacientes (id)
);


CREATE TABLE historias_clinicas(
id INT PRIMARY KEY AUTO_INCREMENT,
paciente_id INT NOT NULL,
doctor_id INT NOT NULL,
motivo_de_la_consulta VARCHAR(2000) NOT NULL,
descripcion_enfermedad VARCHAR(2000) NOT NULL,
antecedentes_medicos VARCHAR(2000) NOT NULL,
diagnostico VARCHAR(2000) NOT NULL,
tratamiento_medico VARCHAR(2000) NOT null,
FOREIGN KEY (doctor_id) REFERENCES doctores(id),
FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);


CREATE TABLE recetario(
id INT PRIMARY KEY AUTO_INCREMENT,
fecha_de_emision DATE NOT NULL,
indicacion_medica VARCHAR(1000) NOT NULL,
via_de_Administracion VARCHAR(1000) NOT NULL,
duracion_del_tratamiento VARCHAR(1000) NOT NULL,
paciente_id INT NOT NULL,
doctor_id INT NOT NULL,
FOREIGN KEY (doctor_id) REFERENCES doctores(id),
FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);





//Consutlas

SELECT *
FROM pacientes;

SELECT *
FROM especialidades;

SELECT *
FROM  doctores;

SELECT *
FROM  citas;



//Restricciones
ALTER TABLE pacientes
ADD CONSTRAINT CHECK (genero IN('M','F'));


ALTER TABLE doctores
ADD CONSTRAINT CHECK (genero IN('M','F'));


INSERT INTO especialidades (nombre_especialidad )
VALUES("Cardiologia");

INSERT INTO especialidades (nombre_especialidad) VALUES
('Cardiología'),
('Pediatría'),
('Dermatología'),
('Neurología'),
('Ginecología'),
('Traumatología'),
('Oncología'),
('Psiquiatría'),
('Oftalmología'),
('Medicina Interna');


INSERT INTO pacientes
(nombre, apellidos, fecha_de_nacimiento, genero, active, numero_de_seguridad) VALUES
('Ana', 'García López', '1985-03-12', 'F', 1, 'NS-1001'),
('Luis', 'Martínez Pérez', '1990-07-22', 'M', 1, 'NS-1002'),
('María', 'Hernández Ruiz', '1978-11-02', 'F', 1, 'NS-1003'),
('Carlos', 'Ramírez Soto', '1982-01-15', 'M', 1, 'NS-1004'),
('Laura', 'Torres Mendoza', '1995-09-30', 'F', 1, 'NS-1005'),
('Jorge', 'Flores León', '1988-06-18', 'M', 1, 'NS-1006'),
('Sofía', 'Vargas Cruz', '2000-12-05', 'F', 1, 'NS-1007'),
('Miguel', 'Ortega Ríos', '1975-04-09', 'M', 1, 'NS-1008'),
('Paola', 'Morales Díaz', '1993-08-21', 'F', 1, 'NS-1009'),
('Andrés', 'Navarro Silva', '1987-02-14', 'M', 1, 'NS-1010');


INSERT INTO doctores
(nombre, apellidos, genero, active, cedula_profesional, especialidad_id) VALUES
('Juan', 'Pérez Gómez', 'M', 1, 'CED-2001', 1),
('Lucía', 'Moreno Díaz', 'F', 1, 'CED-2002', 2),
('Roberto', 'Castillo Vega', 'M', 1, 'CED-2003', 3),
('Elena', 'Ramos Luna', 'F', 1, 'CED-2004', 4),
('Fernando', 'Jiménez Rocha', 'M', 1, 'CED-2005', 5),
('Adriana', 'Salinas Ortiz', 'F', 1, 'CED-2006', 6),
('Héctor', 'Pineda Cruz', 'M', 1, 'CED-2007', 7),
('Patricia', 'Mejía Soto', 'F', 1, 'CED-2008', 8),
('Ricardo', 'López Medina', 'M', 1, 'CED-2009', 9),
('Claudia', 'Fuentes Rangel', 'F', 1, 'CED-2010', 10),
('Jessica Jocelyn','Amador Cuenca','F',1,'CED-2014',1);;

INSERT INTO citas
(doctor_id, paciente_id, cita_inicio, cita_fin, cita_estado) VALUES
(1, 1, '2026-03-01 09:00:00', '2026-03-01 09:30:00', 'pendiente'),
(3, 4, '2026-03-01 10:00:00', '2026-03-01 10:30:00', 'completada'),
(3, 3, '2026-03-02 11:00:00', '2026-03-02 11:30:00', 'cancelada'),
(4, 4, '2026-03-02 12:00:00', '2026-03-02 12:45:00', 'pendiente'),
(5, 5, '2026-03-03 09:30:00', '2026-03-03 10:00:00', 'completada'),
(6, 6, '2026-03-03 10:30:00', '2026-03-03 11:00:00', 'pendiente'),
(7, 7, '2026-03-04 11:00:00', '2026-03-04 11:30:00', 'pendiente'),
(8, 8, '2026-03-04 12:00:00', '2026-03-04 12:30:00', 'completada'),
(9, 9, '2026-03-05 09:00:00', '2026-03-05 09:45:00', 'cancelada'),
(10,10,'2026-03-05 10:00:00', '2026-03-05 10:30:00', 'pendiente');

INSERT INTO historias_clinicas
(paciente_id, doctor_id, motivo_de_la_consulta, descripcion_enfermedad,
 antecedentes_medicos, diagnostico, tratamiento_medico) VALUES
(1, 1, 'Dolor en el pecho', 'Dolor opresivo intermitente', 'Hipertensión', 'Angina estable', 'Control médico y dieta'),
(3, 4, 'Fiebre persistente', 'Fiebre por 3 días', 'Ninguno', 'Infección viral', 'Paracetamol'),
(3, 3, 'Erupción cutánea', 'Lesiones en brazos', 'Alergias', 'Dermatitis', 'Crema tópica'),
(4, 4, 'Dolor de cabeza', 'Cefalea intensa', 'Migraña', 'Migraña crónica', 'Analgesia'),
(5, 5, 'Control ginecológico', 'Revisión anual', 'Ninguno', 'Paciente sana', 'Seguimiento'),
(6, 6, 'Dolor articular', 'Dolor en rodilla', 'Lesión previa', 'Esguince leve', 'Reposo'),
(7, 7, 'Fatiga crónica', 'Cansancio prolongado', 'Diabetes', 'Anemia', 'Suplementos'),
(8, 8, 'Ansiedad', 'Insomnio frecuente', 'Estrés', 'Trastorno de ansiedad', 'Terapia'),
(9, 9, 'Visión borrosa', 'Dificultad para enfocar', 'Miopía', 'Astigmatismo', 'Lentes'),
(10,10,'Chequeo general', 'Sin síntomas', 'Ninguno', 'Buen estado de salud', 'Revisión anual');

INSERT INTO recetario
(fecha_de_emision, indicacion_medica, via_de_Administracion,
 duracion_del_tratamiento, paciente_id, doctor_id) VALUES
('2026-03-01', 'Tomar analgésico', 'Oral', '5 días', 1, 1),
('2026-03-01', 'Antipirético', 'Oral', '3 días', 3, 4),
('2026-03-02', 'Crema dermatológica', 'Tópica', '7 días', 3, 3),
('2026-03-02', 'Analgésico fuerte', 'Oral', '5 días', 4, 4),
('2026-03-03', 'Vitaminas prenatales', 'Oral', '30 días', 5, 5),
('2026-03-03', 'Antiinflamatorio', 'Oral', '7 días', 6, 6),
('2026-03-04', 'Hierro', 'Oral', '30 días', 7, 7),
('2026-03-04', 'Ansiolítico', 'Oral', '14 días', 8, 8),
('2026-03-05', 'Gotas oftálmicas', 'Ocular', '10 días', 9, 9),
('2026-03-05', 'Multivitamínico', 'Oral', '30 días', 10, 10);


SELECT p.nombre,p.apellidos,p.genero,
	   d.nombre ,d.apellidos,
	   c.cita_inicio ,c.cita_fin
FROM pacientes p
INNER JOIN citas c ON c.paciente_id = p.id
INNER JOIN doctores d ON c.doctor_id  = d.id
WHERE c.cita_inicio BETWEEN '2026-03-01' AND '2026-03-03'
ORDER BY c.cita_inicio;


SELECT p.nombre,p.apellidos,p.genero,
	   d.nombre ,d.apellidos,
	   c.cita_inicio ,c.cita_fin
FROM pacientes p
INNER JOIN citas c ON c.paciente_id = p.id
INNER JOIN doctores d ON c.doctor_id  = d.id
WHERE p.nombre  like '%j%'
ORDER BY p.nombre;


SELECT p.nombre ,p.apellidos ,p.fecha_de_nacimiento,p.genero, p.active,p.numero_de_seguridad,
	   d.nombre,d.apellidos,d.genero,d.active,d.cedula_profesional,
	   c.id,c.cita_inicio ,c.cita_fin,c.cita_estado
FROM pacientes p
LEFT JOIN citas c ON c.paciente_id = p.id
LEFT JOIN doctores d ON c.doctor_id  = d.id
WHERE c.cita_estado LIKE "pendiente"
AND (d.nombre is null or d.nombre like '%%')
AND (d.apellidos is null or d.apellidos like "%%")
AND (p.nombre  is null or p.nombre like "%%")
AND (p.apellidos   is null or p.apellidos like "%%")
ORDER BY c.cita_inicio ASC;


select *
from citas c




create user modulo2@localhost IDENTIFIED by "modulo2";
create user modulo2@'172.17.0.1' IDENTIFIED by "modulo2";
grant all privileges on sisgcmher.* to modulo2@localhost;
grant all privileges on sisgcmher.* to modulo2@'172.17.0.1';
flush privileges;



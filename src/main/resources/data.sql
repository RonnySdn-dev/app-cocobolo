
INSERT INTO cliente (nombre_completo, email, telefono) VALUES
 ('Erwin Pascual', 'erwin@gmail.com', '903 006 662'),
 ('Eduardo Guevara', 'eduardo@gmail.com', '902 876 148'),
 ('Ronny Bot', 'ronnybot@gmail.com', '968 617 129'),
 ('Edgar', 'edgar@gmail.com', '921 369 750');

INSERT INTO producto (nombre, precio_unitario, stock, activo) VALUES
 ('Set de cuna Animales', 160.00, 10, true),
 ('Set de cuna Faraon', 150.00, 5, true),
 ('Set de cuna Barcos', 140.00, 8, true);
-- =============================================
-- MÓDULO HISTORIA (Agregado por Edgar)
-- =============================================

-- 1. Crear la tabla (Si no existe, la crea)
CREATE TABLE IF NOT EXISTS historia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    anio INT NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500)
);

-- 2. Insertar datos de prueba
INSERT INTO historia (anio, titulo, descripcion) VALUES 
(2010, 'Fundación Humilde', 'Iniciamos operaciones en el garaje de la casa familiar.'),
(2015, 'Primera Tienda', 'Abrimos nuestra primera sucursal en el centro de Lima.'),
(2023, 'Expansión Web', 'Lanzamos nuestra plataforma online Cocobaby.');
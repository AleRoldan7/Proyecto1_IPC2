PROYECTO 1 IPC2


/*mysql -u root -p*/

CREATE DATABASE IF NOT EXISTS TechHub;

use TechHub;


CREACIÓN DE MOLDE COMPONENTES

CREATE TABLE molde(
    -> idMolde INT PRIMARY KEY AUTO_INCREMENT,
    -> nombreComputadora VARCHAR(150) NOT NULL,
    -> precioVenta DECIMAL(10,2) NOT NULL
    -> );


CREATE TABLE IF NOT EXISTS componente( idComponente INT PRIMARY KEY AUTO_INCREMENT, nobreComponente VARCHAR(150) NOT NULL UNIQUE, tipoComponente ENUM('Procesador','Almacenamiento','Ram','Tarjeta Grafica') NOT NULL, precioComponente DECIMAL(10,2) NOT NULL, cantidadStock INT NOT NULL );

CREATE TABLE IF NOT EXISTS computadora(
    -> idComputadora INT PRIMARY KEY AUTO_INCREMENT,
    -> nombreComputadora VARCHAR(150) NOT NULL UNIQUE,
    -> precioVenta DECIMAL(10,2)
    -> );

CREATE TABLE IF NOT EXISTS molde(
    -> idMolde INT PRIMARY KEY AUTO_INCREMENT,
    -> nombreMolde VARCHAR(200) NOT NULL UNIQUE
    -> );


CREATE TABLE molde_componente( idEnsamblado INT PRIMARY KEY AUTO_INCREMENT, idMolde INT NOT NULL, idComponente INT NOT NULL, FOREIGN KEY (idMolde) REFERENCES molde (idMolde), FOREIGN KEY (idComponente) REFERENCES componente (idComponente));

CREATE TABLE IF NOT EXISTS computadora_molde_componente( idUnion INT PRIMARY KEY AUTO_INCREMENT, idComputadora INT, idMolde INT, idComponente INT, FOREIGN KEY (idComputadora) REFERENCES computadora(idComputadora), FOREIGN KEY (idMolde) REFERENCES molde(idMolde), FOREIGN KEY (idComponente) REFERENCES componente(idComponente) );

CREATE TABLE ensamblajes (
    idEnsamblaje INT AUTO_INCREMENT PRIMARY KEY,
    idMolde INT NOT NULL,
    fechaEnsamblaje TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idMolde) REFERENCES molde(idMolde)
);

ALTER TABLE computadora_molde_componente
    -> ADD COLUMN fechaEnsamblaje TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -> ADD COLUMN nombreUsuario VARCHAR(100) NOT NULL,
    -> ADD COLUMN precioTotal DECIMAL(10,2) NOT NULL;

CREATE TABLE IF NOT EXISTS venta(
    -> idVenta INT PRIMARY KEY AUTO_INCREMENT,
    -> idComprador INT NOT NULL,
    -> idProducto INT NOT NULL,
    -> FOREIGN KEY (idComprador) REFERENCES comprador (idComprador),
    -> FOREIGN KEY (idProducto) REFERENCES computadora_molde_componente (idUnion)
    -> );

 ALTER TABLE venta
    -> ADD COLUMN fechaVenta TIMESTAMP DEFAULT CURRENT_TIMESTAMP;


CREATE TABLE IF NOT EXISTS comprador(
    -> idComprador INT PRIMARY KEY AUTO_INCREMENT,
    -> nombreComprador VARCHAR(250) NOT NULL,
    -> nit VARCHAR(30) UNIQUE NOT NULL,
    -> direccion VARCHAR(250) NOT NULL,
    -> celularComprador VARCHAR(30) NOT NULL,
    -> emailComprador VARCHAR(100) NOT NULL
    -> );


CREATE TABLE IF NOT EXISTS factura (
    idFactura INT AUTO_INCREMENT PRIMARY KEY,
    idComprador INT,
    fechaFactura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2),
    archivoFactura BLOB,  -- Almacenar el PDF en la base de datos
    FOREIGN KEY (idComprador) REFERENCES comprador(idComprador)
);










NOTAS FALTANTE

VENTAS
TENER EL FORMULARIO Y LA LÓGICA EN JAVA PARA CREAR LA FACTURA CON LO COMPRADO
HACER LAS DEVOLUCIONES
GENERAR EL REPORTE DE VENTAS AL DÍA QUE SE MUESTRE

ADMINISTRADOR
OBSERVAR LO MOVIMIENTOS DE CADA DÍAS EN LAS VENTAS
PODER ACCEDER AL ÁREA DE ENSAMBLAJE Y VENTAS Y PODER HACER LO QUE SE PERMITE AHÍ
PODER CARGAR LOS ARCHIVOS PARA AGREGAR USUARIOS, PIEZAS, COMPUTADORAS, ENSAMBLAR Y TODA LA CARGA DE DATOS
Reporte de ventas en un intervalo X de tiempo: por cada venta se muestran los productos
vendidos junto con su precio unitario.
Reporte de devoluciones en un intervalo X de tiempo: Por cada devolución mostrar la información
de la venta, la fecha de devolución y la pérdida para la empresa.
Reporte de ganancias en un intervalo X de tiempo: Mostrar el total de la ganancia en ese
intervalo de tiempo e incluir un listado con todos los productos vendidos incluyendo la ganancia
de ese producto.
Reporte del usuario que registra más ventas en un intervalo X de tiempo: por cada venta se
muestran los productos vendidos junto con su precio unitario.
Reporte del usuario que registra más ganancias en un intervalo X de tiempo: Mostrar el total de la
ganancia en ese intervalo de tiempo e incluir un listado con todos los productos vendidos
incluyendo la ganancia de ese producto.
Reporte de la computadora más vendida, incluyendo el detalle de las ventas, en un intervalo X de
tiempo
Reporte de la computadora menos vendida, incluyendo el detalle de las ventas, en un intervalo X
de tiempo.
Todos los reportes pueden ser exportados a archivos CSV si el usuario así lo deseara.

GENERAL
HACER LOS MANUALES DE USUARIO Y TÉCNICO
DIAGRAMA DE CLASES, DIAGRAMA DE TABLAS Y EL SCRIPT DE LA BASE DE DATOS
CORREGIR ERRORES EN ENSAMBLAJE PARA QUE MUESTRE LOS COMPONENTES 


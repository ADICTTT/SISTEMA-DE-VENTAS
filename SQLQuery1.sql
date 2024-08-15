--PASO 1 creacion de la base de datos
create database db_ventas

use db_ventas

create table cliente(
idcliente varchar(10) primary key,
nombre varchar(20),
apellidos varchar(50),
cedula varchar(8),
sexo char(1),
edad int,
telefono char(8),
direccion varchar(250),
email varchar(50)
)
go

create table proveedor(
idproveedor varchar(10) primary key,
razonsocial varchar(30),
telefono varchar(9),
cedula varchar(8),
direccion varchar(50)
)
go

create table categoria(
idcategoria int identity primary key,
descripcion varchar(15),
)
go

create table productos(
idproducto varchar(10) primary key,
serie varchar(30),
nombre varchar(30),
f_ingreso date,
f_vencimiento date,
prec_compra decimal(9,2),
prec_venta decimal(9,2),
cantidad int,
idcategoria int references categoria(idcategoria)
)
go

create table empleados(
idempleados varchar(5) primary key,
nombre varchar(20),
apellidos varchar(20),
cedula varchar(8),
telefono varchar(9),
direccion varchar(50),
)
go

create table usuarios(
idusuario int identity primary key,
idempleados varchar(5) references empleados(idempleados),
usuario varchar(20),
pass varchar(20),
acceso varchar(20),
estado char(2)
)
go

create table compras(
idcompra int identity primary key,
fecha date,
hora varchar(10),
num_documento varchar(7),
tipo_documento varchar(7),
subtotal decimal(8,2),
igv decimal(8,2),
total decimal(8,2),
estado varchar(20),
idusuario int references usuarios (idusuario),
idproveedor varchar(10) references proveedor(idproveedor)
)
go

create table detalle_compras(
iddetallecompra int identity primary key,
idcompra int references compras(idcompra),
idproducto varchar(10) references productos(idproducto),
cantidad int,
precio decimal(8,2),
total decimal(8,2)
)
go

create table ventas(
idventa int identity primary key,
fecha date,
hora varchar(10),
serie varchar(7),
num_documento varchar(7),
tipo_documento varchar(7),
subtotal decimal(8,2),
igv decimal(8,2),
total decimal(8,2),
estado varchar(20),
idusuario int references usuarios (idusuario),
idcliente varchar(10) references cliente(idcliente)
)
go

create table detalle_ventas(
iddetalleventas int identity primary key,
idventa int references ventas(idventa),
idproducto varchar(10) references productos(idproducto),
cantidad int,
precio decimal(8,2),
total decimal(8,2)
)
go




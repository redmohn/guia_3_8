
CREATE TABLE producto (
    codigo int(3) NOT NULL,
    nombre varchar (255) NOT NULL,
    familia_productos varchar (255) NOT NULL,
    tipo_envase varchar(255) NOT NULL,
    medida double NOT NULL,
    unidad_medida varchar (255) NOT NULL,
    descripcion text,
    precio_venta int NOT NULL DEFAULT '1',
    stock int NOT NULL DEFAULT '0',
    PRIMARY KEY (codigo)
);
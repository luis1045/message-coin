CREATE TABLE producto (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    precio DOUBLE PRECISION,
    stock INTEGER,
    categoria VARCHAR(100),
    imagen_url VARCHAR(500)
);

CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    total DOUBLE PRECISION,
    fecha TIMESTAMP
);

CREATE TABLE detalle_pedido (
    id SERIAL PRIMARY KEY,
    pedido_id BIGINT REFERENCES pedido(id),
    producto_id BIGINT REFERENCES producto(id),
    cantidad INTEGER,
    precio DOUBLE PRECISION
);


---productos
INSERT INTO producto(id, nombre, descripcion, precio, stock, categoria, imagen_url) VALUES(1,'arina','arina de papa',12,4,'primera necesidad','/mi/imagen')

select * from producto;

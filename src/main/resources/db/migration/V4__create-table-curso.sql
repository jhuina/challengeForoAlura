CREATE TABLE curso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_curso_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
ALTER TABLE topicos
    MODIFY fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE topicos
    MODIFY status BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE topicos
    ADD CONSTRAINT fk_topico_usuario
    FOREIGN KEY (autor_id) REFERENCES usuario(id);

ALTER TABLE topicos
    ADD CONSTRAINT fk_topico_curso
    FOREIGN KEY (curso_id) REFERENCES curso(id);
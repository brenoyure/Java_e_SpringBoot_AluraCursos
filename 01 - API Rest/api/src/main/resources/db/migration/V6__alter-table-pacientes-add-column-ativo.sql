ALTER TABLE pacientes
    ADD COLUMN ativo TINYINT;

UPDATE pacientes
    SET ativo=1;

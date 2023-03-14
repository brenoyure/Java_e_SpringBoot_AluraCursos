ALTER TABLE medicos ADD COLUMN ativo TINYINT;
UPDATE medicos SET ativo = 1;

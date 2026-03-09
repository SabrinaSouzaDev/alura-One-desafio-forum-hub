ALTER TABLE cursos
ALTER COLUMN categoria TYPE VARCHAR(255) USING categoria::text;


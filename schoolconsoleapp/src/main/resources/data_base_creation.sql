DO $$ 
BEGIN
    IF NOT EXISTS(SELECT 1 FROM pg_database WHERE datname = 'school') THEN 
        CREATE DATABASE school;
    END IF;
END $$;

DO $$ 
BEGIN
    IF NOT EXISTS(SELECT 1 FROM pg_user WHERE usename = 'yuriy') THEN
        CREATE USER yuriy WITH PASSWORD '1234';
    END IF;
END $$;

ALTER ROLE yuriy SUPERUSER;

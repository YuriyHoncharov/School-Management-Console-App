SELECT 1 FROM pg_database WHERE datname = 'school';
CREATE DATABASE school;

SELECT 1 FROM pg_user WHERE usename = 'yuriy';
CREATE USER yuriy WITH PASSWORD '1234';

ALTER ROLE yuriy SUPERUSER;

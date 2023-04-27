-- Drop tables if exist.
DROP TABLE IF EXISTS groups CASCADE;
DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS courses CASCADE;

CREATE TABLE groups (
group_id SERIAL PRIMARY KEY,
group_name TEXT NOT NULL
);

CREATE TABLE students (
student_id SERIAL PRIMARY KEY NOT NULL,
group_id INTEGER,
first_name TEXT NOT NULL,
last_name TEXT NOT NULL
);

CREATE TABLE courses (
course_id SERIAL PRIMARY KEY,
course_name TEXT NOT NULL,
course_description TEXT NOT NULL
);

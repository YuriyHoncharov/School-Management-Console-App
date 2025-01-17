-- Create School Tables

CREATE TABLE IF NOT EXISTS groups (
id SERIAL PRIMARY KEY,
name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
id SERIAL PRIMARY KEY NOT NULL,
group_id INTEGER REFERENCES groups(id),
first_name TEXT NOT NULL,
last_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS courses (
id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
description TEXT
);

CREATE TABLE IF NOT EXISTS students_courses (
course_id INTEGER REFERENCES courses(id),
student_id INTEGER REFERENCES students(id) ON DELETE CASCADE
);
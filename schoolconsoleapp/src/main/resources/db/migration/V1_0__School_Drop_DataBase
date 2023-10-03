-- Create School Tables

CREATE TABLE IF NOT EXISTS groups (
group_id SERIAL PRIMARY KEY,
group_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
student_id SERIAL PRIMARY KEY NOT NULL,
group_id INTEGER REFERENCES groups(group_id),
first_name TEXT NOT NULL,
last_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS courses (
course_id SERIAL PRIMARY KEY,
course_name TEXT NOT NULL,
course_description TEXT
);

CREATE TABLE IF NOT EXISTS students_courses (
course_id INTEGER REFERENCES courses(course_id),
student_id INTEGER REFERENCES students(student_id)
);

ALTER TABLE students_courses
DROP CONSTRAINT students_courses_student_id_fkey,
ADD CONSTRAINT students_courses_student_id_fkey
FOREIGN KEY (student_id)
REFERENCES students (student_id)
ON DELETE CASCADE;
INSERT INTO groups (group_name) VALUES
  ('Group X'),
  ('Group Y');

INSERT INTO students (group_id, first_name, last_name) VALUES
  (1, 'John', 'Doe'),
  (1, 'Jane', 'Smith'),
  (1, 'Bob', 'Johnson'),
  (2, 'Alice', 'Brown'),
  (2, 'Michael', 'Wilson'),
  (2, 'Sarah', 'Lee');
INSERT INTO courses (course_name, course_description) VALUES
  ('Mathematics', 'Intro to Mathematics'),
  ('History', 'World History');
INSERT INTO students_courses (student_id, course_id) VALUES
  (1, 1), 
  (2, 1), 
  (3, 2), 
  (4, 1), 
  (5, 2), 
  (6, 2);  
  
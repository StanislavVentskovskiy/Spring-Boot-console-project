INSERT INTO postgres.schoolconsoleapp.groups(group_name)
VALUES('testGroup');
INSERT INTO postgres.schoolconsoleapp.courses(course_name, course_description)
VALUES('testCourseName','testCourseDescription');
INSERT INTO postgres.schoolconsoleapp.students(group_id, first_name, last_name)
VALUES('1', 'name', 'surname');
INSERT INTO postgres.schoolconsoleapp.coursesstudents(course_id, student_id)
VALUES ('1','1');


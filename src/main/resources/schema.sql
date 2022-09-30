DROP TABLE IF EXISTS schoolconsoleapp.courses
CASCADE;
DROP TABLE IF EXISTS schoolconsoleapp.groups
CASCADE;
DROP TABLE IF EXISTS schoolconsoleapp.students
CASCADE;
DROP TABLE IF EXISTS schoolconsoleapp.coursesstudents
CASCADE;

CREATE TABLE IF NOT EXISTS schoolconsoleapp.courses(
    id BIGSERIAL NOT NULL,
    course_name varchar(255) COLLATE pg_catalog."default" NOT NULL,
    course_description varchar(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT courses_pkey PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS schoolconsoleapp.courses
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS schoolconsoleapp.groups(
    id BIGSERIAL NOT NULL,
    group_name varchar(255) NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS schoolconsoleapp.groups
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS schoolconsoleapp.students(
    id BIGSERIAL NOT NULL,
    group_id integer,
    first_name varchar(255) COLLATE pg_catalog."default" NOT NULL,
    last_name varchar(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT students_pkey PRIMARY KEY (id),
    CONSTRAINT groups_group_id_fkey FOREIGN KEY (group_id)
        REFERENCES schoolconsoleapp.groups (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS schoolconsoleapp.students
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS schoolconsoleapp.coursesstudents(
    id BIGSERIAL NOT NULL,
    course_id integer,
    student_id integer,
    CONSTRAINT course_id FOREIGN KEY (course_id)
        REFERENCES schoolconsoleapp.courses (id) MATCH SIMPLE,

    CONSTRAINT student_id FOREIGN KEY (student_id)
        REFERENCES schoolconsoleapp.students (id) MATCH SIMPLE
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS schoolconsoleapp.coursesstudents
    OWNER to postgres;

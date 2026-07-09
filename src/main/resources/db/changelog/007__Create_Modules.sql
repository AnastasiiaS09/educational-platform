CREATE TABLE modules (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) UNIQUE,
    course_id BIGINT,
    lesson_number INT NOT NULL,
    description TEXT
);
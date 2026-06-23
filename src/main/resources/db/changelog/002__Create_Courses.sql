CREATE TABLE courses (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    lecture_number INT,
    description VARCHAR(1024)

);
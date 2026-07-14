CREATE TABLE modules (
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
name VARCHAR(128) UNIQUE,
course_id UUID,
lesson_number INT NOT NULL,
description TEXT
);
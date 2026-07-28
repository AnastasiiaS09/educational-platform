CREATE TABLE courses (
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
course_name VARCHAR(64) UNIQUE NOT NULL,
module_quantity INT,
description TEXT
);
CREATE TABLE modules (
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
module_name VARCHAR(128) UNIQUE,
course_id UUID,
lesson_quantity INTEGER,
description TEXT,
module_number INTEGER
);
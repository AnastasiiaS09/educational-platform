CREATE TABLE lessons (
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
name VARCHAR(128) UNIQUE,
module_id UUID NOT NULL,
description TEXT,
lesson_number INT
);
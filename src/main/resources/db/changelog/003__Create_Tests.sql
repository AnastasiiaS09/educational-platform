CREATE TABLE tests (
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
name VARCHAR(64) UNIQUE,
description TEXT,
question_quantity INTEGER
);
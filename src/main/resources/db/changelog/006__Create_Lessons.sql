CREATE TABLE lessons (
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
lesson_name VARCHAR(128) UNIQUE,
module_id UUID NOT NULL,
description TEXT,
lesson_number INTEGER,
poster_video BYTEA,
text TEXT,
type VARCHAR(64)
);
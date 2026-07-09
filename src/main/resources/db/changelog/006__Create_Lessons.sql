CREATE TABLE lessons (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) UNIQUE,
    course_id BIGINT NOT NULL,
    module_id BIGINT NOT NULL,
    description VARCHAR(1024)
);
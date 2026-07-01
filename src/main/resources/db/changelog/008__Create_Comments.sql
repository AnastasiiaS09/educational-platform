CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
    course_id BIGINT,
    module_id BIGINT,
    lesson_id BIGINT,
    user_id BIGINT,
    text VARCHAR(512) NOT NULL,
    created_at TIMESTAMP
);
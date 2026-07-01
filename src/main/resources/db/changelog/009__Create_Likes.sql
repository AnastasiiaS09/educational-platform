CREATE TABLE likes (
    id BIGSERIAL PRIMARY KEY,
    lesson_id BIGINT,
    user_id BIGINT,
    created_at TIMESTAMP
);
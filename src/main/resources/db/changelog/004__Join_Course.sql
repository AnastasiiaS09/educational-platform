CREATE TABLE users_courses (
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
user_id BIGINT,
course_id UUID
);
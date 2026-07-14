CREATE TABLE likes (
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
lesson_id UUID,
user_id UUID,
created_at TIMESTAMP
);
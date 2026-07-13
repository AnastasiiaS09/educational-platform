CREATE TABLE comments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    course_id UUID,
    module_id UUID,
    lesson_id UUID,
    user_id UUID,
    text VARCHAR(512) NOT NULL,
    created_at TIMESTAMP
);
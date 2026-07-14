CREATE TABLE lessons_statuses (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    lesson_id UUID,
    status VARCHAR(150)
);
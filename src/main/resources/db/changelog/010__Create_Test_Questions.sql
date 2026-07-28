CREATE TABLE test_questions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    test_id UUID,
    question_text VARCHAR NOT NULL,
    question_number INT UNIQUE NOT NULL
);
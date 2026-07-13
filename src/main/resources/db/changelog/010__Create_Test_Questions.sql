CREATE TABLE test_questions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    test_id UUID,
    question_number INT NOT NULL,
    correct_answer VARCHAR
);
CREATE TABLE english_test_results (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID,
    question_id UUID,
    user_answer VARCHAR,
    correct_answer VARCHAR,
    correctness VARCHAR(64)
);
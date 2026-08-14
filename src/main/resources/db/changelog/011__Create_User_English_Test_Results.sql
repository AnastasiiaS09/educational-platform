CREATE TABLE english_test_results (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    attempt_id UUID,
    user_answer_id UUID,
    correctness VARCHAR(64)
);
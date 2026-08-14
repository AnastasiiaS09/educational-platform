CREATE TABLE user_test_attempts(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID,
    test_id UUID,
    result INT DEFAULT 0,
    accuracy DOUBLE PRECISION
);
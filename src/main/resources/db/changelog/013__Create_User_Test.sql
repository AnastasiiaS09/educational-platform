CREATE TABLE users_tests(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID,
    test_id UUID,
    result DOUBLE PRECISION DEFAULT 0
);
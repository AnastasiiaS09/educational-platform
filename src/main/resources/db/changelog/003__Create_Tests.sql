CREATE TABLE tests (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    test_name VARCHAR(64) UNIQUE,
    description TEXT,
    max_score INTEGER,
    question_quantity INTEGER
);
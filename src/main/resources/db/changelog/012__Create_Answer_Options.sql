CREATE TABLE answer_options(
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
question_id UUID,
option_text TEXT NOT NULL,
is_correct BOOLEAN
);
CREATE TABLE users_roles (
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
user_id UUID,
role VARCHAR(128)
);
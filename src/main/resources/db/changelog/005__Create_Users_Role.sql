CREATE TABLE users_roles (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    role VARCHAR(150)
);
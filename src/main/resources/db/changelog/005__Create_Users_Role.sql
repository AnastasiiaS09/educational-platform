CREATE TABLE users_role (
     id BIGSERIAL PRIMARY KEY,
     user_id INT,
     role VARCHAR(150)
);
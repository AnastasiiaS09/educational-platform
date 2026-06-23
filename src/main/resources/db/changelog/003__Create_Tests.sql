CREATE TABLE tests (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE,
    description VARCHAR(1024)
);
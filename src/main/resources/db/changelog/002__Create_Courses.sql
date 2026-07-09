CREATE TABLE courses (
    id UUID PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    module_quantity INT,
    description TEXT
);
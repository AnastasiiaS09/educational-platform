CREATE TABLE users (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 user_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  phone VARCHAR(20) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  experience_point INT DEFAULT 0,
  task_counter INT DEFAULT 0,
  day_counter INT DEFAULT 0
);
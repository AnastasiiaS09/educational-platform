CREATE TABLE users_courses (
   id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
   user_id UUID,
   course_id UUID
);
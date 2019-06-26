INSERT INTO roles ("name") VALUES ('ROLE_ADMIN');

INSERT INTO roles ("name") VALUES ('ROLE_USER');

INSERT INTO users ("firstname", "lastname", "email", "password") VALUES ('Admin', 'Adminov', 'admin@mail.ru', '$2a$10$PeSqJMZBC64oDhh.6yTTrOZP/iOMtL.7GKFyatjC.tzDc2rOBUcem');

INSERT INTO user_roles (user_id, role_id) VALUES (
  (SELECT id
   FROM users
   WHERE email = 'admin@mail.ru'  LIMIT 1),
  (SELECT id
   FROM roles
   WHERE name = 'ROLE_ADMIN' LIMIT 1)
);

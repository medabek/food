CREATE TABLE roles(
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE users(
  id BIGSERIAL PRIMARY KEY,
  firstname VARCHAR(25),
  lastname VARCHAR(25),
  email VARCHAR(255),
  password VARCHAR(500)
);

CREATE TABLE user_roles(
  user_id BIGSERIAL REFERENCES users(id),
  role_id BIGSERIAL REFERENCES roles(id)
);
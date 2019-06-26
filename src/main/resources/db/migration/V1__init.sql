CREATE TABLE cafes (
  id BIGSERIAL PRIMARY KEY,
  name varchar(255) DEFAULT NULL
);

CREATE TABLE dishes (
  id BIGSERIAL PRIMARY KEY,
  name varchar(255) DEFAULT NULL,
  portion FLOAT,
  price NUMERIC(10, 4),
  cafe_id BIGINT REFERENCES cafes(id)
);
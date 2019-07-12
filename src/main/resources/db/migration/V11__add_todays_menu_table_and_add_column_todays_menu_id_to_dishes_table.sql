CREATE TABLE todays_menu (
     id BIGSERIAL PRIMARY KEY,
     status VARCHAR(50),
     time TIMESTAMP
);

ALTER TABLE dishes ADD COLUMN todays_menu_id BIGINT REFERENCES todays_menu(id);
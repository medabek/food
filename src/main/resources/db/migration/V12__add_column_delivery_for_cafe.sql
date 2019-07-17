CREATE TABLE orders_cafes(
    order_id BIGSERIAL REFERENCES orders(id),
    cafe_id BIGSERIAL REFERENCES cafes(id)
);

ALTER TABLE cafes
    ADD COLUMN delivery NUMERIC DEFAULT(0);
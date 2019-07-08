CREATE TABLE items (
    id BIGSERIAL PRIMARY KEY,
    dish_id BIGINT,
    dish_name VARCHAR(50),
    price NUMERIC(10, 4),
    quantity INT,
    order_id BIGINT REFERENCES items(id)
);

INSERT INTO dishes("name", "portion", "price", "cafe_id")
VALUES('Brizol', '1.00', '250.00', '1');

INSERT INTO dishes("name", "portion", "price", "cafe_id")
VALUES('Lagman', '0.5', '150.00', '2');
CREATE TABLE company_orders (
    id BIGSERIAL PRIMARY KEY,
    order_status VARCHAR(50),
    opening_time TIMESTAMP,
    closing_time TIMESTAMP,
    manager_id BIGSERIAL REFERENCES users(id),
    total NUMERIC(10, 4)
);

CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(50),
    user_id BIGSERIAL,
    date TIMESTAMP,
    company_order_id BIGINT REFERENCES company_orders(id),
    total NUMERIC(10, 4)
);
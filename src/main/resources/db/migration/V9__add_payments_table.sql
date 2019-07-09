CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    manager_id BIGINT REFERENCES users(id),
    amount NUMERIC(10, 4),
    time TIMESTAMP
);
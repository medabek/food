ALTER TABLE items DROP COLUMN order_id;

ALTER TABLE items ADD COLUMN order_id BIGINT REFERENCES orders(id);
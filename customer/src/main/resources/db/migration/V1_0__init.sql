CREATE TABLE IF NOT EXISTS customer
(
    customer_id         BIGSERIAL   PRIMARY KEY,
    external_id         UUID        NOT NULL,
    name                TEXT        NOT NULL,
    birth_date          DATE        NOT NULL,
    phone               TEXT        NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_customer_external_id ON customer (external_id);

CREATE TABLE IF NOT EXISTS address(
    address_id          BIGSERIAL   PRIMARY KEY,
    external_id         UUID        NOT NULL,
    street              TEXT        NOT NULL,
    address_number      TEXT        NOT NULL,
    zip_code            TEXT        NOT NULL,
    complement          TEXT,
    customer_id         BIGINT      NOT NULL references customer (customer_id)
);

CREATE INDEX IF NOT EXISTS idx_address_customer_id ON address (customer_id);
CREATE INDEX IF NOT EXISTS idx_address_external_id ON address (external_id);
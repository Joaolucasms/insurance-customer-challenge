CREATE TABLE IF NOT EXISTS customer
(
    customer_id         BIGSERIAL   PRIMARY KEY,
    external_id         UUID        NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_customer_external_id ON customer (external_id);

CREATE TABLE IF NOT EXISTS insurance_request
(
    insurance_request_id    BIGSERIAL       PRIMARY KEY,
    external_id             UUID            NOT NULL,
    customer_id             BIGINT          NOT NULL references customer (customer_id),
    amount_to_be_paid       DECIMAL(19,2)   NOT NULL,
    amount_to_receive       DECIMAL(19,2)   NOT NULL,
    valid_until             DATE            NOT NULL,
    type                    TEXT            NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_insurance_request_external_id ON insurance_request (external_id);
CREATE INDEX IF NOT EXISTS idx_insurance_request_customer_id ON insurance_request (customer_id);

CREATE TABLE IF NOT EXISTS insurance
(
    insurance_id            BIGSERIAL       PRIMARY KEY,
    external_id             UUID            NOT NULL,
    customer_id             BIGINT          NOT NULL references customer (customer_id),
    insurance_request_id    BIGINT          NOT NULL references insurance_request (insurance_request_id),
    amount_to_be_paid       DECIMAL(19,2)   NOT NULL,
    amount_to_receive       DECIMAL(19,2)   NOT NULL,
    valid_until             DATE            NOT NULL,
    status                  TEXT            NOT NULL,
    created_at              timestamptz     NOT NULL,
    type                    TEXT            NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_insurance_external_id ON insurance (external_id);
CREATE INDEX IF NOT EXISTS idx_insurance_customer_id ON insurance (customer_id);
CREATE INDEX IF NOT EXISTS idx_insurance_insurance_request_id ON insurance (insurance_request_id);
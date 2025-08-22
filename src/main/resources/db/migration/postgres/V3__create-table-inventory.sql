CREATE TABLE inventory (
    id VARCHAR(26) PRIMARY KEY,
    is_available BOOLEAN NOT NULL DEFAULT TRUE,
    catalog_id VARCHAR(26) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP
);

ALTER TABLE inventory
ADD CONSTRAINT fk_inventory_catalog
FOREIGN KEY (catalog_id)
REFERENCES catalog (id);
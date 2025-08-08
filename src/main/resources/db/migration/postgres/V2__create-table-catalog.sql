CREATE TABLE catalog_items (
    id VARCHAR(26) PRIMARY KEY,
    item_type VARCHAR NOT NULL,
    type_id VARCHAR NOT NULL,
    title VARCHAR,
    author VARCHAR,
    publisher VARCHAR,
    year INTEGER,
    edition INTEGER,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP
);
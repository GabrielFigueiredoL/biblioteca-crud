CREATE TABLE loans (
   id VARCHAR(26) PRIMARY KEY,
   rent_date TIMESTAMP NOT NULL,
   return_date TIMESTAMP,
   due_date TIMESTAMP NOT NULL,

   client_id VARCHAR(26) NOT NULL,
   inventory_id VARCHAR(26) NOT NULL,

   created_at TIMESTAMP DEFAULT NOW(),
   updated_at TIMESTAMP
);

ALTER TABLE loans
ADD CONSTRAINT fk_loan_client
FOREIGN KEY (client_id)
REFERENCES clients (id);

ALTER TABLE loans
ADD CONSTRAINT fk_loan_inventory
FOREIGN KEY (inventory_id)
REFERENCES inventory (id);


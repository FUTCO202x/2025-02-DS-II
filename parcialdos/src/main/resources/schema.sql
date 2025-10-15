DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountNumber VARCHAR(255) UNIQUE,
    ownerName VARCHAR(255),
    balance DECIMAL(19,2),
    active BOOLEAN
);
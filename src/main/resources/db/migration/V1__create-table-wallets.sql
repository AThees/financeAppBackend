CREATE TABLE wallets(
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE,
    amountInCents INTEGER NOT NULL,
    financeInstitution VARCHAR,
    type VARCHAR NOT NULL,
    addToSum BOOLEAN NOT NULL
);
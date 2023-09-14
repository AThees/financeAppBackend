CREATE TABLE wallets(
    id TEXT NOT NULL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE,
    amount_in_cents INTEGER NOT NULL,
    finance_institution TEXT,
    type TEXT NOT NULL,
    add_to_sum BOOLEAN NOT NULL
);
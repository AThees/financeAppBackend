CREATE TABLE expenses(
    id TEXT NOT NULL PRIMARY KEY,
    description TEXT NOT NULL,
    value_in_cents NUMERIC NOT NULL,
    paid BOOLEAN NOT NULL,
    category TEXT NOT NULL,
    wallet_id TEXT NOT NULL
);
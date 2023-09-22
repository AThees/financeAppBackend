CREATE TABLE transactions(
     id TEXT NOT NULL PRIMARY KEY,
     description TEXT NOT NULL,
     value_in_cents NUMERIC NOT NULL,
     sender_wallet_id TEXT NOT NULL,
     receiver_wallet_id TEXT NOT NULL
);
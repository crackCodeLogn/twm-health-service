CREATE TABLE IF NOT EXISTS health_charter(
    psn_dt VARCHAR(50) PRIMARY KEY,
    psn VARCHAR(50) NOT NULL,
    dt timestamptz NOT NULL,
    wt NUMERIC(5,2) NOT NULL,
    fbs NUMERIC(3) DEFAULT -1,
    pp2bs NUMERIC(3) DEFAULT -1,
    sys NUMERIC(3) DEFAULT -1,
    dia NUMERIC(3) DEFAULT -1,
    pulse NUMERIC(3) DEFAULT -1
);
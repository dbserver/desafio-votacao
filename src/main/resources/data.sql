CREATE SCHEMA IF NOT EXISTS voteschallenge;

CREATE TABLE IF NOT EXISTS voteschallenge.associate (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS voteschallenge.ruling (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    results BOOLEAN,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    vote_count_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS voteschallenge.session (
    id SERIAL PRIMARY KEY,
    ruling_id INTEGER NOT NULL,
    duration INTEGER NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ruling
    FOREIGN KEY(ruling_id)
    REFERENCES voteschallenge.ruling(id)
);

CREATE TABLE IF NOT EXISTS voteschallenge.votes (
    id SERIAL PRIMARY KEY,
    session_id INTEGER NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    vote BOOLEAN NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_session
    FOREIGN KEY(session_id)
    REFERENCES voteschallenge.session(id),
    CONSTRAINT fk_associate
    FOREIGN KEY (cpf)
    REFERENCES voteschallenge.associate(cpf)
);
CREATE TABLE stave_session (
    id SERIAL PRIMARY KEY,
    duration NUMERIC NOT NULL,
    status TEXT NOT NULL,
    stave_id BIGINT NOT NULL,

    FOREIGN KEY (stave_id) REFERENCES stave (id)
);
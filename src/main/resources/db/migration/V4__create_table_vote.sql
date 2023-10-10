CREATE TABLE vote (
    id SERIAL PRIMARY KEY,
    vote TEXT NOT NULL,
    associate_id BIGINT NOT NULL,
    stave_session_id BIGINT NOT NULL,

    FOREIGN KEY (associate_id) REFERENCES associate (id),
    FOREIGN KEY (stave_session_id) REFERENCES stave_session (id)
);




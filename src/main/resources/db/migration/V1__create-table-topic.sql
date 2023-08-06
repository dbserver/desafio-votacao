CREATE TABLE topic(
    id SERIAL NOT NULL PRIMARY KEY,
    title varchar(100) NOT NULL,
    start_time timestamp NOT NULL,
    end_time timestamp
);
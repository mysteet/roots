CREATE TABLE IF NOT EXISTS public.street
(
    id      SERIAL PRIMARY KEY,
    title   VARCHAR(255) NOT NULL,
    created TIMESTAMP,
    updated TIMESTAMP
);

CREATE TABLE IF NOT EXISTS public.story
(
    id        SERIAL PRIMARY KEY,
    info      TEXT   NOT NULL,
    street_id BIGINT NOT NULL,
    created   TIMESTAMP,
    updated   TIMESTAMP,
    FOREIGN KEY (street_id) REFERENCES street (id)
);

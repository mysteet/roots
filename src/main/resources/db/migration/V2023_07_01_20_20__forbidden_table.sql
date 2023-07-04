CREATE TABLE IF NOT EXISTS public.forbidden
(
    id      SERIAL PRIMARY KEY,
    "key"   VARCHAR(100) NOT NULL,
    "value" TEXT   NOT NULL
);

insert into public.forbidden ("key", "value")
values ('ru', 'Terrorist state'),
       ('by', 'Swamp'),
       ('ir', 'Terrorists'),
       ('kp', 'No Internet'),
       ('cn', 'Communists dont need history');
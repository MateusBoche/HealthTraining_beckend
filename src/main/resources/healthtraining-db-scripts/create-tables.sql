DROP TABLE user_model IF EXISTS;

CREATE TABLE user_model
(
    id       SERIAL     not null,
    email    varchar(50) not null,
    nomeCompleto varchar(50) not null,
    senha varchar(50) not null,
    PRIMARY KEY (id)
);


CREATE TABLE question
(
    id       SERIAL     not null,
    question    varchar(50) not null,
    answer varchar(50) not null,
    category varchar(50) not null,
    pashe   int not null,
    PRIMARY KEY (id)
);


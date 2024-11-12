DROP TABLE IF EXISTS user_model;
DROP TABLE IF EXISTS question_model;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS user_model;
DROP TYPE IF EXISTS user_role;

CREATE TYPE user_role AS ENUM ('ADMINISTRATOR', 'USER');

CREATE TABLE user_model
(
    id       SERIAL     not null,
    email    varchar(50) not null,
    nomeCompleto varchar(50) not null,
    senha varchar(50) not null,
    PRIMARY KEY (id)
);

CREATE TABLE question_model
(
    id       SERIAL     not null,
    question    varchar(500) not null,
    answer boolean not null,
    category varchar(200) not null,
    phase  int not null,
    link varchar(500),
    PRIMARY KEY (id)

);

CREATE TABLE game
(
    id       SERIAL     not null,
    status varchar(200) not null,
    nivelAtual int not null,
    usuarioID int not null,
    numeroAcertos  int not null,
    numeroErros int not null,
    pontuacao int,
    dataDeCriacao varchar(200) not null,
    PRIMARY KEY (id)
);

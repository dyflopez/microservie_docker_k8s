create table usuario
(
    id_usuario        INT         NOT NULL AUTO_INCREMENT,
    nombre            varchar(100) not null,
    email             varchar(100) not null,
    password          varchar(100) not null,
    primary key (id_usuario)
);
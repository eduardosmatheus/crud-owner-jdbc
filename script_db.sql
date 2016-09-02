
create database ibex_petshop;

use ibex_petshop;

create table proprietarios
(
    id int not null auto_increment primary key,
    nome varchar(255) not null,
    sobrenome varchar(255) not null,
    cpf varchar(11) not null,
    sexo char(1),
    id_endereco numeric(9),
    telefone varchar(14),
    email varchar(200)
);
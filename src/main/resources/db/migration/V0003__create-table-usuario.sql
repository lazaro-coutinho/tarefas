create table usuario(
	id bigserial primary key,
	nome varchar(50) not null,
	login varchar(255) not null,
	senha varchar(255) not null,
	ativo bool not null,
	data_criacao timestamp not null
);
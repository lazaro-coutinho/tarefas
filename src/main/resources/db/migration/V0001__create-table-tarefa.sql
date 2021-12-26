create table tarefa(
	id bigserial primary key,
	nome varchar(50) not null,
	descricao varchar(255) not null,
	ativo bool not null,
	data_criacao timestamp not null
);
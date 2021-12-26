create table usuario_role(
	usuario_id int8 not null,
	role_id int8 not null,
	constraint usuario_role_pkey primary key (usuario_id, role_id)
);
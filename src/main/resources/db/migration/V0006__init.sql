insert into role(id, nome) values(1, 'ADMIN');

insert into usuario(id, nome, login, senha, ativo, data_criacao)
values(1, 'Lázaro', 'lazaro', '$2a$10$pP1IFOoH/iFmKwU4nd.84.dFI1y985BgRJ6W3h4vfuqbUM61iSFty', true, now());

insert into usuario_role(usuario_id, role_id) values(1, 1);
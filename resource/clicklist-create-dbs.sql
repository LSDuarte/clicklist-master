create database clicklistmaster;

USE clicklistmaster;


CREATE TABLE estado ( 
	id_estado int primary key auto_increment NOT NULL,
	sigla varchar(2) NOT NULL,
	nome_estado varchar(30) NOT NULL
);


CREATE TABLE cidade ( 
	id_cidade int primary key auto_increment NOT NULL,
	nome_cidade varchar(100) NOT NULL,
	fk_estado int NOT NULL
);


CREATE TABLE endereco ( 
	id_endereco int primary key auto_increment NOT NULL,
	cep varchar(8) NOT NULL,
	numero varchar(10) NOT NULL,
	complemento varchar(250),
	bairro varchar(250) NOT NULL,
	fk_cidade int NOT NULL,
	fk_pessoa int NOT NULL
);


CREATE TABLE pessoa ( 
	id_pessoa int primary key auto_increment NOT NULL,
	ativo boolean DEFAULT true NOT NULL,
	nome varchar(100) NOT NULL,
    cpf varchar(11) NOT NULL,
	nascimento date,
	sexo varchar(9),
    email varchar(30) NOT NULL,
    fk_endereco int NOT NULL
);


CREATE TABLE usuario ( 
	id_usuario int primary key auto_increment NOT NULL,
	fk_pessoa int NOT NULL,
	login varchar(100) NOT NULL,
	senha varchar(32) NOT NULL
);


CREATE TABLE cliente ( 
	id_cliente int primary key auto_increment NOT NULL,
	data_cadastro date,
	fk_pessoa int NOT NULL
);

CREATE TABLE mercado ( 
	id_mercado int primary key auto_increment NOT NULL,
    nome_mercado varchar(100) NOT NULL
);


CREATE TABLE produto ( 
	id_produto int primary key auto_increment NOT NULL,
    descricao varchar(100) NOT NULL,
	fk_mercado int NOT NULL
);



CREATE TABLE lista_pronta ( 
	id_lista_pronta int primary key auto_increment NOT NULL,
    nome_lista varchar(100) NOT NULL
);


CREATE TABLE lista_personalizada ( 
	id_lista_personalizada int primary key auto_increment NOT NULL,
    nome_lista varchar(100) NOT NULL
);


CREATE TABLE items_lista ( 
	id_items_lista int primary key auto_increment NOT NULL,
    qnt int NOT NULL,
    valor_unit decimal(10,2) NOT NULL,
    valor_total decimal(10,2) NOT NULL,
	fk_lista_pronta int NOT NULL,
    fk_lista_personalizada int,
    fk_pessoa int NOT NULL,
    fk_produto int NOT NULL,
    fk_cliente int NOT NULL
);




-- INDEX DAS TABELAS.


-- CREATE INDEX usuario_pessoa_fkey_idx
-- ON usuario (id_pessoa);

--  CREATE INDEX endereco_municipio_fkey_idx
-- 	ON endereco (id_cidade) ;

-- CREATE INDEX endereco_pessoa_fkey_idx
--  ON endereco (id_pessoa);

-- CREATE INDEX cidade_estado_fkey_idx
-- 	ON cidade (id_estado);

--  CREATE INDEX pessoa_endereco_fkey_idx
-- 	ON pessoa (id_endereco_principal);




-- ALTER TABLE FOREING KEYS DAS TABELAS.

ALTER TABLE cidade ADD CONSTRAINT cidade_estado_fkey 
	FOREIGN KEY (fk_estado) REFERENCES estado (id_estado)
;

ALTER TABLE endereco ADD CONSTRAINT endereco_cidade_fkey 
	FOREIGN KEY (fk_cidade) REFERENCES cidade (id_cidade)
;

ALTER TABLE endereco ADD CONSTRAINT endereco_pessoa_fkey 
	FOREIGN KEY (fk_pessoa) REFERENCES pessoa (id_pessoa)
;

ALTER TABLE pessoa ADD CONSTRAINT pessoa_endereco_principal_fkey 
	FOREIGN KEY (fk_endereco) REFERENCES endereco (id_endereco)
;

ALTER TABLE usuario ADD CONSTRAINT usuario_pessoa_fkey 
	FOREIGN KEY (fk_pessoa) REFERENCES pessoa (id_pessoa)
;

ALTER TABLE cliente ADD CONSTRAINT cliente_pessoa_fkey 
	FOREIGN KEY (fk_pessoa) REFERENCES pessoa (id_pessoa)
;

ALTER TABLE produto ADD CONSTRAINT produto_mercado_fkey 
	FOREIGN KEY (fk_mercado) REFERENCES mercado (id_mercado)
;

ALTER TABLE items_lista ADD CONSTRAINT item_lista_pronta_fkey 
	FOREIGN KEY (fk_lista_pronta) REFERENCES lista_pronta (id_lista_pronta)
;

ALTER TABLE items_lista ADD CONSTRAINT item_lista_personalizada_fkey 
	FOREIGN KEY (fk_lista_personalizada) REFERENCES lista_personalizada (id_lista_personalizada)
;

ALTER TABLE items_lista ADD CONSTRAINT item_lista_pessoa_fkey 
	FOREIGN KEY (fk_pessoa) REFERENCES pessoa (id_pessoa)
;

ALTER TABLE items_lista ADD CONSTRAINT item_lista_produto_fkey 
	FOREIGN KEY (fk_produto) REFERENCES produto (id_produto)
;

ALTER TABLE items_lista ADD CONSTRAINT item_lista_cliente_fkey 
	FOREIGN KEY (fk_produto) REFERENCES produto (id_produto)
;




-- DROPS DAS TABELAS --

-- DROP DATABASE clicklistmaster;

-- DROP TABLE mercado;
-- DROP TABLE estado;
-- DROP TABLE cliente;
-- DROP TABLE endereco;
-- DROP TABLE produto;
-- DROP TABLE lista_pronta;
-- DROP TABLE lista_personalizada;
-- DROP TABLE mercado;
-- DROP TABLE usuario;
-- DROP TABLE items_lista;
-- DROP TABLE pessoa;
-- DROP TABLE cidade;







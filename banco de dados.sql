CREATE TABLE IF NOT EXISTS public.cliente
(
    id_cliente integer NOT NULL DEFAULT nextval('cliente_id_cliente_seq'::regclass),
    nome_cliente character varying(50) COLLATE pg_catalog."default",
    cpf integer,
    telefone integer,
    CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente)
);


CREATE TABLE IF NOT EXISTS public.funcionarios
(
    id_funcionario integer NOT NULL DEFAULT nextval('funcionarios_id_funcionario_seq1'::regclass),
    nome_funcionario character varying(255) COLLATE pg_catalog."default" NOT NULL,
    cargo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT funcionarios_pkey PRIMARY KEY (id_funcionario)
);

CREATE TABLE IF NOT EXISTS public.quartos
(
    id_quarto integer NOT NULL DEFAULT nextval('quarto_id_quarto_seq'::regclass),
    tipo_quarto character varying(50) COLLATE pg_catalog."default",
    status_quarto character varying(50) COLLATE pg_catalog."default",
    numero_quarto integer,
    CONSTRAINT quarto_pkey PRIMARY KEY (id_quarto),
    CONSTRAINT unique_numero_quarto UNIQUE (numero_quarto)
);


CREATE TABLE IF NOT EXISTS public.reserva
(
    id_reserva integer NOT NULL DEFAULT nextval('reserva_id_reserva_seq'::regclass),
    cpf integer,
    nome_cliente character varying COLLATE pg_catalog."default",
    telefone integer,
    tipo_quarto character varying COLLATE pg_catalog."default",
    status_quarto character varying COLLATE pg_catalog."default",
    data_entrada character varying COLLATE pg_catalog."default",
    data_saida character varying COLLATE pg_catalog."default",
    nome_funcionario character varying COLLATE pg_catalog."default",
    status character varying COLLATE pg_catalog."default",
    numero_quarto integer,
    CONSTRAINT reserva_pkey PRIMARY KEY (id_reserva),
    CONSTRAINT fk_numero_quarto FOREIGN KEY (numero_quarto)
        REFERENCES public.quartos (numero_quarto) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);





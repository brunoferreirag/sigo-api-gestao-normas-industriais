CREATE TABLE public."norma-industrial"
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    codigo character varying(30) COLLATE pg_catalog."default" NOT NULL,
    "data-vigor" date NOT NULL,
    versao integer NOT NULL,
    titulo character varying(300) COLLATE pg_catalog."default" NOT NULL,
    autor character varying(100) COLLATE pg_catalog."default" NOT NULL,
    link character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "norma-industrial_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."norma-industrial"
    OWNER to uml26b3g8enhrluke18m;
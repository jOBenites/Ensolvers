--
-- PostgreSQL database dump
--

-- Dumped from database version 17.1 (Debian 17.1-1.pgdg120+1)
-- Dumped by pg_dump version 17.0

-- Started on 2025-03-09 15:23:01

DROP SEQUENCE IF EXISTS "public"."sec_category";
CREATE SEQUENCE public.sec_category
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sec_category OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 58167)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer DEFAULT nextval('public.sec_category'::regclass) NOT NULL,
    name character varying(255) NOT NULL,
    color character varying(500) NOT NULL,
    active character(1) DEFAULT 1 NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 58166)
-- Name: sec_note; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sec_note
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sec_note OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 58174)
-- Name: note; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.note (
    id integer DEFAULT nextval('public.sec_note'::regclass) NOT NULL,
    title character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    active character(1) DEFAULT 1 NOT NULL,
    flag_archived character(1) DEFAULT 0
);


ALTER TABLE public.note OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 58194)
-- Name: sec_note_category; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sec_note_category
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sec_note_category OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 58191)
-- Name: note_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.note_category (
    id integer DEFAULT nextval('public.sec_note_category'::regclass) NOT NULL,
    note_id integer NOT NULL,
    category_id integer NOT NULL
);


ALTER TABLE public.note_category OWNER TO postgres;

--
-- TOC entry 3377 (class 0 OID 58167)
-- Dependencies: 219
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.category VALUES (32, 'Prioridad', '#fefefe', '1');
INSERT INTO public.category VALUES (33, 'Normal', '#ffffff', '1');


--
-- TOC entry 3378 (class 0 OID 58174)
-- Dependencies: 220
-- Data for Name: note; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.note VALUES (32, 'Creación Nota App', 'Presentación de app notas para ensolvers', '1', '0');


--
-- TOC entry 3379 (class 0 OID 58191)
-- Dependencies: 221
-- Data for Name: note_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.note_category VALUES (9, 32, 32);


--
-- TOC entry 3387 (class 0 OID 0)
-- Dependencies: 217
-- Name: sec_category; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sec_category', 33, true);


--
-- TOC entry 3388 (class 0 OID 0)
-- Dependencies: 218
-- Name: sec_note; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sec_note', 32, true);


--
-- TOC entry 3389 (class 0 OID 0)
-- Dependencies: 222
-- Name: sec_note_category; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sec_note_category', 10, true);


--
-- TOC entry 3227 (class 2606 OID 58183)
-- Name: category categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- TOC entry 3229 (class 2606 OID 58185)
-- Name: note note_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.note
    ADD CONSTRAINT note_pkey PRIMARY KEY (id);


-- Completed on 2025-03-09 15:23:02

--
-- PostgreSQL database dump complete
--


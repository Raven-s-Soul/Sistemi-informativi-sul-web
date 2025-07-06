--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

-- Started on 2025-07-07 01:40:13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4845 (class 0 OID 28707)
-- Dependencies: 219
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.author VALUES ('1923-01-01', '1985-01-01', 1, NULL, 'Italo', 'Calvino', 'Italiana');
INSERT INTO public.author VALUES ('1949-01-01', NULL, 2, NULL, 'Haruki', 'Murakami', 'Giappone');
INSERT INTO public.author VALUES ('1775-01-01', '1817-01-01', 3, NULL, 'Jane', 'Austen', 'Regno Unito');


--
-- TOC entry 4847 (class 0 OID 28717)
-- Dependencies: 221
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.book VALUES (2025, 1, 'Libro A');
INSERT INTO public.book VALUES (2025, 2, 'Libro B');
INSERT INTO public.book VALUES (2025, 3, 'Libro C');
INSERT INTO public.book VALUES (2025, 6, 'Libro D');


--
-- TOC entry 4848 (class 0 OID 28722)
-- Dependencies: 222
-- Data for Name: book_authors; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.book_authors VALUES (1, 1);
INSERT INTO public.book_authors VALUES (2, 2);
INSERT INTO public.book_authors VALUES (3, 3);
INSERT INTO public.book_authors VALUES (1, 6);
INSERT INTO public.book_authors VALUES (2, 6);
INSERT INTO public.book_authors VALUES (3, 6);


--
-- TOC entry 4855 (class 0 OID 28754)
-- Dependencies: 229
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users VALUES (2, 'Bob@b.b', 'Bob', 'REGISTERED', 'Beta');
INSERT INTO public.users VALUES (1, 'Admin@a.a', 'Admin', 'ADMIN', 'Alpha');


--
-- TOC entry 4849 (class 0 OID 28725)
-- Dependencies: 223
-- Data for Name: credentials; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.credentials VALUES (1, 1, '$2a$10$uYSewLTykTiN1Silkzzi6uvmrN9J9hvmgJS4Exz5/o8trd6eA2YGi', 'a');
INSERT INTO public.credentials VALUES (2, 2, '$2a$10$AWUVMLHN7mFyef1Z8agB3.1LpDvlVpK609XE.IUpjY.mHYjIJ6Bmi', 'b');


--
-- TOC entry 4851 (class 0 OID 28737)
-- Dependencies: 225
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4853 (class 0 OID 28745)
-- Dependencies: 227
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.review VALUES (5, 6, 1, 2, 'Descrizione pazzesca!', 'Figo!');
INSERT INTO public.review VALUES (4, 2, 2, 2, '8/10 e ci rimetto.', 'OTTOHH!');
INSERT INTO public.review VALUES (1, 6, 3, 1, 'gra... gra...', 'Non mi piace!');
INSERT INTO public.review VALUES (2, 1, 4, 1, 'Viva i calvi....', 'Sono pelato adesso!');


--
-- TOC entry 4861 (class 0 OID 0)
-- Dependencies: 218
-- Name: author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.author_id_seq', 3, true);


--
-- TOC entry 4862 (class 0 OID 0)
-- Dependencies: 220
-- Name: book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.book_id_seq', 6, true);


--
-- TOC entry 4863 (class 0 OID 0)
-- Dependencies: 217
-- Name: credentials_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.credentials_seq', 1, false);


--
-- TOC entry 4864 (class 0 OID 0)
-- Dependencies: 224
-- Name: image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_id_seq', 1, false);


--
-- TOC entry 4865 (class 0 OID 0)
-- Dependencies: 226
-- Name: review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.review_id_seq', 4, true);


--
-- TOC entry 4866 (class 0 OID 0)
-- Dependencies: 228
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 2, true);


-- Completed on 2025-07-07 01:40:13

--
-- PostgreSQL database dump complete
--


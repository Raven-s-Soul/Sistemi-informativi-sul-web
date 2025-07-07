--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

-- Started on 2025-07-07 16:27:11

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
-- TOC entry 4846 (class 0 OID 31351)
-- Dependencies: 219
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.author (birth_date, death_date, id, image_id, first_name, last_name, nationality) VALUES ('1923-01-01', '1985-01-01', 1, NULL, 'Italo', 'Calvino', 'Italiana');
INSERT INTO public.author (birth_date, death_date, id, image_id, first_name, last_name, nationality) VALUES ('1949-02-02', NULL, 2, NULL, 'Haruki', 'Murakami', 'Giapponese');
INSERT INTO public.author (birth_date, death_date, id, image_id, first_name, last_name, nationality) VALUES ('1775-05-06', '1817-06-07', 3, NULL, 'Jane', 'Austen', 'Regno Unito');


--
-- TOC entry 4848 (class 0 OID 31361)
-- Dependencies: 221
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.book (publication_year, id, title) VALUES (2000, 1, 'Libro A');
INSERT INTO public.book (publication_year, id, title) VALUES (2025, 2, 'Libro B');
INSERT INTO public.book (publication_year, id, title) VALUES (2030, 3, 'Libro C');
INSERT INTO public.book (publication_year, id, title) VALUES (2025, 4, 'Libro D');


--
-- TOC entry 4849 (class 0 OID 31366)
-- Dependencies: 222
-- Data for Name: book_authors; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.book_authors (author_id, book_id) VALUES (1, 1);
INSERT INTO public.book_authors (author_id, book_id) VALUES (2, 2);
INSERT INTO public.book_authors (author_id, book_id) VALUES (3, 3);
INSERT INTO public.book_authors (author_id, book_id) VALUES (1, 4);
INSERT INTO public.book_authors (author_id, book_id) VALUES (2, 4);
INSERT INTO public.book_authors (author_id, book_id) VALUES (3, 4);


--
-- TOC entry 4857 (class 0 OID 31399)
-- Dependencies: 230
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, email, name, role, surname) VALUES (2, 'b@b.b', 'Bob', 'REGISTERED', 'Beta');
INSERT INTO public.users (id, email, name, role, surname) VALUES (1, 'a@a', 'Admin', 'ADMIN', 'Alpha');


--
-- TOC entry 4851 (class 0 OID 31370)
-- Dependencies: 224
-- Data for Name: credentials; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.credentials (id, user_id, password, username) VALUES (1, 1, '$2a$10$WRmOwz2nYyl3D611SKwAKOsIsC74x/IPt9G5KeDtMzLXtolwsr3mm', 'a');
INSERT INTO public.credentials (id, user_id, password, username) VALUES (2, 2, '$2a$10$Ajc227gtk7CTnCP.C7LGe.8kYT33OpYnR/I3VAaIwjKLLfvhwWJnu', 'b');


--
-- TOC entry 4853 (class 0 OID 31382)
-- Dependencies: 226
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4855 (class 0 OID 31390)
-- Dependencies: 228
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.review (rating, book_id, id, user_id, text, title) VALUES (4, 1, 1, 1, 'Piacevole', 'A è bello');
INSERT INTO public.review (rating, book_id, id, user_id, text, title) VALUES (1, 4, 2, 1, '...', 'Assurdo!');
INSERT INTO public.review (rating, book_id, id, user_id, text, title) VALUES (2, 4, 3, 2, 'Ci sta!', 'Ok');
INSERT INTO public.review (rating, book_id, id, user_id, text, title) VALUES (4, 2, 4, 2, 'Bonoooo!', 'Bono!');


--
-- TOC entry 4863 (class 0 OID 0)
-- Dependencies: 218
-- Name: author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.author_id_seq', 3, true);


--
-- TOC entry 4864 (class 0 OID 0)
-- Dependencies: 220
-- Name: book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.book_id_seq', 4, true);


--
-- TOC entry 4865 (class 0 OID 0)
-- Dependencies: 223
-- Name: credentials_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.credentials_id_seq', 2, true);


--
-- TOC entry 4866 (class 0 OID 0)
-- Dependencies: 217
-- Name: credentials_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.credentials_seq', 1, false);


--
-- TOC entry 4867 (class 0 OID 0)
-- Dependencies: 225
-- Name: image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_id_seq', 1, false);


--
-- TOC entry 4868 (class 0 OID 0)
-- Dependencies: 227
-- Name: review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.review_id_seq', 4, true);


--
-- TOC entry 4869 (class 0 OID 0)
-- Dependencies: 229
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 2, true);


-- Completed on 2025-07-07 16:27:11

--
-- PostgreSQL database dump complete
--


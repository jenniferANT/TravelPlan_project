--
-- PostgreSQL database dump
--

-- Dumped from database version 14.9 (Ubuntu 14.9-1.pgdg22.04+1)
-- Dumped by pg_dump version 16.0 (Ubuntu 16.0-1.pgdg22.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    address_link_map character varying(2048),
    address_plus_code character varying(2048),
    address_string character varying(255),
    embedded_address character varying(2048),
    latitude double precision,
    longitude double precision
);


ALTER TABLE public.address OWNER TO postgres;

--
-- Name: address_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.address_id_seq OWNER TO postgres;

--
-- Name: address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.address_id_seq OWNED BY public.address.id;


--
-- Name: carts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.carts (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    user_id bigint
);


ALTER TABLE public.carts OWNER TO postgres;

--
-- Name: carts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.carts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.carts_id_seq OWNER TO postgres;

--
-- Name: carts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.carts_id_seq OWNED BY public.carts.id;


--
-- Name: carts_plan_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.carts_plan_list (
    cart_id bigint NOT NULL,
    plan_list_id bigint NOT NULL
);


ALTER TABLE public.carts_plan_list OWNER TO postgres;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    name character varying(255),
    category_id bigint
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- Name: categories_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories_categories (
    category_id bigint NOT NULL,
    categories_id bigint NOT NULL
);


ALTER TABLE public.categories_categories OWNER TO postgres;

--
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categories_id_seq OWNER TO postgres;

--
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categories_id_seq OWNED BY public.categories.id;


--
-- Name: follow; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.follow (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    quantity integer NOT NULL,
    user_id bigint
);


ALTER TABLE public.follow OWNER TO postgres;

--
-- Name: follow_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.follow_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.follow_id_seq OWNER TO postgres;

--
-- Name: follow_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.follow_id_seq OWNED BY public.follow.id;


--
-- Name: follow_places; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.follow_places (
    follow_id bigint NOT NULL,
    places_id bigint NOT NULL
);


ALTER TABLE public.follow_places OWNER TO postgres;

--
-- Name: image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    data bytea NOT NULL,
    name character varying(255),
    type character varying(255),
    places_id bigint,
    review_id bigint,
    user_id bigint
);


ALTER TABLE public.image OWNER TO postgres;

--
-- Name: image_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.image_id_seq OWNER TO postgres;

--
-- Name: image_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.image_id_seq OWNED BY public.image.id;


--
-- Name: link; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.link (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    name character varying(255),
    url character varying(255)
);


ALTER TABLE public.link OWNER TO postgres;

--
-- Name: link_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.link_id_seq OWNER TO postgres;

--
-- Name: link_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.link_id_seq OWNED BY public.link.id;


--
-- Name: places; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.places (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    begin_day time(6) without time zone,
    cost numeric(38,2),
    description character varying(2048),
    end_day time(6) without time zone,
    is_full boolean,
    phone_number character varying(255),
    point real NOT NULL,
    time_places bigint,
    title character varying(255),
    address_id bigint,
    link_id bigint,
    user_id bigint
);


ALTER TABLE public.places OWNER TO postgres;

--
-- Name: places_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.places_category (
    places_id bigint NOT NULL,
    category_id bigint NOT NULL
);


ALTER TABLE public.places_category OWNER TO postgres;

--
-- Name: places_follow; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.places_follow (
    places_id bigint NOT NULL,
    follow_id bigint NOT NULL
);


ALTER TABLE public.places_follow OWNER TO postgres;

--
-- Name: places_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.places_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.places_id_seq OWNER TO postgres;

--
-- Name: places_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.places_id_seq OWNED BY public.places.id;


--
-- Name: places_images; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.places_images (
    places_id bigint NOT NULL,
    images_id bigint NOT NULL
);


ALTER TABLE public.places_images OWNER TO postgres;

--
-- Name: places_reviews; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.places_reviews (
    places_id bigint NOT NULL,
    reviews_id bigint NOT NULL
);


ALTER TABLE public.places_reviews OWNER TO postgres;

--
-- Name: plan_cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plan_cart (
    plan_id bigint NOT NULL,
    cart_id bigint NOT NULL
);


ALTER TABLE public.plan_cart OWNER TO postgres;

--
-- Name: plan_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plan_category (
    plan_id bigint NOT NULL,
    category_id bigint NOT NULL
);


ALTER TABLE public.plan_category OWNER TO postgres;

--
-- Name: plan_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plan_item (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    content character varying(2048),
    image_url character varying(2048),
    index integer NOT NULL,
    is_optional boolean,
    money numeric(38,2),
    note character varying(255),
    place_id bigint,
    start_time timestamp(6) without time zone,
    title character varying(255),
    plan_id bigint
);


ALTER TABLE public.plan_item OWNER TO postgres;

--
-- Name: plan_item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.plan_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.plan_item_id_seq OWNER TO postgres;

--
-- Name: plan_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.plan_item_id_seq OWNED BY public.plan_item.id;


--
-- Name: plans; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plans (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    begin_date timestamp(6) without time zone,
    cost_eat numeric(38,2),
    cost_play numeric(38,2),
    cost_vehicle numeric(38,2),
    destination character varying(255),
    distance bigint NOT NULL,
    end_date timestamp(6) without time zone,
    estimated_total_distance bigint,
    expense numeric(38,2),
    number_people integer,
    number_vehicle integer,
    title character varying(255),
    location_id bigint,
    user_id bigint,
    vehicle_id bigint
);


ALTER TABLE public.plans OWNER TO postgres;

--
-- Name: plans_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.plans_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.plans_id_seq OWNER TO postgres;

--
-- Name: plans_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.plans_id_seq OWNED BY public.plans.id;


--
-- Name: plans_plan_items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plans_plan_items (
    plan_id bigint NOT NULL,
    plan_items_id bigint NOT NULL
);


ALTER TABLE public.plans_plan_items OWNER TO postgres;

--
-- Name: reviews; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reviews (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    content character varying(255),
    point integer NOT NULL,
    places_id bigint,
    user_id bigint
);


ALTER TABLE public.reviews OWNER TO postgres;

--
-- Name: reviews_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reviews_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.reviews_id_seq OWNER TO postgres;

--
-- Name: reviews_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reviews_id_seq OWNED BY public.reviews.id;


--
-- Name: reviews_image_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reviews_image_list (
    review_id bigint NOT NULL,
    image_list_id bigint NOT NULL
);


ALTER TABLE public.reviews_image_list OWNER TO postgres;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    name character varying(255)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.roles_id_seq OWNER TO postgres;

--
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- Name: shares; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shares (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    message character varying(255),
    plan_id bigint,
    receiver_id bigint,
    remitter_id bigint
);


ALTER TABLE public.shares OWNER TO postgres;

--
-- Name: shares_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.shares_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.shares_id_seq OWNER TO postgres;

--
-- Name: shares_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.shares_id_seq OWNED BY public.shares.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    username character varying(255),
    avatar_id bigint,
    role_id bigint
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: vehicle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vehicle (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    average_speed bigint NOT NULL,
    cost numeric(38,2),
    name character varying(255),
    seats integer NOT NULL
);


ALTER TABLE public.vehicle OWNER TO postgres;

--
-- Name: vehicle_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vehicle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vehicle_id_seq OWNER TO postgres;

--
-- Name: vehicle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vehicle_id_seq OWNED BY public.vehicle.id;


--
-- Name: address id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address ALTER COLUMN id SET DEFAULT nextval('public.address_id_seq'::regclass);


--
-- Name: carts id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts ALTER COLUMN id SET DEFAULT nextval('public.carts_id_seq'::regclass);


--
-- Name: categories id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories ALTER COLUMN id SET DEFAULT nextval('public.categories_id_seq'::regclass);


--
-- Name: follow id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.follow ALTER COLUMN id SET DEFAULT nextval('public.follow_id_seq'::regclass);


--
-- Name: image id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image ALTER COLUMN id SET DEFAULT nextval('public.image_id_seq'::regclass);


--
-- Name: link id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.link ALTER COLUMN id SET DEFAULT nextval('public.link_id_seq'::regclass);


--
-- Name: places id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places ALTER COLUMN id SET DEFAULT nextval('public.places_id_seq'::regclass);


--
-- Name: plan_item id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan_item ALTER COLUMN id SET DEFAULT nextval('public.plan_item_id_seq'::regclass);


--
-- Name: plans id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plans ALTER COLUMN id SET DEFAULT nextval('public.plans_id_seq'::regclass);


--
-- Name: reviews id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews ALTER COLUMN id SET DEFAULT nextval('public.reviews_id_seq'::regclass);


--
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- Name: shares id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shares ALTER COLUMN id SET DEFAULT nextval('public.shares_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Name: vehicle id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle ALTER COLUMN id SET DEFAULT nextval('public.vehicle_id_seq'::regclass);


--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.address (id, created_at, updated_at, address_link_map, address_plus_code, address_string, embedded_address, latitude, longitude) FROM stdin;
1	2023-11-13 01:20:22.28549	2023-11-13 01:20:22.285527	https://maps.app.goo.gl/4JkgkAhUp77wRwmb7	address plus code	Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1774.4146363029035!2d107.0823501575645!3d10.322124305079603!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756544a05a779d%3A0x8be1eab135ba4174!2sMui%20Nghinh%20Phong!5e0!3m2!1svi!2s!4v1699761060139!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
2	2023-11-13 01:20:22.29391	2023-11-13 01:20:22.293944	https://maps.app.goo.gl/8EFQjnDQZ1YxWF8s8	address plus code	01, Bà Rịa, Vũng Tàu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15700.770565068155!2d107.07423475033224!3d10.32646294698442!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175655b99e2ee9f%3A0x43bb3d544ec80b01!2zVMaw4bujbmcgxJHDoGkgQ2jDumEgS2l0w7Q!5e0!3m2!1svi!2s!4v1699761138530!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
3	2023-11-13 01:20:22.300862	2023-11-13 01:20:22.300982	https://maps.app.goo.gl/838st7UyPZU7H6Hi7	address plus code	249 Đường số 14, Suối Nghệ, Châu Đức, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d31374.380383552678!2d107.19723096919085!3d10.59499598260942!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x317509e526a5f8f9%3A0x9216f3017d2b032f!2zxJDhu5NpIEPhu6t1IFN14buRaSBOZ2jhu4c!5e0!3m2!1svi!2s!4v1699761288340!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
4	2023-11-13 01:20:22.31394	2023-11-13 01:20:22.314006	https://maps.app.goo.gl/DP9oabv4y77PLs1WA	address plus code	Vũng Tàu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15699.92239632143!2d107.06383090033474!3d10.343436145587418!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f8e1b01b871%3A0x1d67823392eb0d91!2zQsOjaSBUcsaw4bubYw!5e0!3m2!1svi!2s!4v1699761361262!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
5	2023-11-13 01:20:22.321836	2023-11-13 01:20:22.32188	https://maps.app.goo.gl/5c2hT7X8TpARfneA8	address plus code	83HP+2G Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3925.1792610482207!2d107.08636759999999!3d10.327534799999995!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x317565d2fb839c0d%3A0xbcb13a18114925fd!2zxJDhu5NpIENvbiBIZW8!5e0!3m2!1svi!2s!4v1699761414748!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
6	2023-11-13 01:20:22.32739	2023-11-13 01:20:22.327415	https://maps.app.goo.gl/mBHbtbBSTk9LYK4u7	address plus code	G45W+FV5, Unnamed Road, Tân Thành, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3922.898722789623!2d107.14722409999999!3d10.5086427!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31750d5a4a7c558b%3A0xb148b65dbf38d6f!2zSOG7kyDEkMOhIFhhbmggQsOgIFLhu4th!5e0!3m2!1svi!2s!4v1699761867591!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
7	2023-11-13 01:20:22.336159	2023-11-13 01:20:22.336224	https://maps.app.goo.gl/QTcRQFc3LUeLGXuz7	address plus code	Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15699.824374086978!2d107.10001344999999!3d10.345395949999999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756fe297e51efd%3A0xbb3c2fb0da0c83bf!2zQsOjaSBTYXU!5e0!3m2!1svi!2s!4v1699761758017!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
8	2023-11-13 01:20:22.341764	2023-11-13 01:20:22.341806	https://maps.app.goo.gl/FJwWEnF3YSe4PiF6A	address plus code	01, Phường 2, Bà Rịa, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3925.0975370587066!2d107.0776647!3d10.334079100000002!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756552a003a9b9%3A0x32769656e2f73fb9!2zSOG6o2kgxJDEg25nIFbFqW5nIFTDoHU!5e0!3m2!1svi!2s!4v1699761971330!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
9	2023-11-13 01:20:22.347991	2023-11-13 01:20:22.348026	https://maps.app.goo.gl/jtaL12UWs7fEDTS46	address plus code	C53Q+5HW, Phước Tỉnh, Long Điền, Bà Rịa - Vũng Tàu 78000, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3924.2260107489215!2d107.1889971!3d10.403616000000001!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175714e119aa1b1%3A0xfa8b33a47a4d4821!2sPi%20glamping!5e0!3m2!1svi!2s!4v1699762051033!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
10	2023-11-13 01:20:22.354974	2023-11-13 01:20:22.355004	https://maps.app.goo.gl/iaqnPiyp2W3S7mpb8	address plus code	Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1004857.0684576102!2d106.52889246562499!3d10.324036699999997!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175655aa7530835%3A0x243ba5d7f8c1f373!2zSMOybiBCw6A!5e0!3m2!1svi!2s!4v1699762128408!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
11	2023-11-13 01:20:22.36284	2023-11-13 01:20:22.362867	https://maps.app.goo.gl/YtPb2kJYGiq15Jro8	address plus code	66 Cô Giang, Phường 4, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d48658.08229447103!2d107.04825756809986!3d10.376759452538861!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f95531c75a5%3A0x545bba012f5976d7!2zUXXDoW4gY8OgIHBow6ogTmhhzIAgVcyBcCBuZ8awxqHMo2M!5e0!3m2!1svi!2s!4v1699762198529!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
12	2023-11-13 01:20:22.368339	2023-11-13 01:20:22.368382	https://maps.app.goo.gl/Cdaq9cizgryvr7JLA	address plus code	83WW+5GP, Thùy Vân, Phường Thắng Tam, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3924.955123735594!2d107.09632839999998!3d10.345473500000008!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f85bd1a7c4d%3A0x6215bdd590d296f4!2zQ8O0bmcgdmnDqm4gVGjhu48gVHLhuq9uZw!5e0!3m2!1svi!2s!4v1699762254889!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
13	2023-11-13 01:20:22.373834	2023-11-13 01:20:22.373872	https://maps.app.goo.gl/PsXfYgnZGue82kEp9	address plus code	Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3924.5187563454406!2d107.25461580000002!3d10.380309949999996!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175765ab58ff03f%3A0x45263d08fa8dd33d!2zxJBlzIBvIE7GsMahzIFjIE5nb8yjdA!5e0!3m2!1svi!2s!4v1699762341091!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
14	2023-11-13 01:20:22.379567	2023-11-13 01:20:22.379584	https://maps.app.goo.gl/MX9EkEAc21H45CLu8	address plus code	83JG+7P6, Phường 2, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d62802.24185038577!2d107.04176107910155!3d10.330670000000023!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756560976d4597%3A0xf187fd8b18415911!2zTmnhur90IELDoG4gVOG7i25oIHjDoQ!5e0!3m2!1svi!2s!4v1699762487843!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
15	2023-11-13 01:20:22.385128	2023-11-13 01:20:22.385145	https://maps.app.goo.gl/dxWMmru6wBQSS2TN8	address plus code	4 Trần Phú, Phường 1, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d125596.43130466367!2d106.99854725820312!3d10.350801500000019!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f4ec075d9df%3A0xd30bc2e22f6d2ff4!2sB%E1%BA%A1ch%20Dinh%20(White%20Palace%20Historical%20Cultural%20Relic)!5e0!3m2!1svi!2s!4v1699762567203!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
16	2023-11-13 01:20:22.39061	2023-11-13 01:20:22.390637	https://maps.app.goo.gl/TESbXYTmhM7Kf9UM8	address plus code	109 đường Trần Phú, phường 5	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d62790.60956450434!2d107.0628744985014!3d10.388727598775773!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756e50d6eea639%3A0xe2b265b806480275!2zMTA5IFRy4bqnbiBQaMO6LCBQaMaw4budbmcgNSwgVGjDoG5oIHBo4buRIFbFqW5nIFThuqd1LCBCw6AgUuG7i2EgLSBWxaluZyBUw6B1LCBWaeG7h3QgTmFt!5e0!3m2!1svi!2s!4v1699762715230!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
17	2023-11-13 01:20:22.396275	2023-11-13 01:20:22.396293	https://maps.app.goo.gl/tNnq8swhkPAYcy8b7	address plus code	Vũng Tàu, Bà Rịa - Vũng Tàu 790000, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3924.148067695814!2d107.11293749999997!3d10.409812500000012!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x317571f79ea4a9c9%3A0x3313721619f7ad9!2zQsOKzIFOIFRIVVnhu4BOIFbFqE5HIFTDgFUgTUFSSU5B!5e0!3m2!1svi!2s!4v1699762767600!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
18	2023-11-13 01:20:22.402043	2023-11-13 01:20:22.402061	https://maps.app.goo.gl/pJCxJgGrnJ5HFLeL6	address plus code	140A Trần Phú, Phường 5, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3924.633081958935!2d107.0622475!3d10.371194200000001!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756faef38389e3%3A0x60b83316113c26b4!2zxJDhu4FuIFRow6FuaCDEkOG7qWMgTeG6uSBCw6NpIETDonU!5e0!3m2!1svi!2s!4v1699762819064!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
19	2023-11-13 01:20:22.407425	2023-11-13 01:20:22.407444	https://maps.app.goo.gl/DAPGrT2itNsUWkrf9	address plus code	Mạc Thanh Đạm, Phường 8, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu 790000, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d31398.83666714762!2d107.08361553955079!3d10.353510199999999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f3a7048899d%3A0xdbcc5a7fb9aed60d!2zQ-G7lW5nIEhvYSBHaeG6pXkgQ2hlY2sgaW4gVsWpbmcgVMOgdQ!5e0!3m2!1svi!2s!4v1699762902912!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
20	2023-11-13 01:20:22.412903	2023-11-13 01:20:22.412927	https://maps.app.goo.gl/8x2GAXK6cfmCgTrZ8	address plus code	227 Cách Mạng Tháng Tám, Phước Hiệp, Bà Rịa, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3923.0500924360367!2d107.17022359999999!3d10.496717500000006!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175733675f5ba1b%3A0x35ceb96536126683!2zTmjDoCBUaOG7nSBDaMOhbmggVMOyYSBHacOhbyBQaOG6rW4gQsOgIFLhu4th!5e0!3m2!1svi!2s!4v1699762986035!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
21	2023-11-13 01:20:22.418323	2023-11-13 01:20:22.418349	https://maps.app.goo.gl/gCqXHz9iHfdBsGex9	address plus code	02 Phạm Ngọc Thạch, Phường 9, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu 78000, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3924.6771602648764!2d107.0863796!3d10.3676775!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756ff11cabd923%3A0x111f43b74370d551!2sLens%20Studio!5e0!3m2!1svi!2s!4v1699763178197!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
22	2023-11-13 01:20:22.423732	2023-11-13 01:20:22.423751	https://maps.app.goo.gl/snmXajrBXRSo5v3R9	address plus code	Vũng Tàu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d31397.37355492309!2d107.06501839999999!3d10.368113649999996!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756fbb47737b07%3A0x6e4c656745ff0651!2zTsO6aSBM4bubbg!5e0!3m2!1svi!2s!4v1699763241568!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
23	2023-11-13 01:20:22.428996	2023-11-13 01:20:22.429012	https://maps.app.goo.gl/WBahFPQXxcLMQjgJA	address plus code	608 Trần Phú, Phường 5, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3924.5925214238073!2d107.07066770000002!3d10.374429200000007!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756fb38ba27c71%3A0xa395adebef3476f!2zQ2jDuWEgSOG7mSBQaMOhcA!5e0!3m2!1svi!2s!4v1699763321433!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
24	2023-11-13 01:20:22.434773	2023-11-13 01:20:22.434808	https://maps.app.goo.gl/cGjuDW7Y75VsyPmw9	address plus code	Vũng Tàu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15700.56736139334!2d107.06687950163241!3d10.330531886677024!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756560a1e0b197%3A0xf81958e8863cf9d2!2zQsOjaSBE4bupYQ!5e0!3m2!1svi!2s!4v1699763524681!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
25	2023-11-13 01:20:22.441997	2023-11-13 01:20:22.442016	https://maps.app.goo.gl/XyZiVLXbvQcufHkBA	address plus code	83XV+H3C, 3 Tháng 2, Phường Thắng Tam, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7849.823831693425!2d107.09264476184745!3d10.348928082331879!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756fe683debe43%3A0x73d97f258c1bee98!2zVMaw4bujbmcgxJDDoGkgTGnhu4d0IFPhu7k!5e0!3m2!1svi!2s!4v1699763694030!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
26	2023-11-13 01:20:22.448858	2023-11-13 01:20:22.448937	https://maps.app.goo.gl/s11MnxaorFX9x6As7	address plus code	44 Trương Công Định, Phường 3, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3924.9690414474935!2d107.0793599!3d10.344360499999999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756ff290b0989f%3A0x8e3c179ac7d1fe2a!2zTOG6qXUgQ8OhIMSQdeG7kWkgSG_DoG5nIE1pbmggKENow61uaCBIaeG7h3UpIDQ0IFRyxrDGoW5nIEPDtG5nIMSQ4buLbmg!5e0!3m2!1svi!2s!4v1699764793166!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
27	2023-11-13 01:20:22.454863	2023-11-13 01:20:22.454883	https://maps.app.goo.gl/gMX3LjYcNAaxoYpw9	address plus code	10 Trương Công Định, Phường 1, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu 790000, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d25933.661211328585!2d107.0619251995988!3d10.340847544584696!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f8d2cf3946b%3A0x43a54c868899e538!2zTOG6qXUgQ8OhIMSQdeG7kWkgw5p0IE3GsOG7nWk!5e0!3m2!1svi!2s!4v1699764861886!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
28	2023-11-13 01:20:22.462301	2023-11-13 01:20:22.462324	https://maps.app.goo.gl/B5A7XhWVXpAEmQME6	address plus code	268 Trương Công Định, Phường 3, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3924.8471029902516!2d107.0846362!3d10.354107900000004!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756fc022667ef7%3A0x52b68c61cb07ba23!2zQsOhbmggTcOsIFjDrXUgTeG6oWkgSMOgbmcgUXV5w6puIChDaGkgTmjDoW5oIDIp!5e0!3m2!1svi!2s!4v1699764929902!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
29	2023-11-13 01:20:22.472492	2023-11-13 01:20:22.472512	https://maps.app.goo.gl/rC9dzQBtGKoAczt67	address plus code	34 Đ. Hoàng Hoa Thám, Phường 2, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3925.0267785002006!2d107.0797473!3d10.339742!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756ff363327505%3A0xa69712274cf63bab!2zUXXDoW4gxINuIFbGsOG7nW4gWG_DoGk!5e0!3m2!1svi!2s!4v1699765011868!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
30	2023-11-13 01:20:22.481481	2023-11-13 01:20:22.481523	https://maps.app.goo.gl/5HxFk4FNJ2sFKAPL7	address plus code	19 Phan Bội Châu, Phường 2, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu 790000, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3925.0158581084206!2d107.07368261744384!3d10.340615700000006!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f99753c6bc9%3A0x8db10d65915b4ec6!2zQsOhbmggY2FuaCBnaOG6uSBCw6AgQmEgLSBxdcOhbiBiw6FuaCBjYW5oIGdo4bq5IG5nb24sIGNo4bqldCBsxrDhu6NuZyB04bqhaSBWxaluZyBUw6B1!5e0!3m2!1svi!2s!4v1699765073593!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
31	2023-11-13 01:20:22.48998	2023-11-13 01:20:22.490026	https://maps.app.goo.gl/u6yNHfid95snWw9r6	address plus code	109 Võ Thị Sáu, Phường 2, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu 79000, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3925.0901105171397!2d107.08358151744383!3d10.334673600000022!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756ff62fa98811%3A0x76f4db24ca4a9182!2zQsOhbmggY2FuaCBnaOG6uSBBbmggVnk!5e0!3m2!1svi!2s!4v1699765113668!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
32	2023-11-13 01:20:22.497153	2023-11-13 01:20:22.497182	https://maps.app.goo.gl/ZBpZ3XxieJPDcDp38	address plus code	84 Cô Giang, Phường 4, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7849.65279836544!2d107.07244373488768!3d10.355762000000015!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f97ab7098f3%3A0x9147b7c1ee9291f7!2zQsO6biBjaOG6oyBjw6EgLSBCw7puIHPhu6lh!5e0!3m2!1svi!2s!4v1699765173771!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
33	2023-11-13 01:20:22.505372	2023-11-13 01:20:22.505408	https://maps.app.goo.gl/rVQnWG6GZ5VbnAKb9	address plus code	44C Bà Triệu, Phường 1, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d18168.352951858895!2d107.09847342548075!3d10.348134996955267!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f94b870a761%3A0x474ac963653373ff!2zQsO6biBz4bupYSBWxaluZyBUw6B1ICggUXXDoW4gNzkgTmhhIFRyYW5nICk!5e0!3m2!1svi!2s!4v1699765241197!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
34	2023-11-13 01:20:22.514105	2023-11-13 01:20:22.514161	https://maps.app.goo.gl/jJ7kSpE8EWcHuaVj7	address plus code	60 Võ Thị Sáu, Phường 2, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d6783.456000728388!2d107.08249477270623!3d10.33761063370502!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756fd754a854d5%3A0x919512222811c351!2z4buQYyBUaGnDqm4gTmhpw6puIC0gQsOjaSBTYXU!5e0!3m2!1svi!2s!4v1699765315362!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
35	2023-11-13 01:20:22.524923	2023-11-13 01:20:22.525013	https://maps.app.goo.gl/PEhVJfbz4MuQKxLP7	address plus code	46 Cao Xuân Dục, Phường 7, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d31400.57441186451!2d107.04911964452845!3d10.33613910688546!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756ff5819d59dd%3A0xae6c4ff6d3cbfcb0!2zQsO6biDhu5FjIGPDoSBjw7QgSOG6sW5nIDQ2IENhbyBYdcOibiBE4bulYw!5e0!3m2!1svi!2s!4v1699765408398!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
36	2023-11-13 01:20:22.533922	2023-11-13 01:20:22.533939	https://maps.app.goo.gl/fz9RvAiVhBmLKNoR6	address plus code	166 Trương Công Định, Phường 3, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d4296.485287860469!2d107.08175573447082!3d10.350354488529831!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f278bfaa08d%3A0x48343c77e4161ec8!2zTOG6qXUgdMO0bSA1IFJpIChjaGkgbmjDoW5oIFbFqW5nIFTDoHUp!5e0!3m2!1svi!2s!4v1699765500134!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
37	2023-11-13 01:20:22.539019	2023-11-13 01:20:22.539033	https://maps.app.goo.gl/4jpHo7LBqVNaKWxN9	address plus code	14 Nguyễn Trường Tộ, Phường 3, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3925.0194454271677!2d107.07882439999999!3d10.340328700000002!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f075038598d%3A0x7b83f3773aba362c!2zQsOhbmggS2jhu410IEfhu5FjIFbDuiBT4buvYQ!5e0!3m2!1svi!2s!4v1699773145129!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
38	2023-11-13 01:20:22.544206	2023-11-13 01:20:22.544221	https://maps.app.goo.gl/6AkzFTH8RGezauwc9	address plus code	1 Đ. Hoàng Hoa Thám, Phường 3, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15700.04287049662!2d107.06804805033441!3d10.341026945785838!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f3574b531cf%3A0x93afef3306b19cef!2zQsOhbmggS2jhu410IEPDtCBCYQ!5e0!3m2!1svi!2s!4v1699773231689!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
39	2023-11-13 01:20:22.549522	2023-11-13 01:20:22.549548	https://maps.app.goo.gl/yjPGXkv6i6Sm17XF7	address plus code	59 Bà Triệu, Phường 4, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3924.857742130451!2d107.07568490000003!3d10.353257800000005!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f2fd475a923%3A0x28064ad1792f8dbb!2zQsOhbmgga2jhu410IE1p4buBbiDEkcO0bmcgQ2s!5e0!3m2!1svi!2s!4v1699773293002!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
40	2023-11-13 01:20:22.555535	2023-11-13 01:20:22.555552	https://maps.app.goo.gl/iEnKjAp8Rg1qkY469	address plus code	03 Trần Phú, Phường 5, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3924.7729016064673!2d107.05808351744385!3d10.360034900000006!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756febf0656077%3A0xf390596a3075a7df!2zTmjDoCBow6BuZyBHw6BuaCBIw6BvIDE!5e0!3m2!1svi!2s!4v1699773500649!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
41	2023-11-13 01:20:22.560744	2023-11-13 01:20:22.56076	https://maps.app.goo.gl/6E7wM54CELQB5c5PA	address plus code	57 Trần Phú, Phường 5, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d62794.99087626416!2d107.06273739999999!3d10.3668979!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756fa582abf195%3A0x9925c16c2a19c45a!2zUXXDoW4gQ8ahbSBIdXkgQ8aw4budbmc!5e0!3m2!1svi!2s!4v1699773571267!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
42	2023-11-13 01:20:22.566481	2023-11-13 01:20:22.566497	https://maps.app.goo.gl/N89ki3suH8WYesNb6	address plus code	142 Nam Kỳ Khởi Nghĩa, Phường Thắng Tam, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d62796.34897794246!2d107.01945863650894!3d10.360121979905069!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756fee8afc44b1%3A0x52640d0a22d2a774!2zUXXDoW4gQ8ahbSBU4bqlbSBIxrDhu5tuZyBExrDGoW5nICggQ8ahbSBNYSApIC0gS2jDtG5nIEPDsyBDaGkgTmjDoW5o!5e0!3m2!1svi!2s!4v1699773610948!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
43	2023-11-13 01:20:22.572244	2023-11-13 01:20:22.572266	https://maps.app.goo.gl/xQJzkbCPtnJQtD6i9	address plus code	14 Trần Hưng Đạo, Phường 1, Thành phố Vũng Tầu, Bà Rịa - Vũng Tàu 790000, Việt Nam	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d62799.70505794994!2d107.07725210000001!3d10.3433588!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31756f8d33dfc635%3A0xe73e23c068b4f6a0!2zQ8ahbSBuacOqdSBSYXUgVOG6rXAgVMOgbmcgVsWpbmcgVMOgdQ!5e0!3m2!1svi!2s!4v1699773719514!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>	0	0
\.


--
-- Data for Name: carts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.carts (id, created_at, updated_at, user_id) FROM stdin;
1	2023-11-13 15:17:43.12631	2023-11-13 15:17:43.126403	1
\.


--
-- Data for Name: carts_plan_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.carts_plan_list (cart_id, plan_list_id) FROM stdin;
\.


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categories (id, created_at, updated_at, name, category_id) FROM stdin;
1	2023-11-13 10:37:51.072601	2023-11-13 10:37:51.072663	like	\N
13	2023-11-13 10:37:51.106216	2023-11-13 10:37:51.106252	Khám phá	1
14	2023-11-13 10:37:51.119101	2023-11-13 10:37:51.119131	Trải nghiệm	1
15	2023-11-13 10:37:51.130949	2023-11-13 10:37:51.130991	Thư giãn	1
17	2023-11-13 10:37:51.142973	2023-11-13 10:37:51.143018	Mạo hiểm	1
18	2023-11-13 10:37:51.158135	2023-11-13 10:37:51.158171	Leo núi	1
19	2023-11-13 10:37:51.168996	2023-11-13 10:37:51.169059	Bãi biển	1
20	2023-11-13 10:37:51.179312	2023-11-13 10:37:51.179392	Thiên nhiên	1
21	2023-11-13 10:37:51.190868	2023-11-13 10:37:51.190905	Tín ngưỡng	1
22	2023-11-13 10:37:51.201996	2023-11-13 10:37:51.202034	Bảo tàng	1
23	2023-11-13 10:37:51.213482	2023-11-13 10:37:51.213531	Sang trọng	1
24	2023-11-13 10:37:51.224918	2023-11-13 10:37:51.224958	Cổ kính	1
55	2023-11-13 10:37:51.242504	2023-11-13 10:37:51.242564	Lịch sử	1
25	2023-11-13 10:37:51.255785	2023-11-13 10:37:51.255824	Tiết kiệm	\N
26	2023-11-13 10:37:51.269083	2023-11-13 10:37:51.269221	Bình dân	\N
27	2023-11-13 10:37:51.282262	2023-11-13 10:37:51.282346	Vừa	\N
28	2023-11-13 10:37:51.296318	2023-11-13 10:37:51.296393	Cao	\N
29	2023-11-13 10:37:51.311415	2023-11-13 10:37:51.311501	Cao cấp	\N
3	2023-11-13 10:37:51.330326	2023-11-13 10:37:51.330384	Ẩm thực	1
4	2023-11-13 10:37:51.351197	2023-11-13 10:37:51.351222	Quán ăn	3
30	2023-11-13 10:37:51.368668	2023-11-13 10:37:51.368705	Cơm	4
31	2023-11-13 10:37:51.389224	2023-11-13 10:37:51.389252	Cơm tấm	4
32	2023-11-13 10:37:51.40597	2023-11-13 10:37:51.406011	Cơm gà	4
33	2023-11-13 10:37:51.421767	2023-11-13 10:37:51.421806	Cơm chọn món	4
34	2023-11-13 10:37:51.442582	2023-11-13 10:37:51.442633	Lẩu	4
35	2023-11-13 10:37:51.478471	2023-11-13 10:37:51.478519	Lẩu bò	4
36	2023-11-13 10:37:51.524759	2023-11-13 10:37:51.524833	Lẩu dê	4
37	2023-11-13 10:37:51.551741	2023-11-13 10:37:51.551779	Lẩu gà	4
38	2023-11-13 10:37:51.569286	2023-11-13 10:37:51.569323	Chay	4
39	2023-11-13 10:37:51.589244	2023-11-13 10:37:51.589284	Bún	4
40	2023-11-13 10:37:51.603348	2023-11-13 10:37:51.603392	Phở	4
42	2023-11-13 10:37:51.617898	2023-11-13 10:37:51.617937	Hủ tiếu	4
43	2023-11-13 10:37:51.64541	2023-11-13 10:37:51.645454	Bánh canh	4
44	2023-11-13 10:37:51.694517	2023-11-13 10:37:51.694564	Hải sản	4
45	2023-11-13 10:37:51.731614	2023-11-13 10:37:51.731681	Lẩu hải sản	4
51	2023-11-13 10:37:51.750725	2023-11-13 10:37:51.750757	Quán nướng	4
53	2023-11-13 10:37:51.76566	2023-11-13 10:37:51.765696	Nhà hàng	4
54	2023-11-13 10:37:51.780613	2023-11-13 10:37:51.780641	Quán bánh	4
5	2023-11-13 10:37:51.790881	2023-11-13 10:37:51.790914	Quán nước	3
46	2023-11-13 10:37:51.802559	2023-11-13 10:37:51.802595	Cà phê	5
47	2023-11-13 10:37:51.813222	2023-11-13 10:37:51.813247	Trà sữa	5
48	2023-11-13 10:37:51.825088	2023-11-13 10:37:51.825129	Trà đá	5
49	2023-11-13 10:37:51.83751	2023-11-13 10:37:51.83755	Chè	5
50	2023-11-13 10:37:51.852911	2023-11-13 10:37:51.852954	Sinh tố	5
52	2023-11-13 10:37:51.890271	2023-11-13 10:37:51.890426	Kem	5
2	2023-11-13 10:37:51.083651	2023-11-13 10:37:51.920846	area	\N
6	2023-11-13 10:37:51.98153	2023-11-13 10:37:51.981687	TP Hồ Chí Minh	2
7	2023-11-13 10:37:51.996365	2023-11-13 10:37:51.996413	Vũng Tàu	2
8	2023-11-13 10:37:52.011012	2023-11-13 10:37:52.011049	Đà Lạt	2
9	2023-11-13 10:37:52.020801	2023-11-13 10:37:52.020838	Huế	2
10	2023-11-13 10:37:52.031317	2023-11-13 10:37:52.031358	Đà Nẵng	2
11	2023-11-13 10:37:52.042852	2023-11-13 10:37:52.0429	Vịnh Hạ Long	2
12	2023-11-13 10:37:52.068493	2023-11-13 10:37:52.068576	Hà Nội	2
\.


--
-- Data for Name: categories_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categories_categories (category_id, categories_id) FROM stdin;
\.


--
-- Data for Name: follow; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.follow (id, created_at, updated_at, quantity, user_id) FROM stdin;
\.


--
-- Data for Name: follow_places; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.follow_places (follow_id, places_id) FROM stdin;
\.


--
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.image (id, created_at, updated_at, data, name, type, places_id, review_id, user_id) FROM stdin;
\.


--
-- Data for Name: link; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.link (id, created_at, updated_at, name, url) FROM stdin;
1	2023-11-13 01:20:21.983222	2023-11-13 01:20:21.983247	Travel Plan	travelplan.com
2	2023-11-13 01:20:21.989366	2023-11-13 01:20:21.989393	Travel Plan	travelplan.com
3	2023-11-13 01:20:21.994769	2023-11-13 01:20:21.994803	Travel Plan	travelplan.com
4	2023-11-13 01:20:22.000236	2023-11-13 01:20:22.000263	Travel Plan	travelplan.com
5	2023-11-13 01:20:22.005423	2023-11-13 01:20:22.005461	Travel Plan	travelplan.com
6	2023-11-13 01:20:22.012166	2023-11-13 01:20:22.012227	Travel Plan	travelplan.com
7	2023-11-13 01:20:22.019099	2023-11-13 01:20:22.019129	Travel Plan	travelplan.com
8	2023-11-13 01:20:22.025684	2023-11-13 01:20:22.02572	Travel Plan	travelplan.com
9	2023-11-13 01:20:22.03142	2023-11-13 01:20:22.031446	Travel Plan	travelplan.com
10	2023-11-13 01:20:22.03752	2023-11-13 01:20:22.037552	Travel Plan	travelplan.com
11	2023-11-13 01:20:22.044716	2023-11-13 01:20:22.04482	Travel Plan	travelplan.com
12	2023-11-13 01:20:22.054202	2023-11-13 01:20:22.054249	Travel Plan	travelplan.com
13	2023-11-13 01:20:22.061285	2023-11-13 01:20:22.061332	Travel Plan	travelplan.com
14	2023-11-13 01:20:22.071431	2023-11-13 01:20:22.071471	Travel Plan	travelplan.com
15	2023-11-13 01:20:22.080257	2023-11-13 01:20:22.080568	Travel Plan	travelplan.com
16	2023-11-13 01:20:22.088381	2023-11-13 01:20:22.08842	Travel Plan	travelplan.com
17	2023-11-13 01:20:22.094891	2023-11-13 01:20:22.094918	Travel Plan	travelplan.com
18	2023-11-13 01:20:22.101066	2023-11-13 01:20:22.101097	Travel Plan	travelplan.com
19	2023-11-13 01:20:22.108764	2023-11-13 01:20:22.108798	Travel Plan	travelplan.com
20	2023-11-13 01:20:22.117475	2023-11-13 01:20:22.117501	Travel Plan	travelplan.com
21	2023-11-13 01:20:22.130531	2023-11-13 01:20:22.130647	Travel Plan	travelplan.com
22	2023-11-13 01:20:22.141696	2023-11-13 01:20:22.141828	Travel Plan	travelplan.com
23	2023-11-13 01:20:22.148649	2023-11-13 01:20:22.148695	Travel Plan	travelplan.com
24	2023-11-13 01:20:22.154262	2023-11-13 01:20:22.154288	Travel Plan	travelplan.com
25	2023-11-13 01:20:22.160493	2023-11-13 01:20:22.160599	Travel Plan	travelplan.com
26	2023-11-13 01:20:22.165869	2023-11-13 01:20:22.1659	Travel Plan	travelplan.com
27	2023-11-13 01:20:22.171309	2023-11-13 01:20:22.171336	Travel Plan	travelplan.com
28	2023-11-13 01:20:22.17704	2023-11-13 01:20:22.177078	Travel Plan	travelplan.com
29	2023-11-13 01:20:22.184217	2023-11-13 01:20:22.184253	Travel Plan	travelplan.com
30	2023-11-13 01:20:22.192461	2023-11-13 01:20:22.192504	Travel Plan	travelplan.com
31	2023-11-13 01:20:22.199196	2023-11-13 01:20:22.199224	Travel Plan	travelplan.com
32	2023-11-13 01:20:22.204581	2023-11-13 01:20:22.204609	Travel Plan	travelplan.com
33	2023-11-13 01:20:22.210235	2023-11-13 01:20:22.210263	Travel Plan	travelplan.com
34	2023-11-13 01:20:22.215356	2023-11-13 01:20:22.215388	Travel Plan	travelplan.com
35	2023-11-13 01:20:22.22079	2023-11-13 01:20:22.220817	Travel Plan	travelplan.com
36	2023-11-13 01:20:22.226201	2023-11-13 01:20:22.22624	Travel Plan	travelplan.com
37	2023-11-13 01:20:22.231765	2023-11-13 01:20:22.231794	Travel Plan	travelplan.com
38	2023-11-13 01:20:22.23842	2023-11-13 01:20:22.238505	Travel Plan	travelplan.com
39	2023-11-13 01:20:22.246866	2023-11-13 01:20:22.246913	Travel Plan	travelplan.com
40	2023-11-13 01:20:22.253506	2023-11-13 01:20:22.253539	Travel Plan	travelplan.com
41	2023-11-13 01:20:22.258799	2023-11-13 01:20:22.258828	Travel Plan	travelplan.com
42	2023-11-13 01:20:22.265981	2023-11-13 01:20:22.266026	Travel Plan	travelplan.com
43	2023-11-13 01:20:22.272634	2023-11-13 01:20:22.272671	Travel Plan	travelplan.com
\.


--
-- Data for Name: places; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.places (id, created_at, updated_at, begin_day, cost, description, end_day, is_full, phone_number, point, time_places, title, address_id, link_id, user_id) FROM stdin;
1	2023-11-13 01:28:50.65664	2023-11-13 01:28:50.656711	00:00:00	0.00	Mũi Nghinh Phong là một mũi đất dài nhô ra biển, nằm ở phía Nam bán đảo Vũng Tàu, tỉnh Bà Rịa – Vũng Tàu. Nơi đây sở hữu cảnh quanh đắc địa với trước mặt là biển, sau lưng là núi” cực kỳ thơ mộng và hoang sơ, từ lâu Nghinh Phong đã trở thành địa điểm du lịch được nhiều du khách ghé thăm, đặc biệt là các bạn trẻ.	00:00:00	t	0123456789	0	15	Mũi nginh phong	1	1	1
2	2023-11-13 01:28:50.752387	2023-11-13 01:28:50.752421	06:30:00	0.00	Tượng Chúa Giêsu Kitô Vua là một bức tượng Chúa Giêsu được đặt trên đỉnh Núi Nhỏ của thành phố Vũng Tàu, tỉnh Bà Rịa – Vũng Tàu. Tượng được xây dựng từ năm 1974 và được khánh thành vào năm 1994. Tượng Chúa Giêsu Kitô Vua được công nhận là di tích lịch sử-văn hóa cấp quốc gia và được Trung tâm sách Kỷ lục Việt Nam và Công ty Văn hóa Đầm Sen trao cho kỷ lục Tượng Chúa Giêsu lớn nhất Việt Nam	17:00:00	f	0123456789	0	60	Tượng Chúa Giêsu Kitô Vua	2	2	1
3	2023-11-13 01:28:50.866858	2023-11-13 01:28:50.866895	07:00:00	0.00	Đồi cừu Suối Nghệ là một cánh đồng rộng lớn với những đàn cừu được chăn thả tự nhiên. Những chú cừu ở đây có nhiều màu sắc khác nhau, như trắng, đen, nâu, xám,... Chúng có bộ lông dày, mềm mại và vô cùng thân thiện.	19:00:00	f	0123456789	0	30	Đồi Cừu Suối Nghệ	3	3	1
4	2023-11-13 01:28:50.93808	2023-11-13 01:28:50.938113	00:00:00	0.00	Bãi Trước Vũng Tàu là một trong những bãi biển nổi tiếng nhất của thành phố Vũng Tàu. Bãi biển này nằm giữa Núi Lớn và Núi Nhỏ, là một vịnh nhỏ lặng sóng. 	00:00:00	t	0123456789	0	30	Bãi trước Vũng Tàu	4	4	1
5	2023-11-13 01:28:51.009138	2023-11-13 01:28:51.00921	05:00:00	0.00	Đồi Con Heo là một ngọn đồi nhỏ nhô ra từ vách núi, nằm ở khu vực bãi Sau, thành phố Vũng Tàu. Tên gọi Đồi Con Heo bắt nguồn từ hình dáng của ngọn đồi khi nhìn từ xa, trông giống như một đàn heo đang nằm phơi nắng. 	19:00:00	f	0123456789	0	30	Đồi con heo	5	5	1
6	2023-11-13 01:28:51.107087	2023-11-13 01:28:51.107113	08:00:00	20000.00	Hồ Đá Xanh Vũng Tàu là một địa điểm du lịch nổi tiếng, thu hút đông đảo du khách đến tham quan, đặc biệt là các bạn trẻ. Hồ nằm ở huyện Tân Thành, cách trung tâm thành phố Vũng Tàu khoảng 16 km	18:00:00	f	0123456789	0	45	Hồ Đá Xanh	6	6	1
7	2023-11-13 01:28:51.154009	2023-11-13 01:28:51.154038	00:00:00	0.00	Bãi Sau, còn được gọi là bãi Thùy Vân, là một bãi biển nằm ở phía Đông Nam của thành phố Vũng Tàu, tỉnh Bà Rịa – Vũng Tàu. Bãi tắm có chiều dài khoảng 8km, kéo dài từ chân Núi Nhỏ đến khu vực Chí Linh.	00:00:00	t	0123456789	0	30	Bãi tắm sau Vũng Tàu	7	7	1
8	2023-11-13 01:28:51.205189	2023-11-13 01:28:51.205268	07:00:00	0.00	Ngọn Hải Đăng Vũng Tàu là một ngọn hải đăng nằm trên đỉnh núi Nhỏ, thuộc thành phố Vũng Tàu, tỉnh Bà Rịa – Vũng Tàu, Việt Nam. Ngọn hải đăng được người Pháp xây dựng vào năm 1862 nhằm mục đích chỉ đường, báo hiệu cho các tàu thuyền qua lại, nằm ở độ cao là 149m so với mực nước biển.	20:00:00	f	0123456789	0	45	Ngọn Hải Đăng Vũng Tàu	8	8	1
9	2023-11-13 01:28:51.288857	2023-11-13 01:28:51.288924	07:00:00	0.00	Zenna Pool Camp là một khu du lịch sinh thái nằm ở xã Phước Tỉnh, huyện Long Điền, tỉnh Bà Rịa – Vũng Tàu. Nơi đây cách trung tâm thành phố Hồ Chí Minh khoảng 120km, mất khoảng 2-3 giờ di chuyển. 	22:00:00	f	0123456789	0	90	Zenna Pool Camp	9	9	1
10	2023-11-13 01:28:51.347436	2023-11-13 01:28:51.347454	07:00:00	0.00	Hòn Bà là một đảo nhỏ nằm ở gần bờ biển của phường 2, thành phố Vũng Tàu, tỉnh Bà Rịa – Vũng Tàu, Việt Nam. Đảo Hòn Bà cách bờ khoảng 220 m, diện tích 5.450 m². Trên đảo có một ngôi miếu, gọi là Miếu Hòn Bà.	08:00:00	f	0123456789	0	15	Hòn bà	10	10	1
11	2023-11-13 01:48:14.470189	2023-11-13 01:48:14.470314	08:00:00	50000.00	Nhà úp ngược Vũng Tàu là một địa điểm du lịch nổi tiếng, thu hút đông đảo du khách đến tham quan, đặc biệt là các bạn trẻ. Nhà úp ngược Vũng Tàu là một ngôi nhà được xây dựng theo kiểu úp ngược, với tất cả đồ nội thất, vật dụng trong nhà đều được treo lên trần nhà. Điều này tạo ra một không gian vô cùng độc đáo và lạ mắt, khiến du khách như lạc vào một thế giới khác. 	22:00:00	f	0123456789	0	30	Nhà úp ngược Vũng Tàu	11	11	1
12	2023-11-13 01:48:14.713475	2023-11-13 01:48:14.713512	08:00:00	100000.00	Khu vui chơi giải trí Thỏ Trắng Vũng Tàu là một trong những khu vui chơi giải trí nổi tiếng nhất tại thành phố biển xinh đẹp này. Nơi đây thu hút đông đảo du khách, đặc biệt là các bạn trẻ, bởi hệ thống trò chơi đa dạng, phong phú và không gian rộng lớn, thoáng mát.	23:00:00	f	0123456789	0	30	Khu vui chơi giải trí thỏ trắng	12	12	1
13	2023-11-13 01:48:14.809702	2023-11-13 01:48:14.80974	00:00:00	0.00	Đèo Nước Ngọt là một cung đường đèo ngắn, uốn mình nối hai vùng đất là huyện Long Điền và huyện Đất Đỏ, tỉnh Bà Rịa – Vũng Tàu. Đèo cách Vũng Tàu chừng 25km và cách biển Long Hải 2km. 	00:00:00	t	0123456789	0	15	Đèo Nước Ngọt	13	13	1
14	2023-11-13 01:48:14.872563	2023-11-13 01:48:14.872596	07:00:00	0.00	Niết Bàn Tịnh xá Vũng Tàu là một ngôi chùa Phật giáo lớn ở thành phố Vũng Tàu, tỉnh Bà Rịa – Vũng Tàu. Chùa được xây dựng trên triền núi Nhỏ, hướng mặt ra biển, mang đậm kiến trúc Phật giáo hiện đại.	18:00:00	f	0123456789	0	15	Niết Bàn Tịnh xá	14	14	1
15	2023-11-13 02:07:53.701762	2023-11-13 02:07:53.701841	00:00:00	15000.00	Bạch Dinh (tiếng Pháp: Villa Blanche) là một dinh thự có kiến trúc châu Âu cuối thế kỷ 19, nằm bên sườn núi Lớn của thành phố Vũng Tàu. Nơi đây từng được dùng làm nơi nghỉ mát cho Toàn quyền Đông Dương, Hoàng đế Bảo Đại và các đời Tổng thống Việt Nam Cộng hòa.	00:00:00	t	0123456789	0	15	Bạch dinh	15	15	1
16	2023-11-13 02:07:53.799271	2023-11-13 02:07:53.799315	06:00:00	0.00	Hẻm đường Trần Phú Vũng Tàu là một con hẻm nhỏ nằm ở giữa hai căn nhà số 107 và 109 trên đường Trần Phú, thuộc phường 5, thành phố Vũng Tàu. Hẻm này chỉ dài khoảng 100 mét nhưng lại rất nổi tiếng, đặc biệt là đối với giới trẻ. 	18:00:00	f	0123456789	0	15	Hẻm Đường Trần Phú	16	16	1
17	2023-11-13 02:07:53.88898	2023-11-13 02:07:53.889135	00:00:00	30000.00	Bến thuyền Vũng Tàu Marina là một điểm du lịch mới nổi ở Vũng Tàu. Tọa lạc trên vịnh Sông Dinh, bến thuyền mang đến cho du khách một khung cảnh thiên nhiên tuyệt đẹp, với những cánh buồm Catamaran đầy màu sắc, những dãy núi hùng vĩ và biển cả bao la. 	00:00:00	t	0123456789	0	15	BẾN THUYỀN VŨNG TÀU MARINA	17	17	1
18	2023-11-13 02:07:53.974847	2023-11-13 02:07:53.974878	04:00:00	0.00	Đức Mẹ Bãi Dâu là một tổ hợp công trình đền thánh và tượng đài Đức Mẹ Maria, tọa lạc trên sườn Núi Lớn, thành phố Vũng Tàu, Việt Nam. Đây là một địa điểm du lịch tâm linh nổi tiếng ở Vũng Tàu, thu hút du khách từ khắp nơi trên cả nước.	21:30:00	f	0123456789	0	15	Đức Mẹ Bãi Dâu Vũng Tàu	18	18	1
19	2023-11-13 02:07:54.067951	2023-11-13 02:07:54.067996	06:00:00	0.00	Cổng hoa giấy check-in Vũng Tàu là một địa điểm chụp ảnh nổi tiếng ở thành phố biển xinh đẹp này. Cổng hoa giấy nằm trên đường Thùy Vân, gần biển Thùy Vân. Cổng hoa giấy được làm từ những bông hoa giấy rực rỡ, với nhiều màu sắc khác nhau như đỏ, vàng, hồng,... Cổng hoa giấy được thiết kế với hình vòng cung, tạo nên một khung cảnh vô cùng thơ mộng và lãng mạn.	18:00:00	f	0123456789	0	15	Cổng Hoa Giấy Check in Vũng Tàu	19	19	1
20	2023-11-13 02:07:54.162014	2023-11-13 02:07:54.16204	04:30:00	0.00	Nhà thờ Chánh Tòa Bà Rịa hay còn gọi là nhà thờ Thánh Giacôbê và Thánh Philípphê là một nhà thờ Công giáo được xây dựng tại thành phố Bà Rịa, tỉnh Bà Rịa – Vũng Tàu. Đây là nhà thờ chính tòa của Giáo phận Bà Rịa. 	21:00:00	f	0123456789	0	15	Nhà thờ Chánh Tòa Bà Rịa	20	20	1
21	2023-11-13 02:07:54.210485	2023-11-13 02:07:54.210513	07:00:00	40000.00	Lens Studio là một quán cà phê nổi tiếng ở thành phố Vũng Tàu, được ví như “thị trấn Nhật Bản” thu nhỏ.Quán được thiết kế theo phong cách Nhật Bản, với những con hẻm nhỏ, những ngôi nhà gỗ, những chiếc đèn lồng đỏ và những cây hoa anh đào.	22:30:00	f	0123456789	0	15	Lens studio Thị trấn Nhật Bản thu nhỏ	21	21	1
22	2023-11-13 02:07:54.276753	2023-11-13 02:07:54.276846	04:00:00	0.00	Núi Lớn Vũng Tàu là một ngọn núi nằm ở thành phố Vũng Tàu, tỉnh Bà Rịa - Vũng Tàu. Đây là một trong hai ngọn núi lớn của thành phố biển, ngọn còn lại là Núi Nhỏ. Núi Lớn có diện tích khoảng 400 ha, cao 254 mét so với mực nước biển. 	19:00:00	f	0123456789	0	45	Núi Lớn Vũng Tàu	22	22	1
23	2023-11-13 02:07:54.361511	2023-11-13 02:07:54.361532	05:00:00	0.00	Thích Ca Phật Đài là một quần thể kiến trúc Phật giáo lớn, nằm trên sườn phía Bắc của Núi Lớn, thành phố Vũng Tàu, tỉnh Bà Rịa – Vũng Tàu. Nơi đây là một điểm tham quan du lịch và tín ngưỡng nổi tiếng, thu hút hàng triệu lượt khách đến tham quan mỗi năm. 	22:00:00	f	0123456789	0	15	Thích Ca Phật Đài	23	23	1
24	2023-11-13 02:07:54.408816	2023-11-13 02:07:54.408837	00:00:00	0.00	Bãi Dứa là một bãi biển đẹp và hoang sơ, nằm ở phía Tây núi Nhỏ, gần mũi Nghinh Phong, thành phố Vũng Tàu. Bãi biển có hình dáng uốn lượn, nước biển trong xanh, bãi cát trắng mịn, sóng biển êm đềm, thích hợp cho các hoạt động vui chơi, tắm biển, nghỉ dưỡng. 	00:00:00	t	0123456789	0	15	Bãi dứa	24	24	1
25	2023-11-13 02:07:54.446242	2023-11-13 02:07:54.446294	00:00:00	0.00	Tượng đài liệt sĩ Vũng Tàu là một công trình kiến trúc mang ý nghĩa lịch sử, văn hóa và là địa chỉ tham quan du lịch nổi tiếng của thành phố Vũng Tàu. Tượng đài tọa lạc trên đường Thùy Vân, thuộc phường Thắng Tam, thành phố Vũng Tàu. 	00:00:00	t	0123456789	0	15	Tượng Đài Liệt Sỹ	25	25	1
26	2023-11-13 02:07:54.520931	2023-11-13 02:07:54.520974	09:00:00	70000.00		23:59:00	f	0123456789	0	15	Lẩu cá đuối Hoàng Minh	26	26	1
27	2023-11-13 02:07:54.574893	2023-11-13 02:07:54.574914	00:00:00	70000.00		00:00:00	t	0123456789	0	15	Lẩu cá đuối Út Mười	27	27	1
28	2023-11-13 02:07:54.608934	2023-11-13 02:07:54.608963	06:00:00	30000.00		21:00:00	f	0123456789	0	15	Bánh Mì Xíu Mại Hàng Quyên (Chi Nhánh 2)	28	28	1
29	2023-11-13 02:07:54.645725	2023-11-13 02:07:54.645759	10:30:00	50000.00		22:00:00	f	0123456789	0	15	Quán ăn Vườn Xoài	29	29	1
30	2023-11-13 02:07:54.705613	2023-11-13 02:07:54.70565	07:00:00	50000.00		21:00:00	f	0123456789	0	15	Bánh canh ghẹ Bà Ba	30	30	1
31	2023-11-13 02:07:54.76459	2023-11-13 02:07:54.764654	06:00:00	50000.00		23:30:00	f	0123456789	0	15	Bánh canh ghẹ Anh Vy	31	31	1
32	2023-11-13 02:07:54.808239	2023-11-13 02:07:54.808272	15:00:00	50000.00		21:00:00	f	0123456789	0	15	Bún chả cá - Bún sứa	32	32	1
33	2023-11-13 02:07:54.845062	2023-11-13 02:07:54.845096	06:00:00	70000.00		21:00:00	f	0123456789	0	15	Bún sứa Vũng Tàu ( Quán 79 Nha Trang )	33	33	1
34	2023-11-13 02:07:54.907102	2023-11-13 02:07:54.907134	10:00:00	100000.00		23:59:00	f	0123456789	0	15	Ốc Thiên Nhiên - Bãi Sau	34	34	1
35	2023-11-13 02:07:54.981387	2023-11-13 02:07:54.981413	05:00:00	40000.00		23:00:00	f	0123456789	0	15	Bún ốc cá cô Hằng 46 Cao Xuân Dục	35	35	1
36	2023-11-13 02:07:55.027409	2023-11-13 02:07:55.027437	08:00:00	100000.00		22:00:00	f	0123456789	0	15	Lẩu tôm 5 Ri (chi nhánh Vũng Tàu)	36	36	1
37	2023-11-13 02:07:55.074536	2023-11-13 02:07:55.07457	07:00:00	30000.00		15:30:00	f	0123456789	0	15	Bánh Khọt Gốc Vú Sữa	37	37	1
38	2023-11-13 02:07:55.138362	2023-11-13 02:07:55.138392	06:30:00	70.00		22:00:00	f	0123456789	0	15	Bánh Khọt Cô Ba	38	38	1
39	2023-11-13 02:07:55.187543	2023-11-13 02:07:55.18757	06:00:00	35000.00		21:00:00	f	0123456789	0	15	Bánh khọt Miền đông Ck	39	39	1
40	2023-11-13 02:07:55.226386	2023-11-13 02:07:55.226424	10:00:00	150000.00		21:30:00	f	0123456789	0	15	Nhà hàng Gành Hào 1	40	40	1
41	2023-11-13 02:07:55.278752	2023-11-13 02:07:55.27878	08:00:00	70000.00		21:00:00	f	0123456789	0	15	Quán Cơm Huy Cường	41	41	1
42	2023-11-13 02:07:55.375033	2023-11-13 02:07:55.375062	06:00:00	30000.00		22:00:00	f	0123456789	0	15	Quán Cơm Tấm Hướng Dương 	42	42	1
43	2023-11-13 02:07:55.426364	2023-11-13 02:07:55.426392	00:00:00	70000.00		00:00:00	t	0123456789	0	15	Cơm niêu Rau Tập Tàng Vũng Tàu	43	43	1
\.


--
-- Data for Name: places_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.places_category (places_id, category_id) FROM stdin;
29	27
29	30
29	7
29	33
30	27
30	44
30	7
30	43
31	27
31	44
31	7
31	43
36	27
36	44
36	7
36	34
37	26
6	7
6	14
6	26
37	7
37	54
38	26
38	7
38	54
9	7
9	14
9	26
39	26
39	7
39	54
11	7
11	13
11	27
12	7
12	14
12	27
13	7
13	14
13	26
14	7
14	21
14	26
42	26
42	30
42	7
42	31
16	7
16	13
16	26
17	7
17	14
17	26
18	7
18	21
18	26
19	7
19	20
19	26
20	7
20	21
20	26
21	7
21	13
21	14
21	26
23	7
23	21
23	26
32	7
32	39
32	27
33	7
33	39
33	27
34	7
34	44
34	27
35	7
35	39
35	26
40	7
40	44
40	53
40	28
41	7
41	30
41	27
43	7
43	30
43	27
1	13
1	26
1	7
1	20
2	21
2	26
2	7
2	20
2	18
3	20
3	26
3	7
4	13
4	26
4	7
4	19
5	14
5	26
5	7
5	18
7	13
7	26
7	7
7	19
8	13
8	26
8	7
8	18
10	21
10	26
10	7
15	24
15	26
15	7
15	13
22	26
22	7
24	13
24	26
24	7
24	19
25	26
25	7
25	55
26	27
26	44
26	7
26	45
27	27
27	44
27	7
27	45
28	26
28	7
28	54
\.


--
-- Data for Name: places_follow; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.places_follow (places_id, follow_id) FROM stdin;
\.


--
-- Data for Name: places_images; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.places_images (places_id, images_id) FROM stdin;
\.


--
-- Data for Name: places_reviews; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.places_reviews (places_id, reviews_id) FROM stdin;
\.


--
-- Data for Name: plan_cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.plan_cart (plan_id, cart_id) FROM stdin;
\.


--
-- Data for Name: plan_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.plan_category (plan_id, category_id) FROM stdin;
\.


--
-- Data for Name: plan_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.plan_item (id, created_at, updated_at, content, image_url, index, is_optional, money, note, place_id, start_time, title, plan_id) FROM stdin;
\.


--
-- Data for Name: plans; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.plans (id, created_at, updated_at, begin_date, cost_eat, cost_play, cost_vehicle, destination, distance, end_date, estimated_total_distance, expense, number_people, number_vehicle, title, location_id, user_id, vehicle_id) FROM stdin;
\.


--
-- Data for Name: plans_plan_items; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.plans_plan_items (plan_id, plan_items_id) FROM stdin;
\.


--
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reviews (id, created_at, updated_at, content, point, places_id, user_id) FROM stdin;
\.


--
-- Data for Name: reviews_image_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reviews_image_list (review_id, image_list_id) FROM stdin;
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, created_at, updated_at, name) FROM stdin;
1	2023-11-13 01:20:20.701621	2023-11-13 01:20:20.701684	ROLE_USER
2	2023-11-13 01:20:20.720757	2023-11-13 01:20:20.720799	ROLE_ADMIN
\.


--
-- Data for Name: shares; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.shares (id, created_at, updated_at, message, plan_id, receiver_id, remitter_id) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, created_at, updated_at, email, name, password, username, avatar_id, role_id) FROM stdin;
1	2023-11-13 01:20:21.056837	2023-11-13 01:20:21.056878	admin@gmail.com	Admin	$2a$10$F7TwKxJrMF.uzVtj4ACnU.X728FwYW7KqPSdtjJmhxCyEuuH.e7Wi	admin	\N	2
\.


--
-- Data for Name: vehicle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vehicle (id, created_at, updated_at, average_speed, cost, name, seats) FROM stdin;
1	2023-11-13 02:19:55.044405	2023-11-13 02:19:55.044497	40	500.00	Xe máy	2
2	2023-11-13 02:20:26.757953	2023-11-13 02:20:26.758004	50	1500.00	Ô tô	4
\.


--
-- Name: address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.address_id_seq', 50, true);


--
-- Name: carts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.carts_id_seq', 1, true);


--
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categories_id_seq', 54, true);


--
-- Name: follow_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.follow_id_seq', 1, false);


--
-- Name: image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_id_seq', 1, false);


--
-- Name: link_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.link_id_seq', 43, true);


--
-- Name: places_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.places_id_seq', 43, true);


--
-- Name: plan_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.plan_item_id_seq', 89, true);


--
-- Name: plans_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.plans_id_seq', 7, true);


--
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reviews_id_seq', 1, false);


--
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_id_seq', 2, true);


--
-- Name: shares_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.shares_id_seq', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 1, true);


--
-- Name: vehicle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vehicle_id_seq', 2, true);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: carts carts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT carts_pkey PRIMARY KEY (id);


--
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: follow follow_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.follow
    ADD CONSTRAINT follow_pkey PRIMARY KEY (id);


--
-- Name: image image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- Name: link link_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.link
    ADD CONSTRAINT link_pkey PRIMARY KEY (id);


--
-- Name: places places_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places
    ADD CONSTRAINT places_pkey PRIMARY KEY (id);


--
-- Name: plan_item plan_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan_item
    ADD CONSTRAINT plan_item_pkey PRIMARY KEY (id);


--
-- Name: plans plans_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plans
    ADD CONSTRAINT plans_pkey PRIMARY KEY (id);


--
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: shares shares_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shares
    ADD CONSTRAINT shares_pkey PRIMARY KEY (id);


--
-- Name: carts uk_64t7ox312pqal3p7fg9o503c2; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT uk_64t7ox312pqal3p7fg9o503c2 UNIQUE (user_id);


--
-- Name: places_images uk_aulf4uydpqi0vax156jhoj6as; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places_images
    ADD CONSTRAINT uk_aulf4uydpqi0vax156jhoj6as UNIQUE (images_id);


--
-- Name: plans_plan_items uk_b03xc4i4ifprcfgjlgul8hoe0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plans_plan_items
    ADD CONSTRAINT uk_b03xc4i4ifprcfgjlgul8hoe0 UNIQUE (plan_items_id);


--
-- Name: reviews_image_list uk_do7o2lv31cx8rk5csq3v346ei; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews_image_list
    ADD CONSTRAINT uk_do7o2lv31cx8rk5csq3v346ei UNIQUE (image_list_id);


--
-- Name: places uk_gduq8j35po3vry2r7pitrfyqu; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places
    ADD CONSTRAINT uk_gduq8j35po3vry2r7pitrfyqu UNIQUE (link_id);


--
-- Name: places_reviews uk_hhs0h8ui4y94q1veb47y0i7dj; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places_reviews
    ADD CONSTRAINT uk_hhs0h8ui4y94q1veb47y0i7dj UNIQUE (reviews_id);


--
-- Name: follow uk_k534rx4tkmqox2i4c2hsu9oe5; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.follow
    ADD CONSTRAINT uk_k534rx4tkmqox2i4c2hsu9oe5 UNIQUE (user_id);


--
-- Name: categories_categories uk_kj3pastfxqpm3ewiv2xnucf5t; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories_categories
    ADD CONSTRAINT uk_kj3pastfxqpm3ewiv2xnucf5t UNIQUE (categories_id);


--
-- Name: image uk_l8oylel56xvt5huamif0tfhkh; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT uk_l8oylel56xvt5huamif0tfhkh UNIQUE (user_id);


--
-- Name: plans uk_m77kqglkcqr944n8tv28hod9q; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plans
    ADD CONSTRAINT uk_m77kqglkcqr944n8tv28hod9q UNIQUE (location_id);


--
-- Name: places uk_qobs9f75q3txha9i5b43c66e9; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places
    ADD CONSTRAINT uk_qobs9f75q3txha9i5b43c66e9 UNIQUE (address_id);


--
-- Name: users uk_rsulcn2gynjy3cddpwmosv881; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_rsulcn2gynjy3cddpwmosv881 UNIQUE (avatar_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: vehicle vehicle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle
    ADD CONSTRAINT vehicle_pkey PRIMARY KEY (id);


--
-- Name: users fk19lflpg5seis4dwrm2lvjlxfv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk19lflpg5seis4dwrm2lvjlxfv FOREIGN KEY (avatar_id) REFERENCES public.image(id);


--
-- Name: plan_cart fk1a26gkholxidxorqkktdkxk42; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan_cart
    ADD CONSTRAINT fk1a26gkholxidxorqkktdkxk42 FOREIGN KEY (cart_id) REFERENCES public.carts(id);


--
-- Name: categories fk1e7hbubpwyuq2199b8jrx42h8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT fk1e7hbubpwyuq2199b8jrx42h8 FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- Name: reviews fk23n1gchynbmd31mnrwrafxt55; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fk23n1gchynbmd31mnrwrafxt55 FOREIGN KEY (places_id) REFERENCES public.places(id);


--
-- Name: follow_places fk2w2iyjemuneb42a7dcl5ks6lh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.follow_places
    ADD CONSTRAINT fk2w2iyjemuneb42a7dcl5ks6lh FOREIGN KEY (follow_id) REFERENCES public.follow(id);


--
-- Name: image fk3tt20mg8munwx9w2h0yam9p4x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT fk3tt20mg8munwx9w2h0yam9p4x FOREIGN KEY (places_id) REFERENCES public.places(id);


--
-- Name: carts_plan_list fk3wkqxqbr9j0gbwf4k1xpwd68v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts_plan_list
    ADD CONSTRAINT fk3wkqxqbr9j0gbwf4k1xpwd68v FOREIGN KEY (cart_id) REFERENCES public.carts(id);


--
-- Name: follow fk3xydb9wjv9vxkr0ohgba0n0e6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.follow
    ADD CONSTRAINT fk3xydb9wjv9vxkr0ohgba0n0e6 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: plan_item fk472873txisp8v1f01as7jqcul; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan_item
    ADD CONSTRAINT fk472873txisp8v1f01as7jqcul FOREIGN KEY (plan_id) REFERENCES public.plans(id);


--
-- Name: places_follow fk4ystpdqs6vu5lf7494as0wuj5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places_follow
    ADD CONSTRAINT fk4ystpdqs6vu5lf7494as0wuj5 FOREIGN KEY (follow_id) REFERENCES public.follow(id);


--
-- Name: shares fk6c0dceo4hbewjiotlv7k7fe5c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shares
    ADD CONSTRAINT fk6c0dceo4hbewjiotlv7k7fe5c FOREIGN KEY (remitter_id) REFERENCES public.users(id);


--
-- Name: places_follow fk6c5gskkd1puoqxw3mawi7ujxp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places_follow
    ADD CONSTRAINT fk6c5gskkd1puoqxw3mawi7ujxp FOREIGN KEY (places_id) REFERENCES public.places(id);


--
-- Name: plan_category fk7d26t1eq7ui2v2md9qj6kvjey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan_category
    ADD CONSTRAINT fk7d26t1eq7ui2v2md9qj6kvjey FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- Name: plans fk7tfptcvkcde2f4w6lgo2jmcmq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plans
    ADD CONSTRAINT fk7tfptcvkcde2f4w6lgo2jmcmq FOREIGN KEY (vehicle_id) REFERENCES public.vehicle(id);


--
-- Name: image fk93v534xprashn9baeffb9bqim; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT fk93v534xprashn9baeffb9bqim FOREIGN KEY (review_id) REFERENCES public.reviews(id);


--
-- Name: plans_plan_items fk96tt759m9vjubhkb1l64ftydw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plans_plan_items
    ADD CONSTRAINT fk96tt759m9vjubhkb1l64ftydw FOREIGN KEY (plan_items_id) REFERENCES public.plan_item(id);


--
-- Name: plan_cart fka8neccw9jbiisijgtj6s8n4e5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan_cart
    ADD CONSTRAINT fka8neccw9jbiisijgtj6s8n4e5 FOREIGN KEY (plan_id) REFERENCES public.plans(id);


--
-- Name: carts_plan_list fkavmrqe0j15khwq6wedqt1nd9d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts_plan_list
    ADD CONSTRAINT fkavmrqe0j15khwq6wedqt1nd9d FOREIGN KEY (plan_list_id) REFERENCES public.plans(id);


--
-- Name: carts fkb5o626f86h46m4s7ms6ginnop; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT fkb5o626f86h46m4s7ms6ginnop FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: follow_places fkb6xplbptniljqkdad7nnhcsr5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.follow_places
    ADD CONSTRAINT fkb6xplbptniljqkdad7nnhcsr5 FOREIGN KEY (places_id) REFERENCES public.places(id);


--
-- Name: places_category fkbhisladwgg7b3kfegk07hrc6g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places_category
    ADD CONSTRAINT fkbhisladwgg7b3kfegk07hrc6g FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- Name: reviews_image_list fkbti7jt60a8tvs83skeabxb4o8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews_image_list
    ADD CONSTRAINT fkbti7jt60a8tvs83skeabxb4o8 FOREIGN KEY (review_id) REFERENCES public.reviews(id);


--
-- Name: plans fkbybv5po44ssyv6svnv062dwrf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plans
    ADD CONSTRAINT fkbybv5po44ssyv6svnv062dwrf FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: reviews fkcgy7qjc1r99dp117y9en6lxye; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fkcgy7qjc1r99dp117y9en6lxye FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: image fkcvpnctgluno47ac6avana5sqf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT fkcvpnctgluno47ac6avana5sqf FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: plans_plan_items fkd0laa680qnt92n8u2sol2h0wu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plans_plan_items
    ADD CONSTRAINT fkd0laa680qnt92n8u2sol2h0wu FOREIGN KEY (plan_id) REFERENCES public.plans(id);


--
-- Name: categories_categories fkdldd923a7urvcbw425leil1x2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories_categories
    ADD CONSTRAINT fkdldd923a7urvcbw425leil1x2 FOREIGN KEY (categories_id) REFERENCES public.categories(id);


--
-- Name: categories_categories fkeaevrp1cfa4w90h686h6bh2pu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories_categories
    ADD CONSTRAINT fkeaevrp1cfa4w90h686h6bh2pu FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- Name: shares fkj24aklum9vg49bgdvo2sk32d1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shares
    ADD CONSTRAINT fkj24aklum9vg49bgdvo2sk32d1 FOREIGN KEY (receiver_id) REFERENCES public.users(id);


--
-- Name: places_category fkjd1isyoe3fxsfjt078uejjpeg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places_category
    ADD CONSTRAINT fkjd1isyoe3fxsfjt078uejjpeg FOREIGN KEY (places_id) REFERENCES public.places(id);


--
-- Name: shares fkkgylged4xsl348vbr84mcylsb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shares
    ADD CONSTRAINT fkkgylged4xsl348vbr84mcylsb FOREIGN KEY (plan_id) REFERENCES public.plans(id);


--
-- Name: places fkkrd5gjfpkmoxoxr11fts3mmti; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places
    ADD CONSTRAINT fkkrd5gjfpkmoxoxr11fts3mmti FOREIGN KEY (link_id) REFERENCES public.link(id);


--
-- Name: places_images fklyjnecdnnspyfsf6c7pf5pnu7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places_images
    ADD CONSTRAINT fklyjnecdnnspyfsf6c7pf5pnu7 FOREIGN KEY (images_id) REFERENCES public.image(id);


--
-- Name: places_images fkni1nb5gw4vxyo6lse9y65uynm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places_images
    ADD CONSTRAINT fkni1nb5gw4vxyo6lse9y65uynm FOREIGN KEY (places_id) REFERENCES public.places(id);


--
-- Name: users fkp56c1712k691lhsyewcssf40f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkp56c1712k691lhsyewcssf40f FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- Name: places_reviews fkpdr2nn9h5ds09uptn6x2wdh8a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places_reviews
    ADD CONSTRAINT fkpdr2nn9h5ds09uptn6x2wdh8a FOREIGN KEY (places_id) REFERENCES public.places(id);


--
-- Name: places fkqmg0l98kpihrma9jr4hx0x22b; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places
    ADD CONSTRAINT fkqmg0l98kpihrma9jr4hx0x22b FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: plan_category fkqpbo2la77mx8ytg2k962el2id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plan_category
    ADD CONSTRAINT fkqpbo2la77mx8ytg2k962el2id FOREIGN KEY (plan_id) REFERENCES public.plans(id);


--
-- Name: reviews_image_list fkr6uykcycpkukebt5msxy92b7v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews_image_list
    ADD CONSTRAINT fkr6uykcycpkukebt5msxy92b7v FOREIGN KEY (image_list_id) REFERENCES public.image(id);


--
-- Name: places fkrdgni5qf89m7uqrbq6sylgp8j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places
    ADD CONSTRAINT fkrdgni5qf89m7uqrbq6sylgp8j FOREIGN KEY (address_id) REFERENCES public.address(id);


--
-- Name: places_reviews fkspk8tb8bqekfdc2fvd2dck3ca; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.places_reviews
    ADD CONSTRAINT fkspk8tb8bqekfdc2fvd2dck3ca FOREIGN KEY (reviews_id) REFERENCES public.reviews(id);


--
-- Name: plans fktbrk63j1wfj15enhr5kmn0jq3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plans
    ADD CONSTRAINT fktbrk63j1wfj15enhr5kmn0jq3 FOREIGN KEY (location_id) REFERENCES public.address(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--


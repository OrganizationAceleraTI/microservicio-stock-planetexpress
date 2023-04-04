-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

-- DROP SEQUENCE public.inventory_inventory_id_seq;

CREATE SEQUENCE public.inventory_inventory_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.sale_id_sale_seq;

CREATE SEQUENCE public.sale_id_sale_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.shopping_cart_id_shopping_cart_seq;

CREATE SEQUENCE public.shopping_cart_id_shopping_cart_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.shopping_cart_stock_id_shopcart_stock_seq;

CREATE SEQUENCE public.shopping_cart_stock_id_shopcart_stock_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.stock_id_stock_seq;

CREATE SEQUENCE public.stock_id_stock_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.supply_id_supplier_seq;

CREATE SEQUENCE public.supply_id_supplier_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.supply_product_id_supply_seq;

CREATE SEQUENCE public.supply_product_id_supply_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;-- public.inventory definition

-- Drop table

-- DROP TABLE public.inventory;

CREATE TABLE public.inventory (
	inventory_id serial4 NOT NULL,
	person_supplier_id int4 NULL,
	product_id int4 NULL,
	incoming_price int4 NOT NULL,
	current_price int4 NULL,
	quantity int4 NULL,
	CONSTRAINT inventory_pkey PRIMARY KEY (inventory_id)
);


-- public.sale definition

-- Drop table

-- DROP TABLE public.sale;

CREATE TABLE public.sale (
	id_sale serial4 NOT NULL,
	id_client int4 NOT NULL,
	date_sale timestamp NULL,
	state int4 NULL,
	id_delivery int4 NULL,
	CONSTRAINT sale_pkey PRIMARY KEY (id_sale)
);


-- public.shopping_cart definition

-- Drop table

-- DROP TABLE public.shopping_cart;

CREATE TABLE public.shopping_cart (
	id_shopping_cart serial4 NOT NULL,
	id_user int4 NOT NULL,
	last_update timestamp NULL,
	CONSTRAINT shopping_cart_pkey PRIMARY KEY (id_shopping_cart)
);


-- public.stock definition

-- Drop table

-- DROP TABLE public.stock;

CREATE TABLE public.stock (
	id_stock serial4 NOT NULL,
	id_product int4 NOT NULL,
	quantity int4 NULL,
	CONSTRAINT stock_pkey PRIMARY KEY (id_stock)
);


-- public.supply definition

-- Drop table

-- DROP TABLE public.supply;

CREATE TABLE public.supply (
	id_supplier serial4 NOT NULL,
	date_supply timestamp NULL,
	CONSTRAINT supply_pkey PRIMARY KEY (id_supplier)
);


-- public.shopping_cart_stock definition

-- Drop table

-- DROP TABLE public.shopping_cart_stock;

CREATE TABLE public.shopping_cart_stock (
	id_shopcart_stock serial4 NOT NULL,
	inventory_id int4 NOT NULL,
	quantity numeric(16, 2) NOT NULL,
	CONSTRAINT shopping_cart_stock_pkey PRIMARY KEY (id_shopcart_stock, inventory_id),
	CONSTRAINT "fk_SHOPPING_CART_STOCK_INVENTORY1" FOREIGN KEY (inventory_id) REFERENCES public.inventory(inventory_id),
	CONSTRAINT "fk_SHOPPING_CART_STOCK_SHOPCART1" FOREIGN KEY (id_shopcart_stock) REFERENCES public.shopping_cart(id_shopping_cart)
);


-- public.supply_product definition

-- Drop table

-- DROP TABLE public.supply_product;

CREATE TABLE public.supply_product (
	id_supply serial4 NOT NULL,
	id_stock int4 NOT NULL,
	quantity int4 NULL,
	current_price numeric(16, 2) NULL,
	CONSTRAINT supply_product_pkey PRIMARY KEY (id_supply, id_stock),
	CONSTRAINT "fk_SUPPLY_STOCK_SUPPLY1" FOREIGN KEY (id_stock) REFERENCES public.stock(id_stock),
	CONSTRAINT "fk_SUPPLY_SUPPLY_STOCK1" FOREIGN KEY (id_supply) REFERENCES public.supply(id_supplier)
);

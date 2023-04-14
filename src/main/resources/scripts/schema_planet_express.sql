-- SCHEMA DATABASE PLANET EXPRESS
-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

-- DROP TABLE public.stock;
CREATE TABLE public.stock (
	id_stock 					UUID,
	id_product 					NUMERIC(1000) NOT NULL,
	quantity 					NUMERIC(1000) NOT NULL,

	CONSTRAINT stock_pk 		PRIMARY KEY(id_stock)
);

-- DROP TABLE public.supply;
CREATE TABLE public.supply (
	id_supply					UUID,
	id_supplier					UUID NOT NULL,
	date_supply					DATE NOT NULL,

	CONSTRAINT supply_pk 		PRIMARY KEY(id_supply)
);

-- DROP TABLE public.supply_product;
CREATE TABLE public.supply_stock (
	id_supply_stock 			UUID,
	id_supply 					UUID NOT NULL,
	id_stock 					UUID NOT NULL,
	quantity 					NUMERIC(1000) NOT NULL,
	current_price 				NUMERIC(16, 2) NOT NULL,

	CONSTRAINT supply_stock_pk	PRIMARY KEY(id_supply_stock),
	CONSTRAINT stock_fk 		FOREIGN KEY(id_stock) REFERENCES public.stock(id_stock),
	CONSTRAINT supply_fk 		FOREIGN KEY(id_supply) REFERENCES public.supply(id_supply)
);

-- DROP TABLE public.shopping_cart;
CREATE TABLE public.shopping_cart (
	id_shopping_cart 				UUID,
	id_user 						NUMERIC(1000) NOT NULL,
	last_update 					DATE NULL,

	CONSTRAINT shopping_cart_pk 	PRIMARY KEY(id_shopping_cart)
);

-- DROP TABLE public.shopping_cart_stock;
CREATE TABLE public.shopping_cart_stock (
	id_shopping_cart_stock				UUID,
	id_stock 							UUID NOT NULL,
	id_shopping_cart					UUID NOT NULL,
	quantity 							NUMERIC(1000) NOT NULL,

	CONSTRAINT shopping_cart_stock_pk	PRIMARY KEY(id_shopping_cart_stock),
	CONSTRAINT stock_fk 				FOREIGN KEY(id_stock) REFERENCES public.stock(id_stock),
	CONSTRAINT shopping_cart_fk 		FOREIGN KEY(id_shopping_cart) REFERENCES public.shopping_cart(id_shopping_cart)
);

-- DROP TABLE public.sale;
CREATE TABLE public.sale (
	id_sale 					UUID,
	id_client 					NUMERIC(1000) NOT NULL,
	id_salesman 				NUMERIC(1000) NOT NULL,
	date_sale 					DATE NOT NULL,
	state 						VARCHAR(12) NULL,
	
	CONSTRAINT sale_pkey 		PRIMARY KEY (id_sale)
);

-- DROP TABLE public.sale_stock;
CREATE TABLE public.sale_stock (
	id_sale_stock 				UUID,
	id_sale 					UUID NOT NULL,
	id_stock 					UUID NOT NULL,
	quantity 					NUMERIC(16, 2) NOT NULL,
	current_price				NUMERIC(16, 2) NOT NULL,

	CONSTRAINT sales_pk		 	PRIMARY KEY (id_sale_stock),
	CONSTRAINT sale_fk			FOREIGN KEY(id_sale) REFERENCES public.sale(id_sale),
	CONSTRAINT stock_fk			FOREIGN KEY(id_stock) REFERENCES public.stock(id_stock)
);

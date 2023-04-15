-- SCHEMA DATABASE PLANET EXPRESS
-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

-- DROP TABLE public.stock;
CREATE TABLE public.stock (
	product_id 					NUMERIC(1000) NOT NULL,
	quantity 					NUMERIC(1000) NOT NULL,
	current_price 				NUMERIC(16, 2) NOT NULL,

	CONSTRAINT stock_pk 		PRIMARY KEY(product_id)
);

-- DROP TABLE public.supply;
CREATE TABLE public.supply (
	supply_id					UUID,
	supplier_id					NUMERIC(1000) NOT NULL,
	date_supply					DATE NOT NULL,

	CONSTRAINT supply_pk 		PRIMARY KEY(supply_id)
);

-- DROP TABLE public.supply_product;
CREATE TABLE public.supply_stock (
	supply_stock_id 			UUID,
	supply_id 					UUID NOT NULL,
	stock_id 					NUMERIC(1000) NOT NULL,
	quantity 					NUMERIC(1000) NOT NULL,
	supply_price 				NUMERIC(16, 2) NULL,

	CONSTRAINT supply_stock_pk	PRIMARY KEY(supply_stock_id),
	CONSTRAINT supply_fk 		FOREIGN KEY(supply_id) REFERENCES public.supply(supply_id),
	CONSTRAINT stock_fk 		FOREIGN KEY(stock_id) REFERENCES public.stock(product_id)
);

-- DROP TABLE public.shopping_cart;
CREATE TABLE public.shopping_cart (
	shopping_cart_id 				UUID,
	user_id 						NUMERIC(1000) NOT NULL,
	last_update 					DATE NULL,

	CONSTRAINT shopping_cart_pk 	PRIMARY KEY(shopping_cart_id)
);

-- DROP TABLE public.shopping_cart_stock;
CREATE TABLE public.shopping_cart_stock (
	shopping_cart_stock_id				UUID,
	stock_id 							NUMERIC(1000) NOT NULL,
	shopping_cart_id					UUID NOT NULL,
	quantity 							NUMERIC(1000) NOT NULL,

	CONSTRAINT shopping_cart_stock_pk	PRIMARY KEY(shopping_cart_stock_id),
	CONSTRAINT stock_fk 				FOREIGN KEY(stock_id) REFERENCES public.stock(product_id),
	CONSTRAINT shopping_cart_fk 		FOREIGN KEY(shopping_cart_id) REFERENCES public.shopping_cart(shopping_cart_id)
);

-- DROP TABLE public.sale;
CREATE TABLE public.sale (
	sale_id 					UUID,
	client_id 					NUMERIC(1000) NOT NULL,
	salesman_id 				NUMERIC(1000) NOT NULL,
	date_sale 					DATE NOT NULL,
	state 						VARCHAR(12) NULL,
	
	CONSTRAINT sale_pkey 		PRIMARY KEY (sale_id)
);

-- DROP TABLE public.sale_stock;
CREATE TABLE public.sale_stock (
	sale_stock_id 				UUID,
	sale_id 					UUID NOT NULL,
	stock_id 					NUMERIC(1000) NOT NULL,
	quantity 					NUMERIC(16, 2) NOT NULL,
	current_price				NUMERIC(16, 2) NOT NULL,

	CONSTRAINT sales_pk		 	PRIMARY KEY (sale_stock_id),
	CONSTRAINT sale_fk			FOREIGN KEY(sale_id) REFERENCES public.sale(sale_id),
	CONSTRAINT stock_fk			FOREIGN KEY(stock_id) REFERENCES public.stock(product_id)
);

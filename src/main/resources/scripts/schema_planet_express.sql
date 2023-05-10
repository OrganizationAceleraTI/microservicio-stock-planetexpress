CREATE TABLE stock (
	product_id 					INTEGER NOT NULL,
	quantity 					INTEGER NOT NULL,
	current_price 				NUMERIC(16, 2) NOT NULL,

	CONSTRAINT stock_pk 		PRIMARY KEY(product_id)
);

CREATE TABLE supply (
	supply_id					INTEGER,
	supplier_id					INTEGER NOT NULL,
	date_supply					DATE NOT NULL,

	CONSTRAINT supply_pk 		PRIMARY KEY(supply_id)
);

CREATE TABLE supply_stock (
	supply_stock_id 			INTEGER,
	supply_id 					INTEGER  NOT NULL,
	stock_id 					INTEGER NOT NULL,
	quantity 					INTEGER NOT NULL,
	supply_price 				NUMERIC(16, 2) NULL,

	CONSTRAINT supply_stock_pk	PRIMARY KEY(supply_stock_id),
	CONSTRAINT supply_fk 		FOREIGN KEY(supply_id) REFERENCES supply(supply_id),
	CONSTRAINT stock_fk 		FOREIGN KEY(stock_id) REFERENCES stock(product_id)
);

CREATE TABLE shopping_cart (
	shopping_cart_id 				INTEGER,
	user_id 						INTEGER NOT NULL,
	last_update 					DATE NULL,

	CONSTRAINT shopping_cart_pk 	PRIMARY KEY(shopping_cart_id)
);

CREATE TABLE shopping_cart_stock (
	shopping_cart_stock_id				INTEGER,
	stock_id 							INTEGER NOT NULL,
	shopping_cart_id					INTEGER NOT NULL,
	quantity 							INTEGER NOT NULL,

	CONSTRAINT shopping_cart_stock_pk	PRIMARY KEY(shopping_cart_stock_id),
	CONSTRAINT stock_fk_2 				FOREIGN KEY(stock_id) REFERENCES stock(product_id),
	CONSTRAINT shopping_cart_fk 		FOREIGN KEY(shopping_cart_id) REFERENCES shopping_cart(shopping_cart_id)
);

CREATE TABLE sale (
	sale_id 					INTEGER,
	client_id 					INTEGER NOT NULL,
	salesman_id 				INTEGER NOT NULL,
	date_sale 					DATE NOT NULL,
	state 						VARCHAR(12) NULL,

	CONSTRAINT sale_pkey 		PRIMARY KEY (sale_id)
);

CREATE TABLE sale_stock (
	sale_stock_id 				INTEGER,
	sale_id 					INTEGER NOT NULL,
	stock_id 					INTEGER NOT NULL,
	quantity 					NUMERIC(16, 2) NOT NULL,
	current_price				NUMERIC(16, 2) NOT NULL,

	CONSTRAINT sales_pk		 	PRIMARY KEY (sale_stock_id),
	CONSTRAINT sale_fk			FOREIGN KEY(sale_id) REFERENCES sale(sale_id),
	CONSTRAINT stock_fk_3		FOREIGN KEY(stock_id) REFERENCES stock(product_id)
);

-- ---------------------------------------------------
-- Script creación tabla inventory
-- ---------------------------------------------------
DROP TABLE "inventory"; 
CREATE TABLE "inventory" (
  "inventory_id" serial not null,
  "person_supplier_id" Integer,
  "product_id" Integer,
  "incoming_price" Integer NOT NULL,
  "current_price" Integer,
  "quantity" Integer,
  primary key ("inventory_id")
);
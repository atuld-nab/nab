DROP TABLE IF EXISTS BRAND;
CREATE TABLE brand (
  id INT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

INSERT INTO BRAND (id, name) VALUES
  (1, 'Raymonds'),
 (2,'Vimal');
 
 DROP TABLE IF EXISTS CATEGORY;
 
 CREATE TABLE CATEGORY (
  id INT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);
INSERT INTO CATEGORY  VALUES
  (1, 'Mens Fashion');
 
 INSERT INTO CATEGORY  VALUES
  (2,'Kids');
  
  DROP TABLE IF EXISTS SUPPLIER;
  
  CREATE TABLE SUPPLIER (
  id INT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

INSERT INTO SUPPLIER (id, name) VALUES
  (1, 'Kmart'),
 (2,'Target');
 
 DROP TABLE IF EXISTS PRODUCT;
 
 
 CREATE TABLE PRODUCT (
  sku INT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  color VARCHAR(250) NOT NULL,
  size VARCHAR(250) NOT NULL,
  price NUMERIC(8,2) NOT NULL,
  category_id INT NOT NULL,
  brand_id INT NOT NULL,
  supplier_id INT NOT NULL,
 CONSTRAINT FK_PRODUCT_CATEGORY FOREIGN KEY (category_id) REFERENCES CATEGORY(id) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT FK_PRODUCT_SUPPLIER FOREIGN KEY (supplier_id) REFERENCES SUPPLIER(id) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT FK_PRODUCT_BRAND FOREIGN KEY (brand_id) REFERENCES BRAND(id) ON UPDATE CASCADE ON DELETE CASCADE
);


INSERT INTO PRODUCT VALUES
  (1, 'T-shirt','black','XL',40.00,1,2,1),
 (2,'T-shirt','brown','XXL',50.00,2,2,2),
 (3,'T-shirt','blue','XXL',50.00,1,1,1),
 (4,'T-shirt','brown','XXL',50.00,2,2,2);
 
 
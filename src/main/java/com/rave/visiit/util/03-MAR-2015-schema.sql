
CREATE TABLE visiit_2014.salutation (s_id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,  value VARCHAR(45) NOT NULL,  active BOOLEAN NOT NULL,  PRIMARY KEY(s_id));
INSERT INTO visiit_2014.salutation (s_id,value,active) VALUES (1,'Dr',true);
INSERT INTO visiit_2014.salutation (s_id,value,active) VALUES  (2,'Miss',true);
INSERT INTO visiit_2014.salutation (s_id,value,active) VALUES (3,'Mr',true);
INSERT INTO visiit_2014.salutation (s_id,value,active) VALUES  (4,'Mrs',true);
INSERT INTO visiit_2014.salutation (s_id,value,active) VALUES  (5,'Ms',true);
INSERT INTO visiit_2014.salutation (s_id,value,active) VALUES  (6,'Professor',true);
INSERT INTO visiit_2014.salutation (s_id,value,active) VALUES  (7,'Reverend',true);

DROP TABLE visiit_2014.traveller_detail
DROP TABLE visiit_2014.billing_address
DROP TABLE visiit_2014.contact_detail
DROP TABLE visiit_2014.person_detail
CREATE TABLE visiit_2014.traveller_detail(traveller_id integer NOT NULL AUTO_INCREMENT,fk_pk_id INTEGER NOT NULL,dateoftravel Date NOT NULL,tripcode VARCHAR(45),firstname VARCHAR(45) NOT NULL,lastname VARCHAR(45) NOT NULL,phone VARCHAR(45) NOT NULL,email VARCHAR(45) NOT NULL,address varchar(100) NOT NULL,postelcode varchar(10),city varchar(45)  NOT NULL,state varchar(45)  NOT NULL,country varchar(45)  NOT NULL,PRIMARY KEY (traveller_id),FOREIGN KEY (fk_pk_id) REFERENCES package(pk_id));
CREATE TABLE visiit_2014.person_detail(id INTEGER NOT NULL AUTO_INCREMENT,salutation varchar(25)   NOT NULL,age integer NOT NULL,firstname varchar(25) NOT NULL,lastname varchar(25) NOT NULL,fk_pk_id integer,fk_traveller_id integer,PRIMARY KEY (id),FOREIGN KEY (fk_pk_id) REFERENCES package(pk_id),FOREIGN KEY (fk_traveller_id) REFERENCES traveller_detail(traveller_id));

ALTER TABLE visiit_2014.traveller_detail ADD COLUMN package_amount DOUBLE NOT NULL, ADD COLUMN total_amount DOUBLE NOT NULL;

CREATE TABLE visiit_2014.payment_transaction (
  ptid int(11) NOT NULL AUTO_INCREMENT,
  mihpayid varchar(60) DEFAULT NULL,
  status varchar(50) DEFAULT NULL,
  merchantkey varchar(10) DEFAULT NULL,
  txnid varchar(100) DEFAULT NULL,
  name varchar(55) DEFAULT NULL,
  product varchar(100) DEFAULT NULL,
  discount decimal(10,2) NOT NULL DEFAULT '0.00',
  offer varchar(50) DEFAULT NULL,
  mode varchar(50) DEFAULT NULL,
  bankcode varchar(50) DEFAULT NULL,
  bankrefnum varchar(50) DEFAULT NULL,
  pgtype varchar(100) DEFAULT NULL,
  error varchar(100) DEFAULT NULL,
  errormsg varchar(500) DEFAULT NULL,
  hash varchar(500) DEFAULT NULL,
  updatedon timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ptid),
  UNIQUE KEY mihpayid_UNIQUE (mihpayid),
  KEY ptid_index (ptid)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

ALTER TABLE visiit_2014.category DROP COLUMN cat_active;
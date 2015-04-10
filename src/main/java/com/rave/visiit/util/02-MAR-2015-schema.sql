ALTER TABLE visiit_2014.package ADD COLUMN pk_order INT(10);
ALTER TABLE visiit_2014.package ADD COLUMN pk_special VARCHAR(50);
ALTER TABLE visiit_2014.package_exclusion DROP COLUMN pkex_img_url;
--ALTER TABLE visiit_2014.package_inclusion DROP COLUMN image_url;
ALTER TABLE visiit_2014.package_hotel DROP COLUMN pkh_img_url;
ALTER TABLE visiit_2014.package_exclusion ADD COLUMN image_url VARCHAR(500);
ALTER TABLE visiit_2014.package_inclusion ADD COLUMN image_url VARCHAR(500);
ALTER TABLE visiit_2014.package_hotel ADD COLUMN image_url VARCHAR(500);
ALTER TABLE visiit_2014.package_cost ADD COLUMN pkc_offer_price INT(10);
ALTER TABLE visiit_2014.package ADD COLUMN image_url VARCHAR(500);
ALTER TABLE visiit_2014.package_hotel DROP COLUMN pkh_img_url;
CREATE TABLE visiit_2014.billing_address(billing_id INTEGER  NOT NULL AUTO_INCREMENT,firstname varchar(45) NOT NULL,postelcode varchar(10),city varchar(45),state varchar(45),country varchar(45),PRIMARY KEY (billing_id));
CREATE TABLE visiit_2014.traveller_detail(traveller_id integer NOT NULL AUTO_INCREMENT,dateoftravel Date,fk_pkg_id INTEGER,fk_contect_id INTEGER,fk_billing_id INTEGER,PRIMARY KEY (traveller_id),FOREIGN KEY (fk_billing_id) REFERENCES billing_address(billing_id),FOREIGN KEY (fk_contect_id) REFERENCES contact_detail(contact_id),FOREIGN KEY (fk_pkg_id) REFERENCES package(pk_id));
CREATE TABLE visiit_2014.contact_detail (contact_id INTEGER  NOT NULL AUTO_INCREMENT,  firstname VARCHAR(45) NOT NULL,  lastname VARCHAR(45) NOT NULL,  phone VARCHAR(45) NOT NULL,  email VARCHAR(45) NOT NULL,  PRIMARY KEY(contact_id));
CREATE TABLE visiit_2014.person_detail(id INTEGER NOT NULL AUTO_INCREMENT,salutation varchar(25)   NOT NULL,age integer NOT NULL,firstname varchar(25) NOT NULL,lastname varchar(25) NOT NULL,fk_pk_id integer,fk_traveller_id integer,PRIMARY KEY (id),FOREIGN KEY (fk_pk_id) REFERENCES package(pk_id),FOREIGN KEY (fk_traveller_id) REFERENCES traveller_detail(traveller_id));
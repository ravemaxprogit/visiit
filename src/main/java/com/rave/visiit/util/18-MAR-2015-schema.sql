ALTER TABLE visiit_2014.package_itinerary ADD COLUMN pki_guide boolean default 1;
ALTER TABLE visiit_2014.package_itinerary ADD COLUMN pki_jungle boolean default 1;
ALTER TABLE visiit_2014.package_itinerary ADD COLUMN pki_shows boolean default 1;
ALTER TABLE visiit_2014.package_itinerary ADD COLUMN pki_sports boolean default 1;
ALTER TABLE visiit_2014.package_itinerary ADD COLUMN pki_camp_fire boolean default 1;
ALTER TABLE visiit_2014.package_itinerary ADD COLUMN pki_sea_activity boolean default 1;
ALTER TABLE visiit_2014.package_itinerary ADD COLUMN pki_pickup boolean default 1;


ALTER TABLE visiit_2014.customer_registration CHANGE COLUMN cr_cus_name cr_cus_firstname VARCHAR(50);
ALTER TABLE visiit_2014.customer_registration ADD COLUMN cr_cus_lastname VARCHAR(45);

ALTER TABLE visiit_2014.payment_transaction MODIFY COLUMN txnid VARCHAR(45);


SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS visiit_2014.module;
SET FOREIGN_KEY_CHECKS=1;
SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE  visiit_2014.module (
  mod_id int(11) NOT NULL AUTO_INCREMENT,
  mod_code varchar(30) NOT NULL,
  mod_name varchar(50) NOT NULL,
  parent_id int(11) DEFAULT NULL,
  mod_description varchar(100) DEFAULT NULL,
  mod_is_active tinyint(1) DEFAULT '1',
  is_deleted tinyint(1) DEFAULT '0',
  PRIMARY KEY (mod_id),
  KEY mod_idx (mod_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO visiit_2014.module (mod_id,mod_code,mod_name,parent_id,mod_description) VALUES
 (1,'MOD00001','Masters',NULL,'Masters'),
 (2,'MOD00002','Product',NULL,'Product'),
 (3,'MOD00003','Customer Care',NULL,'Customer Care'),
 (4,'MOD00004','Country',1,'Country'),
 (5,'MOD00005','Users',1,'Users'),
 (6,'MOD00006','Department',1,'Department'),
 (7,'MOD00007','Designation',1,'Designation'),
 (8,'MOD00008','Vendor',2,'Vendor'),
 (9,'MOD00009','Hotel',2,'Hotel'),
 (10,'MOD000010','Package',2,'Package'),
 (11,'MOD000011','Package Category',2,'Package Category'),
 (12,'MOD000012','Enquiry Support',3,'Enquiry Support'),
 (13,'MOD000013','Payment Approval',3,'Payment Approval')
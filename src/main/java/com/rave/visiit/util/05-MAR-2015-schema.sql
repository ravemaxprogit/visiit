ALTER TABLE visiit_2014.vendor_information DROP COLUMN vi_active;
ALTER TABLE visiit_2014.vendor_information ADD COLUMN is_deleted boolean default 0;

ALTER TABLE visiit_2014.locations ADD UNIQUE (loc_code);
ALTER TABLE visiit_2014.city ADD UNIQUE (city_code);
ALTER TABLE visiit_2014.state ADD UNIQUE (state_code);
ALTER TABLE visiit_2014.country ADD UNIQUE (country_code);

ALTER TABLE visiit_2014.category DROP COLUMN img_url;
ALTER TABLE visiit_2014.category ADD COLUMN image_url VARCHAR(255);
ALTER TABLE visiit_2014.category ADD COLUMN is_deleted boolean default 0;

ALTER TABLE visiit_2014.hotel_detail MODIFY COLUMN hd_phone VARCHAR(20);
ALTER TABLE visiit_2014.hotel_detail DROP COLUMN hd_postal;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN hd_postal_code VARCHAR(10);
ALTER TABLE visiit_2014.hotel_detail MODIFY COLUMN hd_fax VARCHAR(20);

ALTER TABLE visiit_2014.hotel_contact MODIFY COLUMN hc_contact_name1 VARCHAR(50);
ALTER TABLE visiit_2014.hotel_contact MODIFY COLUMN hc_contact_name2 VARCHAR(50);
ALTER TABLE visiit_2014.hotel_contact MODIFY COLUMN hc_phone1 VARCHAR(20);
ALTER TABLE visiit_2014.hotel_contact MODIFY COLUMN hc_phone2 VARCHAR(20);

ALTER TABLE visiit_2014.category DROP COLUMN cat_updated_date;
ALTER TABLE visiit_2014.category DROP COLUMN cat_updated_by;

ALTER TABLE visiit_2014.category ADD COLUMN cat_modified_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE visiit_2014.category ADD COLUMN cat_modified_by VARCHAR(30);

update category set cat_modified_by='admin' where cat_modified_by is null;
update category set cat_modified_on='2015-02-03 00:00:00' where cat_modified_on='0000-00-00 00:00:00';

ALTER TABLE visiit_2014.users DROP COLUMN user_is_active;
ALTER TABLE visiit_2014.users ADD COLUMN user_is_active boolean default true;

update category set cat_modified_on='2015-02-03 00:00:00' where cat_modified_on='0000-00-00 00:00:00';

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS visiit_2014.designation;
CREATE TABLE  visiit_2014.designation (
  dsg_id int(11) NOT NULL AUTO_INCREMENT,
  dsg_code varchar(30) NOT NULL,
  dsg_name varchar(50) NOT NULL,
  dsg_level int(4) DEFAULT '1',
  dsg_description varchar(100) DEFAULT NULL,
  dsg_is_active tinyint(1) DEFAULT '1',
  dsg_created_on timestamp NULL,
  dsg_modified_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  dsg_modified_by varchar(30) DEFAULT NULL,
  dsg_created_by varchar(30) NOT NULL,
  is_deleted tinyint(1) DEFAULT '0',
  PRIMARY KEY (dsg_id),
  KEY dsg_idx (dsg_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;

--
-- Definition of table `department`
--
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS visiit_2014.department;
CREATE TABLE visiit_2014.department (
  dept_id int(11) NOT NULL AUTO_INCREMENT,
  dept_code varchar(30) NOT NULL,
  dept_name varchar(50) NOT NULL,
  dept_description varchar(100),
  dept_is_active  boolean DEFAULT true,
  is_deleted boolean DEFAULT false,
  dept_created_by varchar(50),
  dept_created_on timestamp NULL,
  dept_modified_by varchar(50),
  dept_modified_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (dept_id),
  KEY dept_idx (dept_id)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS visiit_2014.departments;
SET FOREIGN_KEY_CHECKS=1;
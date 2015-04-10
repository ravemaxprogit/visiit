ALTER TABLE visiit_2014.hotel_detail DROP COLUMN hd_postal;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN hd_postal_code VARCHAR(10)

ALTER TABLE visiit_2014.customer_registration DROP COLUMN cr_security_code;
ALTER TABLE visiit_2014.customer_registration DROP COLUMN cr_updated_on;
ALTER TABLE visiit_2014.customer_registration ADD COLUMN cr_updated_on datetime;
ALTER TABLE visiit_2014.customer_registration ADD COLUMN cr_is_active Boolean default true;
ALTER TABLE visiit_2014.customer_registration ADD COLUMN is_delete Boolean default false;
ALTER TABLE visiit_2014.customer_registration MODIFY COLUMN cr_cus_pass VARCHAR(100);

ALTER TABLE visiit_2014.customer_registration ADD COLUMN cr_expiration_date DATETIME;

--------------------------------------------------------------
ALTER TABLE visiit_2014.vendor_information DROP COLUMN vi_isactive;
ALTER TABLE visiit_2014.vendor_information ADD COLUMN vi_is_active Boolean default true;
ALTER TABLE visiit_2014.vendor_information DROP COLUMN vi_modified_date;
ALTER TABLE visiit_2014.vendor_information ADD COLUMN vi_modified_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP;
update vendor_information set vi_modified_on='2015-02-05 00:00:00' where vi_modified_on='0000-00-00 00:00:00';
--------------------------------------------------------------
ALTER TABLE visiit_2014.country DROP COLUMN country_isactive;
ALTER TABLE visiit_2014.country ADD COLUMN country_is_active Boolean default true;
ALTER TABLE visiit_2014.state DROP COLUMN state_isactive;
ALTER TABLE visiit_2014.state ADD COLUMN state_is_active Boolean default true;
ALTER TABLE visiit_2014.city DROP COLUMN city_isactive;
ALTER TABLE visiit_2014.city ADD COLUMN city_is_active Boolean default true;
ALTER TABLE visiit_2014.locations DROP COLUMN loc_isactive;
ALTER TABLE visiit_2014.locations ADD COLUMN loc_is_active Boolean default true;
---------------------------------------------------------------------------------
ALTER TABLE visiit_2014.hotel_detail DROP COLUMN hd_isactive;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN hd_is_active Boolean default true;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN review_code VARCHAR(30);
ALTER TABLE visiit_2014.locations ADD COLUMN review_code VARCHAR(30);

------------------------------------------------------------------
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS visiit_2014.users;
SET FOREIGN_KEY_CHECKS=1;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS visiit_2014.user;
CREATE TABLE visiit_2014.user (
  user_seq_id int(11) NOT NULL AUTO_INCREMENT,
  user_id varchar(50) NOT NULL,
  user_code varchar(30) NOT NULL,
  user_password varchar(50) DEFAULT NULL,
  user_is_active boolean NOT NULL DEFAULT true,
  user_first_name varchar(30) NOT NULL,
  user_last_name varchar(30) NOT NULL,
  user_office_mail varchar(50) DEFAULT NULL,
  user_mail varchar(50) DEFAULT NULL,
  user_phone varchar(15) NOT NULL,
  user_extention_no varchar(10) DEFAULT NULL,
  user_dept_id int(11) NOT NULL,
  user_dsg_id int(11) NOT NULL,
  user_doj date DEFAULT NULL,
  user_esi_no varchar(20) DEFAULT NULL,
  user_pf_no varchar(20) DEFAULT NULL,
  user_pan_no varchar(20) DEFAULT NULL,
  user_passport_no varchar(20) DEFAULT NULL,
  user_dob date DEFAULT NULL,
  user_created_by varchar(30) NOT NULL,
  user_created_on timestamp NULL,
  user_modified_by varchar(30) NOT NULL,
  user_modified_on timestamp NULL,
  is_deleted tinyint(1) DEFAULT false,
  user_pwd_exp_date timestamp NOT NULL,
  PRIMARY KEY (user_seq_id),
  KEY user_seq_id_index (user_seq_id),
  KEY user_dept_idx (user_dept_id),
  KEY user_dsg_idx (user_dsg_id),
  CONSTRAINT user_dept_idx FOREIGN KEY (user_dept_id) REFERENCES department (dept_id),
  CONSTRAINT user_dsg_idx FOREIGN KEY (user_dsg_id) REFERENCES designation (dsg_id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
------------------------------------------------------------------------------
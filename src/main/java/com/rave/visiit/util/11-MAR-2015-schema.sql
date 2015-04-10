ALTER TABLE visiit_2014.customer_registration ADD COLUMN cr_contact_address VARCHAR(100);
ALTER TABLE visiit_2014.customer_registration ADD COLUMN cr_contact_preference VARCHAR(45);
ALTER TABLE visiit_2014.customer_registration ADD COLUMN cr_date_of_birth DATE;
ALTER TABLE visiit_2014.customer_registration ADD COLUMN cr_age INTEGER;
ALTER TABLE visiit_2014.customer_registration ADD COLUMN cr_city VARCHAR(45);
ALTER TABLE visiit_2014.customer_registration ADD COLUMN cr_state VARCHAR(45);
ALTER TABLE visiit_2014.customer_registration ADD COLUMN cr_country VARCHAR(45);
ALTER TABLE visiit_2014.customer_registration ADD COLUMN cr_postal_code VARCHAR(45);

DROP TABLE IF EXISTS visiit_2014.trip_voucher;
CREATE TABLE  visiit_2014.trip_voucher (
  voucher_id int(11) NOT NULL AUTO_INCREMENT,
  file_name varchar(30) NOT NULL,
  mime_type varchar(50) NOT NULL,
  data MEDIUMBLOB DEFAULT NULL,
  trip_code varchar(50) DEFAULT NULL,
  uploaded_on date DEFAULT NULL,
  uploaded_by varchar(50) DEFAULT NULL,
  modified_by varchar(50) DEFAULT NULL,
  modified_on date DEFAULT NULL,
  is_deleted boolean default 0,
  PRIMARY KEY (voucher_id),
  KEY mod_idx (voucher_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE visiit_2014.traveller_detail ADD COLUMN payment_date DateTime;
ALTER TABLE visiit_2014.customer_enquiry MODIFY COLUMN enq_code VARCHAR(45);
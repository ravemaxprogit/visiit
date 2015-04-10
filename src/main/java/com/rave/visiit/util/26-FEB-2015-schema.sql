DROP TABLE IF EXISTS visiit_2014.package_condition;


CREATE TABLE visiit_2014.package_condition (
   pc_seq_id int(11) NOT NULL AUTO_INCREMENT,
   pc_pk_id int(11) NOT NULL,
   pc_description varchar(500) DEFAULT '',
   pc_isactive varchar(1) NOT NULL DEFAULT 'Y',
   pc_modified_on timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   pc_modified_by varchar(30) NOT NULL,
   pc_active VARCHAR(30) NOT NULL,
  PRIMARY KEY (pc_seq_id),
  KEY pc_id_index (pc_seq_id),
  KEY pc_pk_idx1 (pc_pk_id),
  CONSTRAINT pc_pk_idx1 FOREIGN KEY (pc_pk_id) REFERENCES package (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE visiit_2014.package_hotel ADD COLUMN pkh_active VARCHAR(30) NOT NULL;

update visiit_2014.package_hotel set pkh_active='true' where pkh_active ='';

ALTER TABLE visiit_2014.package_hotel ADD COLUMN pkh_status VARCHAR(7);
ALTER TABLE visiit_2014.package_hotel ADD COLUMN pkh_city VARCHAR(50);

ALTER TABLE visiit_2014.category ADD COLUMN seq_id int(10) unsigned NOT NULL;
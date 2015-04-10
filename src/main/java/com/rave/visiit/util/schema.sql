ALTER TABLE visiit_2014.country ADD COLUMN country_active VARCHAR(50) NOT NULL;
ALTER TABLE visiit_2014.locations ADD COLUMN loc_active VARCHAR(50) NOT NULL;
ALTER TABLE visiit_2014.state ADD COLUMN state_active VARCHAR(50) NOT NULL;
ALTER TABLE visiit_2014.city ADD COLUMN city_active VARCHAR(50) NOT NULL;
ALTER TABLE visiit_2014.vendor_information ADD COLUMN vi_active VARCHAR(50) NOT NULL;
ALTER TABLE visiit_2014.package ADD COLUMN pk_active VARCHAR(50) NOT NULL;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN hd_active VARCHAR(50) NOT NULL;
ALTER TABLE visiit_2014.vendor_information ADD COLUMN vi_code VARCHAR(50) NOT NULL;
ALTER TABLE visiit_2014.hotel_detail MODIFY COLUMN hd_wifi boolean default 0;
ALTER TABLE visiit_2014.hotel_detail MODIFY COLUMN hd_gym boolean default 0;
ALTER TABLE visiit_2014.hotel_detail MODIFY COLUMN haf_helthclub_yoga boolean default 0 ;
ALTER TABLE visiit_2014.hotel_detail MODIFY COLUMN hd_swimmingpool boolean default 0;
ALTER TABLE visiit_2014.hotel_detail MODIFY COLUMN hd_parking boolean default 0;
ALTER TABLE visiit_2014.hotel_detail MODIFY COLUMN hd_transfer boolean default 0;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN hd_bar boolean  default 0;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN hd_food boolean default 0;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN hd_postal VARCHAR(50) NOT NULL;
ALTER TABLE visiit_2014.users MODIFY COLUMN user_password VARCHAR(50);
INSERT INTO visiit_2014.users (user_seq_id,user_id,user_emp_id,user_password,user_sq_id,user_sq_answer,user_is_active,user_default_module_id,role_id,user_modified_by,user_modified_on) VALUES 
 (1,'admin@gmail.com',1,'ISMvKXpXpadDiUoOSoAfww==',2,'2','t',1,2,'admin','2015-02-02 00:00:00');
CREATE TABLE visiit_2014.vendor_information_key (vendorKey int(10) unsigned NOT NULL, PRIMARY KEY (vendorKey));
CREATE TABLE visiit_2014.hotel_detail_key ( hotelKey int(10) unsigned NOT NULL,  PRIMARY KEY (hotelKey));
INSERT INTO visiit_2014.hotel_detail_key (hotelKey) VALUES (1),(2);
INSERT INTO visiit_2014.vendor_information_key (vendorKey) VALUES (1);

--23-Feb-2014 - Schema

ALTER TABLE visiit_2014.Hotel_detail_key CHANGE COLUMN key hotelKey INTEGER; 
ALTER TABLE visiit_2014.vendor_information_key CHANGE COLUMN key vendorKey INTEGER; 
ALTER TABLE visiit_2014.package MODIFY COLUMN pk_place VARCHAR(50);
ALTER TABLE visiit_2014.package_location ADD COLUMN pkl_type int(10) unsigned NOT NULL;
ALTER TABLE visiit_2014.hotel_contact MODIFY COLUMN hc_contact_name2 VARCHAR(30);
ALTER TABLE visiit_2014.hotel_contact MODIFY COLUMN hc_position2 VARCHAR(30);
ALTER TABLE visiit_2014.hotel_contact MODIFY COLUMN hc_phone2 VARCHAR(15);
ALTER TABLE visiit_2014.hotel_contact MODIFY COLUMN hc_email2 VARCHAR(50);
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN hd_type VARCHAR(50) NOT NULL;
ALTER TABLE visiit_2014.hotel_detail MODIFY COLUMN hd_description VARCHAR(500);
ALTER TABLE visiit_2014.package_location MODIFY COLUMN pkl_loc_id int(11); 
ALTER TABLE visiit_2014.package_location ADD COLUMN pkl_location int(11) NOT NULL;
ALTER TABLE visiit_2014.hotel_detail MODIFY COLUMN hd_category VARCHAR(50);
UPDATE visiit_2014.users SET user_password='21232f297a57a5a743894a0e4a801fc3'

update visiit_2014.state set state_active='true' where state_active ='';
update visiit_2014.locations set loc_active='true' where loc_active ='';
update visiit_2014.country set country_active='true' where country_active ='';
update visiit_2014.city set city_active='true' where city_active ='';
update visiit_2014.vendor_information set vi_active='true' where vi_active ='';
update visiit_2014.package set pk_active='true' where pk_active ='';
update visiit_2014.hotel_detail set hd_active='true' where hd_active ='';
update visiit_2014.hotel_detail set hd_wifi = true where hd_wifi is null;
update visiit_2014.hotel_detail set hd_bar = true where hd_wifi is null;
update  visiit_2014.hotel_detail set hd_gym = true where  hd_gym is null;
update  visiit_2014.hotel_detail set haf_helthclub_yoga = true where haf_helthclub_yoga is null;
update  visiit_2014.hotel_detail set hd_swimmingpool = true where hd_swimmingpool is null;
update  visiit_2014.hotel_detail set hd_parking = true where hd_parking is null;
update  visiit_2014.hotel_detail set hd_transfer = true where hd_transfer is null;
update visiit_2014.hotel_detail set hd_food = true where hd_wifi is null;


--25-Feb-2015 - Schema
ALTER TABLE visiit_2014.package_inclusion ADD COLUMN pkin_active VARCHAR(1), ADD COLUMN pkin_img_url VARCHAR(100);
ALTER TABLE visiit_2014.package_exclusion ADD COLUMN pkex_active VARCHAR(1), ADD COLUMN pkex_img_url VARCHAR(100);
CREATE TABLE visiit_2014.package_inclusion_key ( pk_inclusion_key INTEGER UNSIGNED NOT NULL, PRIMARY KEY(pk_inclusion_key));
CREATE TABLE visiit_2014.package_exclusion_key ( pk_exclusion_key INTEGER UNSIGNED NOT NULL, PRIMARY KEY(pk_exclusion_key));
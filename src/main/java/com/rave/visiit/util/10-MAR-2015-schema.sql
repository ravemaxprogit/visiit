ALTER TABLE visiit_2014.package DROP COLUMN pk_flight;
ALTER TABLE visiit_2014.package ADD COLUMN pk_flight boolean default 0;

ALTER TABLE visiit_2014.package DROP COLUMN pk_hotel;
ALTER TABLE visiit_2014.package ADD COLUMN pk_hotel boolean default 0;

ALTER TABLE visiit_2014.package DROP COLUMN pk_food;
ALTER TABLE visiit_2014.package ADD COLUMN pk_food boolean default 0;

ALTER TABLE visiit_2014.package DROP COLUMN pk_train;
ALTER TABLE visiit_2014.package ADD COLUMN pk_train boolean default 0;

ALTER TABLE visiit_2014.package DROP COLUMN pk_bus;
ALTER TABLE visiit_2014.package ADD COLUMN pk_bus boolean default 0;

ALTER TABLE visiit_2014.package DROP COLUMN pk_ferry;
ALTER TABLE visiit_2014.package ADD COLUMN pk_ferry boolean default 0;

ALTER TABLE visiit_2014.package DROP COLUMN pk_visa_fee_inclusion;
ALTER TABLE visiit_2014.package ADD COLUMN pk_visa_fee_inclusion boolean default 0;

ALTER TABLE visiit_2014.package DROP COLUMN pk_transfer;
ALTER TABLE visiit_2014.package ADD COLUMN pk_transfer boolean default 0;

ALTER TABLE visiit_2014.package_condition DROP COLUMN pc_isactive;
ALTER TABLE visiit_2014.package_condition ADD COLUMN pc_is_active boolean default 0;

ALTER TABLE visiit_2014.package_cost DROP COLUMN pkc_active;
ALTER TABLE visiit_2014.package_cost ADD COLUMN is_deleted boolean default 0;

ALTER TABLE visiit_2014.package_itinerary DROP COLUMN pki_isactive;
ALTER TABLE visiit_2014.package_itinerary ADD COLUMN pki_is_active boolean default 0;

ALTER TABLE visiit_2014.package_location DROP COLUMN pkl_isactive;
ALTER TABLE visiit_2014.package_location ADD COLUMN pkl_is_active boolean default 0;

ALTER TABLE visiit_2014.package_hotel DROP COLUMN pkh_isActive;
ALTER TABLE visiit_2014.package_hotel ADD COLUMN pkh_is_active boolean default 0;

ALTER TABLE visiit_2014.package_hotel DROP COLUMN pkh_active;
ALTER TABLE visiit_2014.package_hotel ADD COLUMN is_deleted boolean default 0;

ALTER TABLE visiit_2014.country ADD COLUMN created_on timestamp;
ALTER TABLE visiit_2014.city ADD COLUMN created_on timestamp;
ALTER TABLE visiit_2014.state ADD COLUMN created_on timestamp;
ALTER TABLE visiit_2014.locations ADD COLUMN created_on timestamp;

update visiit_2014.country set created_on='2015-02-03 00:00:00' where created_on='0000-00-00 00:00:00';
update visiit_2014.city set created_on='2015-02-03 00:00:00' where created_on='0000-00-00 00:00:00';
update visiit_2014.state set created_on='2015-02-03 00:00:00' where created_on='0000-00-00 00:00:00';
update visiit_2014.locations set created_on='2015-02-03 00:00:00' where created_on='0000-00-00 00:00:00';


ALTER TABLE visiit_2014.country ADD COLUMN modified_by VARCHAR(50);
ALTER TABLE visiit_2014.state ADD COLUMN modified_by VARCHAR(50);
ALTER TABLE visiit_2014.city ADD COLUMN modified_by VARCHAR(50);
ALTER TABLE visiit_2014.locations ADD COLUMN modified_by VARCHAR(50);

ALTER TABLE visiit_2014.country ADD COLUMN created_by VARCHAR(50);
ALTER TABLE visiit_2014.state ADD COLUMN created_by VARCHAR(50);
ALTER TABLE visiit_2014.city ADD COLUMN created_by VARCHAR(50);
ALTER TABLE visiit_2014.locations ADD COLUMN created_by VARCHAR(50);

ALTER TABLE visiit_2014.traveller_detail ADD COLUMN payment_status VARCHAR(45);
ALTER TABLE visiit_2014.traveller_detail ADD COLUMN approved_status VARCHAR(45);
ALTER TABLE visiit_2014.traveller_detail ADD COLUMN approved_Date DateTime;
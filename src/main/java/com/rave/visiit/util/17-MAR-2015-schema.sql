ALTER TABLE visiit_2014.package_inclusion DROP COLUMN pkin_status;
ALTER TABLE visiit_2014.package_inclusion ADD COLUMN pkin_status boolean default 1;
ALTER TABLE visiit_2014.package_inclusion DROP COLUMN pkin_active;
ALTER TABLE visiit_2014.package_inclusion ADD COLUMN pkin_active boolean default 1;

ALTER TABLE visiit_2014.package_exclusion DROP COLUMN pkex_status;
ALTER TABLE visiit_2014.package_exclusion ADD COLUMN pkex_status boolean default 1;
ALTER TABLE visiit_2014.package_exclusion DROP COLUMN pkex_active;
ALTER TABLE visiit_2014.package_exclusion ADD COLUMN pkex_active boolean default 1;

ALTER TABLE visiit_2014.vendor_information MODIFY COLUMN vi_vendor_phone VARCHAR(20);
ALTER TABLE visiit_2014.vendor_information MODIFY COLUMN vi_contact_no1 VARCHAR(20);
ALTER TABLE visiit_2014.vendor_information MODIFY COLUMN vi_contact_no2 VARCHAR(20);

ALTER TABLE visiit_2014.package ADD COLUMN pk_guide boolean default 1;
ALTER TABLE visiit_2014.package ADD COLUMN pk_jungle boolean default 1;
ALTER TABLE visiit_2014.package ADD COLUMN pk_shows boolean default 1;
ALTER TABLE visiit_2014.package ADD COLUMN pk_sports boolean default 1;
ALTER TABLE visiit_2014.package ADD COLUMN pk_camp_fire boolean default 1;
ALTER TABLE visiit_2014.package ADD COLUMN pk_sea_activity boolean default 1;
ALTER TABLE visiit_2014.package ADD COLUMN pk_pickup boolean default 1;

ALTER TABLE visiit_2014.hotel_detail ADD COLUMN library boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN spa boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN travel_desk boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN tv_dvd boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN bussiness_center boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN heater boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN laundry boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN doctor_on_call boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN air_conditions boolean default 1;

ALTER TABLE visiit_2014.hotel_detail ADD COLUMN ironing boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN kids boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN dial_phone boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN room_safe boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN villa boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN shower boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN pickup boolean default 1;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN floral_request boolean default 1;



ALTER TABLE visiit_2014.category MODIFY COLUMN cat_title VARCHAR(50);
ALTER TABLE visiit_2014.category MODIFY COLUMN cat_description VARCHAR(500);
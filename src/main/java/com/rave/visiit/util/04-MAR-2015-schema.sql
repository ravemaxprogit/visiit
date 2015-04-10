
ALTER TABLE visiit_2014.users ADD COLUMN user_expiration_date DATETIME;
UPDATE visiit_2014.users SET user_expiration_date = CAST('2015-05-25' AS DATETIME)

ALTER TABLE visiit_2014.country DROP COLUMN country_active;
ALTER TABLE visiit_2014.country ADD COLUMN is_deleted boolean default 0;

ALTER TABLE visiit_2014.state DROP COLUMN state_active;
ALTER TABLE visiit_2014.state ADD COLUMN is_deleted boolean default 0;

ALTER TABLE visiit_2014.city DROP COLUMN city_active;
ALTER TABLE visiit_2014.city ADD COLUMN is_deleted boolean default 0;

ALTER TABLE visiit_2014.locations DROP COLUMN loc_active;
ALTER TABLE visiit_2014.locations ADD COLUMN is_deleted boolean default 0;

ALTER TABLE visiit_2014.package DROP COLUMN img_url;

ALTER TABLE visiit_2014.hotel_detail DROP COLUMN hd_active;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN is_deleted boolean default 0;
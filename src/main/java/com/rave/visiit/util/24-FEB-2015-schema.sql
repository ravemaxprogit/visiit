ALTER TABLE visiit_2014.category ADD COLUMN img_url VARCHAR(500);
ALTER TABLE visiit_2014.category ADD COLUMN cat_seq_id INT(30);
ALTER TABLE visiit_2014.package_itinerary ADD COLUMN img_url VARCHAR(500);
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN hd_status VARCHAR(6);
ALTER TABLE visiit_2014.package_cost ADD COLUMN pkc_valid_day INT(10);
ALTER TABLE visiit_2014.package_attractions ADD COLUMN pka_type VARCHAR(500);
ALTER TABLE visiit_2014.package ADD COLUMN pk_price boolean default 0;
ALTER TABLE visiit_2014.package ADD COLUMN pk_activity boolean default 0;
ALTER TABLE visiit_2014.package ADD COLUMN pk_hotels boolean default 0;
ALTER TABLE visiit_2014.category ADD COLUMN cat_active VARCHAR(50) NOT NULL;
ALTER TABLE visiit_2014.package ADD COLUMN img_url VARCHAR(255);
ALTER TABLE visiit_2014.package ADD COLUMN icon_url VARCHAR(255);
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN img_url VARCHAR(255);
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN icon_url VARCHAR(255);
ALTER TABLE visiit_2014.category ADD COLUMN icon_url VARCHAR(255);
ALTER TABLE visiit_2014.package_cost ADD COLUMN pkc_active VARCHAR(50) NOT NULL;


--Use this updated query if you need

update visiit_2014.category set cat_active='true' where cat_active ='';
update visiit_2014.package_cost set pkc_active='true' where pkc_active ='';


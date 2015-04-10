ALTER TABLE visiit_2014.package_hotel ADD COLUMN pkh_img_url VARCHAR(500);
ALTER TABLE visiit_2014.package_exclusion DROP COLUMN pkex_exclusion;
ALTER TABLE visiit_2014.package_inclusion DROP COLUMN pkin_inclusion;
ALTER TABLE visiit_2014.package_itinerary DROP COLUMN img_url;
ALTER TABLE visiit_2014.package_itinerary ADD COLUMN image_url VARCHAR(500);
ALTER TABLE visiit_2014.hotel_detail DROP COLUMN img_url;
ALTER TABLE visiit_2014.hotel_detail ADD COLUMN image_url VARCHAR(500);
DROP TABLE IF EXISTS visiit_2014.user_module;
CREATE TABLE user_module
(
    user_seq_id INT NOT NULL,  
    mod_id INT NOT NULL,  
    PRIMARY KEY (user_seq_id, mod_id),  
    FOREIGN KEY (user_seq_id) REFERENCES visiit_2014.user(user_seq_id),  
    FOREIGN KEY (mod_id) REFERENCES visiit_2014.module(mod_id)
);  

DROP TABLE IF EXISTS visiit_2014.user_sub_module;
CREATE TABLE user_sub_module
(
    user_seq_id INT NOT NULL,  
    mod_id INT NOT NULL,  
    PRIMARY KEY (user_seq_id, mod_id),  
    FOREIGN KEY (user_seq_id) REFERENCES visiit_2014.user(user_seq_id),  
    FOREIGN KEY (mod_id) REFERENCES visiit_2014.module(mod_id)
);
ALTER TABLE visiit_2014.customer_enquiry ADD COLUMN enq_replay VARCHAR(100);

ALTER TABLE visiit_2014.hotel_detail MODIFY COLUMN hd_fax VARCHAR(20);
ALTER TABLE visiit_2014.package DROP COLUMN pk_active;
ALTER TABLE visiit_2014.package ADD COLUMN is_deleted boolean default 0;
ALTER TABLE visiit_2014.package_itinerary DROP COLUMN pki_active;
ALTER TABLE visiit_2014.package_itinerary ADD COLUMN is_deleted boolean default 0;
ALTER TABLE visiit_2014.package_condition DROP COLUMN pc_active;
ALTER TABLE visiit_2014.package_condition ADD COLUMN is_deleted boolean default 0;
ALTER TABLE visiit_2014.traveller_detail ADD COLUMN fk_customer_id INTEGER,
 ADD CONSTRAINT FK_traveller_detail_2 FOREIGN KEY FK_traveller_detail_2 (fk_customer_id)
    REFERENCES customer_registration (cr_cus_id);

ALTER TABLE visiit_2014.customer_enquiry ADD COLUMN fk_customer_id INTEGER,
 ADD CONSTRAINT FK_customer_enquiry_1 FOREIGN KEY FK_customer_enquiry_1 (fk_customer_id)
    REFERENCES customer_registration (cr_cus_id);
    
ALTER TABLE visiit_2014.traveller_detail ADD COLUMN comands VARCHAR(1000);    

ALTER TABLE visiit_2014.traveller_detail ADD COLUMN fk_prize_id INTEGER,
 ADD CONSTRAINT fk_prize FOREIGN KEY fk_prize (fk_prize_id)
    REFERENCES package_cost (pkc_id);
    
ALTER TABLE visiit_2014.package ADD COLUMN is_valid boolean default 0;
ALTER TABLE visiit_2014.package_exclusion ADD COLUMN is_deleted boolean default 0;
ALTER TABLE visiit_2014.package_inclusion ADD COLUMN is_deleted boolean default 0;
ALTER TABLE visiit_2014.package_location ADD COLUMN is_deleted boolean default 0;

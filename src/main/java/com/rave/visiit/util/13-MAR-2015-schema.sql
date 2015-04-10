ALTER TABLE visiit_2014.traveller_detail ADD COLUMN voucher VARCHAR(50);

ALTER TABLE visiit_2014.customer_enquiry ADD COLUMN enq_tripdate DATE;
ALTER TABLE visiit_2014.customer_enquiry ADD COLUMN enq_adults INTEGER;
ALTER TABLE visiit_2014.customer_enquiry ADD COLUMN enq_children INTEGER;
ALTER TABLE visiit_2014.customer_enquiry ADD COLUMN enq_package VARCHAR(50);
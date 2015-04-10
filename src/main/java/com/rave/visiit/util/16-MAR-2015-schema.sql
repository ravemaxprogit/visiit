ALTER TABLE visiit_2014.traveller_detail DROP COLUMN comands;
ALTER TABLE visiit_2014.traveller_detail  ADD COLUMN payment_commands VARCHAR(1000);
ALTER TABLE visiit_2014.traveller_detail  ADD COLUMN status_commands VARCHAR(1000);

ALTER TABLE visiit_2014.traveller_detail MODIFY COLUMN final_amount DOUBLE DEFAULT NULL;
ALTER TABLE visiit_2014.traveller_detail ADD INDEX paymentStatus(payment_status);
ALTER TABLE visiit_2014.traveller_detail ADD INDEX appriveStatys(approved_status);
ALTER TABLE visiit_2014.cron_job_exception DROP COLUMN failure_cause;
ALTER TABLE visiit_2014.cron_job_exception  ADD COLUMN failure_cause VARCHAR(500);
-- Please run this query before start customer site. if you needed.
update visiit_2014.package set is_valid = 1 where is_valid = 0;

SET GLOBAL max_allowed_packet=1073741824;
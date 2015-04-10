ALTER TABLE visiit_2014.traveller_detail ADD COLUMN final_amount INTEGER;

ALTER TABLE visiit_2014.customer_enquiry ADD COLUMN fk_traveller_id INTEGER AFTER enq_package,
 ADD CONSTRAINT FK_customer_enquiry_2 FOREIGN KEY FK_customer_enquiry_2 (fk_traveller_id)
    REFERENCES traveller_detail (traveller_id);
    
    
DROP TABLE IF EXISTS visiit_2014.cron_job_audit;
CREATE TABLE  visiit_2014.cron_job_audit (
  job_id int(11) NOT NULL AUTO_INCREMENT,
  job_name varchar(50) NOT NULL,
  started_on date DEFAULT NULL,
  ended_on date DEFAULT NULL,
  PRIMARY KEY (job_id),
  KEY mod_idx (job_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS visiit_2014.cron_job_exception;
CREATE TABLE  visiit_2014.cron_job_exception (
  exception_id int(11) NOT NULL AUTO_INCREMENT,
  job_id int(11),
  failure_cause VARCHAR(500),
  PRIMARY KEY (exception_id),
  KEY mod_idx (exception_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
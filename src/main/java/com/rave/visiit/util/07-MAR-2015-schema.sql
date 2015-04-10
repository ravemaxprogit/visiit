DROP TABLE IF EXISTS visiit_2014.module;
CREATE TABLE  visiit_2014.module (
  mod_id int(11) NOT NULL AUTO_INCREMENT,
  mod_code varchar(30) NOT NULL,
  mod_name varchar(50) NOT NULL,
  parent_id int(11) DEFAULT NULL,
  mod_description varchar(100) DEFAULT NULL,
  mod_is_active tinyint(1) DEFAULT '1',
  is_deleted tinyint(1) DEFAULT '0',
  PRIMARY KEY (mod_id),
  KEY mod_idx (mod_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO visiit_2014.module (mod_id,mod_code,mod_name,parent_id,mod_description) VALUES 
 (1,'MOD00001','Admin',NULL,'Sample Description'),
 (2,'MOD00002','User',NULL,'Sample Description'),
 (3,'MOD00003','Registration',1,'Sample Sub Description'),
 (4,'MOD00004','Department',1,'Sample Sub Description'),
 (5,'MOD00005','User Password',2,'Sample Sub Description')

INSERT INTO visiit_2014.user (user_seq_id,user_id,user_code,user_password,user_is_active,user_first_name,user_last_name,user_office_mail,user_mail,user_phone, user_extention_no,user_dept_id,user_dsg_id,user_doj,user_esi_no,user_pf_no,user_pan_no,user_passport_no,user_dob,user_created_by,user_created_on,user_modified_by,user_modified_on, is_deleted,user_pwd_exp_date) VALUES 
(1,'admin@gmail.com', 'U0001', '21232f297a57a5a743894a0e4a801fc3', 1, 'Admin','Adm','admin@gmail.com','admin@gmail.com','9632145','1234', 1, 1, '2015-02-02 00:00:00', 'ESI001', 'PF001', 'PAN001', 'PASS001', '2015-02-02 00:00:00','Admin', '2015-02-02 00:00:00', 'Adm','2015-02-02 00:00:00',0,'2020-03-25 00:00:00');

INSERT INTO visiit_2014.department (dept_id, dept_code, dept_name, dept_description, dept_is_active, is_deleted, dept_created_by, dept_created_on, dept_modified_by, dept_modified_on) VALUES 
(1,'DP00001','Account','Account HR Department',1,0,'Admin','2015-03-03 00:00:00','Admin','2015-03-04 00:00:00');

INSERT INTO visiit_2014.designation (dsg_id,dsg_code,dsg_name,dsg_level,dsg_description,dsg_is_active,dsg_created_on,dsg_modified_on,dsg_modified_by,dsg_created_by,is_deleted) VALUES 
 (1,'DSG00001','ADMIN',1,'DSG',1,'2015-03-06 18:38:10','2015-03-06 18:38:15','Admin','Admin',0);
 
 
 
ALTER TABLE visiit_2014.package DROP COLUMN pk_isactive;
ALTER TABLE visiit_2014.package ADD COLUMN pk_is_active Boolean default true;

ALTER TABLE visiit_2014.category DROP COLUMN cat_isactive;
ALTER TABLE visiit_2014.category ADD COLUMN cat_is_active Boolean default true;

DROP TABLE IF EXISTS visiit_2014.customer_enquiry;
CREATE TABLE visiit_2014.customer_enquiry (
  enq_seq_id INTEGER NOT NULL AUTO_INCREMENT,
  enq_customer_name VARCHAR(45) NOT NULL,
  enq_subject VARCHAR(100) NOT NULL,
  enq_message VARCHAR(500) NOT NULL,
  enq_email VARCHAR(45) NOT NULL,
  enq_mobile VARCHAR(15),
  enq_sumbited_date DATETIME NOT NULL,
  enq_created_on DATETIME,
  enq_updated_on DATETIME,
  enq_status VARCHAR(15) NOT NULL,
  enq_code VARCHAR(45),
  PRIMARY KEY(enq_seq_id),
  INDEX name(enq_customer_name),
  INDEX subject(enq_subject),
  INDEX message(enq_message),
  INDEX submitedDate(enq_sumbited_date),
  INDEX status(enq_status),
  INDEX code(enq_code),
  INDEX email(enq_email)
)
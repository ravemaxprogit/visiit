ALTER TABLE visiit_2014.category ADD UNIQUE (cat_seq_id);

DROP TABLE IF EXISTS visiit_2014.newsletter_subscriber;
CREATE TABLE  visiit_2014.newsletter_subscriber (
  sub_id int(11) NOT NULL AUTO_INCREMENT,
  subscriber_email varchar(30) NOT NULL,
  is_sent boolean default 0,
  subscribed_on DATETIME,
  sent_on DATETIME,
  sent_by varchar(50),
  subscribed_by varchar(50),
  PRIMARY KEY (sub_id),
  KEY sub_idx (sub_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS visiit_2014.news_letter;
CREATE TABLE  visiit_2014.news_letter (
  letter_id int(11) NOT NULL AUTO_INCREMENT,
  file_name varchar(30) NOT NULL,
  mime_type varchar(50) NOT NULL,
  data LONGBLOB DEFAULT NULL,
  uploaded_on date DEFAULT NULL,
  uploaded_by varchar(50) DEFAULT NULL,
  PRIMARY KEY (letter_id),
  KEY letter_idx (letter_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE visiit_2014.newsletter_subscriber ADD UNIQUE (subscriber_email);
ALTER TABLE visiit_2014.package_itinerary ADD COLUMN pki_active VARCHAR(50) NOT NULL;

update visiit_2014.package_itinerary set pki_active='true' where pki_active ='';
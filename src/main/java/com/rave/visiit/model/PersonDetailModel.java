package com.rave.visiit.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PersonDetail;
import com.rave.visiit.entity.TravellerDetail;

public class PersonDetailModel {

	private Integer id;
	
	private String salutation;
	
	private String firstname;
	
	private String lastname;
	
	private Integer age;
	
	
	

	public PersonDetailModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonDetailModel(PersonDetail personDetail) {
		super();
		this.setAge(personDetail.getAge());
		this.setFirstname(personDetail.getFirstname());
		this.setId(personDetail.getId());
		this.setLastname(personDetail.getLastname());
		this.setSalutation(personDetail.getSalutation());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

}

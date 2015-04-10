package com.rave.visiit.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rave.visiit.util.Constants;

@Entity
@Table(name = "person_detail", catalog = Constants.DB_CATELOG)
public class PersonDetail {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "fk_sal_id", nullable = false)
//	private Salutation salutationId;

	private String salutation;
	
	private String firstname;
	
	private String lastname;
	
	private Integer age;
	
	@ManyToOne
	@JoinColumn(name="fk_traveller_id")
	private TravellerDetail travellerDetail;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_pk_id")
	private Package pack;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public Salutation getSalutationId() {
//		return salutationId;
//	}
//
//	public void setSalutationId(Salutation salutationId) {
//		this.salutationId = salutationId;
//	}

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

	public TravellerDetail getTravellerDetail() {
		return travellerDetail;
	}

	public void setTravellerDetail(TravellerDetail travellerDetail) {
		this.travellerDetail = travellerDetail;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public Package getPack() {
		return pack;
	}

	public void setPack(Package pack) {
		this.pack = pack;
	}	
	
	
}

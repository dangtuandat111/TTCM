package com.thuctap.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "stores")
@Data
@JsonIgnoreProperties(value = {"export_bills"}, allowGetters = false) 
public class store extends BaseEntity {


	@Column(name = "name")
    private String name;
	
	@Column(name = "location")
    private String location;
	
	@Column(name = "phoneNumber")
    private String phoneNumber;
	

	@Column(name = "status")
    private String status;
	
	@OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<export_bill> export_bills;
	
	public store() {
		
	}
	public store(String name, String location, String phoneNumber, String status) {
		this.location = location;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}

	
}

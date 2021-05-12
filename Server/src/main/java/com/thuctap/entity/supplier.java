package com.thuctap.entity;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Entity
@Table(name = "supplier")
@Data
@JsonIgnoreProperties(value = {"import_bills"}, allowGetters = false) 
public class supplier extends BaseEntity {


	@Column(name = "name")
	private String name;

	@Column(name = "location")
	private String location;

	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(name = "status")
	private String status;
	
	@OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<import_bill> imort_bills;
	
	public supplier() {
		
	}

	public supplier(String name, String location, String phoneNumber, String status) {
		this.location = location;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}
}

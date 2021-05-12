package com.thuctap.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Entity
@Table(name = "products")
@JsonIgnoreProperties(value = {"export_bill_details", "import_bill_details"}, 
allowGetters = false)
@Data
public class product extends BaseEntity {


	@Column(name = "name")
	private String name;

	@Column(name = "status")
	private String status;

	@Column(name = "image")
	private String image;

	@Column(name = "description")
	private String description;

	@ManyToOne(optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	private category category;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<product_detail> product_detail;


	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<export_bill_detail> export_bill_details;
	
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<import_bill_detail> import_bill_details;
	
	public product() {
		
	}
	
	public product(String name, String status, String image, String description, category category) {
		this.name = name;
		this.description = description;
		this.status = status;
		this.image = image;
		this.category = category;
	}

	
	
	

}

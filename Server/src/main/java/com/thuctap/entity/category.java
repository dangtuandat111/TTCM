package com.thuctap.entity;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Entity
@Data
@Table(name = "categories")
@JsonIgnoreProperties(value = {"products"}, 
allowGetters = false)
public class category extends BaseEntity {


	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<product> products;
	
	public category() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<product> getProducts() {
		return products;
	}

	public void setProducts(Set<product> products) {
		this.products = products;
	}
	
	
}

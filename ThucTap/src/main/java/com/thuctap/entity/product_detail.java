package com.thuctap.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "product_details")
@JsonIgnoreProperties(value = {"product"}, 
allowGetters = false)
public class product_detail extends BaseEntity {
	

	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "expiry_date")
	private Date expriy_date;
	
	@Column(name = "cost")
	private float cost;
	
	@Column(name = "status")
	private String status;
	

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	private product product;
}

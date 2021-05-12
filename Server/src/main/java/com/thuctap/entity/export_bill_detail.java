package com.thuctap.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "export_bill_details")
@JsonIgnoreProperties(value = {"export_bill"}, 
allowGetters = false)
public class export_bill_detail {

	@EmbeddedId
	detail_key id;

	@ManyToOne( optional = false)
	@MapsId("productId")
	@JoinColumn(name = "product_id", nullable = false)
	private product product;

	@ManyToOne( optional = false)
	@MapsId("billId")
	@JoinColumn(name = "bill_id", nullable = false)
	private export_bill export_bill;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "cost")
	private float cost;

}

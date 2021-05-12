package com.thuctap.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "import_bill_details")
public class import_bill_detail {
	@EmbeddedId
	detail_key id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@MapsId("productId")
	@JoinColumn(name = "product_id", nullable = false)
	private product product;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@MapsId("billId")
	@JoinColumn(name = "bill_id", nullable = false)
	private import_bill import_bill;
	
	@Column(name = "quantity")
	private int quantity;

	@Column(name = "cost")
	private float cost;

}

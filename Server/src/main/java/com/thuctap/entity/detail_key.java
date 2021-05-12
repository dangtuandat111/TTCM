package com.thuctap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class detail_key implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "bill_id")
	int billId;

	@Column(name = "product_id")
	int productId;
}

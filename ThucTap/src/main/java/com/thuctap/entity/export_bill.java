package com.thuctap.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "export_bills")
public class export_bill extends BaseEntity {


	@Column(name = "status")
	private String status;

	@Column(name = "total")
	private float total;

	@Column(name = "created_date")
	private Date created_date;

	@OneToMany(mappedBy = "export_bill", cascade = CascadeType.ALL)
	private List<export_bill_detail> export_bill_details;

	@ManyToOne(optional = false)
	@JoinColumn(name = "store_id", nullable = false)
	private store store;
	
	public export_bill() {
		
	}
}

package com.thuctap.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "import_bills")
public class import_bill extends BaseEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "total")
	private float total;
	
	@Column(name = "created_date")
	private Date created_date;
	
	@OneToMany(mappedBy = "import_bill", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<import_bill_detail> import_bill_details;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "supplier_id", nullable = false)
	private supplier supplier;
}

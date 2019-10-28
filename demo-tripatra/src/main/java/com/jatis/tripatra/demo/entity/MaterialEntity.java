package com.jatis.tripatra.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jatis.tripatra.demo.constants.MaterialUnit;

@Entity
public class MaterialEntity {

	@Id
	private String code;
	private String name;
	private Double unitPrice;
	
	@Enumerated(EnumType.STRING)
	private MaterialUnit unit;
	private Double availableUnits;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;
	
	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Double getUnitPrice() {
		return unitPrice;
	}



	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}



	public MaterialUnit getUnit() {
		return unit;
	}



	public void setUnit(MaterialUnit unit) {
		this.unit = unit;
	}



	public Double getAvailableUnits() {
		return availableUnits;
	}



	public void setAvailableUnits(Double availableUnits) {
		this.availableUnits = availableUnits;
	}



	public Date getLastUpdate() {
		return lastUpdate;
	}



	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}



	@PrePersist
	@PreUpdate
	private void prePersist() {
		lastUpdate = new Date();
	}
}

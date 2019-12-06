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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jatis.tripatra.demo.constants.MaterialUnit;

@Entity
public class WarehouseEntity {
	private static final Logger log = LoggerFactory.getLogger(WarehouseEntity.class);
	@Id
	private String code;
	private String name;
	private String address;
	
	@Enumerated(EnumType.STRING)
	private MaterialUnit unit;
	private Double capacity;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public MaterialUnit getUnit() {
		return unit;
	}
	public void setUnit(MaterialUnit unit) {
		this.unit = unit;
	}
	public Double getCapacity() {
		return capacity;
	}
	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@PrePersist
	@PreUpdate
	public void updateLastUpdate() {
		this.lastUpdate = new Date();
		if (log.isDebugEnabled()) {
			log.debug("Last update sudah terupdate"+this.lastUpdate);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WarehouseEntity other = (WarehouseEntity) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
}

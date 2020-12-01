package com.example.demo.auditing;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AuditClass<T> {
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDay;
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date LastModifiedDate;
	@CreatedBy
	private String createdBy;
	@LastModifiedBy
	private String LastModifiedBy;


}

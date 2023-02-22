package com.example.currencyconvertion.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.springframework.lang.Nullable;

@Entity
@Table(name="audit_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuditInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long request_id;
	String status;
	String request;
	String Response;
	Timestamp create_ts;
	Timestamp update_ts;
}

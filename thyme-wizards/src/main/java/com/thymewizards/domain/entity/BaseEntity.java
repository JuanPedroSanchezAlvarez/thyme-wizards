package com.thymewizards.domain.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1698682020811464779L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Version
    private Long version;

}

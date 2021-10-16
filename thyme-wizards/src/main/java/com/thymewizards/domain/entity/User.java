package com.thymewizards.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	private static final long serialVersionUID = -3015906337554635972L;

	@Column(length = 25, name = "user_name")
	private String userName;

	@Column(length = 25, name = "first_name")
	private String firstName;

	@Column(length = 25, name = "last_name")
	private String lastName;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private LocalDate birthday;

	@Column(length = 50)
	private String email;

	@Column(length = 9, name = "phone_number")
	private String phoneNumber;

}

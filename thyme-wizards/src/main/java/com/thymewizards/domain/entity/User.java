package com.thymewizards.domain.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.thymewizards.util.Gender;
import com.thymewizards.util.UserRole;

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

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	private LocalDate birthday;

	@Column(length = 50)
	private String email;

	@Column(length = 9, name = "phone_number")
	private String phoneNumber;

	@ElementCollection(targetClass = UserRole.class)
	@Enumerated(value = EnumType.STRING)
	@CollectionTable(name = "user_roles")
	@Column(name = "role")
	private Set<UserRole> setOfRoles;

	private String password;

}

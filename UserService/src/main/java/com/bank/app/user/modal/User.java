package com.bank.app.user.modal;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userid_gen")
	@SequenceGenerator(name = "userid_gen", initialValue = 10000, allocationSize = 1, sequenceName = "userid_seq")
	@Column(name = "id", length = 5, nullable = false)
	@Max(value = 99999, message = "User Id can not be greter then 99999")
	private int userId;

	@NotNull(message = "Name can't be NULL")
	@Pattern(regexp = "[ a-zA-Z0-9_\\\\-]*", message = "Name can not have special characters")
	@Size(min = 3, max = 40, message = "Name can't be less then 3 characters")
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull(message = "Email Id can't be NULL")
	@Email(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Email format is not appropriate")
	@Column(name = "emailid", nullable = false)
	private String emailId;

	@NotNull(message = "Mobile number can't be NULL")
	@Digits(message = "", fraction = 0, integer = 10)
	@Size(min = 10, max = 10, message = "Mobile number should be of 10 digits")
	@Column(name = "mobno", nullable = false)
	private String mobNo;

	@Pattern(regexp = "^[0-9]{10}$", message = "Sec Mobile number is not valid")
	@Size(min = 10, max = 10, message = "Secondary mobile number should be of 10 digits")
	@Column(name = "secondmobno")
	private String secondMobNo;

	@NotNull(message = "dob can't be null")
	@Pattern(regexp = "^(([1-9])|([0][1-9])|([1-2][0-9])|([3][0-1]))\\-(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)\\-\\d{4}$", message = "Dob is not valid, should be in format of DD-MMM-YYYY")
	@Column(name = "dob")
	private String dob;

	public enum Gender {
		M("M"), F("F");

		private String value;

		Gender(String value) {
			this.value = value;
		}

		@JsonCreator
		public static Gender fromValue(String v) {
			for (Gender g : Gender.values()) {
				if (String.valueOf(g.value).equals(v)) {
					return g;
				}
			}
			return null;
		}
	}
	
	@NotNull(message = "Gender should be M or F")
	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	private Gender gender;

}

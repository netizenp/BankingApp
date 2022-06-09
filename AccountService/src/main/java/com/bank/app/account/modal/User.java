package com.bank.app.account.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class User {

	private int userId;

	private String name;
	private String emailId;
	private String mobNo;
	private String secondMobNo;
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

	private Gender gender;
}
package com.bank.app.account.modal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_gen")
	@SequenceGenerator(name = "account_gen", initialValue = 1000000000, allocationSize = 1, sequenceName = "account_seq")
	@Max(value = 9999999999L, message = "Account Id reached the max limit")
	@Column(name = "accountid", length = 10, nullable = false)
	private Long accountId;

	@Pattern(regexp = "[ a-zA-Z0-9_\\\\-]*", message = "Please enter branch name correctly")
	@Size(min = 3, max = 40, message = "Name can't be less then 3 characters")
	@NotNull(message = "Branch name can't be NULL")
	@Column(name = "branchname")
	private String branchName;

	public enum AccountType {
		S("S"), C("C");

		private String value;

		AccountType(String value) {
			this.value = value;
		}

		@JsonCreator
		public static AccountType fromValue(String v) {
			for (AccountType a : AccountType.values()) {
				if (String.valueOf(a.value).equals(v)) {
					return a;
				}
			}
			return null;
		}
	}

	@NotNull(message = "Account type should be S or C")
	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@Column(name = "accountbal", nullable = false)
	private int accountBal;

	@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "userId", nullable = false)
	private User user;

}
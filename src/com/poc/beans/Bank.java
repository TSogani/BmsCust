package com.poc.beans;

import java.util.List;

public class Bank {

	
	private String bank_Code;
	private String AbbreviatedBankName;
	private String BankName;
	private List bankoffices;
	

	
	public String getBank_Code() {
		return bank_Code;
	}



	public void setBank_Code(String bank_Code) {
		this.bank_Code = bank_Code;
	}



	public String getAbbreviatedBankName() {
		return AbbreviatedBankName;
	}



	public void setAbbreviatedBankName(String abbreviatedBankName) {
		AbbreviatedBankName = abbreviatedBankName;
	}



	public String getBankName() {
		return BankName;
	}



	public void setBankName(String bankName) {
		BankName = bankName;
	}



	public List getBankoffices() {
		return bankoffices;
	}



	public void setBankoffices(List bankoffices) {
		this.bankoffices = bankoffices;
	}



	@Override
	public String toString() {
		return "EISCDmapping [bank_Code=" + bank_Code
				+ ", AbbreviatedBankName=" + AbbreviatedBankName
				+ ", BankName=" + BankName + ", bankoffices=" + bankoffices
				+ "]";
	}





}

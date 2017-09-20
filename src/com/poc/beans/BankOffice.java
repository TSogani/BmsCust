package com.poc.beans;

public class BankOffice {


	private String BankOffices_SORTCODE;
	private String Address;
	private String ChapsCHAPSSterling_status;
	private String FPServiceOffice_status;
	
	
	public String getBankOffices_SORTCODE() {
		return BankOffices_SORTCODE;
	}
	public void setBankOffices_SORTCODE(String bankOffices_SORTCODE) {
		BankOffices_SORTCODE = bankOffices_SORTCODE;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getChapsCHAPSSterling_status() {
		return ChapsCHAPSSterling_status;
	}
	public void setChapsCHAPSSterling_status(String chapsCHAPSSterling_status) {
		ChapsCHAPSSterling_status = chapsCHAPSSterling_status;
	}
	public String getFPServiceOffice_status() {
		return FPServiceOffice_status;
	}
	public void setFPServiceOffice_status(String fPServiceOffice_status) {
		FPServiceOffice_status = fPServiceOffice_status;
	}
	@Override
	public String toString() {
		return "BankOffice [BankOffices_SORTCODE=" + BankOffices_SORTCODE
				+ ", Address=" + Address + ", ChapsCHAPSSterling_status="
				+ ChapsCHAPSSterling_status + ", FPServiceOffice_status="
				+ FPServiceOffice_status + "]";
	}

	
	

}

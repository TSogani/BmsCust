package com.poc.handler;

import java.util.ArrayList;
import java.util.Iterator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.poc.beans.BankOffice;
import com.poc.beans.Bank;

public class NewHandlar extends DefaultHandler {

	ArrayList<BankOffice> bankOffices;
	Bank eiscDmapping;
	private String temp;
	ArrayList<Bank> fullList;
	public boolean fpsServiceOffice = false;
	BankOffice bankoffice;
	int officeCount;

	public NewHandlar() {
		fullList = new ArrayList<Bank>();
	}

	public ArrayList<Bank> getFullList() {
		return fullList;
	}

	public Bank getEiscDmapping() {
		return eiscDmapping;
	}
	public void setEiscDmapping(Bank eiscDmapping) {
		this.eiscDmapping = eiscDmapping;
	}
	
	
	/*
	 * 
	* When the parser encounters plain text (not XML elements),
	* it calls(this method, which accumulates them in a string buffer
	*/
	public void characters(char[] buffer, int start, int length) {
	    temp = new String(buffer, start, length);
	}
	
	
	/*
	* Every time the parser encounters the beginning of a new element,
	* it calls this method, which resets the string buffer
	*/ 
	public void startElement(String uri, String localName,
	           String qName, Attributes attributes) throws SAXException {
	    temp = "";
	
	    
	    if (qName.equalsIgnoreCase("Bank")) {
	    	eiscDmapping = new Bank();
	    	eiscDmapping.setBank_Code(attributes.getValue("BankCode"));
	    	bankOffices = new ArrayList<BankOffice>();
	    }
	    else if (qName.equalsIgnoreCase("BankOffices")) {
	    	officeCount++;
	    	// create list for multiple BankOffices. 
	    	
	    	bankoffice = new BankOffice();
	    	//System.out.println(attributes.getValue("SortCode"));
	    	bankoffice.setBankOffices_SORTCODE(attributes.getValue("SortCode"));
		}
	    else if(qName.equalsIgnoreCase("FPServiceOffice")){
	    	fpsServiceOffice = true;
	    }
	}
	
	/*
	* When the parser encounters the end of an element, it calls this method
	*/
	public void endElement(String uri, String localName, String qName)
	           throws SAXException {
	
		
		
	    if (qName.equalsIgnoreCase("Bank")) {
	    	
	    	eiscDmapping.setBankoffices(bankOffices);
	    	//System.out.println("total no if offices : "+bankOffices.size());
	    	fullList.add(eiscDmapping);
	    	//System.out.println("size od FullList : "+fullList);
	    	bankOffices = null;
	    	eiscDmapping = null;
	    }else if (qName.equalsIgnoreCase("AbbreviatedBankName")) {
	    	eiscDmapping.setAbbreviatedBankName(temp);
	    } else if (qName.equalsIgnoreCase("BankName")) {
	    	eiscDmapping.setBankName(temp);
	    }else if(qName.equalsIgnoreCase("BankOffices")){
	    	officeCount++;
	    	bankOffices.add(bankoffice);
	    	bankoffice = null;
	    }
	    else if(qName.equalsIgnoreCase("Status")){
			if(fpsServiceOffice == true){
				//System.out.println("VAlue of status"+temp);
				bankoffice.setFPServiceOffice_status(temp);
				fpsServiceOffice = false;
			}
	    }
	    if(qName.equalsIgnoreCase("Data")){
	    	//System.out.println("Total number of Bnaks :"+fullList.size());
	    	//System.out.println("Total no count is"+getEiscDmapping().getBankoffices().size());
	    	if(fullList.contains(eiscDmapping)){
	   		
	    	}
	    	Iterator<Bank> iterator = fullList.iterator();
	    	int totalOfficeSize=0;
	    /*	while(iterator.hasNext()) {
	            Bank element = (Bank)iterator.next();
	            totalOfficeSize = totalOfficeSize+element.getBankoffices().size();
	            //System.out.print("Bank size" + element.getBankoffices().size());
	            
	         }
	    	System.out.println("---"+totalOfficeSize);*/
	    }
	}
}

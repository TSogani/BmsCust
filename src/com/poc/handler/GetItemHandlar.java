package com.poc.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GetItemHandlar extends DefaultHandler{
	int noOfItem;
	int noOfBankOffice;
	public GetItemHandlar() {
	}

	
	
	@Override
	public void startDocument() throws SAXException {
		noOfItem=0;

	}



	public int getNoOfItem() {
		return noOfItem;
	}


	public int noOfBankOffice() {
		return noOfItem;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("Bank")) {
			noOfItem++;
		}
		if(qName.equals("BankOffices")){
			noOfBankOffice++;
		}
	}
	
}

package com.poc.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PrintPOHandlar extends DefaultHandler{

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Entry in document");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println();
		System.out.println("reading finish, Document close");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//System.out.print(""+"<"+qName+">");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//System.out.println("</"+qName+">");
	}

	@Override
	public void characters(char[] ch, int offset, int length) throws SAXException {
		String data = new String(ch, offset, length);
		//System.out.print(data);
		System.out.println("ch is :"+ch);
/*		for (char c : ch) {
			System.out.print(c);
		}
*/	}
	
	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		super.startPrefixMapping(prefix, uri);
	}
}

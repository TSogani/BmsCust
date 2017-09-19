package com.poc.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GetAttribute extends DefaultHandler{

	@Override
	public void startElement(String uri, String qName, String localName, Attributes attributes) throws SAXException {
		System.out.print("<"+localName+">");
		System.out.println(attributes.getQName(0)+" : "+attributes.getValue(0));
		//System.out.println(attributes.getLength());
	}

	@Override
	public void endElement(String uri, String qName, String localName) throws SAXException {
		System.out.print("</"+localName+">");
	}

	@Override
	public void characters(char[] ch, int offset, int length) throws SAXException {
		String data = new String(ch, offset, length);
		System.out.print(data);
	}
}

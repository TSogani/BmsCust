package com.poc.test;

import java.io.File;
import java.io.FileFilter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.poc.handler.MyErrorHandler;
import com.poc.handler.NewHandlar;

public class EISCDTest {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws SAXException 
	 */
	
	public static File lastFileModified(String dir) {
	    File fl = new File(dir);
	    File[] files = fl.listFiles(new FileFilter() {          
	        public boolean accept(File file) {
	            return file.isFile();
	        }
	    });
	    long lastMod = Long.MIN_VALUE;
	    File choice = null;
	    for (File file : files) {
	        if (file.lastModified() > lastMod) {
	            choice = file;
	            lastMod = file.lastModified();
	        }
	    }
	    return choice;
	}
	
	public static void main(String[] args) throws SAXException, Exception {

		File lastFileModified = lastFileModified("D:/FOLDER_TASK/STSEclipsPOC/EISCDUploader/src/com/poc/resource");
		if(lastFileModified != null){
		System.out.println("---"+lastFileModified);
		
		//File f2 = new File("D:/FOLDER_TASK/STSEclipsPOC/EISCDUploader/src/com/poc/resource/EISCD.xml");
		File f2 = new File(lastFileModified+"");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		System.out.println(factory);
		factory.setValidating(false);
		factory.setNamespaceAware(true);
		SAXParser parser = factory.newSAXParser();
		System.out.println(parser);
	
		XMLReader reader = parser.getXMLReader();
		reader.setErrorHandler(new MyErrorHandler());
		reader.parse(new InputSource(lastFileModified+""));
		
/*		factory.setValidating(false);
		factory.setNamespaceAware(true);

		XMLReader reader = parser.getXMLReader();
		reader.setErrorHandler(new ErrorHandler());
		reader.parse(new InputSource(lastFileModified+""));
*/		
		
		//PrintPOHandlar pHnadlar = new PrintPOHandlar();
		//parser.parse(f2, pHnadlar);
		
		//ExpectedHandlar expectedHandlar = new ExpectedHandlar();
		//parser.parse(f2, expectedHandlar);
		
		NewHandlar newHandlar = new NewHandlar();
		parser.parse(f2, newHandlar);
		
		//EISCDmapping dmapping =new EISCDmapping();
		//System.out.println(newHandlar.getEiscDmapping());
		System.out.println(newHandlar.getFullList().size());
		//GetItemHandlar iHandlar = new GetItemHandlar();
		//getQuantity qHandlar = new getQuantity();
		//GetAttribute a = new GetAttribute();
		//parser.parse(f2, a);
		//System.out.println(qHandlar.getQuantity());
		//parser.parse(f2, iHandlar);
		//System.out.println(iHandlar.getNoOfItem());
		//System.out.println(iHandlar.noOfBankOffice());
		}else{
		System.out.println("no files available");
		}
	}
}
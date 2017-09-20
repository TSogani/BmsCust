package com.poc.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.poc.beans.Bank;
import com.poc.beans.BankOffice;
import com.poc.handler.MyErrorHandler;
import com.poc.handler.NewHandlar;
import com.poc.service.EISCDService;

public class EISCDTest {
	
	static NewHandlar newHandlar;
	static String sourcePath ="D:/FOLDER_TASK/STSEclipsPOC/EISCDUploader/src/com/poc/resource";
	public static File lastFileModified(String dir) { // get lastest modify file
		File fl = new File(dir);
		File[] files = fl.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.isFile();
			}
		});
		long lastMod = Long.MIN_VALUE;
		File choice = null;
		for (File file : files) {
			// Check file validation with file name contain ISCD or end with .xml
			if (file.getName().contains("ISCD")
					&& file.getName().endsWith(".xml")) {
				if (file.lastModified() > lastMod) {
					choice = file;
					lastMod = file.lastModified();
				}
			}
		}
		return choice;
	}

	public static void moveFileToArchive(String sourcePath,String destPath){
		
		File source = new File(sourcePath);
		File dest = new File(destPath);
		File[] files = source.listFiles();
		if(files.length > 0 ){
		
			for (int i=0; i < files.length; i++){
				if(files[i].isFile())
				{	
					System.out.println(files[i].getName());
					files[i].renameTo(new File(dest + "/"+files[i].getName()));
					files[i].delete();
				}
			}
			System.out.println("Move file DTU to Archive folder");
		}
	}
	
	public static void validationAndLoad() {

		newHandlar = new NewHandlar();
		
		
		File lastFileModified = lastFileModified(sourcePath);
		
		// printing file name and size of file
		// Check if file not avilable or not at DTU location
		if (lastFileModified != null) {
			 System.out.println("---File name--"+lastFileModified.getName());
			if (lastFileModified.length() <= 0 && lastFileModified.isFile()) // 0 byte file check.
			{
				System.out.println(lastFileModified + "is 0 byte file");
			} else {
				File f2 = new File(lastFileModified + "");
				SAXParserFactory factory = SAXParserFactory.newInstance();
				factory.setValidating(false);
				factory.setNamespaceAware(true);
				SAXParser parser;
				try {
					parser = factory.newSAXParser();
					XMLReader reader = parser.getXMLReader();
					reader.setErrorHandler(new MyErrorHandler());
					reader.parse(new InputSource(lastFileModified + ""));
					parser.parse(f2, newHandlar);
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("no files available with ISCD and .xml format");
		}
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		validationAndLoad();
		moveFileToArchive(sourcePath,sourcePath+"/fp");	
		
		ArrayList<Bank> fullList = newHandlar.getFullList();
		EISCDService eiscdService = new EISCDService();
		boolean process = eiscdService.process(fullList);
		if(process){
			System.out.println("record insert successfully");
		}else{
			System.out.println("getting error while storing data to DB");
		}
/*
		
		String FILENAME = "D:/FOLDER_TASK/STSEclipsPOC/EISCDUploader/src/com/poc/resource/output/filename.txt";

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			//Call validation and load method for checking latest modified file and check 0 byte file
			validationAndLoad();
			moveFileToArchive(sourcePath,sourcePath+"/fp");	
			
			ArrayList<Bank> fullList = newHandlar.getFullList();
			EISCDService eiscdService = new EISCDService();
			boolean process = eiscdService.process(fullList);
			if(process){
				System.out.println("record insert successfully");
			}else{
				System.out.println("getting error while storing data to DB");
			}
			
			Iterator<Bank> iterator = fullList.iterator();
			
			// iterate and write into file 
			while (iterator.hasNext()) {
				Bank bank = iterator.next();
				// bw.write(bank.getBank_Code()+"  ");
				// bw.write(bank.getBankName())+"  ";
				Iterator<BankOffice> iterator2 = bank.getBankoffices().iterator();
				while (iterator2.hasNext()) {
					bw.write(bank.getBank_Code() + "  ");
					bw.write(bank.getBankName() + "  ");

					BankOffice boffice = iterator2.next();

					bw.write(boffice.getBankOffices_SORTCODE() + "\n");
					// bw.write(boffice.getAddress());
					// bw.write(boffice.getChapsCHAPSSterling_status());
					// bw.write(boffice.getFPServiceOffice_status());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();

			}

		}
*/
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("total taken time : "+totalTime/1000);
	}
}
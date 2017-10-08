package com.poc.test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.poc.beans.AUDITDto;
import com.poc.beans.Bank;
import com.poc.handler.MyErrorHandler;
import com.poc.handler.NewHandlar;
import com.poc.service.EISCDService;

public class EISCDTest {
	
	static EISCDService eiscdService;
	public static String exceptionMessage;
	public static String EISCDFILENAME;
	public void setEiscdService(EISCDService eiscdService) {
		this.eiscdService = eiscdService;
	}
 
	static NewHandlar newHandlar;
	static String sourcePath ="D:/FOLDER_TASK/STSEclipsPOC/EISCDUploader/src/com/poc/resource";
	
	
	public static void m1(){
		
		AUDITDto auditDto = new AUDITDto();
		auditDto.setCurrentDate(new Timestamp(System.currentTimeMillis()));
		auditDto.setFileName(EISCDFILENAME);
		auditDto.setJobName("EISCDUpload");
		
		if(validationAndLoad()){
			auditDto.setFileName(lastFileModified(sourcePath).getName());	
			moveFileToArchive(sourcePath,sourcePath+"/fp");	
			
			ArrayList<Bank> fullList = newHandlar.getFullList();
			//EISCDService eiscdService = new EISCDService();
			boolean process = eiscdService.process(fullList);
			if(process){
				
				System.out.println("record insert successfully");
				exceptionMessage = "SUCCESS : record insert successfully ";
				auditDto.setStatus(exceptionMessage);
				eiscdService.updteAUDIT(auditDto);
			}else{
				System.out.println("getting error while storing data to DB");
				exceptionMessage = "FAIL : Getting error while storing data to DB "+exceptionMessage;
				auditDto.setStatus(exceptionMessage);
				eiscdService.updteAUDIT(auditDto);
			}
		}else{
			auditDto.setStatus("FAIL : "+exceptionMessage);
			eiscdService.updteAUDIT(auditDto);
		}
	}
	
	public static boolean validationAndLoad() {

		newHandlar = new NewHandlar();
		
		
		File lastFileModified = lastFileModified(sourcePath);
		
		// printing file name and size of file
		// Check if file not avilable or not at DTU location
		if (lastFileModified != null) {
			EISCDFILENAME=lastFileModified.toString();
			
			 System.out.println("---File name--"+lastFileModified.getName());
			if (lastFileModified.length() <= 0 && lastFileModified.isFile()) // 0 byte file check.
			{
				exceptionMessage = lastFileModified.getName() + " is 0 byte file";
				System.out.println(lastFileModified + " is 0 byte file");
				return false;
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
					exceptionMessage = e.getMessage();
					e.printStackTrace();
					return false;
				} catch (SAXException e) {
					exceptionMessage = e.getMessage();
					e.printStackTrace();
					return false;
				} catch (IOException e) {
					exceptionMessage = e.getMessage();
					e.printStackTrace();
					return false;
				}catch(Exception e){
					exceptionMessage = e.getMessage();
					e.printStackTrace();
					return false;
				}
			}
		} else {
			
			System.out.println("no files available with ISCD and .xml format");
			exceptionMessage = "FAILED : FILE is Not Available with ISCD and .xml format At DTU location. !!";
			return false;
		}
		
		return true;
	}
	
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
		/*@Test
		public void insetEISCDMapping(){
			EISCDDao dao= new EISCDDao();
			List<Bank> bankList= new ArrayList<>();
			Bank bank1= new Bank();
			bank1.set();
			
			//bank2
			dao.insetEISCDMapping(bankList);
			
			
			
			// object method call 
			// list parameter means data 
		}
		*/
	
}
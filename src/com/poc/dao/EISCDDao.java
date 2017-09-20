package com.poc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.StringUtils;

import com.poc.beans.Bank;
import com.poc.beans.BankOffice;

public class EISCDDao {

	public boolean loadEISCD(List<?> eiscd){
		int i = 0;
		String sqlQuery = "insert into TO_PMR_EISCD_MAPPING values(?,?,?)";
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","spring","spring");
			
			PreparedStatement prepareStatement = connection.prepareStatement(sqlQuery);
			System.out.println("eiscd length"+eiscd.size());
			Iterator<Bank> iterator = (Iterator<Bank>) eiscd.iterator();
			while(iterator.hasNext()){
				Bank bank = iterator.next();
				
				 Iterator<BankOffice> iterator2 = bank.getBankoffices().iterator();
				 System.out.println("Bank offices size"+bank.getBankoffices().size()+": "+i);i++;
				 while(iterator2.hasNext()){
					 BankOffice bankOffice = iterator2.next();
						prepareStatement.setInt(1,Integer.parseInt(bank.getBank_Code()));
						String bankName = bank.getBankName();
						//String substring = bankName.substring(0, 34);
						//bankName = StringUtils.left(bankName, 35);
						prepareStatement.setString(2,"dfdsafds");
						prepareStatement.setInt(3, Integer.parseInt(bankOffice.getBankOffices_SORTCODE()));
						prepareStatement.addBatch();
				 }
			}
			int[] executeBatch = prepareStatement.executeBatch();
			System.out.println("total no of record inserted :"+executeBatch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}

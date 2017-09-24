package com.poc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.annotation.Transactional;

import com.poc.beans.Bank;
import com.poc.beans.BankOffice;

public class EISCDDao {

	
	private final String QUERY_FOR_INSERT = "insert into TO_PMR_EISCD_MAPPING values(?,?,?,?,?)";
	private final String QUEEY_FOR_UPDATE_ISACTIVE = "update TO_PMR_EISCD_MAPPING SET ISACTIVE='F' ";
	private final String QUERY_FOR_DELETE_OLD_EISCD_RECORD = "delete from TO_PMR_EISCD_MAPPING where ISACTIVE='F'";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional
	public boolean insetEISCDMapping(final List<Bank> eiscd){
		
				Iterator<Bank> iterator = (Iterator<Bank>) eiscd.iterator();
				while(iterator.hasNext()){
					final Bank bank = iterator.next();
					
				jdbcTemplate.batchUpdate(QUERY_FOR_INSERT, new BatchPreparedStatementSetter() {			 
				
				@Override
				public void setValues(PreparedStatement prepareStatement, int i) throws SQLException {
				
//					Iterator<BankOffice> iterator2 = bank.getBankoffices().iterator();
					BankOffice bankOffice =(BankOffice) eiscd.iterator().next().getBankoffices().get(i);
//					 System.out.println("Bank offices size"+bank.getBankoffices().size()+": "+i);i++;
				
/*					 while(iterator2.hasNext()){
						 BankOffice bankOffice = iterator2.next();
*/ 
					prepareStatement.setInt(1,Integer.parseInt(bank.getBank_Code()));
							String bankName = bank.getBankName();
							//String substring = bankName.substring(0, 34);
							//bankName = StringUtils.left(bankName, 35);
							prepareStatement.setString(2,"dfdsafds");
							prepareStatement.setInt(3, Integer.parseInt(bankOffice.getBankOffices_SORTCODE()));
							prepareStatement.setString(4, "EISCD.xml");
					 }
				@Override
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return  eiscd.iterator().next().getBankoffices().size();
				}
				}
			
				
			
			
			
		);
				}
		return true;
	}
	
	
	
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
	

	/*@SuppressWarnings("unchecked")
	public boolean insertLoadData(List<?> eiscd){
		
		Object execute = jdbcTemplate.execute(QUERY_FOR_INSERT,);
		System.out.println("Type of execute : "+execute);
		return true;
	}*/
	
	static final public class PreparedStatementEiscdCreater implements PreparedStatementCreator{

		@Override
		public PreparedStatement createPreparedStatement(Connection con)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		
	} 
	static final public class PreparedStatementEISCDCallback implements PreparedStatementCallback {

		@Override
		public Object doInPreparedStatement(PreparedStatement ps)
				throws SQLException, DataAccessException {

		//	ps.setString(1, e)
			
			return null;
		}
		
		
	}
	
	public boolean getInsert(List<?> eiscd){

		try {

		Connection connection = jdbcTemplate.getDataSource().getConnection();
		connection.setAutoCommit(false);
		
		PreparedStatement prepareStatement2 = connection.prepareStatement(QUEEY_FOR_UPDATE_ISACTIVE);
		prepareStatement2.execute(QUERY_FOR_DELETE_OLD_EISCD_RECORD);
		PreparedStatement prepareStatement1 = connection.prepareStatement(QUEEY_FOR_UPDATE_ISACTIVE);
		prepareStatement1.execute(QUEEY_FOR_UPDATE_ISACTIVE);
		PreparedStatement prepareStatement = connection.prepareStatement(QUERY_FOR_INSERT);
		
			System.out.println("eiscd length"+eiscd.size());
			Iterator<Bank> iterator = (Iterator<Bank>) eiscd.iterator();
			while(iterator.hasNext()){
				Bank bank = iterator.next();
				
				 Iterator<BankOffice> iterator2 = bank.getBankoffices().iterator();
				 System.out.println("Bank offices size"+bank.getBankoffices().size());
				 while(iterator2.hasNext()){
					 BankOffice bankOffice = iterator2.next();
						prepareStatement.setInt(1,Integer.parseInt(bank.getBank_Code()));
						String bankName = bank.getBankName();
						//String substring = bankName.substring(0, 34);
						//bankName = StringUtils.left(bankName, 35);
						prepareStatement.setString(2,"dfdsafds");
						System.out.println("BANK OFFICE : "+bankOffice.getBankOffices_SORTCODE());
						prepareStatement.setInt(3, Integer.parseInt(bankOffice.getBankOffices_SORTCODE()));
						prepareStatement.setString(4, "EISCD.xml");
						prepareStatement.setString(5, "T");
						prepareStatement.addBatch();
				 }	
			}
			int[] executeBatch = prepareStatement.executeBatch();
			System.out.println("total no of record inserted :"+executeBatch.length);
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}




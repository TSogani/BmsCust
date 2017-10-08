package com.poc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.poc.beans.AUDITDto;
import com.poc.beans.Bank;
import com.poc.beans.BankOffice;
import com.poc.test.EISCDTest;

public class EISCDDao {

	
	private static final String QUERY_FOR_DELETE_OLD_EISCD_RECORD = "delete from TO_PMR_EISCD_MAPPING where ISDELETED='T'";
	private static final String QUERY_FOR_UPDATE_ISACTIVE_ISDELETED = "update TO_PMR_EISCD_MAPPING SET ISDELETED='T' where ISACTIVE='F' ";
	private static final String QUERY_FOR_UPDATE_ISACTIVE = "update TO_PMR_EISCD_MAPPING SET ISACTIVE='F' where iSACTIVE='T'";
	private static final String QUERY_FOR_INSERT = "insert into TO_PMR_EISCD_MAPPING values(?,?,?,?,?,?)";
	private static final String QUERY_FOR_AUDIT_TBL="INSERT INTO to_pmt_audit values(?,?,?,?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional
	/*public boolean insetEISCDMapping(final List<Bank> eiscd){
		
				Iterator<Bank> iterator = (Iterator<Bank>) eiscd.iterator();
				while(iterator.hasNext()){
					final Bank bank = iterator.next();
					
				jdbcTemplate.batchUpdate(QUERY_FOR_INSERT, new BatchPreparedStatementSetter() {			 
				
				@Override
				public void setValues(PreparedStatement prepareStatement, int i) throws SQLException {
				
//					Iterator<BankOffice> iterator2 = bank.getBankoffices().iterator();
					BankOffice bankOffice =(BankOffice) eiscd.iterator().next().getBankoffices().get(i);
//					 System.out.println("Bank offices size"+bank.getBankoffices().size()+": "+i);i++;
				
					 while(iterator2.hasNext()){
						 BankOffice bankOffice = iterator2.next();
 
					prepareStatement.setInt(1,Integer.parseInt(bank.getBank_Code()));
							String bankName = bank.getBankName();
							//String substring = bankName.substring(0, 34);
							//bankName = StringUtils.left(bankName, 35);
							prepareStatement.setString(2,"dfdsafds");
							prepareStatement.setInt(3, Integer.parseInt(bankOffice.getBankOffices_SORTCODE()));
							prepareStatement.setString(4, "EISCD.xml");
							prepareStatement.setString(5, "T");
							prepareStatement.setString(6, "F");
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
	
*/	
	
/*	public boolean loadEISCD(List<?> eiscd){
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
	
*/
	/*@SuppressWarnings("unchecked")
	public boolean insertLoadData(List<?> eiscd){
		
		Object execute = jdbcTemplate.execute(QUERY_FOR_INSERT,);
		System.out.println("Type of execute : "+execute);
		return true;
	}*/
	
	/*static final public class PreparedStatementEiscdCreater implements PreparedStatementCreator{

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
		
		
	}*/
	
	public boolean getInsert(List<?> eiscd){
		System.out.println("inside getInsert");
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		PreparedStatement prepareStatement1 = null;
		PreparedStatement prepareStatement2 = null;
		PreparedStatement prepareStatement3 = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
		
		
		Statement statement1 = connection.createStatement();
		int executeUpdate = statement1.executeUpdate(QUERY_FOR_DELETE_OLD_EISCD_RECORD);
		System.out.println("QUERY_FOR_DELETE_OLD_EISCD_RECORD : return :"+executeUpdate);
		

/*		PreparedStatement prepareStatement2 = connection.prepareStatement(QUERY_FOR_DELETE_OLD_EISCD_RECORD);
		prepareStatement2.execute(QUERY_FOR_UPDATE_ISACTIVE_ISDELETED);
*/		
		
		Statement statement2 = connection.createStatement();
		int executeUpdate1 = statement2.executeUpdate(QUERY_FOR_UPDATE_ISACTIVE_ISDELETED);
		System.out.println("QUERY_FOR_UPDATE_ISACTIVE_ISDELETED : return :"+executeUpdate1);
		
		/*PreparedStatement prepareStatement1 = connection.prepareStatement(QUERY_FOR_UPDATE_ISACTIVE_ISDELETED);
		prepareStatement1.execute();
		*/
		
		Statement statement3 = connection.createStatement();
		int executeUpdate2 = statement3.executeUpdate(QUERY_FOR_UPDATE_ISACTIVE);
		System.out.println("QUERY_FOR_UPDATE_ISACTIVE : return : "+executeUpdate2);
		
		/*PreparedStatement prepareStatement3 = connection.prepareStatement(QUERY_FOR_UPDATE_ISACTIVE);
		prepareStatement3.execute();*/
		
/*		prepareStatement2 = connection.prepareStatement(QUERY_FOR_UPDATE_ISACTIVE);
		prepareStatement2.execute(QUERY_FOR_DELETE_OLD_EISCD_RECORD);

		prepareStatement3 = connection.prepareStatement(QUERY_FOR_UPDATE_ISACTIVE);
		prepareStatement3.execute(QUERY_FOR_UPDATE_ISACTIVE_ISDELETED);

		prepareStatement1 = connection.prepareStatement(QUERY_FOR_UPDATE_ISACTIVE);
		prepareStatement1.execute(QUERY_FOR_UPDATE_ISACTIVE);
*/		
		prepareStatement = connection.prepareStatement(QUERY_FOR_INSERT);
		System.out.println("Inserting record");
//		QUERY_FOR_UPDATE_ISACTIVE_ISDELETED 

		System.out.println("eiscd length"+eiscd.size());
			Iterator<Bank> iterator = (Iterator<Bank>) eiscd.iterator();
			while(iterator.hasNext()){
				Bank bank = iterator.next();
				
				 Iterator<BankOffice> iterator2 = bank.getBankoffices().iterator();
				 System.out.println("In Bank element BankOffices element size is : "+bank.getBankoffices().size());
				 
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
						prepareStatement.setString(6, "F");
						prepareStatement.addBatch();
				 }	
			}
			int[] executeBatch = prepareStatement.executeBatch();
			System.out.println("total no of record inserted :"+executeBatch.length);
			connection.commit();
		}catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			EISCDTest.exceptionMessage = " \"Number format Execption\" : "+e.getMessage();
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//connection = null;
			//connection.isClosed();
			EISCDTest.exceptionMessage = e.getMessage();
			e.printStackTrace();
			return false;
		}finally{
			if (prepareStatement != null) {
				try {
					prepareStatement.close();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
			}
			if (prepareStatement != null) {
				try {
					prepareStatement.close();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
			}

			if (prepareStatement1 != null) {
				try {
					prepareStatement1.close();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
			}

			if (prepareStatement2 != null) {
				try {
					prepareStatement2.close();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
			}
			if (prepareStatement3 != null) {
				try {
					prepareStatement3.close();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
			}


			if(connection != null){
				try{
					connection.rollback();
					connection.close();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
			}
		}
		System.out.println("TEst return");
		return true;
	}
	
	
	public void updateAUDTBL(AUDITDto dto){
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(QUERY_FOR_AUDIT_TBL);
			prepareStatement.setString(1, dto.getJobName());
			prepareStatement.setTimestamp(2, dto.getCurrentDate());
			prepareStatement.setString(3, dto.getFileName());
			prepareStatement.setString(4,dto.getStatus());
			
			prepareStatement.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}




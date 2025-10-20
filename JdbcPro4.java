package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcPro4 {
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String pwd="2003";
	
	String sqlQuery1="insert into employee values('108','Rohit','Sharma',45000,'Mumbai')";
	
	void meth1() 
	{
		System.out.println("Inserting the data into a employee table");
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(DBurl, Uname, pwd);
			System.out.println("Connection Created");
			Statement stmt=con.createStatement();
			int rowCount=stmt.executeUpdate(sqlQuery1);
			if(rowCount==1)
				System.out.println("Data Updated");
			else
				System.out.println("Data Not Updated");
			} 
		
		catch ( java.sql.SQLIntegrityConstraintViolationException sicve) {
			System.out.println("Duplicate EMPID's are NOT allowed");
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) 
	{
		new JdbcPro4().meth1();	
		}

}

package com.pack1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class jdbcPro17 
{
  
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String Upwd="2003";
	
	String sqlQuery1="insert into mydata2 values(?,?)";
	String sqlQuery2="select file_data from mydata2 where id=?";
	
	void meth1()
	{
		System.out.println("\nImplenting Clob Interface ");
		
		try 
		{

			Class.forName(driver);
			Connection con=DriverManager.getConnection(DBurl, Uname, Upwd);
			PreparedStatement pstmt=con.prepareStatement(sqlQuery1);
			pstmt.setString(1,"101");
			
			FileReader filereader=new FileReader("C:\\sss\\ert.txt.txt");
			BufferedReader br=new BufferedReader(filereader);
			pstmt.setClob(2,br);
			int rowCount=pstmt.executeUpdate();
			if(rowCount==0)
			{
				throw new SQLException("Insertion operation failed no rows affected");
			
			}
			System.out.println("Text file inserted as CLOB successfully");
			
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void meth2()
	{
		System.out.println("Reteriving Data from database");
		try {
		
			Connection con=DriverManager.getConnection(DBurl, Uname, Upwd);
			PreparedStatement pstmt2=con.prepareStatement(sqlQuery2);
			pstmt2.setString(1,"101");
			ResultSet rs=pstmt2.executeQuery();
			if(rs.next())
			{
				Clob b=rs.getClob(1);
				Reader data=b.getCharacterStream();
				BufferedReader br=new BufferedReader(data);
				FileWriter fw=new FileWriter("C:\\sss\\ert2.txt.txt");
				String line;
				while((line=br.readLine())!=null)
		        {
					fw.write(line);
					fw.write("\n");
					
				}
				br.close();
				fw.close();
				System.out.println("Clob data Reterived");
				
			}
			else
			{
				throw new SQLException("Invalid Id");
			}
			}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		jdbcPro17 pro=new jdbcPro17();
		//pro.meth1();
		pro.meth2();
	}
	
}

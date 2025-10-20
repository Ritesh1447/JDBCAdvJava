package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcPro9 {
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String pwd="2003";
	
		void meth1()
	{
		System.out.println("Implementing Resultset Interface\n");
		
		try 
		{
			Class.forName(driver);
			Connection  con=DriverManager.getConnection(DBurl, Uname, pwd);
			//Statement stmt=con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			Statement stmt=con.createStatement(1003,1007);
			
			System.out.println("TYPE_FORWARD_ONLY : "+ResultSet.TYPE_FORWARD_ONLY);
			System.out.println("TYPE_SCROLL_INSENSITIVE : "+ResultSet.TYPE_SCROLL_INSENSITIVE);
			System.out.println("TYPE_SCROLL_SENSITIVE : "+ResultSet.TYPE_SCROLL_SENSITIVE);
			
			System.out.println("\nCONCUR_READ_ONLY : "+ResultSet.CONCUR_READ_ONLY);
			System.out.println("CONCUR_UPDATABLE : "+ResultSet.CONCUR_UPDATABLE);
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			}
	}
 public static void main(String[] args) {
	new jdbcPro9().meth1();
}
}

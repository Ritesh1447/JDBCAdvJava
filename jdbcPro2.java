package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcPro2 {
	
	
	
		String driver="oracle.jdbc.OracleDriver";
		String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
		String Uname="Ritu45";
		String pwd="2003";
		String sqlQuery="select *from employee";
		
		void reteriveData() {
			System.out.println("Using Statement Interface\n");
			try {
				Class.forName(driver);
				Connection con=DriverManager.getConnection(DBurl, Uname, pwd);
				System.out.println("Connection created\n");
				Statement stmt=con.createStatement();
				System.out.println("statement object created\n");
				ResultSet rs=stmt.executeQuery(sqlQuery);
				while (rs.next()) 
				{
					System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
				//	System.out.println(rs.getString("EID")+" "+rs.getString(2)+" "+rs.getShort(3)+" "+rs.getInt(4)+" "+rs.getString(5));
					//break;
				}
				con.close();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		
		
		}
public static void main(String[] args) {
	new jdbcPro2().reteriveData();
}
}

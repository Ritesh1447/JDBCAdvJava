package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcPro1 {
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String pwd="2003";
	
	
	void meth1() {
		System.out.println("Connection to the database");
		try {
			Class.forName(driver);//Loading the driver
			Connection con=DriverManager.getConnection(DBurl, Uname, pwd); //Establishing the connection
			System.out.println("Connection Created");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	public static void main(String[] args) {
		new JdbcPro1().meth1();
	}

}

package com.pack1;

import java.sql.Connection;

public class JdbcPro14 {
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String dbUpwd="2003";
	
	ConnectionPooling cp=new ConnectionPooling(DBurl, Uname, dbUpwd);
	
	void meth1()
	{
		System.out.println("Implementing Connection Pooling\n");
		cp.con_Initislization();
		
		System.out.println("\n######User-1#######");
		Connection con1=cp.con_Acquisition();
		System.out.println("User1 acquired the Connection Object");
		System.out.println("Connection pool Size : "+cp.v.size());
	
		System.out.println("\n######User-2#######");
		Connection con2=cp.con_Acquisition();
		System.out.println("User1 acquired the Connection Object");
		System.out.println("Connection pool Size : "+cp.v.size());
	
		System.out.println("\n######User-3#######");
		Connection con3=cp.con_Acquisition();
		System.out.println("User1 acquired the Connection Object");
		System.out.println("Connection pool Size : "+cp.v.size());
	   
		cp.con_Return(con1);
		cp.con_Return(con2);
		cp.con_Return(con3);
		
	
	}
	public static void main(String[] args) {
		new JdbcPro14().meth1();
	}
}

package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcPro18 
{

	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String Upwd="2003";
	
	Scanner sc= new Scanner(System.in);
	
	void meth1()
	{
		System.out.println("Implementing Batch Processing");
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(DBurl, Uname,Upwd);
			Statement stmt=con.createStatement();
			System.out.println("How many queries you want to enter?");
			int no_queries=Integer.parseInt(sc.nextLine());
			
			
			for(int i=1;i<=no_queries;i++)
			{
				System.out.println("Enter your"+i+"query");
				stmt.addBatch(sc.nextLine());
			}
			int arr[]=stmt.executeBatch();
			
			for(int rowCount:arr)
			{
				System.out.println("=====>"+rowCount+"<=====");
			}
			stmt.clearBatch();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new JdbcPro18().meth1();
	}
}

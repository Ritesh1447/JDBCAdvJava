package com.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class jdbcPro12 {
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String Upwd="2003";
	Scanner sc=new Scanner(System.in);
	
	void meth1()
	{ 
		System.out.println("Implementing Callable Statement: procedure1\n");
	try
	{
		Class.forName(driver);
		Connection con =DriverManager.getConnection(DBurl, Uname, Upwd);
		CallableStatement cstmt=con.prepareCall("{call InsertEmpDetails (?,?,?,?,?)}");
		
		System.out.println("Enter Employee Id");
		String e_id=sc.nextLine();
		
		System.out.println("Enter Employee Name");
		String e_name=sc.nextLine();
		
		System.out.println("Enter Employee Desg");
		String e_desg=sc.nextLine();
		
		System.out.println("Enter Employee Basic Salary");
		int e_bsal=Integer.parseInt(sc.nextLine());
		
		float e_tsal=e_bsal+(0.35f*e_bsal)+(0.15f*e_bsal);
		
		cstmt.setString(1,e_id);
		cstmt.setString(2,e_name);
		cstmt.setString(3,e_desg);
		cstmt.setInt(4,e_bsal);
		cstmt.setFloat(5,e_tsal);
		
		cstmt.execute();
		
		System.out.println("Data Updated");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	void meth2()
	{
		System.out.println("Implementing Callable Statement :Procedure 2\n");
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(DBurl, Uname, Upwd);
			CallableStatement cstmt=con.prepareCall("{call ReteriveEmpData (?,?,?,?,?)}");
			
			System.out.println("Enter Employee Id");
			String e_id=sc.nextLine();

			cstmt.setString(1, e_id);
			cstmt.registerOutParameter(2,Types.VARCHAR);
			cstmt.registerOutParameter(3,Types.VARCHAR);
			cstmt.registerOutParameter(4,Types.NUMERIC);
			cstmt.registerOutParameter(5,Types.FLOAT);
			
			cstmt.execute();
			
			System.out.println("\n-----Employee-Details-----");
			System.out.println("EmpId : "+e_id);
			System.out.println("Emp Name : "+cstmt.getString(2));
			System.out.println("Emp Desg : "+cstmt.getString(3));
			System.out.println("Emp Basic salary : "+cstmt.getInt(4));
			System.out.println("Emp Total Salary : "+cstmt.getFloat(5));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void meth3()
	{
		System.out.println("Implementing Callable Statement :Function\n");
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(DBurl, Uname, Upwd);
			CallableStatement cstmt=con.prepareCall("{call ?:=ReteriveTotalsal(?)}");
			
			System.out.println("Enter Employee Id");
			String e_id=sc.nextLine();
		
			cstmt.setString(2,e_id);
			cstmt.registerOutParameter(1,Types.FLOAT);
			
			cstmt.execute();
			
			System.out.println("\n-----Employee-Details-----");
			System.out.println("EmpId : "+e_id);
			System.out.println("Emp Total Salary : "+cstmt.getFloat(1));
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
   public static void main(String[] args) {
	//new jdbcPro12().meth1();
	 //  new jdbcPro12().meth2();
	   new jdbcPro12().meth3();
}
}

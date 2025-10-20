package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcPro5 {
String driver="oracle.jdbc.OracleDriver";
String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
String Uname="Ritu45";
String pwd="2003";
Scanner sc= new Scanner(System.in);
String sqlQuery1="select * from ";
String sqlQuery2="insert into employee values('108','Rohit','Sharma',45000,'Mumbai')";
String  sqlQuery3="select * from employee where eid=101" ; 
String sqlQuery4="update employee set esal=50000 where eid='101'";
String sqlQuery5="delete from employee where eid=110";

Connection connect() 
{  
	Connection con=null;
	try {
		Class.forName(driver);
	    con=DriverManager.getConnection(DBurl, Uname, pwd);
		System.out.println("Connection Created");
	
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return con;
	}
   void meth1() {
	   System.out.println("Reteriving the data from the table ");
	   System.out.println("Enter the table name");
	   String tname=sc.nextLine();
	   Connection con=connect();
	   try {
		  Statement stmt=con.createStatement();
		 ResultSet rs= stmt.executeQuery(sqlQuery1.concat(tname));
		  while(rs.next()) {
			  System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
			  
		  }
	}
	   catch (Exception e)
	   {
		e.printStackTrace();
	}
	  System.out.println("\nData reterived from "+tname+" table");
	   
	   }
   void meth2()
   {
	   System.out.println("Inserting  the data into the table ");  
	   
	   System.out.println("Enter EmpId");
	   String eid=sc.nextLine(); 
	   System.out.println("Enter Emp FirstName");
	   String efname=sc.nextLine();
	   System.out.println("Enter Emp LastName");
	   String elname=sc.nextLine();
	   System.out.println("Enter Emp salary");
	   int esal=Integer.parseInt(sc.nextLine());
	   System.out.println("Enter Emp Address");
	   String eaddress=sc.nextLine();
	   
	   Connection con=connect();
	   try 
	   {
		Statement stmt=con.createStatement();
		int rowCount=stmt.executeUpdate("insert into employee values('"+eid+"','"+efname+"','"+elname+"',"+esal+",'"+eaddress+"')");
		if(rowCount==1)
		{
			System.out.println("Data Updated");
			System.out.println("Do you want to view the data (Yes/No)");
			if(sc.nextLine().toLowerCase().equals("yes"))
			meth1();
			else
				System.exit(0);
		}
		} 
	   catch ( java.sql.SQLIntegrityConstraintViolationException sicve)
	   {
			System.out.println("Duplicate EMPID's are NOT allowed");
			
		}
	   catch (Exception e)
	   {
		   e.printStackTrace();
	}
  }
   void meth3()
   {
	  System.out.println("Reterving specific data from the table");
	  
	  System.out.println("Enter Employee Id");
	  String empId=sc.nextLine();
	  
	  Connection con=connect();
	  try 
	  {
		  Statement stmt=con.createStatement();
		  ResultSet rs=stmt.executeQuery("select * from employee where eid='"+empId+"' ");
		if(rs.next())
			 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
		else
			System.out.println("There is No record with "+empId);
	} 
	  catch (Exception e)
	  {
		e.printStackTrace();
		}
	  System.out.println("\nRecord Reterived");
	  }
   void meth4()
   {
	   System.out.println("Updating specific data in the Employee table\n");
	   
	   System.out.println("Enter Employee Id");
	   String empId=sc.nextLine();
	   
	   System.out.println("Enter the salary which you want to Update");
	   int empSal=Integer.parseInt(sc.nextLine());
	   
	   Connection con=connect();
	   try 
	   {
		   Statement stmt=con.createStatement();
		   int rowcount=stmt.executeUpdate("update employee set esal="+empSal+" where eid='"+empId+"'");
		   if(rowcount==1)
			   System.out.println("Data Updated");
		   else
			   System.out.println("Data NOT Updated");
	} 
	   catch (Exception e) 
	   {
		e.printStackTrace();
	}
	   
   }
   void meth5() 
   {
	   System.out.println("Deleting specific data from the Employee table\n");
	   
	   System.out.println("Enter Employee Id");
	   String empId=sc.nextLine();
	   
	   Connection con=connect();
	   try 
	   {
		   Statement stmt=con.createStatement();
		   int rowcount=stmt.executeUpdate("delete from employee where eid='"+empId+"'");
		   if(rowcount==1)
			   System.out.println("Data Deleted");
		   else
			   System.out.println("Data NOT Deleted");
	} catch (Exception e) 
	   {
		e.printStackTrace();
	  }
   }
   public static void main(String[] args) {
	   jdbcPro5 obj=new jdbcPro5();
	  // obj.meth1();
	   obj.meth2();
	  // obj.meth3();
	  // obj.meth4();
	  // obj.meth5();
	   
}
}
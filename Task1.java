package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class Task1
{
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String pwd="2003";
	static Scanner sc=new Scanner(System.in);
	
	Connection connect(){
		Connection con=null;
		try 
		{ 
			Class.forName(driver);
			  con=DriverManager.getConnection(DBurl, Uname, pwd);
				//System.out.println("Connection Created");
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return con;
		
	}
	
	void EmployeeRegisteration()
	{
		Connection con=connect();
		
		try
		{
			PreparedStatement pstmt=con.prepareStatement("insert into EmployeeRegistartion values(?,?,?,?,?,?,?)");
			
			System.out.println("Please Enter Employee Details");
			System.out.println("Enter Employeee Name");
			String ename=sc.next();
			System.out.println();
			
			System.out.println("Enter  Password");
			String password=sc.next();
			
			
			System.out.println("Enter First Name");
			String fname=sc.next();
			System.out.println();
			
			System.out.println("Enter Last Name");
			String lname=sc.next();
			System.out.println();
			
			System.out.println("Enter Address");
			String address=sc.next();
			System.out.println();
			
			System.out.println("Enter Mail_Id");
			String mail=sc.next();
			System.out.println();
			
			System.out.println("Enter Phone Number");
			String phone=sc.next();
			
			pstmt.setString(1,ename);
			pstmt.setString(2,password);
			pstmt.setString(3,fname);
			pstmt.setString(4,lname);
			pstmt.setString(5,address);
			pstmt.setString(6,mail);
			pstmt.setString(7,phone);
			
			int rowCount=pstmt.executeUpdate();
	    	if(rowCount>0) 
	    		System.out.println("Registration Successful");
	    	    System.exit(0);
		}
	    	
	    	
		catch(SQLIntegrityConstraintViolationException i)
		{
			System.out.println("Registration NOT Successful");
			  System.exit(0);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void userLogin()
	{
		Connection con=connect();
		try
		{
			PreparedStatement pstmt2=con.prepareStatement("Select * from EmployeeRegistartion where ename=? AND password=?");
			
			System.out.println("Enter Employeee Name");
			String ename=sc.next();
			System.out.println();
		
			
			System.out.println("Enter  Password");
			String password=sc.next();
			
			pstmt2.setString(1,ename);
			pstmt2.setString(2,password);
			
			ResultSet rs=pstmt2.executeQuery();
			if(rs.next()) {
	    		System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
			}
	    	else {
	    		System.out.println("Input detail not matched");
			  System.exit(0);
	    	}
			  
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void viewProfile()
	{
		Connection con=connect();
		try
		{
			PreparedStatement pstmt3=con.prepareStatement("select * from EmployeeRegistartion" );
			
			ResultSet rs=pstmt3.executeQuery();
	    	while(rs.next())
	    		System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
	    	System.out.println();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void updateProfile()
	{
		Connection con=connect();
		try
		{
			PreparedStatement pstmt4=con.prepareStatement("update EmployeeRegistartion set mail_id=? where ename=?");
			System.out.println("Enter EmployeeName");
			String ename=sc.next();
			
			
		
			
			System.out.println("Enter Mail ");
			String mail=sc.next();
			
			  pstmt4.setString(2,ename);
		  	   
				  pstmt4.setString(1,mail);
				  int rowCount=pstmt4.executeUpdate();
			    	if(rowCount>0)
			    		System.out.println("Profile Updated");
			    	else
			    		System.out.println("Profile Not Updated");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) {
		Task1 t=new Task1();
		System.out.println("1.Employee Registration\n2.Employee Login\n3.Exit");
		int choice =sc.nextInt();
	
		while(true)
		{
			switch(choice) {
			case 1:
				t.EmployeeRegisteration();
				
				case 2:
					t.userLogin();
					System.out.println();
					  System.out.println("1.View Profile\n2.Update Profile\n3.Exit");
						int call =sc.nextInt();
					  while(true)
					  {
						  switch(call)
						  {
						  case 1:
							  t.viewProfile();
							  break;
						  case 2:
							  t.updateProfile();
							  break;
						  case 3:
							  System.exit(0);
							  break;
							  default:
								  break;
						  
					  }
					  break;
					   }
					
				case 3:
				System.exit(0);
				break;
				default:
					System.out.println("Invalid Input");
					System.exit(0);
					
					break;
			
			}
		}	
		
	}
}

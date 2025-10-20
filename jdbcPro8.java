package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class jdbcPro8 {
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
				System.out.println("Connection Created");
				
			
	}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return con;
		
	}
	
	void addPatient() {
		
		Connection con=connect();	
		try {
			PreparedStatement pstmt1=con.prepareStatement("insert into patient values(?,?,?,?)");
			
		System.out.println("Adding Patient Data");
    	
    	System.out.println("Enter Patient Id");
    	String pat_id=sc.nextLine();
    	
    	System.out.println("Enter Patient Name");
    	String pat_name=sc.nextLine();
    	
    	System.out.println("Enter Patient Age");
    	int pat_age=Integer.parseInt(sc.nextLine());
    	
    	System.out.println("Enter Patient Contact");
    	long pat_contact=Long.parseLong(sc.nextLine());
    	

		pstmt1.setString(1,pat_id);
    	pstmt1.setString(2,pat_name);
    	pstmt1.setInt(3, pat_age);
    	pstmt1.setLong(4,pat_contact);
    	
    	int rowCount=pstmt1.executeUpdate();
    	if(rowCount>0)
    		System.out.println("Patient Record Inserted");
    	else
    		System.out.println("Patient Record NOT Inserted");
		}
		 catch ( java.sql.SQLIntegrityConstraintViolationException sicve)
		   {
				System.out.println("Duplicate PatientId's are NOT allowed");
		   }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
    	
	}
	void reterivePatient() 
	
	{
		Connection con=connect();
		try {
		PreparedStatement pstmt2=con.prepareStatement("select * from patient");
		
		System.out.println("Reteriving the Patient table data");
    	ResultSet rs=pstmt2.executeQuery();
    	while(rs.next())
    		System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getLong(4));
    	System.out.println();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    	
	}
	void reterivePatienceId() 
	{
		Connection con=connect();
		try
		{
			PreparedStatement pstmt3=con.prepareStatement("select * from patient where pid=?");
			
			
		System.out.println("Reteriving patient data basing on 'pid'");
    	
    	System.out.println("Enter Patient Id");
    	String pat_id2=sc.nextLine();
    	pstmt3.setString(1, pat_id2);
    	
    	ResultSet rs2=pstmt3.executeQuery();
    	if(rs2.next())
    		System.out.println(rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getInt(3)+" "+rs2.getLong(4));
    	else
    		System.out.println("There is No data with patient_Id"+pat_id2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void updatePatient()
	{
		Connection con=connect();
		try
		{
			PreparedStatement pstmt4=con.prepareStatement("update patient set age=? where pid=?");
			
		  System.out.println("Updating Patient Data");
	    	
	       System.out.println("Enter Patient Id");
	  	   String pat_id3=sc.nextLine();
	  	   
	  	   System.out.println("Enter the Patient age ");
	  	   int pat_age2=Integer.parseInt(sc.nextLine());
	  	   
	  	   pstmt4.setString(2, pat_id3);
	  	   pstmt4.setInt(1, pat_age2);
	  	   
	     int rowCount2=pstmt4.executeUpdate( );
	     if(rowCount2>0)
				   System.out.println("Patient Record Updated\n");
			   else
				   System.out.println("Patient Record NOT Updated");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	void deletePatient()
	{
		Connection con=connect();
		try
		{ 
			PreparedStatement pstmt5=con.prepareStatement("delete from patient where pid=?");
			
		System.out.println("Deleting Patient Record");
    	
    	System.out.println("Enter patient Id");
    	String pat_id4=sc.nextLine();
    	pstmt5.setString(1, pat_id4);
    	
    	int rowCount3=pstmt5.executeUpdate();
    	if(rowCount3>0)
			   System.out.println("Patient Record Deleted");
		   else
			   System.out.println("Patient Record NOT Deleted");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
}
	public static void main(String[] args) {
		jdbcPro8 obj=new jdbcPro8();
		while(true)
		{
			System.out.println("\nWelcome to Patient Database");
		System.out.println("1.Add Patient\n2. View Patient data\n3. Reterive Patient data\n4. Update Patient data\n"+"5. Delete Patient data\n6.Exit");
	    System.out.println("Enter your Choice");
	    int Choice=Integer.parseInt(sc.nextLine());
	    switch(Choice) 
	    {
	    case 1:
	    	obj.addPatient();
	    	break;
	    case 2:
	    	obj.reterivePatient();
	    	break;
	    case 3:
	    	obj.reterivePatienceId();
	    	break;
	    case 4:
	    	obj.updatePatient();
	    	break;
	    case 5:
	    	obj.deletePatient();
	    	break;
	    case 6:
	    	System.out.println("Thank you see you soon :)");
	    	System.exit(0);
	    	break;
	    	default:System.out.println("\n<<<<Please choose valid Option>>>>\n");
	}
		}
		
	
	}
}
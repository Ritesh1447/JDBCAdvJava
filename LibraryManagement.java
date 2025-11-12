package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcPro6 {
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String pwd="2003";
   Scanner sc=new Scanner(System.in);
	String sqlQuery1="insert into LIBRABY values('105','vector','rdx2','playground',300)";
	String sqlQuery2="select * from LIBRABY";
	String sqlQuery3="delete from LIBRABY where bookId='110'";
	String sqlQuery4="select * from LIBRABY where bookId='109'";
	
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
	void meth1() 
	{
		Connection con=connect();
		try 
		{
			while(true)
			{
				System.out.println("\n---->Libraby<-----");
				System.out.println("press 1 to add book");
				System.out.println("press 2 to view book");
				System.out.println("press 3 to delete book");
				System.out.println("press 4 to  result");
				System.out.println("press 5 to exit");
				
				int Choice=Integer.parseInt(sc.nextLine());
				switch(Choice) 
				{
				case 1:
					System.out.println("Adding a Book");
					
					
					System.out.println("Enter bookId:");
					String id=sc.nextLine();
					System.out.println("Enter bookName");
					String bookname=sc.nextLine();
					System.out.println("Enter author Name");
					String author1=sc.nextLine();
					System.out.println("Enter Genere");
					String genere=sc.nextLine();
					System.out.println("Enter bookCost");
					int bookcost=Integer.parseInt(sc.nextLine());
					
					Statement stmt=con.createStatement();
		     		int rowCount=stmt.executeUpdate("insert into LIBRABY values('"+id+"','"+bookname+"','"+author1+"','"+genere+"',"+bookcost+")");
		     		if(rowCount==1)
						System.out.println("Book Added");
					else
						System.out.println("Book Not Added");
					
					break;
				case 2:
					System.out.println("Reterving the data\n");
					
					 Statement Stmt=con.createStatement();
					 ResultSet rs=Stmt.executeQuery(sqlQuery2);
					 while(rs.next()) 
					 {
						 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
						 
					 }
					break;
				case 3:
					System.out.println("Deleting a book");
					
					System.out.println("Enter BookId");
				    String bookid=sc.nextLine();
				    
				    Statement st=con.createStatement();
					   int rowcount=st.executeUpdate("delete from LIBRABY where bookId='"+bookid+"'");
					   if(rowcount==1)
						   System.out.println("Book Deleted");
					   else
						   System.out.println("Book NOT deleted");
					break;
				case 4:
					 System.out.println("Reterving the data\n");
					 System.out.println("Enter BookId");
					 String book_id=sc.nextLine();
					 
					 Statement s=con.createStatement();
					 ResultSet r=s.executeQuery("select * from LIBRABY where bookId='"+book_id+"'");
					 while(r.next()) 
					 {
						 System.out.println(r.getString(1)+" "+r.getString(2)+" "+r.getString(3)+" "+r.getString(4)+" "+r.getString(5));
						 
					 }
					
					break;
				case 5:
					System.out.println("Thank you see you soon :)");
			    	System.exit(0);
					break;
					
					default:
						System.out.println("Enter Valid Choice");
				}
			}
			
		}
		catch ( java.sql.SQLIntegrityConstraintViolationException sicve)
		   {
				System.out.println("Duplicate BOOKPID's are NOT allowed");
				
			}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new jdbcPro6().meth1();
	}
}

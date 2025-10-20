package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

public class jdbcPro13 
{
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String Upwd="2003";
	
	Connection connect()
	{
		Connection con=null;
		try 
		{ 
			Class.forName(driver);
			  con=DriverManager.getConnection(DBurl, Uname, Upwd);
				//System.out.println("Connection Created");
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return con;
		
	}
	void ticketBooking()
	{
		System.out.println("Implementing Transaction Management\n");
		
		String sqlQuery1="update  TrainseatAvailability SET available_seats=available_seats-1 WHERE train_id=? AND journey_date=? AND class = ? AND available_seats >0";
		
		String sqlQuery2="insert into BookingDetails values(?,?,?,?,?)";
		String sqlQuery3="select payment_status from customerpayment where customer_id=? ";
		String sqlQuery4="update BookingDetails set status='Confirmed' where booking_id=?";
		try
		{
			Connection con=connect();
			con.setAutoCommit(false);
			System.out.println("Is setAutoCommit() mode on ? ===>"+con.getAutoCommit());
			
			PreparedStatement pstmt1=con.prepareStatement(sqlQuery1);
			pstmt1.setString(1,"12345");
			pstmt1.setString(2,"2024-10-10");
			pstmt1.setString(3,"Sleeper");
			
			int rowCount= pstmt1.executeUpdate();
			if(rowCount==0)
			{
			 throw new RuntimeException("Seat NOT locked");
			}
			System.out.println("Seat locked");
			Savepoint sp1=con.setSavepoint();//Creating  a Savepoint for our program
			                                     // so that if there is any problem occured our program will be rolling back to this point	
			//con.commit();
			
			PreparedStatement pstmt2=con.prepareStatement(sqlQuery2);
			pstmt2.setString(1,"B101");
			pstmt2.setString(2, "12345");
			pstmt2.setString(3, "C123");
			pstmt2.setInt(4,1);
			pstmt2.setString(5,"Payment Pending");
			int rowCount2=pstmt2.executeUpdate();
			if(rowCount2==0)
			{
			 throw new RuntimeException("Booking Failed");
			}
			System.out.println("Booking Success\nWaiting for payment Confirmation!!!");
			
			PreparedStatement pstmt3=con.prepareStatement(sqlQuery3);
			pstmt3.setString(1,"C123");
			ResultSet rs=pstmt3.executeQuery();
			String status="Failed";
			if(rs.next())
			{
				status=rs.getString(1);
				if(status.equalsIgnoreCase("Success"))
				{
					System.out.println("Payment Done!!!");
					PreparedStatement pstmt4=con.prepareStatement(sqlQuery4);
					pstmt4.setString(1,"B101");
					int rowCount3= pstmt4.executeUpdate();
					if(rowCount3==0)
					{
						throw new RuntimeException("Transaction NOT Success");
					}
					System.out.println("Transaction Success");
					con.commit();
					System.out.println("All the savepoints are released");
				}
				}
				else
				{
					System.out.println("Payment Failed");
					System.out.println("Transaction Rolling back to the last savepoint");
					con.rollback(sp1);
				}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new jdbcPro13().ticketBooking();
	}
}

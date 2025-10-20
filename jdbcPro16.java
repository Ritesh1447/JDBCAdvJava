package com.pack1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class jdbcPro16 
{
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String Upwd="2003";
	
	String sqlQuery1="insert into mydata values(?,?)";
	String sqlQuery2="select pic_data from mydata where id=?";

	void meth1()
	{
		System.out.println("Implementing Blob Interface");
		
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(DBurl, Uname, Upwd);
			PreparedStatement pstmt=con.prepareStatement(sqlQuery1);
			pstmt.setString(1,"101");
			
			FileInputStream fis=new FileInputStream("C:\\sss\\pic11.png");
			pstmt.setBlob(2,fis,fis.available());
			
			int rowCount=pstmt.executeUpdate();
			if(rowCount>0)
				System.out.println("Image saved in the database");
			else
			System.out.println("Image NOT Saved!!!");
			
			PreparedStatement pstmt2=con.prepareStatement(sqlQuery2);
			pstmt2.setString(1,"101");
			ResultSet rs=pstmt2.executeQuery();
			if(rs.next())
			{
				Blob b=rs.getBlob(1);
				byte arr[]=b.getBytes(1,(int)b.length());
				
				FileOutputStream fos=new FileOutputStream("C:\\sss\\pic12.png");
				fos.write(arr);
				fos.close();
				System.out.println("Image Reterived & saved in ===> C:\\\\sss\\\\pic12.png ");
				
			}
			else
				System.out.println("Data is missing!!!!");
		
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new jdbcPro16().meth1();
		
	}
}

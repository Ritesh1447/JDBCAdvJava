package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.TreeSet;

public class jdbcPro3 {
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String pwd="2003";
	String SqlQuery1="select * from employee";
	
	
	
	void meth1() {
		try
		{
		   Class.forName(driver);
		   Connection con	=DriverManager.getConnection(DBurl, Uname, pwd);
		   System.out.println("Connection Created!!!");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(SqlQuery1);
		    TreeSet<Object> ts=new TreeSet<Object>();
			while(rs.next()) 
			{
				//System.out.println(rs.getString(1));
				ts.add(rs.getString(1));
			}
			//System.out.println("EmpIds in ascending order : "+ts);
			System.out.println("EmpIds in ascending order : ");
			for(Object o:ts)
				System.out.print(o+" ");
			
			System.out.println("\n\nEmpIds in descending order : ");
		   Iterator<Object> i =ts.descendingIterator();
			while(i.hasNext())
				System.out.print(i.next()+" ");
			con.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		
	}
	public static void main(String[] args) {
		new jdbcPro3().meth1();
	}

}

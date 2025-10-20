package com.pack1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class ConnectionPooling {
	String dbUrl;
	String dbUname;
	String dbUpwd;
	
	
	Vector<Connection> v=new Vector<Connection>();
	
	public ConnectionPooling(String dbUrl, String dbUname, String dbUpwd) {

		this.dbUrl = dbUrl;
		this.dbUname = dbUname;
		this.dbUpwd = dbUpwd;
		
	}
	void con_Initislization()
	{
		System.out.println("Creating '5' Connection Objects\n");
		System.out.println("Before "+v.size()+"\n");
		while(v.size()<5)
		{
			try
			{
				Connection con=DriverManager.getConnection(dbUrl, dbUname, dbUpwd);
			 v.addElement(con);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		for(Connection c:v)
			System.out.println(c);
		System.out.println("After : "+v.size());
	}
	Connection con_Acquisition()
	{
		Connection con=v.get(0);
		v.remove(0);
		return con;
	}
	void con_Return(Connection con)
	{
		System.out.println("\n Adding the connection object returned by the user");
		v.addElement(con);
		
		System.out.println("-------------");
		for(Connection c:v)
			System.out.println(c);
	}
	
	
	

}

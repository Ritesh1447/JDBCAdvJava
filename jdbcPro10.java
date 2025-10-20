package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcPro10 
{
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String pwd="2003";
	
	Connection connect()
	{
		Connection con=null;
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(DBurl, Uname, pwd);
			
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		}
		return con;
	}
    void meth1() 
    {
    	System.out.println("Implementing Scrollable Read-Only Resultset from Statment Interface");
        Connection con=connect();
        try
        {
           Statement stmt=con.createStatement(1004,1007);
           ResultSet rs=stmt.executeQuery("select * from employee");
           rs.afterLast();
           while(rs.previous())
        	   System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
           
           System.out.println();
            
            rs.beforeFirst();
            while(rs.next())
         	   System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
           
            rs.last();
            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
            
            System.out.println();
            
            rs.first();
            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
            
            System.out.println();
            
            rs.absolute(3);
           // while(rs.next())
            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
            
            System.out.println();
            
            rs.absolute(-2);
            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
            
            System.out.println();
            
            rs.relative(-1);
            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
            
             }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    
    void meth2()
    {
    	System.out.println("Implementing Scrollable UpdTatable ResultSet from Statement Interface\n");
    
    	Connection con=connect();
    	try 
    	{
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=stmt.executeQuery("select eid,efname,elname,esal from employee");
			String emp_id=null;
			while(rs.next())
			{
				 emp_id=rs.getString(1);
				if(emp_id.equals("103"))
				{
					System.out.println("Updating the sal of employee having empid as : "+emp_id);
					System.out.println("Prvious sal is :"+rs.getInt("esal"));
					rs.updateInt("esal", 70000);
					rs.updateRow();
					break;
			}
    	} 
			System.out.println("Data Updated");
			
			Statement stmt2=con.createStatement();
			ResultSet rs2=stmt2.executeQuery("select * from employee where eid='"+emp_id+"'");
		while(rs2.next())
			//if(rs2.last()) //It generates Exception bcoz of Non-Scrollable Resultset
				 System.out.println(rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getString(3)+" "+rs2.getString(4)+" "+rs2.getString(5));
				
    	}
    	catch (Exception e) 
    	{
		 e.printStackTrace();
		}
    }
    public static void main(String[] args) {
		//new jdbcPro10().meth1();
    	new jdbcPro10().meth2();
	}
}

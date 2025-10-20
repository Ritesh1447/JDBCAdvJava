package com.pack1;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class jdbcPro11 {
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String pwd="2003";
	
	void meth1()
	{
		System.out.println("Implementing JdbcRowSet Interface ");
		
		try 
		{
			RowSetFactory rsf=RowSetProvider.newFactory();
			JdbcRowSet jrs=rsf.createJdbcRowSet();
			
			jrs.setUrl(DBurl);
			jrs.setUsername(Uname);
			jrs.setPassword(pwd);
			jrs.setCommand("select * from employee");
			jrs.execute();
			
		//	jrs.close();//It generates java.sql.SQLRecoverableException: closed Resultset (becasuse jdbc Rowset is Connetcted)
			
		  //jrs.last();
		 //  System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getString(4)+" "+jrs.getString(5)+"\n");
			
			jrs.beforeFirst();
			while(jrs.next())
			 System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getString(4)+" "+jrs.getString(5));
				
		} 
		catch (Exception e)
		{
		 e.printStackTrace();
		}
	}
	void meth2()
	{
		System.out.println("Implementing JdbcRowSet Interface ");
		
		try
		{
			RowSetFactory rsf=RowSetProvider.newFactory();
			CachedRowSet crs=rsf.createCachedRowSet();
			
			crs.setUrl(DBurl);
			crs.setUsername(Uname);
			crs.setPassword(pwd);
			crs.setCommand("select * from employee");
			crs.execute();
			
		//	crs.close();//CachedRowset will work both in Connected and disconnected MODE
			
			while(crs.next())
			{
			String emp_id=crs.getString(1);
			if(emp_id.equals("102"))
			{
				crs.updateInt("esal", 30000);
				crs.updateRow();
			}
			}
			crs.acceptChanges();
			System.out.println("Data Updated");
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
	jdbcPro11	obj=new jdbcPro11();
    obj.meth1();
	//obj.meth2();
}

}

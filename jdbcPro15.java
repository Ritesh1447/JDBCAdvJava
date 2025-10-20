package com.pack1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class jdbcPro15 
{
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String Uname="Ritu45";
	String Upwd="2003";
	
	String sqlQuery1="select efname,elname from employee where eid=?";
	
	void meth1()
	{
		System.out.println("Implementing Metadata in JDBC\n");
		
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(DBurl, Uname, Upwd);
			
			DatabaseMetaData dmtdt=con.getMetaData();
			
			System.out.println("*******DatabaseMetaData********");
			System.out.println("getDatabaseProductName() : "+dmtdt.getDatabaseProductName());
			System.out.println("getDatabaseProductVersion() :"+dmtdt.getDatabaseProductVersion());
			System.out.println("getDriverName() : "+dmtdt.getDriverName());
			System.out.println("supportsStoredProcedures() : "+dmtdt.supportsStoredProcedures());
			
			PreparedStatement pstmt=con.prepareStatement(sqlQuery1);
			pstmt.setString(1,"101");
			ResultSet rs=pstmt.executeQuery();
			
			ParameterMetaData pstmtdt=pstmt.getParameterMetaData();
			System.out.println("\n*******ParameterMetaData*******");
			System.out.println("getParameterCount() : "+pstmtdt.getParameterCount());
			System.out.println("getParameterType() : "+pstmtdt.getParameterType(1));
			System.out.println("getParameterMode() : "+pstmtdt.getParameterMode(1));
			System.out.println("isNullable() : "+pstmtdt.isNullable(1));
			
			ResultSetMetaData rstmtdt=rs.getMetaData();
			System.out.println("\n*******ResultSetMetaData*******");
			System.out.println("getColumnCount() : "+rstmtdt.getColumnCount());
			System.out.println("getColumnName() : "+rstmtdt.getColumnName(1));
			System.out.println("getColumnDisplaySize() : "+rstmtdt.getColumnDisplaySize(1));
			System.out.println("isAutoIncrement() : "+rstmtdt.isAutoIncrement(1));
			
			RowSetFactory rsf=RowSetProvider.newFactory();
			CachedRowSet crs=rsf.createCachedRowSet();
			crs.setUrl(DBurl);
			crs.setUsername(Uname);
			crs.setPassword(Upwd);
			crs.setCommand("select eid,efname,elname from employee");
			crs.execute();
			
			RowSetMetaData rowsmtdat=(RowSetMetaData)crs.getMetaData();
			System.out.println("\n*******RowSetMetaData*******");
			System.out.println("getColumnCount() : "+rowsmtdat.getColumnCount());
			System.out.println("getColumnName() : "+rowsmtdat.getColumnName(1));
			System.out.println("getColumnType() : "+rowsmtdat.getColumnType(1));
			System.out.println("isAutoIncrement() : "+rowsmtdat.isAutoIncrement(1));
			
}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new jdbcPro15().meth1();
	}
}

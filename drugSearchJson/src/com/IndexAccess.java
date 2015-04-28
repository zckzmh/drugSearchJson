package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class IndexAccess {
	 public static String query(String key){
		 System.out.println(key);
		  String md5_id = null;  
		  PreparedStatement sta;
		    Connection conn;
		    ResultSet rs = null;
		   try{
			   Context ctx=new InitialContext();
			    ctx=(Context) ctx.lookup("java:comp/env");
			    DataSource ds=(DataSource) ctx.lookup("jdbc/drug");
			    conn=ds.getConnection();
		    if(conn!=null){
		    //System.out.println("数据库连接成功");
		    }
		    sta=conn.prepareStatement("select md5_id from drugs where producer=?");
		    sta.setString(1, key);
//		    System.out.print(key);
		    rs=sta.executeQuery();
		   
		   md5_id=rs.getString(1);
		    rs.close();
		    sta.close();
		    conn.close();
		    
		   }catch(Exception ex){
			   System.out.println("ERROR: " + ex.getMessage());
		   }
		   
		  return md5_id;
		  
	  }
}
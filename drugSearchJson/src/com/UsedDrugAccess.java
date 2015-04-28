package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UsedDrugAccess {
	public static void query(String key){  
		  PreparedStatement sta;
		    Connection conn;
		   try{
			   Context ctx=new InitialContext();
			    ctx=(Context) ctx.lookup("java:comp/env");
			    DataSource ds=(DataSource) ctx.lookup("jdbc/drug");
			    conn=ds.getConnection();
		    if(conn!=null){
		    //System.out.println("数据库连接成功");
		    }
		    sta=conn.prepareStatement("insert into used_drugs(name) values(?)");
		    sta.setString(1, key);
		    
		    //System.out.print(key);
		    sta.executeUpdate();
		   }catch(Exception ex){
			   System.out.println("ERROR: " + ex.getMessage());
		   }
		
	}
}

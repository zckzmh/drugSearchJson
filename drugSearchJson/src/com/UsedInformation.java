package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class UsedInformation {
	 public static ArrayList<HashMap<String, String>> query(){
	ArrayList<HashMap<String,String>> List=new ArrayList<HashMap<String,String>>();	  
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
	    sta=conn.prepareStatement("select * from md5_id order by count desc");
//	    System.out.print(key);
	    rs=sta.executeQuery();
	    while(rs.next()){
	    	HashMap<String, String> map = new HashMap<String , String>();
			map.put("md5_id" , rs.getString(1));
			
			List.add(map);
		  }
	    //System.out.println(count);
	    rs.close();
	    sta.close();
	    conn.close();
	    
	   }catch(Exception ex){
		   System.out.println("ERROR: " + ex.getMessage());
	   }
	   
	  return List;
	  
}
}

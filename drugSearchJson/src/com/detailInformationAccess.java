package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

public class detailInformationAccess {
	public static ArrayNode show(String key){
	 ObjectMapper mapper=new ObjectMapper();
	 //ObjectNode content_info=mapper.createObjectNode();
	 ArrayNode arrayNode=mapper.createArrayNode();
	  PreparedStatement sta;
	    Connection conn;
	    ResultSet rs = null;
		//System.out.println(key);
	   try{
		   Context ctx=new InitialContext();
		    ctx=(Context) ctx.lookup("java:comp/env");
		    DataSource ds=(DataSource) ctx.lookup("jdbc/drug");
		    conn=ds.getConnection();
	    if(conn!=null){
	    //System.out.println("数据库连接成功");
	    }
	    sta=conn.prepareStatement("select item,content from drug_items where drug_md5_id=?");
	    sta.setString(1, key);
//	    System.out.print(key);
	    rs=sta.executeQuery();
	    while(rs.next()){
	    	 ObjectNode info_information=mapper.createObjectNode();
	    	info_information.put("item" , rs.getString(1));
			info_information.put("content", rs.getString(2));
			arrayNode.add(info_information);
		  }
	    //System.out.println(count);
	    //content_info.put("arrayNode", arrayNode);
	    rs.close();
	    sta.close();
	    conn.close();
	    
	   }catch(Exception ex){
		   System.out.println("ERROR: " + ex.getMessage());
	   }
	   
	  return arrayNode;
}
}


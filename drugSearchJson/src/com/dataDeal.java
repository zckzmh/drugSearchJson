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

public class dataDeal {
  public static ArrayNode query(String key){
	  //ArrayList<HashMap<String,String>> List=new ArrayList<HashMap<String,String>>();	  
	  PreparedStatement sta;
	    Connection conn;
	    ResultSet rs = null;
	    ObjectMapper mapper = new ObjectMapper();
	    ArrayNode arrayNode=mapper.createArrayNode(); 
	    //ObjectNode content_info = mapper.createObjectNode();
	   try{
		   Context ctx=new InitialContext();
		    ctx=(Context) ctx.lookup("java:comp/env");
		    DataSource ds=(DataSource) ctx.lookup("jdbc/drug");
		    conn=ds.getConnection();
	    if(conn!=null){
	    //System.out.println("数据库连接成功");
	    }
	    sta=conn.prepareStatement("select name,product_name,symptom,md5_id,producer from drugs where name like ? or product_name like ? or py_name like ?");
	    sta.setString(1, '%'+key+'%');
	    sta.setString(2, '%'+key+'%');
	    sta.setString(3, '%'+key+'%');
//	    System.out.print(key);
	    rs=sta.executeQuery();
	    
	    while(rs.next()){
	    	ObjectNode info_information=mapper.createObjectNode();
	    	info_information.put("name" , rs.getString(1));
	    	info_information.put("product_name", rs.getString(2));
	    	info_information.put("symptom", rs.getString(3));
	    	info_information.put("md5_id", rs.getString(4));
	    	info_information.put("producer", rs.getString(5));
	    	arrayNode.add(info_information);
		  }
	    //content_info.put("arrayNode",arrayNode);
	   // System.out.println(count);
	    rs.close();
	    sta.close();
	    conn.close();
	    
	   }catch(Exception ex){
		   System.out.println("ERROR: " + ex.getMessage());
	   }
	   
	  return arrayNode;
	  
  }
}


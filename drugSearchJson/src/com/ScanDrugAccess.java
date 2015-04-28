package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class ScanDrugAccess {
	public static void insert(String key){
		ArrayList<HashMap<String, String>> List1 = new ArrayList<HashMap<String, String>>();
		PreparedStatement sta;
		PreparedStatement sta1;
		ResultSet rs;
		Connection conn = null;
		int count = 0;
		int sum = 0;
		try {
			Context ctx = new InitialContext();
			ctx = (Context) ctx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/drug");
			conn = ds.getConnection();
			if (conn != null) {
				System.out.println("数据库连接成功");
			}
			sta1 = conn.prepareStatement("select *from md5_id");
			rs = sta1.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString(1));
				HashMap<String, String> map = new HashMap<String, String>();
				//count = rs.getInt(2);
				map.put("md5_id", rs.getString(1));
				map.put("count", rs.getString(2));
				List1.add(map);
				sum++;
			}

			//System.out.println(List1.get(0).get("md5_id"));
			//System.out.println(key);
			for (int i = 0; i < sum; i++) {
				if (List1.get(i).get("md5_id").equals(key)==false) {
					sta = conn.prepareStatement("insert into md5_id(md5_id,count) values(?,?)");
							
					sta.setString(1, key);
					sta.setInt(2, 1);
					// System.out.print(key);
					sta.executeUpdate();
				}
			}
		}catch (Exception ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}
		try {
			for (int n = 0; n < sum; n++) {
                 
				if (List1.get(n).get("md5_id").equals(key)) {
					count=Integer.parseInt(List1.get(n).get("count"));
					++count;
				}
				
					sta = conn.prepareStatement("update md5_id set count=? where md5_id=?");
				
				sta.setInt(1, count);
				sta.setString(2, key);
				// System.out.print(key);
				sta.executeUpdate();
				//System.out.println(count);
			} 		
			}catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		 

	}
}


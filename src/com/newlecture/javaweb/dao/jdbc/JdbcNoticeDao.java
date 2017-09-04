package com.newlecture.javaweb.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.entity.Notice;
import com.newlecture.javaweb.entity.NoticeView;

public class JdbcNoticeDao implements NoticeDao{

	public List<NoticeView> getList(int page,String query) {
		
		List<NoticeView> list = null;
		int offset =(page-1) *10;
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
	    String sql = "SELECT * FROM NoticeView WHERE title like ? order by regDate DESC limit ?, 10";
	   
	    // Jdbc ����̹� �ε�
	    try {
	       Class.forName("com.mysql.jdbc.Driver");

	       // ���� / ����
	       Connection conn = DriverManager.getConnection(url, "sist", "cclass"); // dclass
	       // ����
	      /* Statement st = conn.createStatement();*/
	       PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
	       st.setString(1, "%"+query+"%");
	       st.setInt(2, offset);
	       // ��� ��������
	       ResultSet rs = st.executeQuery();
	       
	      list = new ArrayList<>();
	       while (rs.next()) {
	    	   NoticeView n = new NoticeView();  

	          n.setId(rs.getString("ID"));
	          n.setTitle(rs.getString("TITLE"));
	          n.setContent(rs.getString("CONTENT"));
	          n.setWriterId(rs.getString("writerId"));
	          n.setWriterName(rs.getString("writerName"));
	          n.setHit(rs.getInt("hit"));
	          n.setCountCmt(rs.getInt("countCmt"));

	          list.add(n);
	       }
	       rs.close();
	       st.close();
	       conn.close();
	    
	    } catch (ClassNotFoundException e) {
	       e.printStackTrace();
	    } catch (SQLException e) {
	       e.printStackTrace();
	    }
	    return list;
	}

	public int getCount() {
		
		int count = 0;
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
	  
	    String sqlcount = "SELECT COUNT(id) count FROM Notice ";

	    // Jdbc ����̹� �ε�
	    try {
	       Class.forName("com.mysql.jdbc.Driver");

	       // ���� / ����
	       Connection conn = DriverManager.getConnection(url, "sist", "cclass"); // dclass

	       // ����
	     
	       
	      /* ��ü ������ �˱� ���� sql�� �ѹ� �� ��*/
	      Statement stCount =conn.createStatement();
	       ResultSet rsCount = stCount.executeQuery(sqlcount);
	      rsCount.next();
	       
	       count = rsCount.getInt("count");
	   
	       rsCount.close();
	       stCount.close();
	       conn.close();
		    
		    } catch (ClassNotFoundException e) {
		       e.printStackTrace();
		    } catch (SQLException e) {
		       e.printStackTrace();
		    }
		return count;
	}

	@Override
	public NoticeView get(String id) {

		
		NoticeView n = null;
	      String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
	      String sql = "SELECT * FROM NoticeView WHERE id like ?";

	      try {
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection conn = DriverManager.getConnection(url, "sist", "cclass");
	         // Statement st = conn.createStatement();
	         PreparedStatement st = conn.prepareStatement(sql);
	         st.setString(1, id);
	         ResultSet rs = st.executeQuery();
	         
	         if (rs.next()) {
	          n = new NoticeView();  
	            
	            n.setId(rs.getString("ID"));
	            n.setTitle(rs.getString("TITLE"));
	            n.setWriterId(rs.getString("WRITERID"));
	            n.setContent(rs.getString("CONTENT"));
	            n.setHit(rs.getInt("HIT"));
	            n.setRegDate(rs.getDate("REGDATE"));
	         }

	         rs.close();
	         st.close();
	         conn.close();

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
		return n;
	}

	@Override
	public int update(String id, String title, String content) {
		
		int result = 0;
		  String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
	      String sql = "UPDATE Notice SET title=?, content=? where id=?";
	      		
	      try {
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection conn = DriverManager.getConnection(url, "sist", "cclass");
	         // Statement st = conn.createStatement();
	         PreparedStatement st = conn.prepareStatement(sql);
	         st.setString(1, title);
	         st.setString(2, content);
	         st.setString(3, id);
	         
	        result = st.executeUpdate();
	    /*    ������Ʈ �� row������ �˷���. st.executeUpdate*/

	         st.close();
	         conn.close();

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
		return result;
	}

}
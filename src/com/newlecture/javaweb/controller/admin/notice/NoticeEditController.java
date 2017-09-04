package com.newlecture.javaweb.controller.admin.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Notice;

@WebServlet("/admin/notice/edit")
public class NoticeEditController extends HttpServlet {
  /*
	���� ������ post�� �����. �׷� �Ʒ��� doPost�Լ��� ����ǰ���. �Է»��ڼ� ������ ���޵������ϴµ�
	Ű�� �Բ� �����ؾߵ�. ���⼭ Ű�� name. input�ڿ� name�� �ֱ�.
	<input name = "title" value = "">�̷�������.
	�ٵ� �ΰ�(input�� �� )�� ���� ������ ���� ����. --> �����̶� ����
	�������� �Խñ����� �˷������. �׷��� �غκп�
	<input type="hidden" name="id" value="${notice.id}"/>
	id�� ���ܼ� ������.*/
	
	protected void doPost(
         HttpServletRequest request, 
         HttpServletResponse response) throws ServletException, IOException {
	   
		request.setCharacterEncoding("UTF-8");
		
		String id =request.getParameter("id");
		String title=request.getParameter("title");
		String content = request.getParameter("content");
	
	      NoticeDao noticeDao = new JdbcNoticeDao() ;
	      int result =noticeDao.update(id,title,content);
	    /* ������� noticeDao.update(notice);*/
	      response.sendRedirect("detail?id="+id);
	  /*    sendRedirect�׳� ���������� ���� ��. ����� ������ߵǴϱ�*/
		
	 /*  super.doPost(request, response);*/
	   
   }
	/*   get��û����, post��û���� ������ �ʿ䰡 ����. 
	   ���� url�� �θ��� ������. */
	/*   
	   doGet : get�� ó����.
	   doPost : post�� ó����.
	   == protected void doGet{
		   �̷������� �������̵� �ϸ� �� !
	   }
	   */
   
      protected void doGet(  HttpServletRequest request, 
         HttpServletResponse response) throws ServletException, IOException{
      String _id = request.getParameter("id");
      String id = "";
      
      if(_id != null && !_id.equals(""))
         id = _id;
      
      Notice n = null;

      String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
      String sql = "SELECT * FROM Notice WHERE id like ?";

      try {
         Class.forName("com.mysql.jdbc.Driver");
         Connection conn = DriverManager.getConnection(url, "sist", "cclass");
         // Statement st = conn.createStatement();
         PreparedStatement st = conn.prepareStatement(sql);
         st.setString(1, id);
         ResultSet rs = st.executeQuery();
         
         if (rs.next()) {
            n = new Notice();
            
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
      
      request.setAttribute("detail", n);
     
      request.getRequestDispatcher("/WEB-INF/views/admin/notice/edit.jsp").forward(request, response);
   }
}
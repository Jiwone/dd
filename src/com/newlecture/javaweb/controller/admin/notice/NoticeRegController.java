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

import com.newlecture.javaweb.entity.Notice;

@WebServlet("/admin/notice/reg")
public class NoticeRegController extends HttpServlet {
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
		
		  String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
	      String sql = "insert into Notice (id, title,content,writerId) values ((select IFNULL(MAX(CAST(id as unsigned)),0)+1 from Notice as b) ,?,?,?)";
	      try {
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection conn = DriverManager.getConnection(url, "sist", "cclass");
	         // Statement st = conn.createStatement();
	         PreparedStatement st = conn.prepareStatement(sql);
	         st.setString(1, title);
	         st.setString(2, content);
	         st.setString(3, "newlec");

	         int result = st.executeUpdate();
	    /*    ������Ʈ �� row������ �˷���. st.executeUpdate*/

	         st.close();
	         conn.close();

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
	      response.sendRedirect("list");
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

     
      request.getRequestDispatcher("/WEB-INF/views/admin/notice/reg.jsp").forward(request, response);
   }
}
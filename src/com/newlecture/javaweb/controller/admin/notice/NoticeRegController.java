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
	제출 누르면 post가 실행됨. 그럼 아래의 doPost함수가 실행되겠지. 입력상자속 내용이 전달도ㅐ야하는데
	키와 함께 전달해야됨. 여기서 키는 name. input뒤에 name을 주기.
	<input name = "title" value = "">이런식으로.
	근데 두개(input에 들어간 )만 따로 전달할 수가 없음. --> 제목이랑 내용
	누구꺼의 게시글인지 알려줘야함. 그래서 밑부분에
	<input type="hidden" name="id" value="${notice.id}"/>
	id를 숨겨서 보내줌.*/
	
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
	    /*    업데이트 된 row갯수를 알려줌. st.executeUpdate*/

	         st.close();
	         conn.close();

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
	      response.sendRedirect("list");
	  /*    sendRedirect그냥 딴페이지로 가는 거. 목록을 보여줘야되니까*/
		
	 /*  super.doPost(request, response);*/
	   
   }
	/*   get요청인지, post요청인지 구분할 필요가 있음. 
	   같은 url을 부르기 때문에. */
	/*   
	   doGet : get만 처리해.
	   doPost : post만 처리해.
	   == protected void doGet{
		   이런식으로 오버라이드 하면 됨 !
	   }
	   */
   
      protected void doGet(  HttpServletRequest request, 
         HttpServletResponse response) throws ServletException, IOException{

     
      request.getRequestDispatcher("/WEB-INF/views/admin/notice/reg.jsp").forward(request, response);
   }
}
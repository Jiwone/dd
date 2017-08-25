package com.newlecture.javaweb.controller.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.javaweb.entity.Notice;

@WebServlet("/customer/notice-list")
public class NoticeListController extends HttpServlet {
@Override

protected void service(
		HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	
	
	String _title = request.getParameter("title");
/*
	application.setAttribute("x", "어플");
	session.setAttribute("x", "세션");
	request.setAttribute("x", "리");
	pageContext.setAttribute("x", "페이지");*/
	
	String title=""; //기본값
	if(_title != null  && !_title.equals("") )
		title = _title;
	
	/* System.out.println(title);*/
	 
	  List<Notice> list = null;
	  
    String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
    String sql = "SELECT * FROM Notice WHERE title like ?";

    // Jdbc 드라이버 로드
    try {
       Class.forName("com.mysql.jdbc.Driver");

       // 연결 / 인증
       Connection conn = DriverManager.getConnection(url, "sist", "cclass"); // dclass

       // 실행
      /* Statement st = conn.createStatement();*/
       PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
       st.setString(1, "%"+title+"%");
      		 // 결과 가져오기
       ResultSet rs = st.executeQuery();

      list = new ArrayList<>();

       while (rs.next()) {
          Notice n = new Notice();  

          n.setId(rs.getString("ID"));
          n.setTitle(rs.getString("TITLE"));
          n.setContent(rs.getString("CONTENT"));

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

	request.setAttribute("list", list);
	//response.sendRedirect("notice.jsp"); //아예 새로 출발
	request.getRequestDispatcher("/WEB-INF/views/customer/notice/list.jsp").forward(request, response); //이어서 출발
	//redirect
	//forward
	//둘다 페이지를 다른데로 이동할 때 사용.
	
}
	

}

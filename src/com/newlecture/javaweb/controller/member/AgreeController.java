package com.newlecture.javaweb.controller.member;

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

@WebServlet("/member/agree")
public class AgreeController extends HttpServlet {
   protected void doGet(
         HttpServletRequest request, 
         HttpServletResponse response) throws ServletException, IOException {

      request.getRequestDispatcher("/WEB-INF/views/member/agree.jsp").forward(request, response);
   }
   
   
   
   
   protected void doPost(
	         HttpServletRequest request, 
	         HttpServletResponse response) throws ServletException, IOException {

	   String _agree=request.getParameter("agree");
	   System.out.println(_agree);
	 /*  response.sendRedirect("agree"); *//*���ӻ縦 ������ �ʾҴٸ�=���Ǹ� ��������*/
	 /*  ���Ǹ� �ߴ� ���ߴٸ� �����ָ� �� . post . ���� �����ҷ�. ���ҷ�.
	*/
	   String agree = "no";
	      
	   if(_agree != null && !_agree.equals(""))
	    	  agree = _agree;

	   if(!agree.equals("ok")) 
		   response.sendRedirect("agree?error=1"); /*üũ�� ��������*/
	   else
		   response.sendRedirect("join"); /*���Ǹ� �ߴٸ�*/
	   }
   
}
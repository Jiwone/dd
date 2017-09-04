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
	 /*  response.sendRedirect("agree"); *//*으ㅣ사를 밝히지 않았다면=동의를 안했으면*/
	 /*  동의를 했다 안했다만 보여주면 됨 . post . 나는 동의할래. 안할래.
	*/
	   String agree = "no";
	      
	   if(_agree != null && !_agree.equals(""))
	    	  agree = _agree;

	   if(!agree.equals("ok")) 
		   response.sendRedirect("agree?error=1"); /*체크를 안했으면*/
	   else
		   response.sendRedirect("join"); /*동의를 했다면*/
	   }
   
}
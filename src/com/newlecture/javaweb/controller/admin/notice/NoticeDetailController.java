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

@WebServlet("/admin/notice/detail")
public class NoticeDetailController extends HttpServlet {
   protected void service(
         HttpServletRequest request, 
         HttpServletResponse response) throws ServletException, IOException {
      
      String _id = request.getParameter("id");
      String id = "";
      
      if(_id != null && !_id.equals(""))
         id = _id;
      //model얻어오는 작업
      NoticeDao dao = new JdbcNoticeDao();
      Notice n =dao.get(id);

      request.setAttribute("detail", n);
      
      request.getRequestDispatcher("/WEB-INF/views/admin/notice/detail.jsp").forward(request, response);
   }
}
package com.newlecture.javaweb.controller.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Notice;

@WebServlet("/customer/notice-list")
public class NoticeListController extends HttpServlet {
@Override

protected void service(
		HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	
	
	String _title = request.getParameter("title");
	String _page = request.getParameter("p");

	int page=1; //������ �ȵ��� �� �⺻��
	
	if(_page!=null && !_page.equals(""))
		page = Integer.parseInt(_page);

	String title=""; //�⺻��
	if( _title != null  && ! _title.equals("") )
		title =  _title;
	
	/* System.out.println(title);*/
	 
	/*int count = 0;
	List<Notice> list = null;*/
	  
	 /*���ڿ��� �ν��Ͻ�ȭ���ִ� Ŭ���� ���̺귯�� : class.forName("com.mysql.jdbc.Driver")
	  * 
	   NoticeDao noticeDao = "noticedao";
	   noticedao com.newlecture.webprj.dao.jdbc.JdbcNoticeDao*/
	
   NoticeDao noticeDao = new JdbcNoticeDao();

	request.setAttribute("list", noticeDao.getList(page, title));
	request.setAttribute("count",noticeDao.getCount());
	//response.sendRedirect("notice.jsp"); //�ƿ� ���� ���
	request.getRequestDispatcher("/WEB-INF/views/customer/notice/list.jsp").forward(request, response); //�̾ ���
	//redirect
	//forward
	//�Ѵ� �������� �ٸ����� �̵��� �� ���.
	
}
	

}

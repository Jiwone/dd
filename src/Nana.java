import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.mysql.jdbc.PreparedStatement;
import com.newlecture.javaweb.entity.Notice;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/nana")
public class Nana extends HttpServlet {
   public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
     
	   response.setCharacterEncoding("UTF-8");
	   //���� ������ UTF-8�� ���� !!
	  response.setContentType("text/html; character=UTF-8"); //ũ�ҿ��� �۾� ������ �����Ҷ�
	  //text/html : mime type (html�� �о� ), character=UTF-8 : UTF-8(�����ڵ�)�� �о�
	/*  OutputStream os = response.getOutputStream();
      PrintStream out = new PrintStream(os);
*/ 
	  PrintWriter out=response.getWriter();
	//���� �Ȱ���. ���� writer�� ���� ����
	  String title = request.getParameter("title");
	 /* System.out.println(title);*/
	 
	  
	  
	  
	  
	  
	  List<Notice> list = null;
	  
      String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
      String sql = "SELECT * FROM Notice WHERE title like ?";

      // Jdbc ����̹� �ε�
      try {
         Class.forName("com.mysql.jdbc.Driver");

         // ���� / ����
         Connection conn = DriverManager.getConnection(url, "sist", "cclass"); // dclass

         // ����
        /* Statement st = conn.createStatement();*/
         PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
         st.setString(1, "%"+title+"%");
        		 // ��� ��������
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
   /*   -------------------view-----------------------------*/
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset=\"EUC-KR\">");
      out.println("<title>Insert title here</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<form action=\"notice\" method=\"get\">");
   //   <!-- �˻��ؼ� �޶�� �ϴ� �Ŵϱ� :get = ���������� �� �˻��� �������� �����ϴ� �� :post = ȸ�������� post  -->
      out.println("<label>�˻���</label>");
      out.println("<input type=\"text\" name=\"title\" />");
      out.println("<input type=\"submit\"/>");
      out.println("	</form>");
      	
        for(Notice n : list)
       	 out.println(n.getTitle()+ "<br />");
       /* out.println(list.get(0).getTitle());*/
        out.println("</body>");
        out.println("</html>");
      
      
    
     	
   }
}
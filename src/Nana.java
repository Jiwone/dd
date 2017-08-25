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
	   //내가 보낼때 UTF-8로 보내 !!
	  response.setContentType("text/html; character=UTF-8"); //크롬에서 글씨 옆으로 나열할때
	  //text/html : mime type (html로 읽어 ), character=UTF-8 : UTF-8(유니코드)로 읽어
	/*  OutputStream os = response.getOutputStream();
      PrintStream out = new PrintStream(os);
*/ 
	  PrintWriter out=response.getWriter();
	//위랑 똑같음. 단지 writer는 문자 단위
	  String title = request.getParameter("title");
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
   /*   -------------------view-----------------------------*/
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset=\"EUC-KR\">");
      out.println("<title>Insert title here</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<form action=\"notice\" method=\"get\">");
   //   <!-- 검색해서 달라고 하는 거니까 :get = 공지사항을 줘 검색된 공지사항 제출하는 건 :post = 회원가입은 post  -->
      out.println("<label>검색어</label>");
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